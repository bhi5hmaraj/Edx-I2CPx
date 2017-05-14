import java.util.*;
import java.io.*;
public class CreateaTeam
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static double max = Double.MIN_VALUE;
	static int wt[][];
	static boolean marked[];
	static int pos[];
	
	static void findOpt(int idx) {
		if(idx >= 3) {
			double eff = Math.sqrt(Math.pow(wt[0][pos[0]], 2) + Math.pow(wt[1][pos[1]], 2) + Math.pow(wt[2][pos[2]], 2));
			max = Math.max(max,eff);
		}
		else {
			for(int i=0;i<3;i++) {
				if(!marked[i]) {
					marked[i] = true;
					pos[idx] = i;
					findOpt(idx + 1);
					marked[i] = false;
				}
			}
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		wt = new int[3][3];
		marked = new boolean[3];
		pos = new int[3];
		
		for(int i=0;i<3;i++)
			wt[i] = s1.nextIntArray(3);
		
		findOpt(0);
		out.println(max);
			
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "team";
	
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