import java.util.*;
import java.io.*;
public class GenerateTests
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
		private static void solve2(FastScanner s1, PrintWriter out){ // Slow solution TLE
		
		int K = s1.nextInt();
		int divisors[] = new int[K + 10];
		
		for(int i=1;i<=K;i++)
			for(int j=i;j<=K;j += i)
				divisors[j]++;
		
		int idx = 1;
		for(int i = 1;i <= K;i++)
			idx = divisors[i] > divisors[idx] ? i : idx;
		
		out.println(K - idx + 1);
	}
	

	static void research() {

		int K = (int)(1e7);
		int divisors[] = new int[K + 10];
		
		for(int i=1;i<=K;i++)
			for(int j=i;j<=K;j += i)
				divisors[j]++;
		
		int idx = 1;
		ArrayList<Integer> arl = new ArrayList<>();
		for(int i = 1;i <= K;i++)
			if(divisors[i] > divisors[idx]) {
				idx = i;
				arl.add(i);
			}
		
		System.out.println(arl.size());
		System.out.println(arl);
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		// research();
		int antiPrimes[] = { 2, 4, 6, 12, 24, 36, 48, 60, 120, 180, 240, 360, 720, 840, 1260, 1680, 2520, 5040, 7560, 10080, 15120, 20160, 25200, 27720, 45360, 50400, 55440, 83160, 110880, 166320, 221760, 277200, 332640, 498960, 554400, 665280, 720720, 1081080, 1441440, 2162160, 2882880, 3603600, 4324320, 6486480, 7207200, 8648640};
		TreeSet<Integer> set = new TreeSet<>();
		for(int a : antiPrimes)
			set.add(a);
		
		int K = s1.nextInt();
		out.println(K - set.floor(K) + 1);
		
	}
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "testgen";
	
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
