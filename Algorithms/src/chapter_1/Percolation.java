package chapter_1;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Percolation {
	
	private int[][] color;
	private int[][] id;
	private int[][] sizeTree;
	private int black = 0;
	private int white = 1;
	public int numOfBlack;
	private int top;
	private int bottom;
	public static int originalQuickUnion = 0;
	public static int weighedUnionImprov = 1;
	public static int weighedCompressTreeQuickUnion = 2;
	
	//construct a N*N block where all the squares are black
	public Percolation(int N) {
		color = new int [N][N];
		id = new int [N][N];
		sizeTree = new int [N][N];
		numOfBlack = N * N;
		top = 0;
		bottom = N * N - 1;
		for(int i = 0; i < N; i++) {
			for(int j=0; j < N; j++) {
				color[i][j] = black; //black -> block
				id[i][j] = i * N + j; //initialize the Quick_Union_Tree
				sizeTree[i][j] = 1;
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
			/* Don't understand why change
			 * `if (root(0, i, N) == root(N-1, N-1, N) || root(N-1, i, N) == root(0, 0, N))`
			 * to
			 * `if (root(0, i, N) == bottom || root(N-1, i, N) == top)`
			 * or
			 * `if (root(0, i, N) == N * N - 1 || root(N-1, i, N) == 0)`
			 * will break everything.
			 * */
			if (root(0, i, N) == root(N-1, N-1, N) || root(N-1, i, N) == root(0, 0, N))
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
	
	//find the root of a square and compress the tree
	private int rootAndCompress(int i, int j, int N) {
		int x;
		while((i * N + j) != id[i][j]) {
			x = id[i][j];
			id[i][j] = id[x / N][x % N];
			i = x / N;
			j = x % N;
		}
		
		return id[i][j];
	}
	
	public void blackToWhite(int p, int q, int N, int signal){
		
		while(color[p][q] == white) {
			p = generateRandomInt(N);
			q = generateRandomInt(N);
		}
		
		//change a square from black to white
		color[p][q] = white; //white -> unblock -> ready to connect
		if(signal == weighedUnionImprov) {
			//make the union of this square with the surrounding white square
			if(p > 0 && color[p-1][q] == white) {
				union_weighed(p, q, p-1, q, N);
			}
			if(p < N-1 && color[p+1][q] == white) {
				union_weighed(p, q, p+1, q, N);
			}
			if(q > 0 && color[p][q-1] == white) {
				union_weighed(p, q, p, q-1, N);
			}
			if(q < N-1 && color[p][q+1] == white) {
				union_weighed(p, q, p, q+1, N);
			}
		} else if (signal == originalQuickUnion){
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
		} else {
			if(p > 0 && color[p-1][q] == white) {
				union_weighed_compress(p, q, p-1, q, N);
			}
			if(p < N-1 && color[p+1][q] == white) {
				union_weighed_compress(p, q, p+1, q, N);
			}
			if(q > 0 && color[p][q-1] == white) {
				union_weighed_compress(p, q, p, q-1, N);
			}
			if(q < N-1 && color[p][q+1] == white) {
				union_weighed_compress(p, q, p, q+1, N);
			}
		}
		
		
		
		numOfBlack--;
		
	}
	
	public void union(int p, int q, int i, int j, int N) {
		int r1 = root(p, q, N);
		int r2 = root(i, j, N);
		id[r2/N][r2%N] = id[r1/N][r1%N]; 
	}
	
	//Quick_Union with weighed tree
	public void union_weighed(int p, int q, int i, int j, int N) {
		int r1 = root(p, q, N);
		int r2 = root(i, j, N);
	
		if (p == i && q == j) 
			return;
		
		if (sizeTree[p][q] < sizeTree[i][j]) {
			id[r1/N][r1%N] = r2;
			sizeTree[i][j] += sizeTree[p][q]; 
		} else {
			id[r2/N][r2%N] = r1; 
			sizeTree[p][q] += sizeTree[i][j]; 
		} 	
	}

	//Quick_Union with weighed and compress tree
	public void union_weighed_compress(int p, int q, int i, int j, int N) {
		int r1 = rootAndCompress(p, q, N);
		int r2 = rootAndCompress(i, j, N);
	
		if (p == i && q == j) 
			return;
		
		if (sizeTree[p][q] < sizeTree[i][j]) {
			id[r1/N][r1%N] = r2;
			sizeTree[i][j] += sizeTree[p][q]; 
		} else {
			id[r2/N][r2%N] = r1; 
			sizeTree[p][q] += sizeTree[i][j]; 
		} 	
	}
	
	
	public int generateRandomInt(int N) {
		Random random = new Random();
		int randomNumber = random.nextInt(N);
		return randomNumber;
	}
	
	public double summarize(int numOfTest, int N, Percolation p, int signal) {
		double [] testArray = new double[numOfTest];
		int numOfWhite;
		//do the test for numOfTest times
		for(int i=0; i < numOfTest; i++) {
			numOfWhite = 0;
			p = new Percolation(N);
			while(!p.checkPercolation(N)){
				p.blackToWhite(p.generateRandomInt(N), p.generateRandomInt(N), N, signal);
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
		return average;
	}
	

	public static void main(String[] args) {
		int numOfTest; //how many times we do this test?
		int N; //the length of the big square.
		double result; //the final result of possibility
		int signal; //determine which function to use to make union
		System.out.println("This is a simulation of percolation."
				+ "Enter two numbers\n"
				+ "the first one is the length of squares\n"
				+ "the second one is the time of the tests:");
		
		//standard input
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		numOfTest = in.nextInt();
		
		//choose a method for union:
		System.out.println("Choose a method for union:\n"
				+ "0 -> Original quick_union\n"
				+ "1 -> Quick_union with weighed tree\n"
				+ "2 -> Quick_union with weighed tree and compress the tree every time we touch it.\n"
				+ "3 -> Ran the test under all the above three methods.");
		signal = in.nextInt();
		System.out.println("-> -> -> -> -> -> -> -> -> -> -> ->");
		
		if(signal != 0 && signal != 1 && signal != 2 && signal != 3) {
			System.out.println("\nYou entered something I don't have :-( \n"
					+ "I'll give you the default: The original quick_union.\n");
			signal = 0;
		}
		
		//construct the class Percolation
		Percolation p = new Percolation(N);
		
		
		//print out
		String out = new String();
		
		if(signal != 3) {
			
			long startTime = System.currentTimeMillis();
			result = p.summarize(numOfTest, N, p, signal);
			long estimatedTime = System.currentTimeMillis() - startTime;
			
			if (signal == weighedUnionImprov) {
				out = "Quick_Union with weighed trees";
			} else if (signal == originalQuickUnion) {
				out = "original Quick_Union";
			} else {
				out = "weighed and compress trees";
			} 
			System.out.println("\n---------------------------------------------------------------------------------\n"
							+ "The average posibility of white squares when it percolates is: "
					 		+ String.format("%.6f", result) + ".\n"
							+ "The time elapse under the " + out +" is:" + estimatedTime);
		} else {
			result = 0;
			System.out.println();
			for(int i=0; i<3; i++) {
				long startTime = System.currentTimeMillis();
				result += p.summarize(numOfTest, N, p, i);
				long estimatedTime = System.currentTimeMillis() - startTime;
				if (i == weighedUnionImprov) {
					out = "Quick_Union with weighed trees";
				} else if (i == originalQuickUnion) {
					out = "original Quick_Union";
				} else {
					out = "weighed and compress trees";
				} 
				System.out.println("The time elapse under the " + out +" is:" + estimatedTime);			
			}
			result /= 3;
			System.out.println("The average posibility of white squares when it percolates is: "
				 		+ String.format("%.6f", result) + ".\n");
			
		}
		
	}

}
