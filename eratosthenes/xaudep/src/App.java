import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static int bs(int[] f, int[] p, int tix, int numberOne) {
        int l = 0;
        int r = numberOne + 1;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (f[mid] >= tix) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (r == numberOne + 1) {
            return -1;
        }
        return p[r];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);

        int[] N = new int[n];
        String[] lineTwo = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(lineTwo[i]);
        }

        int[] T = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (N[i - 1] == 0) {
                T[i] = T[i - 1] + 1;
            } else {
                T[i] = T[i - 1];
            }
        }

        int[] p = new int[n + 1];
        int[] f = new int[n + 1];

        int numberOne = 0;

        for (int j = 0; j < n; j++) {
            if (N[j] == 1) {
                numberOne++;
                p[numberOne] = j + 1;
                f[numberOne] = T[p[numberOne]];
            }
        }

        long kq = numberOne;

        for (int x = 1; x <= (n - numberOne) / 2; x++) {
            int i = 0;
            int count = 0;
            while (true) {
                int j = bs(f, p, T[i] + x, numberOne);
                if (j >= 0) {
                    i = j;
                    count++;
                } else {
                    break;
                }
            }
            if (T[n] - T[i] < x) {
                count--;
            }
            if (count > 0) {
                kq = Math.max(kq, (x + 1) * count + x);
            }

        }

        System.out.println(kq);
    }
}
