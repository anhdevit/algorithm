import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static long getBit(long p, int i) {
        return (p >> i) & 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");

        int a = Integer.parseInt(lineOne[0]);
        int b = Integer.parseInt(lineOne[1]);

        long m = 1000000000 + 7;

        long[] x = new long[64];
        x[0] = a % m; // a^(2^0) % m

        for (int i = 1; i < 64; i++) {
            // if (getBit(b, i) == 1) {
            // if (kq == 0) {
            // kq = (long) (Math.pow(Math.pow(a, 2), i) % m);
            // } else {
            // kq *= (long) (Math.pow(Math.pow(a, 2), i) % m);
            // }
            // }

            x[i] = Long.parseUnsignedLong(x[i - 1] * x[i - 1] % m); // a^(2^i) %m = (a^(2^(i-1))%m)*(a^(2^(i-1))%m)%m
        }

        long kq = 1;
        for (int i = 0; i < 64; i++) {
            if (getBit(b, i) == 1) {
                kq = kq * x[i] % m;
            }
        }
        System.out.println(kq);
    }
}
