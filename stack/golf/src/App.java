import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    static class Node {
        public int value;
        public int index;

        public Node() {

        }

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static long solve(int[] h, int n) {
        ArrayList<Node> st = new ArrayList<Node>();
        int[] l = new int[n + 1];
        int[] r = new int[n + 1];

        st.add(new Node(-1, 0));

        for (int i = 1; i <= n; i++) {

            while (st.get(st.size() - 1).value >= h[i]) {
                st.remove(st.size() - 1);
            }
            l[i] = st.get(st.size() - 1).index + 1;
            st.add(new Node(h[i], i));
        }

        st.clear();
        st.add(new Node(-1, n + 1));
        for (int i = n; i >= 1; i--) {
            while (st.get(st.size() - 1).value >= h[i]) {
                st.remove(st.size() - 1);
            }
            r[i] = st.get(st.size() - 1).index - 1;
            st.add(new Node(h[i], i));
        }

        long kq = 0;

        for (int i = 1; i <= n; i++) {
            kq = Math.max(kq, 1l * (h[i] + 1) * (r[i] - l[i] + 2));
        }
        return kq;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int m = Integer.parseInt(lineOne[0]);
        int n = Integer.parseInt(lineOne[1]);
        int[][] h = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            String[] lineRow = br.readLine().split(" ");
            for (int j = 0; j <= n; j++) {
                h[i][j] = Integer.parseInt(lineRow[j]);
            }
        }

        int[][] a = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (h[i][j] >= h[i - 1][j] && h[i][j] >= h[i][j - 1] & h[i - 1][j] >= h[i - 1][j - 1]
                        && h[i][j - 1] >= h[i - 1][j - 1]) {
                    a[i][j] = 1;
                }
            }
        }

        long kq = 0;
        int[] h2 = new int[n + 1];

        for (int i = 0; i <= m - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                if (a[i][j] == 1) {
                    h2[j]++;
                } else {
                    h2[j] = 0;
                }
            }
            kq = Math.max(kq, solve(h2, n));
        }

        System.out.println(kq);

    }
}
