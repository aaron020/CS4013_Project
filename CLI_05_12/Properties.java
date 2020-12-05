package com.project;
import java.util.ArrayList;

/**
 * An arraylist of property objects
 * Written by Aaron Meade(19271034)
 */
public class Properties {
    private ArrayList<Property> properties;
    public Properties(){
        properties = new ArrayList<>();
    }

    /**
     * Adds property to the an arraylist
     * @param p
     */
    public void addProperty(Property p){
        properties.add(p);
    }

    /**
     * Adds properties object, used from loading from the CSV file when the program starts
     * @param other
     */
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

    public ArrayList<String> getPropertiesByName(){
        ArrayList<String> names = new ArrayList<>();
        for(Property p : properties){
            names.add(p.getOwners().toString());
        }
        ArrayList<String> namesNoRepeat = new ArrayList<>();
        for(String s:names){
            if(!namesNoRepeat.contains(s)){
                namesNoRepeat.add(s);
            }
        }
        return namesNoRepeat;
    }

    /**
     * Returns the properties arraylist
     * @return
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }
}
