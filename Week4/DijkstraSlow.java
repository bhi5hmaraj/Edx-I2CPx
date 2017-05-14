import java.util.*;
import java.io.*;
public class DijkstraSlow
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int V;
	static long cost[][];
	static long distTo[];
	static final long INF = (long)(1e15);
	
	static void dijkstra(int start) {
		boolean marked[] = new boolean[V + 1];
		Arrays.fill(distTo, INF);
		distTo[start] = 0;
		
		for(int i=1;i<=V;i++) {
			
			int curr = 0;
			long min = INF;
			for(int j=1;j<=V;j++)
				if(!marked[j] && distTo[j] < min) {
					min = distTo[j];
					curr = j;
				}
			
			if(curr == 0)return;
			
			marked[curr] = true;
			// System.out.println(Arrays.toString(cost[curr]));
			
			for(int j=1;j<=V;j++) 
				if(cost[curr][j] != -1)
					distTo[j] = Math.min(distTo[j],distTo[curr] + cost[curr][j]);
		
			
		}
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		V = s1.nextInt();
		int start = s1.nextInt();
		int end = s1.nextInt();
		
		cost = new long[V + 1][];
		for(int i=1;i<=V;i++)
			cost[i] = s1.nextLongArrayOneBased(V);
		
		distTo = new long[V + 1];
		
		dijkstra(start);
		
		out.println(distTo[end] == INF ? "-1" : distTo[end]);
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "dense";
	
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