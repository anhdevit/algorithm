import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class App {

    static long check(long h, int[] a) {
        long s = 0;
        for (int i = 0; i < a.length; i++) {
            s += Math.max(0, a[i] - h);
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");

        int n = Integer.parseInt(lineOne[0]);
        int m = Integer.parseInt(lineOne[1]);

        int[] N = new int[n];

        String[] lineTwo = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(lineTwo[i]);
        }

        Arrays.sort(N);

        long l = 0;
        long r = N[n - 1];

        while (r - l > 1) {
            long mid = (l + r) / 2;
            if (check(mid, N) >= m) {
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.println(l);
    }
}
