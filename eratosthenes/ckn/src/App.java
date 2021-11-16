import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int T = Integer.parseInt(lineOne[0]);
        for (int i = 0; i < T; i++) {
            String[] lineTwo = br.readLine().split(" ");
            int n = Integer.parseInt(lineTwo[0]);
            int k = Integer.parseInt(lineTwo[1]);
            int[] f = new int[n + 1];
            for (int x = 2; x <= n; x++) {
                if (f[x] == 0) {
                    for (int num = x; num <= n; num += x) {
                        f[num] = x;
                    }
                }
            }

            int[] countA = new int[n + 1];
            int[] countB = new int[n + 1];

            for (int p = n - k + 1; p <= n; p++) {
                int t = p;
                while (t > 1) {
                    countA[f[t]]++;
                    t /= f[t];
                }
            }

            for (int p = 1; p <= k; p++) {
                int t = p;
                while (t > 1) {
                    countB[f[t]]++;
                    t /= f[t];
                }
            }

            long kq = 1;
            for (int j = 1; j <= n; j++) {
                for (int z = 0; z < countA[j] - countB[j]; z++) {
                    kq = (kq * j) % (1000000000 + 7);
                }
            }
            System.out.println(kq);
        }
    }
}
