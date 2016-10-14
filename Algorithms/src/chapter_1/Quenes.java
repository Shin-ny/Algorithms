package chapter_1;

import java.util.Iterator;
import java.util.Scanner;

public class Quenes<Item> {
	
	private Node first = null; 
	private Node last = null;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
	}
	
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		if(isEmpty()) {
			last = null;
		}
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
		Quenes<String> q = new Quenes<String>();
		Scanner in = new Scanner(System.in);
		String x;
		while(in.hasNext()) {
			x = in.next();
			if(x.charAt(0) == '-') {
				String result = q.dequeue();
				System.out.println(result);
			} else {
				q.enqueue(x);
			}
		}
		
		
		
		//Implement the Iterable:
		Iterator<String> i = q.iterator();
		while (i.hasNext()) {
		 String s = i.next();
		 System.out.println(s);
		}
		
		
		in.close();

	}

}
