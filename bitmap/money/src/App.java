import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static long getBit(long p, int i) {
        return (p >> i) & 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");

        int n = Integer.parseInt(nLine[0]);
        int k = Integer.parseInt(nLine[1]);

        int[] a = new int[n];

        String[] lineTwo = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(lineTwo[i]);
        }

        int lastCount = 0;

        int count = 0;
        long sum = 0;
        for (int mask = 0; mask < (1 << n) - 1; mask++) {
            for (int i = 0; i < n - 1; i++) {
                if (getBit(mask, i) == 1) {
                    sum += a[i];
                    count++;
                }
            }
            if (sum == k) {
                if (count > lastCount) {
                    lastCount = count;
                }
            }
        }

        if (lastCount > 0) {
            System.out.println(lastCount);
            return;
        }
        System.out.println(-1);

    }
}
