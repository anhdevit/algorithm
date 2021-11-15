import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static boolean check(long s, boolean print, int[] a, long[] T, int m, int k) {
        int i = m;
        int j = m;
        int dem = 0;
        boolean[] p = new boolean[m + 1];

        for (int x = 0; i < m; x++) {
            p[x] = false;
        }

        while (i > 0) {
            while (j > 0 && (T[i] - T[j - 1]) <= s) {
                j--;
            }
            if (i == j) {
                return false;
            }
            dem++;
            j = Math.max(j, k - dem);
            p[j] = true;
            i = j;
        }

        if (print) {
            for (int y = 1; y <= m; y++) {
                System.out.print(a[y - 1] + " ");
                if (p[y]) {
                    System.out.print("/" + " ");
                }
            }
        }
        return dem <= k;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] oneLine = br.readLine().split(" ");
        int cases = Integer.parseInt(oneLine[0]);

        for (int i = 0; i < cases; i++) {

            String[] caseLine = br.readLine().split(" ");
            int n = Integer.parseInt(caseLine[0]);
            int k = Integer.parseInt(caseLine[1]);

            int[] books = new int[n];
            String[] bookLines = br.readLine().split(" ");
            long totalPage = 0;
            for (int j = 0; j < n; j++) {
                books[j] = Integer.parseInt(bookLines[j]);
                totalPage += books[j];
            }

            long[] prefixSumBooks = new long[n + 1];

            for (int j = 1; j <= n; j++) {
                prefixSumBooks[j] = prefixSumBooks[j - 1] + books[j - 1];
            }

            long l = 0;
            long r = totalPage;

            while (r - l > 1) {
                long mid = (l + r) / 2;
                if (check(mid, false, books, prefixSumBooks, n, k)) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            check(r, true, books, prefixSumBooks, n, k);
            System.out.println("");
        }
    }
}
