import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

    static long check(int h, int[] a) {
        long s = 0;
        for (int i = 0; i < a.length; i++) {
            s += Math.max(0, a[i] - h);
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] oneLine = br.readLine().split(" ");
        int n = Integer.parseInt(oneLine[0]);
        int m = Integer.parseInt(oneLine[1]);

        int[] N = new int[n];
        String[] secondLine = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(secondLine[i]);
        }

        Arrays.sort(N);

        int l = 0;
        int r = N[n - 1];

        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (check(mid, N) >= m) {
                l = mid;
            } else {
                r = mid;
            }
        }

        System.out.println(l);

    }
}
