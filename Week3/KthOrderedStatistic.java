import java.util.*;
import java.io.*;
public class KthOrderedStatistic
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int arr[];
	static int N;
	static Random rand = new Random();
	
	static void swap(int i , int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static void selection(int L , int R , int k) {
		int lt , gt , pt; 
		while(L <= R) {
			swap(L, L + rand.nextInt(R - L + 1));
			int pivot = arr[L];
			lt = pt = L;
			gt = R;
			while(pt <= gt) {
				if(arr[pt] < pivot)
					swap(pt++, lt++);
				else if(arr[pt] > pivot)
					swap(pt, gt--);
				else
					pt++;
			}
			if(k >= lt && k <= gt)
				return;
			else if(k < lt)
				R = lt - 1;
			else
				L = gt + 1;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		int k1 = s1.nextInt();
		int k2 = s1.nextInt();
		int A = s1.nextInt();
		int B = s1.nextInt();
		int C = s1.nextInt();
		
		arr = new int[N];
		arr[0] = s1.nextInt();
		arr[1] = s1.nextInt();
		// int test[] = new int[N];
		for(int i=2;i<N;i++)
			arr[i] = (A * arr[i - 2]) + (B * arr[i - 1]) + C;
		// System.arraycopy(arr, 0, test, 0, N);
		// System.out.println("initial " + Arrays.toString(arr));
		// long start = System.nanoTime();
		selection(0 , N - 1 , k1 - 1);
		selection(k1 - 1, N - 1 , k2 - 1);
		int aux[] = new int[k2 - k1 + 1];
		System.arraycopy(arr, k1 - 1, aux, 0, aux.length);
		Arrays.sort(aux);
		// long stop = System.nanoTime();
		
		// System.err.println("Time for selection " + ((stop - start) / 1e9));
		for(int a : aux)
			out.print(a + " ");
		/*
		boolean check = true;
		start = System.nanoTime();
		Arrays.sort(test);
		stop = System.nanoTime();
		
		System.err.println("Time for brute force " + ((stop - start) / 1e9));
		
		for(int i=k1-1;i<k2;i++)
			out.print(test[i] + " ");
		*/
	
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "kth";
	
	public static void main(String []args) throws IOException {
		InputStream  inputStream  = (System.getProperty("JUDGE") != null) ? (new FileInputStream(fileName  + ".in"))  : System.in;
		OutputStream outputStream = (System.getProperty("JUDGE") != null) ? (new FileOutputStream(fileName + ".out")) : System.out;
		FastScanner in  = new FastScanner(inputStream);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    
	
	static class FastScanner{
		BufferedReader reader;
		StringTokenizer st;
		public FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
		String next()
		{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
		st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
		String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
		int    nextInt()   {return Integer.parseInt(next());}
		long   nextLong()  {return Long.parseLong(next());}		
		double nextDouble(){return Double.parseDouble(next());}
		char   nextChar()  {return next().charAt(0);}
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}

