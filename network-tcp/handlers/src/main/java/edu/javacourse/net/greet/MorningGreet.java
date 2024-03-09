package edu.javacourse.net.greet;

import edu.javacourse.net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good morning, " + userName;
    }
}
