package com.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

public class PropertyTax {
    private String file = "CSV/PropertyTaxData";

    public void addPropertyTax(Property p){
        //Randomly picks if the property tax has been paid or not
        int pick = (int)(Math.random()*2+1);
        if(pick == 1){
            writeToCSV(p.getEircode(),Calendar.getInstance().get(Calendar.YEAR),
                    p.getPropertyTax(),"Paid");
        }else{
            writeToCSV(p.getEircode(),Calendar.getInstance().get(Calendar.YEAR),
                    p.getPropertyTax(),"Not Paid");
        }
    }

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

}
