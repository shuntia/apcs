import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MiscFunStuff {
	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;
		public Reader()
		{
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		public Reader(String file_name) throws IOException
		{
			din = new DataInputStream(
				new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					}
					else {
						continue;
					}
				}
				buf[cnt++] = (byte)c;
			}
			return new String(buf, 0, cnt-1);
		}
		public int nextInt() throws IOException
		{
			int ret = 0;
			byte c = read(); 
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}
		public long nextLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}
		public double nextDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}
			if (neg)
				return -ret;
			return ret;
		}
		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0,
								BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}
		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
		}
	}


	public static void main(String[] args) throws IOException{
		System.out.println("Welcome! Enter your choice.");
		System.out.print("(1) Convert in. to cm. (2) Even or Odd  (3) Advice: ");
		Reader in=new Reader();
		switch (in.nextInt()){
			case 1->convert();
			case 2->evenOdd();
            case 3->randAdvice();
            default->throw new IllegalArgumentException("Invalid input!");
		}
	}
	
	public static void convert() throws IOException{
		System.out.print("Enter your height in inches: ");
		System.out.println("Your height in cm is " + inchesToCm(new Reader().nextInt()));
	}
	
	static double inchesToCm(double x) {
		return x*2.54;
	}
	
	public static void evenOdd() throws IOException{
		System.out.print("Give me an integer: ");
		double number=new Reader().nextDouble();
		if(number%2==0.0) 
			System.out.println("It's even!");
		else if(number%2==1.0)
			System.out.println("It's odd!");
		else
			System.out.println("It's neither...enter an integer!");
	}
	
	public static void randAdvice() { // practice debugging with System.out!
		int rand = (int)(Math.random()*3); // ** generate a rand int from 1 to 3
		String[] advices=new String[]{"try harder", "keep going!","deep breaths"};
		System.out.println(advices[rand]);		
	}
}

	