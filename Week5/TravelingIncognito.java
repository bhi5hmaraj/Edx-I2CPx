import java.util.*;
import java.io.*;
public class TravelingIncognito
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Edge>[] adj;
	static int V;
	static class Edge implements Comparable<Edge> {
		int v;
		long cost;

		Edge(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	static long[] dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		long distTo[] = new long[V + 1];
		boolean marked[] = new boolean[V + 1];
		
		while (!pq.isEmpty()) {
			Edge min = pq.remove();
			int u = min.v;
			if(!marked[u]){
				marked[u] = true;
				distTo[u] = min.cost;
				for (Edge e : adj[u])
					if (!marked[e.v])
						pq.add(new Edge(e.v, e.cost + distTo[u]));
			}
		}
		
		return distTo;
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){ // 100% wrong (misinterpreted the problem)
		
		V = s1.nextInt();
		int E = s1.nextInt();
		int S = s1.nextInt();
		int T = s1.nextInt();
		
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			long wt = s1.nextLong();
			adj[u].add(new Edge(v, wt));
			adj[v].add(new Edge(u, wt));
		}
		
		long A = s1.nextLong();
		long B = s1.nextLong();
		
		long dist1[] = dijkstra(S);
		long dist2[] = dijkstra(T);
		
		long min = Long.MAX_VALUE;
		
		for(int i=1;i<=V;i++) {
			if(dist1[i] <= A && dist2[i] >= B)
				min = Math.min(min,dist1[i] + dist2[i]);
		}
			
		out.println(min == Long.MAX_VALUE ? -1 : min);
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){
		
		
		
	}
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "travel";
	
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