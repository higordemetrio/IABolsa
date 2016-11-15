package br.edu.iftm.bolsa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    	//ArrayList<String> dates = ReadCSV.readCSVdates("Inputs/table.csv");
    	//ArrayList<Double> closes = ReadCSV.readCSVcloses("Inputs/table.csv");
    	
    	//ArrayList<Double> closes = ReadCSV.readCSVclosesWithDate("Inputs",
    	//		"2016-11-08", "2016-10-05");
    	
    	//minimo de 6
    	ArrayList<Double> closes = ReadCSV.readCSVclosesWithAmount("Inputs/table.csv",6);
    	
    	
			System.out.println(closes);
		
    	
    	RedeNeural.training(closes);
		
    			
    			
    			
    			
    }

}