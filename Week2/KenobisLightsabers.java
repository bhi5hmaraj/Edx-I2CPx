import java.util.*;
import java.io.*;
public class KenobisLightsabers
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static void resize(ArrayDeque<Integer> left , ArrayDeque<Integer> right) {
	
		if(right.size() - left.size() > 1)
			left.addLast(right.removeFirst());
		else if(left.size() - right.size() > 0)
			right.addFirst(left.removeLast());
	
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		ArrayDeque<Integer> left  = new ArrayDeque<>();
		ArrayDeque<Integer> right = new ArrayDeque<>();
		ArrayDeque<Integer> temp;
		
		int N = s1.nextInt();
		while(N-->0) {
			// out.println("left " + left);
			// out.println("right " + right);
			switch(s1.next()) {
			case "add":
				int x = s1.nextInt();
				right.addLast(x);
				break;
			case "take":
				right.removeLast();
				break;
			case "mum!":
				temp = left;
				left = right;
				right = temp;
				break;
			}
			resize(left, right);
		}
		
		out.println(left.size() + right.size());
		for(int a : left) out.print(a + " ");
		for(int a : right) out.print(a + " ");
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "kenobi";
	
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
