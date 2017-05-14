import java.util.*;
import java.io.*;
public class Inversions
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int aux[] , arr[] , N;
	static long merge(int L , int M , int R ) {
		long inv = 0;
		System.arraycopy(arr, L, aux, L, R - L);
		int k = L;
		for(int i=L,j=M;i<M || j<R;) {
			if(i < M && j < R && aux[i] > aux[j])
				inv += (M - i);
			if(i < M && (j >= R || aux[i] <= aux[j]))
				arr[k++] = aux[i++];
			else if(j < R && (i >= M || aux[j] < aux[i]))
				arr[k++] = aux[j++];
		}
		return inv;
	}
	
	static long sort(int L , int R) { // left inclusive , right exclusive
		if(L + 1 < R) {
			int M = (L + R) / 2;
			return (sort(L, M) + sort(M, R) + merge(L, M, R));
		}
		else
			return 0;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		arr = s1.nextIntArray(N);
		aux = new int[N];
		
		out.println(sort(0, N));
			
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "inversions";
	
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