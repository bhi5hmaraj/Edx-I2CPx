import java.util.*;
import java.io.*;
public class Moscow2042
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Pair {
		int r , c;
		double dist;
		Pair(int r , int c , double dist){
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int hashCode() {
			return Objects.hash(r , c);
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair)obj;
			return p.r == r && p.c ==c;
		}
	}
	
	static class Circle {
		int radius , dir;
		Circle(int radius , int dir){
			this.radius = radius;
			this.dir = dir;
		}
	}

	static Circle circle[];
	static int roads[] ;
	
	double dijkstra(Pair start , Pair end) {
		
		HashSet<Pair> visited = new HashSet<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(start.r, start.c, 0));
		
		while(!pq.isEmpty()) {
			
			Pair curr = pq.remove();
			
			
		}
		
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		
			
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "";
	
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