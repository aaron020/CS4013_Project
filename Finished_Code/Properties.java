package com.project;
import java.util.ArrayList;

/**
 * An arraylist of property objects
 * @author Aaron Meade(19271034)
 */
public class Properties {
    private ArrayList<Property> properties;
    public Properties(){
        properties = new ArrayList<>();
    }

    /**
     * Adds property to the an arraylist
     * @param p Property
     */
    public void addProperty(Property p){
        properties.add(p);
    }

    /**
     * Adds properties object, used for loading from the CSV file when the program starts
     * @param other Properties
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
     * @param o Owner
     * @return Arraylist of property
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
     * Arraylist of string with all the unique names
     * @return Arraylist of String
     */
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
     * Changes the property tax calculator in each of the properties
     * @param newRates double array of new rates for the calculator
     * @param charge int array of new charges for the calculator
     */
    public void changeCalc(double[] newRates,int[] charge){
        for(Property p : properties){
            p.changeCalc(newRates,charge);
        }
    }

    /**
     * Returns the properties arraylist
     * @return Arraylist og Property
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }
}
