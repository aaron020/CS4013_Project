package com.company;

import java.util.ArrayList;

public class Properties {
    ArrayList<Property> properties;

    public Properties(){
        properties = new ArrayList<>();
    }

    public void addProperty(Property p){
        properties.add(p);
    }

}
