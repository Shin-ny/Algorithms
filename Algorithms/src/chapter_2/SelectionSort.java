package chapter_2;

import java.util.Scanner;

public class SelectionSort {
	
	public static int[] a;
	
	public SelectionSort(int num) {
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
		int i;
		for(int j=0; j < a.length; j++) {
			int min = a[j];
			int swap = j;
			for(i=j; i < a.length; i++) {
				if(min > a[i]) {
					min = a[i];
					swap = i;
				}
			}
			exchange(swap, j);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.print("How many numbers you want to sort? ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		System.out.println("The numbers:");
		SelectionSort s = new SelectionSort(num);	
		s.sort();
		System.out.println("The sorted numbers:");
		for(int i=0; i < num; i++) {
			System.out.print(a[i] + " ");
		}
		in.close();
		
	}

}
