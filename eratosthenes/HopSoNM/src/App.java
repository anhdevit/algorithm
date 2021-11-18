import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static long f(Double n, int p) {
        return (long) (n / p);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        String[] lineTwo = br.readLine().split(" ");

        int n = Integer.parseInt(lineOne[0]);
        int m = Integer.parseInt(lineTwo[0]);

        int c = 1000000 + 1;

        int[] f = new int[c + 1];
        for (int x = 2; x <= c; x++) {
            if (f[x] == 0) {
                for (int num = x; num <= c; num += x) {
                    f[num] = x;
                }
            }
        }

        int[] dd = new int[c];

        for (int i = 2; i < c; i++) {
            if (dd[i] != 1) {
                for (int j = 2 * i; j < c; j += i) {
                    dd[j] = 1;
                }
            }
        }

        long kq = 0;

        dd[1] = 1;

        for (int p = (int) Math.pow(10, m - 1); p <= Math.pow(10, m) - 1; p++) {
            if (dd[p] == 0) {
                int S = p % 10;
                kq += f((S + 1) * Math.pow(10, n - m - 1) - 1, p) - f((S) * Math.pow(10, n - m - 1) - 1, p);
            }
        }
        System.out.println(kq);
    }
}
