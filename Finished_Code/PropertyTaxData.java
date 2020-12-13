package com.project;

/**
 * PropertyTaxData Object
 * @author Aaron Meade(19271034)
 */
public class PropertyTaxData {
    private String eircode;
    private int year;
    private double propertyTax;
    private boolean paid;

    /**
     * Constructs a PropertyTaxObject
     * @param eircode Eircode of the property
     * @param year Current year
     * @param propertyTax Property Tax on the property
     * @param paid If property tax has been paid(true) or not (false)
     */
    public PropertyTaxData(String eircode, int year, double propertyTax, boolean paid) {
        this.eircode = eircode;
        this.year = year;
        this.propertyTax = propertyTax;
        this.paid = paid;
    }

    /**
     * Constructs a PropertyTaxObject - allows you to use a String value for paid rather than boolean
     * @param eircode Eircode of the property
     * @param year Current year
     * @param propertyTax Property Tax on the property
     * @param paid "Paid" or "Not paid"
     */
    public PropertyTaxData(String eircode,int year,double propertyTax, String paid){
        if(paid.compareToIgnoreCase("Not Paid") == 0){
            this.eircode = eircode;
            this.year = year;
            this.propertyTax = propertyTax;
            this.paid = false;
        }else{
            this.eircode = eircode;
            this.year = year;
            this.propertyTax = propertyTax;
            this.paid = true;
        }
    }

    /**
     * Compares two eircodes to see if their are equal
     * @param eircode Eircode
     * @return true/false
     */
    public boolean equals(String eircode){
        return eircode.compareToIgnoreCase(this.eircode) == 0;
    }

    /**
     * Compares two routing keys to see if their equal
     * @param route Routing Key
     * @return true/false
     */
    public boolean equalRoute(String route){
        return route.compareToIgnoreCase(getRouting()) == 0;
    }

    /**
     * Tells you if PropertyTaxData is paid or not for a certain year
     * @param year Year
     * @return true/false
     */
    public boolean overdue(int year){
        if(year == this.year){
            return !paid;
        }
        return false;
    }

    /**
     * Tells you if PropertyTaxData is paid or not for a certain routing key
     * @param routing routing key
     * @return true/false
     */
    public boolean overdue(String routing){
        if(equalRoute(routing)){
            return !paid;
        }
        return false;
    }

    /**
     * Gets the routing key of an eircode
     * @return String
     */
    public String getRouting(){
        return eircode.substring(0,3);
    }

    /**
     * Returns a more detailed version of toString
     * @return String
     */
    public String toStringMore(){
        return eircode + " " + propertyTax + " was not paid.";
    }

    /**
     * Gets the property tax
     * @return property tax
     */
    public double getPropertyTax() {
        return propertyTax;
    }

    /**
     * Gets the year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Converts object to string
     * @return String
     */
    @Override
    public String toString() {
        if(paid){
            return year + ",  " + propertyTax + " was paid";
        }else{
            return year + ",  " + propertyTax + " was not paid";
        }

    }

}
