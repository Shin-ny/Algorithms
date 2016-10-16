package chapter_1;

import java.util.Scanner;

public class DijkstraTwoStackAlgorithm {

	public static void main(String[] args) {
		StacksArr<String> value = new StacksArr<String>();
		StacksArr<String> operator = new StacksArr<String>();
		System.out.println("This is a simple calculator:\n"
				+ "You can use +, -, *, /.\n"
				+ "You have to use parentheses at the begining and the end of the formation!\n"
				+ "Also, there should be a '=' at the end of the formation.\n"
				+ "For example: '( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = '");
		Scanner in = new Scanner(System.in);
		String result;
		String x;
		while(in.hasNext()) {
			x = in.next();
			if(x.charAt(0) == '=') {
				result = value.pop();
				System.out.println(result);
			} else if(x.charAt(0) == '+') {
				operator.push(x);
			} else if(x.charAt(0) == '-') {
				operator.push("-");
			} else if(x.charAt(0) == '*') {
				operator.push("*");
			} else if(x.charAt(0) == '/') {
				operator.push("/");
			} else if(x.charAt(0) == ')') {
				String op = operator.pop();
				if (op.equals("+")) {
					value.push(Integer.toString(Integer.parseInt(value.pop()) + Integer.parseInt(value.pop())));
				}
				else if (op.equals("-")) {
					value.push(Integer.toString(Integer.parseInt(value.pop()) - Integer.parseInt(value.pop())));
				}
				else if (op.equals("*")) {
					value.push(Integer.toString(Integer.parseInt(value.pop()) * Integer.parseInt(value.pop())));
				}
				else if (op.equals("/")) {
					value.push(Integer.toString(Integer.parseInt(value.pop()) / Integer.parseInt(value.pop())));
				}
			} else if(x.charAt(0) == '(') {
				//do nothing
			} else {
				value.push(x);
			}
		}
		in.close();

	}

}
