import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

    static boolean check_vien(int i, int j, int n, int m) {
        return i == 1 || j == 1 || i == n || j == m;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");

        int n = Integer.parseInt(lineOne[0]);
        int m = Integer.parseInt(lineOne[1]);
        int io = Integer.parseInt(lineOne[2]);
        int jo = Integer.parseInt(lineOne[3]);
        int[] dx = { -1, 0, 0, 1 };
        int[] dy = { 0, -1, 1, 0 };
        int[][] arr = new int[n + 1][m + 1];
        long[][] dd = new long[n + 1][m + 1];
        long[][] dist = new long[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            String[] lineTwo = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i + 1][j + 1] = Integer.parseInt(lineTwo[j]);
            }
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(io, jo));
        dd[io][jo] = 1;
        dist[io][jo] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (check_vien(node.i, node.j, n, m)) {
                System.out.println(dist[node.i][node.j] += 1);
                break;
            }

            for (int h = 0; h <= 3; h++) {
                int newI = node.i + dx[h];
                int newJ = node.j + dy[h];
                if (dd[newI][newJ] == 0 && arr[newI][newJ] == 0) {
                    queue.add(new Node(newI, newJ));
                    dd[newI][newJ] = 1;
                    dist[newI][newJ] = dist[node.i][node.j] + 1;
                }

            }

        }
    }
}
