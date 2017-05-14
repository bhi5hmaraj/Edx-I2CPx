import java.util.*;
import java.io.*;
public class Drying
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int arr[] , N , K;
	
	static boolean possible(int time) {
		
		long req = 0;
		for(int i=0;i<N;i++) 
			if(K > 1 && arr[i] > time) 
				req += ((arr[i] - time) / (K - 1)) + (((arr[i] - time) % (K - 1)) != 0 ? 1 : 0);
		
		// System.out.println("req " + req);
		
		return (req <= time);
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N   = s1.nextInt();
		arr = s1.nextIntArray(N);
		K   = s1.nextInt();
		
		if(K == 1) {
			// Special Case
			int max = 0;
			for(int a : arr)
				max = Math.max(max,a);
			out.println(max);
			return;
		}
		
		int L = 1 , R = (int)1e9 , M;
		int opt = -1;
		while(L <= R) {
			M = L + ((R - L) / 2);
			// System.out.println("M " + M + " res " + possible(M));
			if(possible(M)) {
				R = M - 1;
				opt = M;
			}
			else
				L = M + 1;
		}
		
		out.println(opt);
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "drying";
	
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