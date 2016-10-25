package chapter_2;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class KnuthShuffle {
	int[] a;
	
	public KnuthShuffle(int num) {
		a = new int[num];
	}
	
	public void print(int num) {
		for(int i=0; i < num; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public int generateRandom(int i) {
		return ThreadLocalRandom.current().nextInt(0, i + 1);
	}
	
	public void exchange(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public void shuffle() {
		for(int i=0; i < a.length; i++) {
			exchange(i, generateRandom(i));
		}
	}
	public static void main(String[] args) {
		int num = 5; //the number of cards
		KnuthShuffle s = new KnuthShuffle(num);
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i < num; i++) {
			s.a[i] = scanner.nextInt();
		}
		s.shuffle();
		System.out.println("After randomly sorted: ");
		s.print(num); //after randomly sorted
	}

}
