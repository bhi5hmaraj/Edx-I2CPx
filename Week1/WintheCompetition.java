import java.util.*;
import java.io.*;
public class WintheCompetition
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int t[];
	static int memo[][];
	static int findOpt(int idx , int time) {
		if(idx == 0)
			return time >= t[idx] ? 1 : 0;
		else {
			
			if(memo[idx][time] != -1)
				return memo[idx][time];
			
			int optimum;
			if(time >= t[idx])
				optimum = Math.max(1 + findOpt(idx - 1, time - t[idx]),findOpt(idx - 1, time));
			else
				optimum = findOpt(idx - 1, time);
			
			return memo[idx][time] = optimum;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		t = s1.nextIntArray(N);
		int MAX_TIME = 300 * 60;
		memo = new int[N][MAX_TIME + 1];
		for(int i=0;i<N;i++)
			Arrays.fill(memo[i], -1);
		
		out.println(findOpt(N - 1, MAX_TIME));

	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	static String fileName = "win";

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