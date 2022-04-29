package com.cinco;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;

public class DataConverter {
    
	public static MapHolder main(String[] args) {
        Map<String, Person> PersonMap = new HashMap<>();
        Map<String, Customer> CustomerMap = new HashMap<>();
        Map<String, Product> ProductMap = new HashMap<>();
        //HashMaps are used for easy searching during making Customers, Products, and Invoices
        //Run though persons list first to allow for Customers, Products, and Invoices to link back to a person if need be
        //Every file is processed in different try catches for easier bug catching and formatting

        //Start with Persons
        try {
            Scanner s = new Scanner(new File("data/Persons.dat"));
            String line = s.nextLine();
            while(s.hasNext()){
                line = s.nextLine();
                String[] PersonTokens = line.split(";");
                if(PersonTokens.length==4){
                    PersonMap.put(PersonTokens[0],new Person(PersonTokens[0],PersonTokens[1],PersonTokens[2],PersonTokens[3]));
                } else {
                    PersonMap.put(PersonTokens[0],new Person(PersonTokens[0],PersonTokens[1],PersonTokens[2],""));
                }
                
            }
       
            s.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        //Move on to Customers
        try {
            Scanner s = new Scanner(new File("data/Customers.dat"));
            String line = s.nextLine();
            while(s.hasNext()){
                line = s.nextLine();
                String[] CustomerTokens = line.split(";");
                CustomerMap.put(CustomerTokens[0],new Customer(CustomerTokens[0],CustomerTokens[1],PersonMap.get(CustomerTokens[2]),CustomerTokens[3],CustomerTokens[4]));
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       
        //Move on to Equipment
        try {
            Scanner s = new Scanner(new File("data/Products.dat"));
            String line = s.nextLine();
            while(s.hasNext()){
                line = s.nextLine();
                String[] ProductTokens = line.split(";");
                //System.out.print(ProductTokens[1]);
                if(ProductTokens[1].equals("E")){
                    ProductMap.put(ProductTokens[0], new Equipment(ProductTokens[0],ProductTokens[1],ProductTokens[2],ProductTokens[3]));
                } else if (ProductTokens[1].equals("L")){
                    ProductMap.put(ProductTokens[0], new License(ProductTokens[0],ProductTokens[1],ProductTokens[2],ProductTokens[3], ProductTokens[4]));
                } else {
                    ProductMap.put(ProductTokens[0], new Consultation(ProductTokens[0],ProductTokens[1],ProductTokens[2],PersonMap.get(ProductTokens[3]), ProductTokens[4]));
                }
            }
            
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MapHolder returnValue = new MapHolder(PersonMap, CustomerMap, ProductMap);

        //Java doesn't like passing hashmaps to methods especially hashmaps with general Objects, so we can't make this the following code into a method :feelsBadMan:
        String Output = "{\n    \"Persons\":[\n";

        Iterator<Map.Entry<String, Person>> mapIterator = PersonMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, Person> entry = mapIterator.next();
            String json = gson.toJson(entry.getValue());
            Output = Output + json + ",";
        }
        
        String FinalOutput = "";
        FinalOutput = FinalOutput + Output.substring(0, Output.length()-1) + "\n]\n}";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Persons.json"));
            writer.write(FinalOutput);
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
            //This should never happen, except maybe invalid write permissions, but
            //java complains about it regardless so its in a try catch loop
        }

        Output = "{\n    \"Customers\":[\n";
        Iterator<Map.Entry<String, Customer>> map1Iterator = CustomerMap.entrySet().iterator();
        while (map1Iterator.hasNext()) {
            Map.Entry<String, Customer> entry = map1Iterator.next();
            String json = gson.toJson(entry.getValue());
            Output = Output + json + ",";
        }
        
        FinalOutput = "";
        FinalOutput = FinalOutput + Output.substring(0, Output.length()-1) + "\n]\n}";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Customers.json"));
            writer.write(FinalOutput);
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }

        Output = "{\n    \"Products\":[\n";
        Iterator<Map.Entry<String, Product>> map2Iterator = ProductMap.entrySet().iterator();
        while (map2Iterator.hasNext()) {
            Map.Entry<String, Product> entry = map2Iterator.next();
            String json = gson.toJson(entry.getValue());
            Output = Output + json + ",";
        }
        
        FinalOutput = "";
        FinalOutput = FinalOutput + Output.substring(0, Output.length()-1) + "\n]\n}";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Products.json"));
            writer.write(FinalOutput);
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    
        return returnValue;
    }
    
}