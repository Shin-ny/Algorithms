package chapter_1;

import java.util.Iterator;
import java.util.Scanner;

public class Stacks<Item> {

	private Node first = null;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListeIterator();
	}
	
	private class ListeIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			
		}
		
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public static void main(String[] args) {
		Stacks<String> sa = new Stacks<String>();
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

		
		//Implement the Iterable:
		Iterator<String> i = sa.iterator();
		while (i.hasNext())
		{
		 String s = i.next();
		 System.out.println(s);
		}
		
		
		in.close();
	}

}
