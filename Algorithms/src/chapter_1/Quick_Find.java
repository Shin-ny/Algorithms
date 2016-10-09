package chapter_1;

public class Quick_Find {
	
	private int[] id;
	
	public Quick_Find(int N) {
		id = new int[N];
		for(int i=0; i<N; i++) {
			id[i] = i;
		}
	}
	
	public boolean connnected(int p, int q) {
		return id[p] == id[q];
	}
	
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for(int i=0; i<id.length; i++) {
			if(id[i] == pid) {
				id[i] = qid;
			}
		}
	}

	public static void main(String[] args) {
		Quick_Find qf = new Quick_Find(10);
		qf.union(1, 2);
		qf.union(3, 2);
		System.out.println(qf.connnected(1, 3));
		
	}

}
