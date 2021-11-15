import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    static int A(int x, ArrayList<Integer> L) {
        int l = -1;
        int r = L.size();
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (L.get(mid) > x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    static int B(int x, ArrayList<Integer> R) {
        int l = -1;
        int r = R.size();
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (R.get(mid) >= x) {
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

        ArrayList<Integer>[] listN = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            listN[i] = new ArrayList<>();
        }

        int[] c = new int[m];
        int[] s = new int[m];

        String[] cline = br.readLine().split(" ");
        String[] sline = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            c[i] = Integer.parseInt(cline[i]);
            s[i] = Integer.parseInt(sline[i]);
            listN[c[i]].add(s[i]);
        }

        for (int i = 1; i < n; i++) {
            Collections.sort(listN[i]);
        }

        for (int i = 0; i < m; i++) {
            int r1 = A(s[i], listN[c[i]]) - B(s[i], listN[c[i]]) - 1;
            int r2 = listN[c[i]].size() - A(s[i], listN[c[i]]);
            System.out.print(r1 + " ");
            System.out.println(r2);
        }

    }
}
