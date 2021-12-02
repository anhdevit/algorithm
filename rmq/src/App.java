import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int[] N = new int[n + 1];

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            N[i] = Integer.parseInt(lineTwo[i - 1]);
        }
        String[] lineThree = br.readLine().split(" ");
        int m = Integer.parseInt(lineThree[0]);

        int[][] K = new int[m + 1][2];

        for (int i = 1; i <= m; i++) {
            String[] lineK = br.readLine().split(" ");
            K[i][0] = Integer.parseInt(lineK[0]);
            K[i][1] = Integer.parseInt(lineK[1]);
        }

        int[][] st = new int[n + 1][(int) (Math.log(n) / Math.log(2) + 1)];
        for (int i = 1; i <= n; i++) {
            st[i][0] = N[i];
        }

        for (int j = 1; j <= Math.log(n) / Math.log(2); j++) {
            for (int i = 1; i + (1 << j - 1) <= n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }

        int kq = 0;

        for (int i = 1; i <= m; i++) {
            int l = K[i][0] + 1;
            int r = K[i][1] + 1;

            int k = (int) (Math.log(r - l + 1) / Math.log(2));

            kq += Math.min(st[l][k], st[r - (1 << k) + 1][k]);
        }

        System.out.println(kq);
    }
}
