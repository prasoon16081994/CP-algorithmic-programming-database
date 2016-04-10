package CODECHEF.LONG_CHALLENGE.FEB15.src;import java.io.*;
import java.util.InputMismatchException;

/**
 * Created by Shreyans on 6/2/15 at 11:47 PM using IntelliJ IDEA (Fast IO Template)
 */

class STFM
{
    public static void main(String[] args) throws Exception
    {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n=in.readInt();
        long m=in.readLong();
        long[] b=new long[n];
        long mf=0;
        for(int i=0;i<n;i++)
        {
            b[i]=in.readLong();
        }
        long ans=0;
        long cntm=0;
        for(int j=0;j<n;j++)
        {
            ans+=((b[j]*(b[j]*(b[j]+1)/2))%m);
            if(b[j]<=m)
            {
                ans+=FACT(b[j],m);
                //ans=ans.mod(m);
            }
            else
            {
                if(cntm==0)
                {
                    mf=FACT(m,m);
                    cntm++;
                }
                ans+=mf;
                //ans=ans.mod(m);
            }
            //System.out.println(ans);
            //ans=ans.mod(new BigInteger(Integer.toString(m)));
        }
        out.printLine(ans);
        {
            out.close();
        }
    }
    private static long FACT(long n,long m)
    {
        long c2=n;
        long ans=0;
        long i=1;
        long bi=1;
        while (i<=c2)
        {
            bi=bi*i;
            i++;
        }
        i--;
        ans+=((bi*i));
        //ans=ans.mod(m);
        //c2=c2.subtract(BigInteger.ONE);
        while(c2>0)
        {
            bi=bi/c2;
            i--;
            ans+=((bi*i));
            //ans=ans.mod(m);
            //ans=ans.mod(m);
            c2--;
        }
        //System.out.println("ans="+ans);
        return(ans%m);
    }

    //FAST IO
    private static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public double readDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public long readLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter
    {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream)
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer)
        {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects)
        {
            for (int i = 0; i < objects.length; i++)
            {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects)
        {
            print(objects);
            writer.println();
        }

        public void close()
        {
            writer.close();
        }

        public void flush()
        {
            writer.flush();
        }
    }
}