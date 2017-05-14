import java.util.*;
import java.io.*;
public class FindaCycle
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static int color[]; // 0 - unmarked , 1 - visited but not terminated , 2 - visited and terminated
	static int parent[];
	static int dfs(int u , int par) {
		color[u] = 1;
		parent[u] = par;
		for(int v : adj[u]) {
			if(color[v] == 2)
				continue;
			else if(color[v] == 1) {
				parent[v] = 0;
				return u;
			}
			else {
				int res = dfs(v, u);
				if(res > 0)
					return res;
			}
		}

		color[u] = 2;
		return -1;
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int V = s1.nextInt();
		int E = s1.nextInt();
		adj = new ArrayList[V + 1];
		color = new int[V + 1];
		parent = new int[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		while(E-->0)
			adj[s1.nextInt()].add(s1.nextInt());
		
		for(int i=1;i<=V;i++) {
			if(color[i] == 0) {
				int res = dfs(i, -1);
				if(res > 0) {
					out.println("YES");
					ArrayDeque<Integer> stack = new ArrayDeque<>();
					while(res != 0) {
						stack.push(res);
						res = parent[res];
					}
					for(int a : stack)
						out.print(a + " ");
					return;
				}
			}
		}
		
		out.println("NO");
		
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	static String fileName = "cycle";

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