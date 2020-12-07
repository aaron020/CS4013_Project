package com.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class PropertyTax {
    private String file = "CSV/PropertyTaxData.txt";
    private ArrayList<PropertyTaxData> data = new ArrayList<>();

    public PropertyTax(){
        readCSV();
    }

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

    public double totalTax(String routing){
        return totalAverage(routing,true);
    }

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

    public double averageTax(String routing){
        return totalAverage(routing,false);
    }

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

    private ArrayList<PropertyTaxData> findByRouting(String routing){
        ArrayList<PropertyTaxData> route = new ArrayList<>();
        for(PropertyTaxData r:data){
            if(r.equalRoute(routing)){
                route.add(r);
            }
        }
        return route;
    }


    public ArrayList<PropertyTaxData> overdue(int year){
        ArrayList<PropertyTaxData> overdue = new ArrayList<>();
        for(PropertyTaxData r: data){
            if(r.overdue(year)){
                overdue.add(r);
            }
        }
        return overdue;
    }

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





