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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = new String();
        while ((str = br.readLine()) != null && str.length() != 0) {
            String[] lineOne = str.split(" ");
            int n = Integer.parseInt(lineOne[0]);
            if (n == 0) {
                return;
            }
            int[] h = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                h[i] = Integer.parseInt(lineOne[i]);
            }

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
                kq = Math.max(kq, 1l * h[i] * (r[i] - l[i] + 1));
            }
            System.out.println(kq);
        }

    }
}
//