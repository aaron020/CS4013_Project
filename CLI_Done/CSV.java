package com.project;

import java.io.*;

/**
 * Reads and adds data to CSV file
 * Written by Aaron Meade(19271034)
 */
public class CSV {
    private String file = "CSV/CSV.txt";

    /**
     * Adds property data to CSV file
     * @param p
     */
    public void add(Property p){
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(p.getOwners() + "," + p.getAddress() + "," +
                    p.getEircode() + "," + p.getEstMarketVal() + "," +
                    p.getCategory() + "," + p.isPrivateRes());
            pw.flush();
            pw.close();

        }catch (Exception E){
            System.out.println("Error writing to csv");
        }
    }

    /**
     * Reads all data in the CSV files and returns it as an object of type properties
     * @return
     */
    public Properties read(){
        String line = "";
        Properties prop = new Properties();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                String[] names = values[0].split(" ");
                Owners o = new Owners(names[0],names[1]);
                double estMarketVal = Double.parseDouble(values[3]);
                boolean privateRes = Boolean.parseBoolean(values[5]);
                Property p = new Property(o,values[1],values[2],estMarketVal,values[4],privateRes);
                prop.addProperty(p);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }
}
