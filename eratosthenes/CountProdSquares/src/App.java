import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static long ckn(int k, long n) {
        long kq = (n * (n - 1) * (n - 2)) / 6;
        return kq;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);

        int[] f = new int[n + 1];
        for (int x = 2; x <= n; x++) {
            if (f[x] == 0) {
                for (int num = x; num <= n; num += x) {
                    f[num] = x;
                }
            }
        }

        long[] count = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            int t = i;
            int y = 1;
            while (t > 1) {
                int p = f[t];
                int _count = 0;
                while (t % p == 0) {
                    t /= p;
                    _count++;
                }
                if (_count % 2 == 1) {
                    y *= p;
                }
            }
            count[y]++;
        }

        long kq = 0;

        for (int x = 1; x <= n; x++) {
            kq += ckn(3, count[x]);
        }
        System.out.println(kq);

    }
}
