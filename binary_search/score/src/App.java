import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

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
        String[] oneLine = br.readLine().split(" ");
        int n = Integer.parseInt(oneLine[0]);
        int m = Integer.parseInt(oneLine[1]);

        ArrayList<Integer>[] scores = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            scores[i] = new ArrayList<>();
        }

        int[] c = new int[m];
        int[] s = new int[m];

        String[] cLine = br.readLine().split(" ");
        String[] sLine = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            c[i] = Integer.parseInt(cLine[i]);
            s[i] = Integer.parseInt(sLine[i]);
            scores[c[i]].add(s[i]);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(scores[i]);
        }

        // int r1 = A
        for (int i = 0; i < m; i++) {
            int r1 = A(s[i], scores[c[i]]) - B(s[i], scores[c[i]]) - 1;
            int r2 = scores[c[i]].size() - A(s[i], scores[c[i]]);
            System.out.print(r1 + " ");
            System.out.println(r2);
        }

        // for(int i = 0; i < scores.size(); i++) {
        // for (int j =0; j < scores.) {

        // }
        // }

        // for

    }
}

// n 2 m 10
// 2 1 1 1 1 2 2 2 1 1
// 5 2 5 2 1 3 4 4 4 1
// 1
// 1 1 1 1 1 1
// 2 5 2 1 4 1
// 2
// 2 2 2 2
// 5 3 4 4
// scores[1] = [1,1,2,2,4,5]
// scores[2] = [3,4,4,5]
