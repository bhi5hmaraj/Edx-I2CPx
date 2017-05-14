import java.util.*;
import java.io.*;
public class BellmanFord
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final long INF = (long)3e18;
	static long distTo[];
	
	static class Edge {
		int v;
		long cost;
		Edge(int vv, long wt) {
			v = vv;
			cost = wt;
		}
	}
	static ArrayList<Edge>[] adj;
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int V = s1.nextInt();
		int E = s1.nextInt();
		int start = s1.nextInt();
		
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		
		distTo = new long[V + 1];
		Arrays.fill(distTo, INF);
		
		while(E-->0)
			adj[s1.nextInt()].add(new Edge(s1.nextInt(), s1.nextLong()));
		
		distTo[start] = 0;
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		boolean cycleMarked[] = new boolean[V + 1];
		
		for(int i=1;i<=V-1;i++) 
			for(int u=1;u<=V;u++)
				for(Edge e : adj[u])
					distTo[e.v] = Math.abs(distTo[u]) == INF ? distTo[e.v] :Math.max(-INF,Math.min(distTo[e.v],distTo[u] + e.cost));
		
		
		for(int u=1;u<=V;u++)
			for(Edge e : adj[u])
				if(distTo[u] != INF && distTo[u] + e.cost < distTo[e.v] && !cycleMarked[u]) {
					cycleMarked[u] = true;
					queue.add(u);
				}
		
		while(!queue.isEmpty()) {
			int u = queue.remove();
			for(Edge e : adj[u])
				if(!cycleMarked[e.v]) {
					cycleMarked[e.v] = true;
					queue.add(e.v);
				}
		}
		
		for(int i = 1;i<=V;i++) {
			if(distTo[i] == INF)
				out.println("*");
			else if(cycleMarked[i])
				out.println("-");
			else
				out.println(distTo[i]);
		}
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "path";
	
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