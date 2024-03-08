package edu.javacourse.net.greet;

import edu.javacourse.net.Greetable;

public class EveningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
            return "Goog evening, " + userName;
    }
}
