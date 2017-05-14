import java.util.*;
import java.io.*;
public class FootballBroadcasting
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int C , R;
	static int xx[] = {-1 , 0 , 1 , 0}; //up , right , down and left 
	static int yy[] = {0 , 1 , 0 , -1};
	static boolean grid[][][]; 
	static int moves[][];
	static boolean isValid(int i , int j) {
		return i >= 1 && i <= R && j >= 1 && j <= C;
	}
	
	static boolean dfs(int idx , int x , int y) {
		
		moves[idx][0] = x;
		moves[idx][1] = y;
		
		if(idx == 0)
			return true;
		else if(!grid[idx][x][y]) {
			grid[idx][x][y] = true;
			for(int i=0;i<4;i++)
				if(isValid(x+xx[i], y+yy[i]) && dfs(idx - 1, x+xx[i], y+yy[i]))
					return true;
			
			return false;
		}
		else
			return false;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		R = s1.nextInt();
		C = s1.nextInt();
		int N = s1.nextInt();
		grid = new boolean[N + 1][R + 1][C + 1];
		moves = new int[N + 1][2];
		for(int i=1;i<=N;i++) {
			
			int x1 = s1.nextInt();
			int y1 = s1.nextInt();
			int x2 = s1.nextInt();
			int y2 = s1.nextInt();
			
			for(int j=x1;j<=x2;j++)
				for(int k=y1;k<=y2;k++)
					grid[i][j][k] = true;
			
		}
		for(int i=1;i<=R;i++) 
			for(int j=1;j<=C;j++) {
				if(dfs(N, i, j)) {
					for(int k=1;k<=N;k++)
						out.println(moves[k][0] + " " + moves[k][1]);
					
					return;
				}
			}
			
		out.println("Impossible");
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "broadcast";
	
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