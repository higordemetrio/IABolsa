package br.edu.iftm.bolsa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
	/*
	private ArrayList<String> dates = new ArrayList();
	private ArrayList<String> closes = new ArrayList();
	
	
	
	public ReadCSV(ArrayList<String> dates, ArrayList<String> closes) {
		super();
		this.dates = dates;
		this.closes = closes;
	}
	*/
	
	public static ArrayList<String> readCSVdates(String string){

        String csvFile = string;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> dates = new ArrayList();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] dado = line.split(cvsSplitBy);

                dates.add(dado[0]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		return dates;
		
	}
	public static ArrayList<Double> readCSVcloses(String string){
	    String csvFile = string;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Double> closes = new ArrayList();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] dado = line.split(cvsSplitBy);
                double info = Double.parseDouble(dado[3]);
                closes.add(info);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		return closes;
		
	}



	public static ArrayList<Double> readCSVclosesWithAmount(String string, int sizeTable) {
	    String csvFile = string;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Double> closes = new ArrayList();
        int cont = 0;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            
        	while ((line = br.readLine()) != null) {
        	
            // use comma as separator
        		
        		if(cont<sizeTable){
		            String[] dado = line.split(cvsSplitBy);
		            double info = Double.parseDouble(dado[3]);
		            closes.add(info);
		            cont ++;
        		}else{
        			break;
        		}
        		
        		
                
                
        	
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		return closes;
		
	}
		
}
