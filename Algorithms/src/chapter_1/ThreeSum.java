package chapter_1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ThreeSum {
	
	public int N;
	public int[] num;
	
	public ThreeSum(int N, int low, int high) {
		this.N = N;
		num = new int[N];
		
		for(int i=0; i < N; i++) {
			int number = this.generateRandomInt(low, high);
			while(!correctGenerateNum(i, number)) {
				number = this.generateRandomInt(low, high);
			}
			num[i] = number;	
		}
	}
	
	public boolean correctGenerateNum(int i, int generateNum) {
		for(int j=0; j < i; j++) {
			if(generateNum == num[j]) {
				return false;
			}
		}
		return true;
	}
	
	public int originalComupte() {
		int sum = 0;
		for(int i=0; i < N; i++) {
			for(int j=i+1; j < N; j++) {
				for(int k=j+1; k < N; k++) {
					if(num[i] + num[j] + num[k] == 0) {
						sum++;
					}
				}
			}
		}
		return sum;
	}
	
	public int binarySearch(int search, int j) {
		int low = j+1;
		int high = N-1;
		while(low <= high) {
			int mid = low + (high - low) / 2;
			if(search < num[mid])
				high = mid - 1;
			else if(search > num[mid])
				low = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	public int improvComupte() {
		int sum = 0;
		Arrays.sort(num);
		for(int i=0; i < N; i++) {
			for(int j=i+1; j < N; j++) {
				if((binarySearch(-(num[i]+num[j]), j) != -1)) {
					sum ++;
				}
			}
		}
		return sum;
	}
	
	public int generateRandomInt(int low, int high) {
		Random random = new Random();
		int randomNumber = random.nextInt((high - low)) + low;
		return randomNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime;
		long estimatedTime;
		int signal;
		Scanner in = new Scanner(System.in);
		System.out.println("Compute how many times there is three distinct numbers that can sum to 0.\n"
				+ "----------->\n"
				+ "Please enter three numbers:\n"
				+ "The length of distinct numbers.\n"
				+ "The lower bound of these numbers.\n"
				+ "The higher bound of these numbers.");
		ThreeSum ts = new ThreeSum(in.nextInt(), in.nextInt(), in.nextInt());
		
		System.out.println("Do you want to print these random numbers?\n"
				+ "0 -> Yes\n"
				+ "1 -> No\n");
		
		signal = in.nextInt();
		
		if(signal == 0) {
			System.out.println("The random generated numbers: ");
			for(int i=0; i<ts.N; i++) {
				System.out.print(ts.num[i] + "\n");
			}
		}
		
		System.out.println("Do you want to skip the original ThreeSum solution?\n"
				+ "(Cause it can took a lot of time!!)\n"
				+ "0 -> Yes\n"
				+ "1 -> No\n");
		
		signal = in.nextInt();
		if(signal == 1) {
			startTime = System.currentTimeMillis();
			System.out.println("\nThe original one: " + ts.originalComupte());
			estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println("The time elapse under the original one is: " + estimatedTime
					+ "\n-----------------------------------------------------");
		}
		
		
		startTime = System.currentTimeMillis();
		System.out.println("The improv one:" + ts.improvComupte());
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("The time elapse under the improved one is: " + estimatedTime);
		in.close();
	}

}
