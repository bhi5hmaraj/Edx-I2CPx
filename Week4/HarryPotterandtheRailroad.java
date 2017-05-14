import java.util.*;
import java.io.*;
public class HarryPotterandtheRailroad
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Edge {
		int u ,  v , num , index;
		Edge(int index , int u , int v) {
			this.index = index;
			this.u = u;
			this.v = v;
			num = 0;
		}
		int other(int x) {
			return x == u ? v : u;
		}
	}
	
	static ArrayList<Edge>[] adj;
	static int curr = 1;
	static boolean marked[];
	
	static void dfs(int u) {
		marked[u] = true;
		for(Edge e : adj[u]) {
			if(e.num == 0)
				e.num = curr++;
			
			if(!marked[e.other(u)])
				dfs(e.other(u));
		}
	}
	
	static int gcd(int a , int b) {
		int big = Math.max(a,b);
		int small = Math.min(a,b);
		return small == 0 ? big : gcd(small, big % small);
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int V = s1.nextInt();
		int E = s1.nextInt();
		
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		
		marked = new boolean[V + 1];
		
		for(int i=0;i<E;i++) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			Edge edge = new Edge(i ,u, v);
			adj[u].add(edge);
			adj[v].add(edge);
		}
		
		int start = 1;
		for(int i=1;i<=V;i++)
			if(adj[i].size() == 1) {
				start = i;
				break;
			}

		dfs(start);
		
		boolean flag = true;
		
		for(int i=1;i<=V;i++) {
			int gcd = 0;
			for(Edge e : adj[i])
				gcd = gcd(gcd, e.num);
			
			flag &= gcd == 1;
		}
		
		if(!flag)
			out.println("IMPOSSIBLE");
		else {
			int ans[] = new int[E];
			for(int i=1;i<=V;i++)
				for(Edge e : adj[i])
					ans[e.index] = e.num;
			
			for(int a : ans)
				out.println(a);
		}
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "railroad";
	
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