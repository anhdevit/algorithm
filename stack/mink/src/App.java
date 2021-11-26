import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
        String[] lineOne = br.readLine().split(" ");
        int t = Integer.parseInt(lineOne[0]);

        for (int i = 0; i < t; i++) {
            String[] lineNK = br.readLine().split(" ");
            int n = Integer.parseInt(lineNK[0]);
            int k = Integer.parseInt(lineNK[1]);
            int[] arr = new int[n + 1];

            String[] lineArr = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                arr[j] = Integer.parseInt(lineArr[j - 1]);
            }

            ArrayDeque<Node> st = new ArrayDeque<Node>();

            for (int j = 1; j <= n; j++) {
                while (st.isEmpty() == false && st.getLast().value >= arr[j]) {
                    st.removeLast();
                }
                st.add(new Node(arr[j], j));
                if (j >= k) {
                    System.out.print(st.getFirst().value + " ");
                    if (st.getFirst().index <= j - k + 1) {
                        st.removeFirst();
                    }
                }
            }
            System.out.println(" ");
        }
    }
}
