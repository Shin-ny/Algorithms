package chapter_2;

import java.util.Scanner;

public class InsertionSort {
	
	public static int[] a;
	
	public InsertionSort(int num) {
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
		for(int i=1; i < a.length; i++) {
			for(int j=i; j > 0; j--) {
				if(a[j] < a[j-1]) exchange(j, j-1);
			}
		}
	}

	public static void main(String[] args) {
		System.out.print("How many numbers you want to sort? ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		System.out.println("The numbers:");
		InsertionSort s = new InsertionSort(num);	
		s.sort();
		System.out.println("The sorted numbers:");
		for(int i=0; i < num; i++) {
			System.out.print(a[i] + " ");
		}
		in.close();

	}

}
