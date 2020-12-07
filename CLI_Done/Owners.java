package com.project;

/**
 * Owners Object
 * Written by Aaron Meade(19271034)
 */
public class Owners {
    private String firstname;
    private String lastname;

    /**
     * Initializes the Owner class
     * @param firstname
     * @param lastname
     */
    public Owners(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Returns firstname
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns lastname
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Checks to see if two Owner objects are equal
     * @param other
     * @return
     */
    public boolean equal(Owners other){
        if(other.getFirstname().compareToIgnoreCase(firstname) == 0 &&
            other.getLastname().compareToIgnoreCase(lastname) == 0){
            return true;
        }else {
            return false;
        }

    }
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
