/*  
25
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
1111111111111111111111111
 */
import java.util.*;
import java.io.*;
public class HamiltonianPaths
{


	/************************ SOLUTION STARTS HERE ***********************/

	static boolean isEdge[][];
	static boolean DP[][];
	static boolean visited[][];
	static int V;
	static ArrayList<String> answer;
	static int pos[];
	static void hamiltonianTour(int mask , int u) {
		if(!DP[mask][u]) {
			DP[mask][u] = true;
			for(int v=1;v <= V;v++)
				if(isEdge[u][v] && (mask & (1 << v)) == 0)  
					hamiltonianTour(mask | (1 << v), v);
		}
	}

	static void backtrack(int mask , int curr , int end , int len) {

		pos[len] = curr;
		if(len == 1 && !visited[curr][end]) {
			visited[curr][end] = true;
			StringBuilder sb = new StringBuilder();
			for(int i=1;i<=V;i++)
				sb.append(pos[i] + (i == V ? "" : " -> "));
			answer.add(sb.toString());
		}
		else {
			for(int i=1;i<=V;i++)
				if(isEdge[i][curr] && ((mask & (1 << i)) != 0) && DP[mask ^ (1 << curr)][i])
					backtrack(mask ^ (1 << curr), i, end, len - 1);
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		isEdge  = new boolean[V + 1][V + 1];
		visited = new boolean[V + 1][V + 1];
		answer  = new ArrayList<>();
		pos 	= new int[V + 1];
		int visitAll = (1 << (V + 1)) - 2;

		for(int i=1;i<=V;i++) {
			String line = s1.nextLine();
			for(int j=0;j<V;j++)
				isEdge[i][j + 1] = line.charAt(j) == '1';
			isEdge[i][i] = false;
		}

		long start = System.nanoTime();
		for(int i=1;i<=V;i++) {
			DP      = new boolean[1 << (V + 1)][V + 1];
			hamiltonianTour(1 << i, i);
			for(int j=1;j<=V;j++)
				backtrack(visitAll, j, j, V);
		}
		long stop = System.nanoTime();
		System.err.println("Time taken : " + ((stop - start) / 1e9));



		if(answer.isEmpty())
			out.println("No paths");
		else
			for(String s : answer)
				out.println(s);


	}

	// Mask will be from 1 but dp[mask] will be from 0

	static void hamiltonianTour2(int mask , int u , int graph[] , int dp[]) {
		if((dp[mask] & (1 << u)) == 0) {
			dp[mask] |= (1 << u);
			for(int req = graph[u] ^ (graph[u] & ((mask << 1) | 1));req > 0;) {
				int v = Integer.numberOfTrailingZeros(req);
				hamiltonianTour2(mask | (1 << (v - 1)), v, graph , dp);
				req ^= (1 << v);
			}
		}
	}
	
	static void hamiltonianTour3(int graph[] , int dp[]) {
		
		dp[0] = 1;
		for(int mask = 1; mask < dp.length;mask++) {
			int curr = mask;
			while(curr > 0) {
				int u = Integer.numberOfTrailingZeros(curr);
				if((dp[mask ^ (1 << u)] & graph[u + 1]) != 0)
					dp[mask] |= (1 << (u + 1));
				
				curr ^= (1 << u);
			}
		}
		
	}


	private static void solve2(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		int[] graph = new int[V];
		int[] invGraph = new int[V];
		for(int i=0;i<V;i++) {
			String line = s1.nextLine();
			for(int j=0;j<V;j++) {
				graph[i] = (line.charAt(j) == '1' && j != i) ? graph[i] | (1 << j) : graph[i];
				invGraph[j] = (line.charAt(j) == '1' && j != i) ? invGraph[j] | (1 << i) : invGraph[j];
			}
		}
		
		//long start = System.nanoTime();

		int dp1[] = new int[1 << (V - 1)];
		int dp2[] = new int[1 << (V - 1)];
		
		//hamiltonianTour2(0, 0, graph, dp1);
		//hamiltonianTour2(0, 0, invGraph, dp2);
		
		hamiltonianTour3(invGraph, dp1);
		hamiltonianTour3(graph, dp2);

		int U = (1 << (V - 1)) - 1;
		int all = ((1 << V) - 1);
		int visited[] = new int[V];
		int visitInv[] = new int[V];
		boolean isPath = false;
		
		for(int subset = 0;subset < dp1.length;subset++) {
			if(dp1[subset] != 0) {
				int remain = (U ^ subset);
				if(dp2[remain] != 0) {
					//System.out.println("SET 1 " + Integer.toBinaryString(subset));
					//System.out.println("SET 2 " + Integer.toBinaryString(remain));
					int uSubset = dp1[subset];
					while(uSubset > 0) {
						int u = Integer.numberOfTrailingZeros(uSubset);
						if(visitInv[u] == (all ^ (1 << u))) {
							//System.out.println("continued");
							uSubset ^= (1 << u);
							continue;
						}
						StringBuilder pathU = null;
						//String pathU = null;
						int cache = dp2[remain];
						int vSubset = cache ^ (cache & visitInv[u]);
						while(vSubset > 0) {
							int v = Integer.numberOfTrailingZeros(vSubset);
							if(((visited[v] & (1 << u)) == 0)) {
								isPath = true;
								visited[v] |= 1 << u;
								visitInv[u] |= 1 << v;
								//System.out.println("From " + (v + 1) + " To " + (u + 1));
								if(pathU == null) {
									int mask = subset;
									int curr = u;
									pathU = new StringBuilder();
									// pathU = "";
									while(mask != 0) {
										//System.out.println("curr " + curr);
										pathU.append(new StringBuilder(" -> " + (curr + 1)).reverse());
										// pathU = " -> " + (curr + 1) + pathU;
										//out.println("pathU " + pathU);
										int prev = mask ^ (1 << (curr - 1));
										curr = Integer.numberOfTrailingZeros(dp1[prev] & invGraph[curr]);
										mask = prev;
									}
									pathU.reverse();
								}

								StringBuilder path = new StringBuilder();
								int mask = remain;
								int curr = v;
								while(curr != 0) {
									path.append((curr + 1) + " -> ");
									int prev = mask ^ (1 << (curr - 1));
									curr = Integer.numberOfTrailingZeros(dp2[prev] & graph[curr]);
									mask = prev;
								}
								path.append("1").append(pathU);
								out.println(path);
							}
							vSubset ^= (1 << v);
						}
						uSubset ^= (1 << u);
					}
				}
			}
		}
		
		if(!isPath)
			out.println("No paths");

		//long stop = System.nanoTime();
		

		//out.println("\nTime elapsed for total alone : " + ((stop - start) / 1e9));
	}

	/************************ SOLUTION ENDS HERE ************************/


	/*
16
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
1111111111111111
	 */


	/************************ TEMPLATE STARTS HERE *********************/

	static String fileName = "hamiltonian";

	public static void main(String []args) throws IOException {
		InputStream  inputStream  = (System.getProperty("JUDGE") != null) ? (new FileInputStream(fileName  + ".in"))  : System.in;
		OutputStream outputStream = (System.getProperty("JUDGE") != null) ? (new FileOutputStream(fileName + ".out")) : System.out;
		FastScanner in  = new FastScanner(inputStream);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), false); 
		solve2(in, out);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}