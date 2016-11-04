package chapter_2;

import java.util.Scanner;

//randomly shuffle a bunch of card
public class Shuffle {
	static double[][] a;
	
	public Shuffle(int num) {
		a = new double[num][2];
	}
	
	public void print(int num) {
		for(int i=0; i < num; i++) {
			System.out.print((int)a[i][0] + " ");
		}
	}
	
	public void generateRandom(int num) {
		for(int i=0; i < num; i++){
			this.a[i][1] = Math.random();
		}
	}
	
	public static void exchange(int i, int j) {
		double temp1 = a[i][1];
		double temp2 = a[i][0];
		a[i][1] = a[j][1];
		a[i][0] = a[j][0];
		a[j][1] = temp1;
		a[j][0] = temp2;
	}
	
	public void Shellsort() {
		int h = 1;
		while (h < a.length/3) h = 3*h + 1; 
		while(h >= 1) {
			for(int i=h; i < a.length; i += h) {
				for(int j=i; j > 0; j -= h) {
					if(a[j][1] < a[j-h][1]) exchange(j, j-h);
				}
			}
			h = h/3;
		}
		
	}
	
	public static void main(String[] args) {
		int num = 5; //the number of cards
		Shuffle s = new Shuffle(num);
		Scanner ss = new Scanner(System.in);
		for(int i=0; i < num; i++) {
			s.a[i][0] = ss.nextInt();
		}
		s.generateRandom(num);
		s.Shellsort();
		System.out.println("After randomly sorted: ");
		s.print(num); //after randomly sorted

		
	}

}
