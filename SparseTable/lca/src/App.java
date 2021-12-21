import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static void getDeep(int r, ArrayList<Integer>[] c, int[] d) {
        for (int i : c[r]) {
            d[i] = d[r] + 1;
            getDeep(i, c, d);
        }
    }

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

    static long getLCA(int u, int v, int[] d, int[] p, int n, int[][] a) {
        if (d[u] > d[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int delta = d[v] - d[u];

        for (int i = log2(n); i >= 0; i--) {
            if (getBit(delta, i) == 1) {
                v = a[v][i];
            }
        }
        if (u == v) {
            return u;
        }

        for (int i = log2(n); i >= 0; i--) {
            if (a[u][i] != a[v][i]) {
                u = a[u][i];
                v = a[v][i];
            }
        }
        return p[u];

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int[] p = new int[n + 1];

        String[] lineTwo = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            p[i + 1] = Integer.parseInt(lineTwo[i]);
        }

        String[] lineThree = br.readLine().split(" ");
        int q = Integer.parseInt(lineThree[0]);

        ArrayList<Integer>[] c = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            c[i] = new ArrayList<>();
        }

        int[][] a = new int[n + 1][log2(n) + 1];

        for (int u = 1; u <= n; u++) {
            c[p[u]].add(u);
            a[u][0] = p[u];
        }
        int[] d = new int[n + 1];
        d[0] = 0;
        getDeep(0, c, d);

        for (int i = 1; i <= log2(n); i++) {
            for (int u = 1; u <= n; u++) {
                a[u][i] = a[a[u][i - 1]][i - 1];
            }
        }

        for (int i = 0; i < q; i++) {
            String[] lineUv = br.readLine().split(" ");
            int u = Integer.parseInt(lineUv[0]);
            int v = Integer.parseInt(lineUv[1]);
            long kq = getLCA(u, v, d, p, n, a);
            System.out.println(kq);
        }
    }
}
