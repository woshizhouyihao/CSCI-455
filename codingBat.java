import java.util.Scanner;

public class codingBat{
	// Exercise 1
	public static int longestWordLen(String words){
		if(words.length() == 0) return 0;
		int max = scanner(words);
		return Math.max(max, longestWordLen(words.substring(max)));
	}

	public static int scanner(String words){
		Scanner sc = new Scanner(words);
		return sc.next().length();
	}

	// Exercise 2
	public static String string1UpToN(int n){
		if(n == 1) return "1";
		return string1UpToN(n-1) + " " + n;
	}

	//Exercise 3
	public static int fastExpon(int x, int n){
		if(n == 0) return 1;
		if(n == 1) return x;
		if(n % 2 == 0) return fastExpon(x, n/2) * fastExpon(x, n/2);
		else return fastExpon(x, n/2) * fastExpon(x, n/2) * x;
	}

	public static void main(String[] args){
		String s = "a bb cc dd e";
		System.out.println(longestWordLen(s));

		System.out.println(string1UpToN(5));
		
		for(int i = 0; i < 12; i++){
			System.out.println(fastExpon(2,i));
		}
		
	}
}