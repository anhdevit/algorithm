import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class App {

    static int check(int d, int[] a) {
        int cCount = 1;
        int i = 1;
        ArrayList<Integer> set = new ArrayList<Integer>();
        set.add(a[0]);
        while (i < a.length) {
            Boolean kq = (a[i] - d) >= set.get(set.size() - 1);
            if (kq) {
                set.add(a[i]);
                cCount += 1;
            }
            i++;
        }

        return cCount;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineTest = br.readLine().split(" ");
        int t = Integer.parseInt(lineTest[0]);
        for (int i = 0; i < t; i++) {
            String[] lineNC = br.readLine().split(" ");
            int n = Integer.parseInt(lineNC[0]);
            int c = Integer.parseInt(lineNC[1]);

            int[] N = new int[n];

            for (int j = 0; j < n; j++) {
                String[] lineN = br.readLine().split(" ");
                N[j] = Integer.parseInt(lineN[0]);
            }

            Arrays.sort(N);

            int l = 1;
            int r = N[n - 1] - N[0];

            while (r - l > 1) {
                int mid = (r + l) / 2;
                int count = check(mid, N);
                if (count >= c) {
                    l = mid;
                } else {
                    r = mid;
                }
            }

            int kq = 0;
            if (check(r, N) >= c) {
                kq = r;
            } else {
                kq = l;
            }

            System.out.println(kq);
        }
    }
}
