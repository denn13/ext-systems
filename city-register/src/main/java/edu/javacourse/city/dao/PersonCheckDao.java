package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckExeption;

import java.sql.*;

public class PersonCheckDao {

    private static final String SQL_REQUEST =
            "SELECT ap.temporal  FROM cr_address_person ap " +
                    "INNER JOIN cr_address a on a.address_id = ap.address_id " +
                    "INNER JOIN cr_person p on p.person_id = ap.person_id " +
                    "where " +
                    "CURRENT_DATE >= ap.start_date " +
                    "and (CURRENT_DATE <= ap.end_date or ap.end_date is null) " +
                    "and upper(p.sur_name) = upper(?) " +
                    "and upper(p.given_name) = upper(?) " +
                    "and upper(p.patronymic) = upper(?) " +
                    "and p.date_of_birth = ? " +
                    "and a.street_code = ? " +
                    "and upper(a.building) = upper(?) " +
                    "and upper(a.extension) = upper(?) " +
                    "and upper(a.apartment) = upper(?)";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckExeption {
        PersonResponse response = new PersonResponse();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)) {

            stmt.setString(1, request.getSurName());
            stmt.setString(2, request.getGivenName());
            stmt.setString(3, request.getPatronymic());
            stmt.setDate(4, java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(5, request.getStreetCode());
            stmt.setString(6, request.getBuilding());
            stmt.setString(7, request.getExtension());
            stmt.setString(8, request.getApartment());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }

        } catch (SQLException ex) {
            throw new PersonCheckExeption(ex);
        }

        return response;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost/city_register",
                "postgres","nimda");
    }

}
