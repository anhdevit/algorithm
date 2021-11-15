import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

    static boolean check(double k, int n, int[] a, int L) {
        double[] f = new double[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Math.min(f[i - 1] + k, a[i + 1]);
            if (f[i] < a[i]) {
                return false;
            }
        }
        return f[n] == L;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int L = Integer.parseInt(lineOne[1]);
        int[] a = new int[n + 2];

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(lineTwo[i]);
        }

        a[n + 1] = L;

        Arrays.sort(a);

        double l = 0;
        double r = L;

        while (r - l > 1e-6) {
            double mid = (l + r) / 2;
            if (check(mid, n, a, L)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        System.out.println(r);

    }
}
