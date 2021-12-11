import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int m = Integer.parseInt(lineOne[1]);
        int[] A = new int[n + 1];
        long[] sum = new long[n + 1];

        for (int i = 0; i < m; i++) {
            String[] lineM = br.readLine().split(" ");
            int l = Integer.parseInt(lineM[0]);
            int r = Integer.parseInt(lineM[1]);
            int v = Integer.parseInt(lineM[2]);
            A[l] += v;
            if (r < n) {
                A[r + 1] -= v;
            }
        }

        for (int i = 1; i <= n; i++) {
            sum[i] = A[i] + sum[i - 1];
            System.out.print(sum[i] + " ");
        }
    }
}
