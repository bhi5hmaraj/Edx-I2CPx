import java.util.*;
import java.io.*;
public class Dijkstra
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Edge implements Comparable<Edge> {
		int v , cost;
		Edge(int vv , int c) {
			v = vv;
			cost = c;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(cost, o.cost);
		}
	}
	
	static ArrayList<Edge>[] adj;
	static int distTo[];
	static boolean marked[];
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge curr = pq.remove();
			if(!marked[curr.v]) {
				marked[curr.v] = true;
				distTo[curr.v] = curr.cost;
				for(Edge e : adj[curr.v])
					if(!marked[e.v])
						pq.add(new Edge(e.v, curr.cost + e.cost));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int V = s1.nextInt();
		int E = s1.nextInt();
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		
		distTo = new int[V + 1];
		marked = new boolean[V + 1];
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			int cost = s1.nextInt();
			adj[u].add(new Edge(v, cost));
			adj[v].add(new Edge(u, cost));
		}
		
		dijkstra();
		
		for(int i=1;i<=V;i++)
			out.print(distTo[i] + " ");
			
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "sparse";
	
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