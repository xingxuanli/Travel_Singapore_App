package com.example.user.andoridproject;

/**
 * Created by study on 4/12/17.
 */

public class RouteType {
    public String place;
    public String mode;

    public RouteType(String place, String mode) {
        this.place =place;
        this.mode =mode;
    }

    public String getString(String place, String mode) {
        String opt ="";
        if (place.equals("Marina Bay Sands")) {
            place ="Marina Bay Sands";
        }
        if (mode.equals("walk")) {
            opt +="Walk to " +place;
        } else if (mode.equals("public")) {
            opt +="Take public transport to " +place;
        } else {
            opt +="Take a taxi to " +place;
        }
        return opt;
    }
}
