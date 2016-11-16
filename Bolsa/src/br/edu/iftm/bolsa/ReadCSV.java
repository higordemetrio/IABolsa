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
	public static ArrayList<Double> readCSVclosesWithDate(String string, String dataInicio, String dataFim) {
		 String csvFile = string;
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        ArrayList<Double> closes = new ArrayList();
	        int cont = 0;
	        
	        String[] dataI = dataInicio.split("-");
	        String[] dataF = dataInicio.split("-");
	        
	        int anoI = Integer.parseInt(dataI[0]);
	        int mesI = Integer.parseInt(dataI[1]);
	        int diaI = Integer.parseInt(dataI[2]);
	        
	        int anoF = Integer.parseInt(dataF[0]);
	        int mesF = Integer.parseInt(dataF[1]);
	        int diaF = Integer.parseInt(dataF[2]);
	        
	        
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            line = br.readLine();
	            
	        	while ((line = br.readLine()) != null) {
	        		
           
		            String[] dado = line.split(cvsSplitBy);
		            String[] dataA = dado[0].split("-");
			        int anoA = Integer.parseInt(dataA[0]);
			        int mesA = Integer.parseInt(dataA[1]);
			        int diaA = Integer.parseInt(dataA[2]);
			        
			        if(anoA < anoF && anoA > anoI){
		            double info = Double.parseDouble(dado[3]);
		            closes.add(info);
		           
			        }else if(anoA == anoI){
			        	if (mesA > mesI) {
			        		double info = Double.parseDouble(dado[3]);
				            closes.add(info);
				           
						}
			        	else if( mesA == mesI){
			        		if(diaA >= diaI){

			        			double info = Double.parseDouble(dado[3]);
					            closes.add(info); 
			        		}
			        		
			        	}
			        }else if(anoA == anoF){
			        	if (mesA < mesF) {
			        		double info = Double.parseDouble(dado[3]);
				            closes.add(info);
						}
			        	else if( mesA == mesF){
			        		if(diaA <= diaF){
			        			double info = Double.parseDouble(dado[3]);
					            closes.add(info);
			        		}
			        	}
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
