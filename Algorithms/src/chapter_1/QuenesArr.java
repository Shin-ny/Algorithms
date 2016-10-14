package chapter_1;


public class QuenesArr<Item> {
	
	private Item[] s;
	private int first = 0;
	private int last = 0;
	
	public QuenesArr() {
		s = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return last == first;
	}
	
	public void enqueue(Item item) {
		s[last] = item;
		last++;
	}
	
	public Item dequeue() {
		Item head = s[first];
		first++;
		return head;
	}
	
	private void resize(int capacity) {
		int j = 0;
		Item[] copy = (Item[]) new Object[capacity];
		for(int i = first; i < last; i++) {
			copy[j] = s[i];
			j++;
		}
		s = copy;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
