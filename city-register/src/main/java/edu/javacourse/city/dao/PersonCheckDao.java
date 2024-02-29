package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckExeption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {

    private static final String SQL_REQUEST =
            "SELECT ap.temporal  FROM cr_address_person ap " +
            "INNER JOIN cr_address a on a.address_id = ap.address_id " +
            "INNER JOIN cr_person p on p.person_id = ap.person_id " +
            "where " +
            "upper(p.sur_name) = upper(?) " +
            "and upper(p.given_name) = upper(?) " +
            "and upper(p.patronymic) = upper(?) " +
            "and p.date_of_birth = ? " +
            "and a.street_code = ?"+
            "and upper(a.building) = upper(?) " +
            "and upper(a.extension) = upper(?) " +
            "and upper(a.apartment) = upper(?)";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckExeption {
        PersonResponse response = new PersonResponse();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)) {
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

    private Connection getConnection() {
        return null;
    }

}
