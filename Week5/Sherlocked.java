import java.util.*;
import java.io.*;
public class Sherlocked // Could have implemented bucket sort
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int R , C;
	static int DP[],grid[];

	static int x[] = {-1 , 0 , 1 , 0}; //up , right , down and left 
	static int y[] = {0 , 1 , 0 , -1};

	static boolean isValid(int i , int j) {
		return i >= 0 && i < R && j >= 0 && j < C;
	}

	static int rec(int i , int j) {
		if(DP[(i * C) + j] != -1)
			return DP[(i * C) + j];
		else {
			int max = 0;
			for(int k=0;k < 4;k++)
				if(isValid(i+x[k], j+y[k]) && grid[((i+x[k]) * C) + (j+y[k])] == grid[(i * C) + j] + 1)
					max = Math.max(max,rec(i+x[k], j+y[k]));

			return (DP[(i * C) + j] = max + 1);
		}
	}

	static int recMod(int idx) {
		if(DP[idx] != -1)
			return DP[idx];
		else {
			int max = 0;
			int cache = grid[idx];

			if(idx - C >= 0 && cache + 1== grid[idx - C])
				max = Math.max(max,recMod(idx - C));
			if(idx + C < (R*C) && cache + 1== grid[idx + C] )
				max = Math.max(max,recMod(idx + C));
			if(idx % C != 0 && cache + 1== grid[idx - 1] )
				max = Math.max(max,recMod(idx - 1));
			if(idx % C != C - 1 && cache + 1 == grid[idx + 1])
				max = Math.max(max,recMod(idx + 1));

			DP[idx] = max + 1;
			return max + 1;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		R = s1.nextInt();
		C = s1.nextInt();
		grid = s1.nextIntArray(R * C);
		DP = new int[R * C];
		Arrays.fill(DP, -1);

		int max = 0;
		for(int i=0;i<grid.length;i++)
			max = Math.max(max,recMod(i));

		out.println(max);

	}

	static class Pair {
		int idx , key;
		Pair(int idx , int key){
			this.idx = idx;
			this.key = key;
		}
	}
	static int global_MAX = 0;
	private static void solve2(FastScanner s1, PrintWriter out){

		R = s1.nextInt();
		C = s1.nextInt();
		grid = s1.nextIntArray(R * C);
		DP = new int[R * C];
		
		final int MAX = (int)(1e7);

		Pair arr[] = new Pair[R * C];
		
		int freq[] = new int[MAX + 1];
		for(int i=0;i<arr.length;i++)
			freq[grid[i]]++;
		
		for(int i=1;i<=MAX;i++)
			freq[i] += freq[i - 1];
		
		for(int i=arr.length-1;i >= 0;i--) {
			arr[freq[grid[i]] - 1] = new Pair(i, grid[i]);
			freq[grid[i]]--;
		}
		
		for(int i=0;i<arr.length;i++) {
			int max = 0;
			int idx = arr[i].idx;
			if(idx - C >= 0 && grid[idx] == grid[idx - C] + 1)
				max = Math.max(max,DP[idx - C]);
			if(idx + C < arr.length && grid[idx] == grid[idx + C] + 1)
				max = Math.max(max,DP[idx + C]);
			if(idx % C != 0 && grid[idx] == grid[idx - 1] + 1)
				max = Math.max(max,DP[idx - 1]);
			if(idx % C != C - 1 && grid[idx] == grid[idx + 1] + 1)
				max = Math.max(max,DP[idx + 1]);

			DP[idx] = max + 1;
			global_MAX = Math.max(global_MAX,max + 1);
		}

		out.println(global_MAX);
	}


	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	static String fileName = "sherlocked";
	/*
	public static void main(String []args) throws IOException {
		InputStream  inputStream  = (System.getProperty("JUDGE") != null) ? (new FileInputStream(fileName  + ".in"))  : System.in;
		OutputStream outputStream = (System.getProperty("JUDGE") != null) ? (new FileOutputStream(fileName + ".out")) : System.out;
		FastScanner in  = new FastScanner(inputStream);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    */
	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new Sherlocked().run();
			}
		}, "Increase Stack", 1 << 25).start();

	}

	void run() {	
		try {
			InputStream  inputStream  = (System.getProperty("JUDGE") != null) ? (new FileInputStream(fileName  + ".in"))  : System.in;
			OutputStream outputStream = (System.getProperty("JUDGE") != null) ? (new FileOutputStream(fileName + ".out")) : System.out;
			FastScanner in  = new FastScanner(inputStream);
			PrintWriter out = 
					new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), false); 
			solve2(in, out);
			in.close();
			out.close();
		}
		catch(Exception e){
			
		}
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