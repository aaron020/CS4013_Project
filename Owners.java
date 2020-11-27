package com.project;

public class Owners {
    private String firtsname;
    private String lastname;

    public Owners(String firtsname, String lastname) {
        this.firtsname = firtsname;
        this.lastname = lastname;
    }

    public String getFirtsname() {
        return firtsname;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean equal(Owners other){
        if(other.getFirtsname().compareToIgnoreCase(firtsname) == 0 &&
            other.getLastname().compareToIgnoreCase(lastname) == 0){
            return true;
        }else {
            return false;
        }

    }
    @Override
    public String toString() {
        return firtsname + " " + lastname;
    }
}
