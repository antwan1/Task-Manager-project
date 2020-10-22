package model.utility;

import java.util.ArrayList;

/**
 * @author manmohansingh
 * 17/10/2020 14:30
 *
 * (Thompson, 2020)
 *
 * ********************************************************************
 * Title: TemperatureRecording
 * Author: Thompson, E (@thompel1)
 * Date: 2020
 * Code Version: N/A
 * Availability: https://gitlab.com/FoOOSD/temperaturerecording.git
 * ********************************************************************
 * [Source Code] https://gitlab.com/FoOOSD/temperaturerecording.git
 *
 */

public class Errors extends ArrayList<String> {
    public Errors(String error) {
        super();
        if (error != null && !error.equals("")) {
            this.add(error);
        }
    }

    public Errors() { super(); }

    @Override
    public boolean add(String error) {
        if (error != null && !error.equals("")) {
            return super.add(error);
        }
        return false;
    }

    public static Errors create(String error) {
        return new Errors(error);
    }
}
