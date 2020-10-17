package model.utility;

import java.util.ArrayList;

/**
 * @author manmohansingh
 * 17/10/2020 14:30
 *
 * [1] (Thompson, 2020)
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
