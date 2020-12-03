package com.project;

import java.util.ArrayList;

public class Properties {
    private ArrayList<Property> properties;
    public Properties(){
        properties = new ArrayList<>();
    }

    /**
     * Adds property to the an array ,to the CSV file and also property tax data to PropertyTaxData file
     * @param p
     */
    public void addProperty(Property p){
        //edit : this causes an endless loop of writing to the CSV files
//         CSV write = new CSV();
//         write.add(p);
//         PropertyTax tax = new PropertyTax();
//         tax.addPropertyTax(p);
        properties.add(p);
    }

    public void addProperties(Properties other){
        ArrayList<Property> p = other.getProperties();
        for(Property n : p){
            addProperty(n);
        }
    }

    /**
     * Searches all properties for a given name,
     * returns an Arraylist of properties owned by the specified person
     * @param o
     * @return
     */
    public ArrayList<Property> searchByName(Owners o){
        ArrayList<Property> byName = new ArrayList<>();
        for(Property p : properties){
            if(p.getOwners().equal(o)){
                byName.add(p);
            }
        }
        return byName;
    }

    /**
     * Returns the properties arraylist
     * @return
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }
}
