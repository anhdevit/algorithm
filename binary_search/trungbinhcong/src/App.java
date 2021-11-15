import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

    static boolean check(double[] prefix, int k, int n, double[] prefixMin) {
        for (int i = k; i <= n; i++) {
            if (prefix[i] - prefixMin[i - k + 1] >= 0) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int k = Integer.parseInt(lineOne[1]);
        int[] a = new int[n];

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(lineTwo[i]);
        }

        int[] _a = new int[n];
        for (int i = 0; i < n; i++) {
            _a[i] = a[i];
        }
        Arrays.sort(_a);

        double l = _a[0];
        double r = _a[n - 1];

        while (r - l > 1e-6) {
            double mid = (l + r) / 2;
            double[] a2 = new double[n];
            for (int i = 0; i < n; i++) {
                a2[i] = a[i] - mid;
            }
            double[] prefix = new double[n + 1];
            double[] prefixMin = new double[n + 1];
            double minSum = Double.POSITIVE_INFINITY;
            for (int i = 1; i <= n; i++) {
                prefix[i] = prefix[i - 1] + a2[i - 1];
                prefixMin[i] = Math.min(prefix[i - 1], minSum);
                minSum = prefixMin[i];
            }

            if (check(prefix, k, n, prefixMin)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        System.out.println(l);

    }
}