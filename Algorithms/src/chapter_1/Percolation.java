package chapter_1;

import java.util.Random;

public class Percolation {
	
	private int[][] color;
	private int[][] id;
	private int black = 0;
	private int white = 1;
	public int numOfBlack;
	private int top;
	private int bottom;
	
	//construct a N*N block where all the squares are black
	public Percolation(int N) {
		color = new int [N][N];
		id = new int [N][N];
		numOfBlack = N * N;
		top = 0;
		bottom = N * N - 1;
		for(int i = 0; i < N; i++) {
			for(int j=0; j < N; j++) {
				color[i][j] = black; //black -> block
				id[i][j] = i * N + j; //initialize the Quick_Union_Tree
			}
		}
		//construct the top and bottom tree
		for(int i=0; i < N; i++) {
			id[0][i] = top;
			id[N-1][i] = bottom;		
		}
	}
	

	
	//check percolation
	public boolean checkPercolation(int N) {
		for(int i=0; i < N; i++) {
			if (root(0, i, N) == bottom || root(N-1, i, N) == top)
				return true;
		}
		return false;
	}
	
	//find the root of a square
	private int root(int i, int j, int N) {
		int x;
		while((i * N + j) != id[i][j]) {
			x = id[i][j];
			i = x / N;
			j = x % N;
		}
		return id[i][j];
	}
	
	
	public void blackToWhite(int p, int q, int N){
		
		while(color[p][q] == white) {
			p = generateRandomInt(N);
			q = generateRandomInt(N);
		}
		
		//change a square from black to white
		color[p][q] = white; //white -> unblock -> ready to connect
		
		//make the union of this square with the surrounding white square
		if(p > 0 && color[p-1][q] == white) {
			union(p, q, p-1, q, N);
		}
		if(p < N-1 && color[p+1][q] == white) {
			union(p, q, p+1, q, N);
		}
		if(q > 0 && color[p][q-1] == white) {
			union(p, q, p, q-1, N);
		}
		if(q < N-1 && color[p][q+1] == white) {
			union(p, q, p, q+1, N);
		}
		
		numOfBlack--;
		
	}
	
	public void union(int p, int q, int i, int j, int N) {
		int r1 = root(p, q, N);
		int r2 = root(i, j, N);
		id[r1/N][r1%N] = id[r2/N][r2%N];
	}
	
	public int generateRandomInt(int N) {
		Random random = new Random();
		int randomNumber = random.nextInt(N);
		return randomNumber;
	}
	

	public static void main(String[] args) {
		int numOfTest = 100; //how many times we do this test?
		int N = 2; //the length of the big square.
		double [] testArray = new double[numOfTest];
		
		//do the test for numOfTest times
		for(int i=0; i < numOfTest; i++) {
			double numOfWhite = 0;
			Percolation p = new Percolation(N);
			while(!p.checkPercolation(N)){
				p.blackToWhite(p.generateRandomInt(N), p.generateRandomInt(N), N);
				numOfWhite++;
			}
			testArray[i] = numOfWhite / (double)(N * N);
		}
		
		//average the result of each test
		double sum = 0;
		for(int i=0; i < numOfTest; i++) {
			sum = sum + testArray[i];
		}
		double average = sum / numOfTest;
		
		System.out.println(average);
	}

}
