package com.project;

/**
 * Owners Object
 * @author Aaron Meade(19271034)
 */
public class Owners {
    private String firstname;
    private String lastname;

    /**
     * Initializes the Owner class
     * @param firstname Owners firstname
     * @param lastname Owners Lastname
     */
    public Owners(String firstname, String lastname) {
        firstname = firstname.replaceAll(" ","");
        firstname = firstname.replaceAll(",","");
        lastname = lastname.replaceAll(" ","");
        lastname = lastname.replaceAll(",","");

        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Returns firstname
     * @return String
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns lastname
     * @return String
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Checks to see if two Owner objects are equal
     * @param other Owners Object
     * @return true/false
     */
    public boolean equal(Owners other){
        if(other.getFirstname().compareToIgnoreCase(firstname) == 0 &&
            other.getLastname().compareToIgnoreCase(lastname) == 0){
            return true;
        }else {
            return false;
        }

    }

    /**
     * Converts Owners object to string
     * @return String
     */
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
