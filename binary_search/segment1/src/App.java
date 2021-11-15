import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static int A(int x, int[] L) {
        int l = -1;
        int r = L.length;
        while (r - l > 1) {
            int mid = (r + l) / 2;
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
            int mid = (r + l) / 2;
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
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int m = Integer.parseInt(lineOne[1]);

        int[] L = new int[n];
        int[] R = new int[n];

        for (int i = 0; i < n; i++) {
            String[] lineN = br.readLine().split(" ");
            int a1 = Integer.parseInt(lineN[0]);
            int b1 = Integer.parseInt(lineN[1]);
            int swap = a1;
            if (a1 > b1) {
                b1 = a1;
                b1 = swap;
            }
            L[i] = a1;
            R[i] = b1;
        }

        String[] lineM = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            int kq = A(Integer.parseInt(lineM[i]), L) - B(Integer.parseInt(lineM[i]), R);
            System.out.print(kq + " ");
        }

    }
}
