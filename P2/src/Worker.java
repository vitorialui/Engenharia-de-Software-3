import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Worker implements Callable<ArrayList<ArrayList <Integer>>>{
	private int startI, startJ;
    private int endI, endJ;
    ArrayList<ArrayList<String>> matrizA = new ArrayList<ArrayList<String>>(); 
	ArrayList<ArrayList<String>> matrizB = new ArrayList<ArrayList<String>>();
    
	public Worker(int startI, int startJ, int endI, int endJ, ArrayList<ArrayList<String>> matrizA, ArrayList<ArrayList<String>> matrizB) {
		this.startI = startI;
		this.startJ = startJ;
		this.endI = endI;
		this.endJ = endJ;
		this.matrizA = matrizA;
		this.matrizB = matrizB;
	}

	
	public ArrayList<ArrayList <Integer>> call() throws Exception {
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for (int i=startI; i < endI; i++) {
			ArrayList<Integer> aux = new ArrayList<Integer>();
			for(int j=startJ; j < endJ; j++) {
				int soma = Integer.parseInt(matrizA.get(i).get(j)) + Integer.parseInt(matrizB.get(j).get(i));
				aux.add(soma);
			}
		temp.add(aux);
		}
		return temp;
	}
      
}
