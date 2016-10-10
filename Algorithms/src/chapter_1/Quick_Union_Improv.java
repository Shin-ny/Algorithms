package chapter_1;

public class Quick_Union_Improv {
	private int[] id;
	private int[] sizeTree;
	
	public Quick_Union_Improv(int N) {
		id = new int[N];
		sizeTree = new int[N];
		for(int i=0; i<N; i++){
			id[i] = i;
			sizeTree[i] = 0;
		}
	}
	
	private int root(int i) {
		while(i != id[i]) {
			//compress the tree everytime it finds connection
			id[i] = id[id[i]];
			
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
		if (i == j) 
			return;
		if (sizeTree[i] < sizeTree[j]) {
			id[i] = j; 
			sizeTree[j] += sizeTree[i]; 
		}
		else {
			id[j] = i; 
			sizeTree[i] += sizeTree[j]; 
		} 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quick_Union_Improv qu = new Quick_Union_Improv(10);
		qu.union(1, 2);
		qu.union(3, 2);
		System.out.println(qu.connected(1, 3));
	}

}
