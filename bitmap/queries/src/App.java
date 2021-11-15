import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static void doiBit(long p, long[] dd) {
        long u = p / 32;
        long v = p & 31;
        dd[(int) u] ^= (1 << v);
    }

    static long getBit(long p, long[] dd) {
        long u = p / 32;
        long v = p & 31;
        return (dd[(int) u] >> v) & 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int Q = Integer.parseInt(lineOne[0]);
        int S1 = Integer.parseInt(lineOne[1]);
        int A = Integer.parseInt(lineOne[2]);
        int B = Integer.parseInt(lineOne[3]);

        long S = S1;

        long[] dd = new long[1 << 26];

        long sum = 0;

        for (int i = 1; i < Q + 1; i++) {
            long p = S / 2;
            if (S % 2 == 1) {
                if (getBit(p, dd) == 0) {
                    sum += p;
                    doiBit(p, dd);
                }
            } else if (getBit(p, dd) == 1) {
                sum -= p;
                doiBit(p, dd);
            }
            // getBit(p, dd)
            S = (A * S + B) % (1l << 32);
        }

        System.out.println(sum);

    }
}
