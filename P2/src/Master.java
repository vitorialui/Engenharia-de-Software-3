import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Master {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ArrayList<ArrayList<String>> matrizA = new ArrayList<ArrayList<String>>(); 
		ArrayList<ArrayList<String>> matrizB = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> matrizSoma = new ArrayList<ArrayList<Integer>>();
		int[][] matrizC = new int[1000][1000];
		long t1, t2;
		int numWorkers = 8, ordem = 1000;
		
		ExecutorService tpes = Executors.newCachedThreadPool();
		
		Future<ArrayList<ArrayList<Integer>>> futures[] = new Future[numWorkers];
		
		t1 = System.currentTimeMillis();
		
		try{
			 
	         BufferedReader brA = new BufferedReader(new FileReader("C:\\Users\\vitor\\Downloads\\Fatec\\ENG 3\\Prova\\p12A.txt"));
	         BufferedReader brB = new BufferedReader(new FileReader("C:\\Users\\vitor\\Downloads\\Fatec\\ENG 3\\Prova\\p12B.txt"));
	         while(brA.ready()){
	        	 String linha = brA.readLine();
	        	 String[] div = linha.split(" ", ordem);
	        	 ArrayList<String> aux = new ArrayList<String>();
	        	 for (String item : div) {
	       	      	aux.add(item);
	        	 }
	        	 matrizA.add(aux);
	         }
	         brA.close();
	         while(brB.ready()){
	        	 String linha = brB.readLine();
	        	 String[] div = linha.split(" ", ordem);
	        	 ArrayList<String> aux = new ArrayList<String>();
	        	 for (String item : div) {
	       	      	aux.add(item);
	        	 }
	        	 matrizB.add(aux);
	         }
	         brB.close();
	      }catch(IOException ioe){
	         ioe.printStackTrace();
	      }
		
		int factor = matrizA.size()/numWorkers;
        for (int i = 0; i < numWorkers; i++) {
        	futures[i] = tpes.submit(new Worker(i*factor, 0, (i+1)*factor, matrizA.size(), matrizA, matrizB));
        	ArrayList<ArrayList<Integer>> aux = futures[i].get();
        	for (ArrayList item : aux) {
	       	      matrizSoma.add(item);
	        }
        }
        
        for (int i = 0; i < matrizB.size(); i++) {
        	for (int k = 0; k < matrizB.get(i).size(); k++) {
        		for (int j = 0; j < matrizB.get(i).size(); j++) {
                    matrizC[i][j] += (Integer.parseInt(matrizB.get(i).get(k)) * matrizSoma.get(k).get(j));
                }
            }
        }
    	
        FileWriter arq;
		try {
			arq = new FileWriter("C:\\Users\\vitor\\Downloads\\Fatec\\ENG 3\\Prova\\p12C.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			for (int i=0; i < 1000; i++) {
				for(int j = 0; j < 1000; j++) {
					gravarArq.printf(matrizC[i][j] + " ");
				}
				gravarArq.printf("\n");
			}
		    arq.close();
		    System.out.println("Gravado com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		tpes.shutdown();
		t2 = System.currentTimeMillis();
		System.out.println("Elapsed: " + (t2-t1));
	}
}