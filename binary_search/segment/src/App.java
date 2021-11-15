import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

    static int A(int x, int[] L) {
        int l = -1;
        int r = L.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (L[mid] > x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    static int B(int x, int[] R) {
        int l = -1;
        int r = R.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (R[mid] >= x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        int m = Integer.parseInt(nLine[1]);

        int[] L = new int[n];
        int[] R = new int[n];

        for (int i = 0; i < n; i++) {
            String[] niLine = br.readLine().split(" ");
            L[i] = Integer.parseInt(niLine[0]);
            R[i] = Integer.parseInt(niLine[1]);

            if (L[i] > R[i]) {
                int swap = R[i];
                R[i] = L[i];
                L[i] = swap;
            }

        }
        Arrays.sort(L);

        Arrays.sort(R);

        String[] miLine = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            int a1 = A(Integer.parseInt(miLine[i]), L);
            int b1 = B(Integer.parseInt(miLine[i]), R);
            int kq = a1 - b1;
            System.out.print(kq + " ");
        }

    }
}
