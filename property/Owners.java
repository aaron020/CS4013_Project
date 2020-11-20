package com.company;

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

    @Override
    public String toString() {
        return firtsname + " " + lastname;
    }
}
