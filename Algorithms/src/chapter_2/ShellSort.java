package chapter_2;

import java.util.Scanner;

public class ShellSort {
	
	public static int[] a;
	
	public ShellSort(int num) {
		a = new int[num];
		Scanner in = new Scanner(System.in);
		for(int i=0; i < num; i++) {
			a[i] = in.nextInt();
		}
		in.close();
	}
	
	public static void exchange(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public void sort() {
		int h = 1;
		while (h < a.length/3) h = 3*h + 1; 
		while(h >= 1) {
			for(int i=h; i < a.length; i += h) {
				for(int j=i; j > 0; j -= h) {
					if(a[j] < a[j-h]) exchange(j, j-h);
				}
			}
			h = h/3;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.print("How many numbers do you want to sort? ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		System.out.println("The numbers:");
		ShellSort s = new ShellSort(num);	
		s.sort();
		System.out.println("The sorted numbers:");
		for(int i=0; i < num; i++) {
			System.out.print(a[i] + " ");
		}
		in.close();
		
	}
}
