package com.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args){
//        Properties p = new Properties();
//        CSV csv = new CSV();
//        p.addProperties(csv.read());
//        ArrayList<Property> props = new ArrayList<>();
//        props = p.searchByName(new Owners("john","murphy"));
//        for (Property n : props){
//            System.out.println(n);
//        }
        Menu n = new Menu();
        n.start();

//        Properties props = new Properties();
//        CSV csv = new CSV();
//        props.addProperties(csv.read());
//        CreateFiles(props);


    }

    //--NEEDS TO BE REMOVED--
    public static void CreateFiles(Properties array) {
        String file = "CSV/PropertyTaxData.txt";
        ArrayList<Property> props = array.getProperties();
        for (Property property : props) {
            double propTax = property.getPropertyTax();
            String eircode = property.getEircode();
            int year = (int) (Math.random() * (2020 - 2000) + 2000);
            int pick = (int) (Math.random() * 2 + 1);
            double propHolder = propTax;
            for (; year <= 2020; year++) {
                if (pick == 1) {
                    try {
                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println(eircode + "," + year + "," + propHolder + "," + "Paid");
                        pw.flush();
                        pw.close();

                    } catch (Exception E) {
                        System.out.println("Error writing to csv");
                    }
                    propHolder = propTax;
                } else {
                    try {
                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println(eircode + "," + year + "," + propHolder + "," + "Not Paid");
                        pw.flush();
                        pw.close();

                    } catch (Exception E) {
                        System.out.println("Error writing to csv");
                    }
                    propHolder = 2 * propHolder + (propHolder * 0.07);
                }
                pick = (int) (Math.random() * 2);
            }
        }
    }
}
