import java.util.*;
import java.io.*;
public class WriteaCodeTemplate
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Pair {
		int x , y;
		Pair(int x , int y){
			this.x = x;
			this.y = y;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int C = s1.nextInt();
		int R = s1.nextInt();

		HashMap<Character , Pair> map = new HashMap<>();
		for(int i=0;i<R;i++){
			String line = s1.nextLine();
			for(int j=0;j<C;j++)
				map.put(line.charAt(j), new Pair(i, j));
		}

		s1.nextLine();

		String template = null;
		int minTime = Integer.MAX_VALUE;
		for(int run=0;run < 3;run++) {
			String name = s1.nextLine();
			int time = 0;
			char prev = 0;
			String line = null;
			while(!(line = s1.nextLine()).equals(" ")) {  // !isEmpty() works in the grader
				// System.out.println(line);
				for(int i=0 , len = line.length(); i < len;i++){
					if(prev == 0) {
						prev = line.charAt(0);
						continue;
					}
					Pair p1 = map.get(prev);
					Pair p2 = map.get(line.charAt(i));
					time += Math.max(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
					prev = line.charAt(i);
				}
			}
			
			if(time < minTime) {
				template = name;
				minTime = time;
			}
		}
		out.println(template);
		out.println(minTime);
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	static String fileName = "template";

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
