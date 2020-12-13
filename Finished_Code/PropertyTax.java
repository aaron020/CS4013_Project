package com.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Reads and adds data to CSV file
 * Adds and reads property tax data from the CSV
 * Does calculations on the property tax data
 * @author Aaron Meade(19271034)
 */
public class PropertyTax {
    private String file = "CSV/PropertyTaxData.txt";
    private ArrayList<PropertyTaxData> data = new ArrayList<>();

    /**
     * Initialises object by reading all data from the CSV to an arraylist
     */
    public PropertyTax(){
        readCSV();
    }

    /**
     * When a new property is added to the system, its property tax
     * for the current year is added to the CSV and also whether they
     * have paid property tax or not
     * @param p Property Object
     */
    public void addPropertyTax(Property p){
        //Randomly picks if the property tax has been paid or not
        int pick = (int)(Math.random()*2+1);
        if(pick == 1){
            writeToCSV(p.getEircode(),Calendar.getInstance().get(Calendar.YEAR),
                    p.getPropertyTax(),"Paid");
            data.add(new PropertyTaxData(p.getEircode(),Calendar.getInstance().get(Calendar.YEAR),
                    p.getPropertyTax(),"Paid"));
        }else{
            writeToCSV(p.getEircode(),Calendar.getInstance().get(Calendar.YEAR),
                    p.getPropertyTax(),"Not Paid");
            data.add(new PropertyTaxData(p.getEircode(),Calendar.getInstance().get(Calendar.YEAR),
                    p.getPropertyTax(),"Not Paid"));
        }
    }

    /**
     * Used to write to the CSV
     * @param eircode Eircode of the property
     * @param year The current year
     * @param propTax Property tax on the property
     * @param s String for "Paid" or "Not Paid"
     */
    private void writeToCSV(String eircode,int year,double propTax,String s){
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(eircode+","+year + "," + propTax + "," + s);
            pw.flush();
            pw.close();

        }catch (Exception E){
            System.out.println("Error writing to csv");
        }
    }

    /**
     * reads all data in the CSV and places it in an arraylist of type PropertyTaxData
     */
    private void readCSV(){
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                int year = Integer.parseInt(values[1]);
                double propertyTax = Double.parseDouble(values[2]);
                boolean paid;
                paid = values[3].compareToIgnoreCase("Paid") == 0;
                PropertyTaxData t = new PropertyTaxData(values[0],year,propertyTax,paid);
                data.add(t);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Find the PropertyTaxData for a certain property
     * @param p Property Object
     * @return Arraylist of type PropertyTaxData
     */
    public ArrayList<PropertyTaxData> findByProperty(Property p){
        String eircode = p.getEircode();
        ArrayList<PropertyTaxData> eir = new ArrayList<>();
        for(PropertyTaxData t:data){
            if(t.equals(eircode)){
                eir.add(t);
            }
        }
        return eir;
    }

    /**
     * Total tax paid based off the routing key of the eircode
     * @param routing Routing key
     * @return total tax paid
     */
    public double totalTax(String routing){
        return totalAverage(routing,true);
    }

    /**
     * Total tax paid based off the routing key or
     * Average tax paid based off the routing key
     * @param routing Routing key
     * @param total If true then it returns the total and if false then it returns the average
     * @return total/Average
     */
    private double totalAverage(String routing,boolean total){
        ArrayList<PropertyTaxData> totalAvg = findByRouting(routing);
        double totalTax = 0;
        int count = 0;
        for(PropertyTaxData r : totalAvg){
            if(!r.overdue(r.getYear())){
                count++;
                totalTax = totalTax + r.getPropertyTax();
            }
        }
        if(!total && count!=0){
            return totalTax/count;
        }else{
            return totalTax;
        }
    }

    /**
     * Average tax paid based off of the routing key
     * @param routing Routing key
     * @return average
     */
    public double averageTax(String routing){
        return totalAverage(routing,false);
    }

    /**
     * Percentage of tax paid based off the routing key
     * @param routing Routing key
     * @return total
     */
    public double taxPercent(String routing){
        ArrayList<PropertyTaxData> t = findByRouting(routing);
        int amount = t.size();
        int count = 0;
        for(PropertyTaxData r : t){
            if(r.overdue(r.getYear())){
                count++;
            }
        }
        if(amount>0){
            return 100*((double)count/amount);
        }else{
            return 0;
        }

    }

    /**
     * Returns an arraylist with the unique routing keys
     * @return Arraylist of string
     */
    public ArrayList<String> getRouting(){
        ArrayList<String> routingKeys = new ArrayList<>();
        for(PropertyTaxData p : data){
            if(!routingKeys.contains(p.getRouting())){
                routingKeys.add(p.getRouting());
            }
        }
        return routingKeys;
    }

    /**
     * Property Tax data based off the routing key
     * @param routing Routing key
     * @return Arraylist of type PropertyTaxData
     */
    private ArrayList<PropertyTaxData> findByRouting(String routing){
        ArrayList<PropertyTaxData> route = new ArrayList<>();
        for(PropertyTaxData r:data){
            if(r.equalRoute(routing)){
                route.add(r);
            }
        }
        return route;
    }

    /**
     * Overdue property tax based off of the year
     * @param year year
     * @return Arraylist of type PropertyTaxData
     */
    public ArrayList<PropertyTaxData> overdue(int year){
        ArrayList<PropertyTaxData> overdue = new ArrayList<>();
        for(PropertyTaxData r: data){
            if(r.overdue(year)){
                overdue.add(r);
            }
        }
        return overdue;
    }

    /**
     * Overdue property tax based off of the year and sorted by routing key
     * @param year year
     * @param routing Routing key
     * @return Arraylist of type PropertyTaxData
     */
    public ArrayList<PropertyTaxData> overdue(int year,String routing){
        ArrayList<PropertyTaxData> overdue = overdue(year);
        ArrayList<PropertyTaxData> overdueRouting = new ArrayList<>();
        for(PropertyTaxData r : overdue){
            if(r.overdue(routing)){
                overdueRouting.add(r);
            }
        }
        return overdueRouting;
    }

}





