package chapter_1;

public class Quick_Union {
	
	private int[] id;
	
	public Quick_Union(int N) {
		id = new int[N];
		for(int i=0; i<N; i++){
			id[i] = i;
		}
	}
	
	private int root(int i) {
		while(i != id[i]) {
			i = id[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		id[i] = id[j];
	}

	public static void main(String[] args) {
		Quick_Union qu = new Quick_Union(10);
		qu.union(1, 2);
		qu.union(3, 2);
		System.out.println(qu.connected(0, 3));

	}

}
