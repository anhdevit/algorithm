import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class App {

    static long getBit(long p, int i) {
        return (p >> i) & 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");

        int n = Integer.parseInt(nLine[0]);
        long k = Long.parseLong(nLine[1]);

        long[] a = new long[n];

        String[] lineTwo = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(lineTwo[i]);
        }

        int n1 = n / 2;
        int n2 = n - n1;

        HashMap<Long, Integer> mapSum = new HashMap<Long, Integer>();

        for (long mask = 0; mask < (1l << n2); mask++) {
            int count = 0;
            long sum = 0;

            for (int i = n1; i < n; i++) {
                if (getBit(mask, i - n1) == 1) {
                    sum += a[i];
                    count++;
                }
            }
            mapSum.put(sum, Math.max(count, mapSum.getOrDefault(sum, 0)));
        }

        long kq = -1;

        for (long mask = 0; mask < (1l << n1); mask++) {
            int count = 0;
            long sum = 0;

            for (int i = 0; i < n1; i++) {
                if (getBit(mask, i) == 1) {
                    sum += a[i];
                    count++;
                }
            }
            if (mapSum.get(k - sum) != null) {
                kq = Math.max(count + mapSum.get(k - sum), kq);
            }
        }
        System.out.println(kq);

    }
}
