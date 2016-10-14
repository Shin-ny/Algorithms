package chapter_1;

import java.util.Scanner;

public class StacksArr<Item> {
	private Item[] s;
	private int N = 0;
	
	public StacksArr() {
		s = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(Item item) {
		if(N == s.length) {
			resize(2 * s.length);
		}
		s[N++] = item;
	}
	
	public Item pop() {
		Item item = s[--N];
		s[N] = null;
		if(N > 0 && N == s.length / 4) {
			resize(s.length / 2);
		}
		return item;
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for(int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}
	
	public static void main(String[] args) {
		StacksArr<String> sa = new StacksArr<String>();
		Scanner in = new Scanner(System.in);
		String x;
		while(in.hasNext()) {
			x = in.next();
			if(x.charAt(0) == '-') {
				String result = sa.pop();
				System.out.println(result);
			} else {
				sa.push(x);
			}
		}
		in.close();

	}
}
