package com.project;

import java.util.ArrayList;

public class Properties {
    private ArrayList<Property> properties;
    public Properties(){
        properties = new ArrayList<>();
    }

    public void addProperty(Property p){
        properties.add(p);
    }

    public void addProperties(Properties other){
        ArrayList<Property> p = other.getProperties();
        for(Property n : p){
            addProperty(n);
        }
    }

    public ArrayList<Property> searchByName(Owners o){
        ArrayList<Property> byName = new ArrayList<>();
        for(Property p : properties){
            if(p.getOwners().equal(o)){
                byName.add(p);
            }
        }
        return byName;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }
}
