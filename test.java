public class test{
	public static void main(String[] args){
		int[] a = new int[]{10,9,8,7,6,5,4,3,2,1};
		merge(a,0,a.length - 1);
		for(int x:a) System.out.print(x+" ");
		System.out.println(" ");
		int[] a1 = new int[]{2,3,4,5,1,2,3,4};
		System.out.println(merge(a1,0,4, a1.length - 1));
		for(int x:a1) System.out.print(x+" ");
		// here is a change!!
		System.out.println(" ");
		print(4);

		String s = "abcdba";
		System.out.println(palin(s));
	}

	public static boolean palin(String str){
		if(str.length() <= 1) return true;
		if(str.charAt(0) != str.charAt(str.length() - 1)) return false;
		return palin(str.substring(1,str.length() -1));
	}

	public static int findMin(int[] arr, int s, int f){
		int m = (f+s)/2;
		if(s == f) return arr[s];
		if(arr[m] < arr[arr.length - 1]) return findMin(arr, s, m);
		else return findMin(arr, m+1,f);
	}

	public static int merge(int[] arr, int s, int f){
		int result = 0;
		if(s < f){
			int m = (s+f)/2;
			result += merge(arr,s,m);
			result += merge(arr,m+1,f);
			result += merge(arr,s,m + 1,f);
		}
		return result;
	}

	public static int merge(int[] a, int s, int mid, int f){
		int[] r = new int[mid - s + 1 + f - mid + 1];
		int m = s; 
		int n = mid;
		int j = 0;
		int count = 0;

		while(m < mid && n <= f){
			if(a[m] > 2 * a[n]){
				count+=(mid - m);
				n++;
			}else{
				m++;
			}
		}

		m = s; 
		n = mid;
		while(m < mid && n <= f){
			if(a[m] < a[n]){
				r[j] = a[m];
				m++;
				j++;
			}else{
				r[j] = a[n];
				n++;
				j++;
			}
		}

		while(m < mid){
			r[j] = a[m];
			m++;
			j++;
		}
		while(n <= f){
			r[j] = a[n];
			n++;
			j++;
		}
		j = 0;
		for(int i = s; i <= f; i++){
			a[i] = r[j];
			j++;
		}
		return count;
	}

	public static void print(int n){
		if(n == 0) return;
		System.out.print(n+" ");
		print(n - 1);
	}
}
