import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class App {

    static class Node {
        public int i;
        public int j;

        public Node() {

        }

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static boolean checkOut(int i, int j, int n, int m) {
        return i == m && j == n;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");

        int m = Integer.parseInt(lineOne[0]);
        String[] lineTwo = br.readLine().split(" ");
        int n = Integer.parseInt(lineTwo[0]);

        int[][] arr = new int[m + 1][n + 1];
        long[][] dd = new long[m + 1][n + 1];
        long[][] dist = new long[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            String[] lineI = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i + 1][j + 1] = Integer.parseInt(lineI[j]);
            }
        }

        ArrayList<Node>[] S = new ArrayList[1000000 + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S[i * j] == null) {
                    S[i * j] = new ArrayList<Node>();
                }
                S[i * j].add(new Node(i, j));
            }
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(1, 1));
        dd[1][1] = 1;
        dist[1][1] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (checkOut(node.i, node.j, n, m)) {
                System.out.println("yes");
                return;
            }

            ArrayList<Node> Sa = S[arr[node.i][node.j]];
            if (Sa != null) {
                for (int x = 0; x < Sa.size(); x++) {
                    Node _node = Sa.get(x);
                    int newI = _node.i;
                    int newJ = _node.j;
                    if (dd[newI][newJ] == 0) {
                        queue.add(new Node(newI, newJ));
                        dd[newI][newJ] = 1;
                        dist[newI][newJ] = dist[node.i][node.j] + 1;
                    }
                }
            }

        }

        System.out.println("no");
    }
}
