import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    static class Node {
        public long index;
        public long parent;
        public long distance;

        public Node() {

        }

        public Node(long index, long parent, long distance) {
            this.index = index;
            this.parent = parent;
            this.distance = distance;
        }
    }

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static void getDeep(long r, ArrayList<Node>[] c, int[] d, long[] len) {
        for (Node i : c[(int) r]) {
            d[(int) i.index] = d[(int) r] + 1;
            len[(int) i.index] = len[(int) r] + (int) i.distance;
            getDeep(i.index, c, d, len);
        }
    }

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

    static long getLCA(int u, int v, int[] d, Node[] p, int n, int[][] a) {
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
        return p[u].parent;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] lineOne = br.readLine().split(" ");
            int N = Integer.parseInt(lineOne[0]);
            if (N == 0) {
                break;
            }
            Node p[] = new Node[N + 1];
            p[0] = new Node(0, 0, 0);
            for (int i = 1; i < N; i++) {
                String[] lineAl = br.readLine().split(" ");
                int a = Integer.parseInt(lineAl[0]);
                int l = Integer.parseInt(lineAl[1]);
                p[i] = new Node(i, a, l);
            }

            String[] lineQ = br.readLine().split(" ");
            int Q = Integer.parseInt(lineQ[0]);

            int[][] ST = new int[Q][2];
            for (int i = 0; i < Q; i++) {
                String[] lineSt = br.readLine().split(" ");
                ST[i][0] = Integer.parseInt(lineSt[0]);
                ST[i][1] = Integer.parseInt(lineSt[1]);
            }

            ArrayList<Node>[] c = new ArrayList[N + 1];

            for (int i = 0; i < N; i++) {
                c[i] = new ArrayList<>();
            }

            int[][] a = new int[N + 1][log2(N) + 1];
            long[] len = new long[N + 1];

            for (int u = 1; u <= N; u++) {
                c[(int) p[u].parent].add(p[u]);
                a[u][0] = (int) p[u].parent;
            }

            int[] d = new int[N + 1];
            d[0] = 0;
            len[0] = 0;
            getDeep(0, c, d, len);

            for (int i = 1; i <= log2(N); i++) {
                for (int u = 1; u <= N; u++) {
                    a[u][i] = a[a[i][i - 1]][i - 1];
                }
            }
            System.out.println(" ");

            for (int i = 0; i < Q; i++) {
                int u = ST[i][0];
                int v = ST[i][1];
                long uv = getLCA(u, v, d, p, N, a);
                long kq = len[u] + len[v] - 2 * len[(int) uv];
                System.out.print(kq + " ");
            }
            System.out.println(" ");
        }

    }
}