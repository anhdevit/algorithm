import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static class Node {
        public int l;
        public int r;
        public Node left;
        public Node right;
        public long max_value;

        public Node() {

        }

        public Node(int l, int r, Node left, Node right, long max_value) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.max_value = max_value;
        }
    }

    static void update(Node p, int i, int v) {
        if (p.l == p.r) {
            p.max_value = v;
            return;
        }
        if (p.left.l <= i && i <= p.left.r) {
            update(p.left, i, v);
        } else {
            update(p.right, i, v);
        }
        p.max_value = Math.max(p.left.max_value, p.right.max_value);
    }

    static long get_max(Node p, int i, int j) {
        if (i <= p.l && p.r <= j) {
            return p.max_value;
        }
        long max_left = -1000000000;
        if (p.left.r >= i) {
            max_left = get_max(p.left, i, j);
        }
        long max_right = -1000000000;
        if (p.right.l <= j) {
            max_right = get_max(p.right, i, j);
        }
        return Math.max(max_left, max_right);
    }

    static Node build(int[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, a[l]);
        }
        int mid = (l + r) / 2;
        Node left = build(a, l, mid);
        Node right = build(a, mid + 1, r);
        return new Node(l, r, left, right, Math.max(left.max_value, right.max_value));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int t = Integer.parseInt(lineOne[1]);
        int[] N = new int[n + 1];

        Node root = build(N, 1, n);

        int[][] querry = new int[n][3];

        for (int i = 0; i < t; i++) {
            String[] lineTwo = br.readLine().split(" ");
            querry[i][0] = Integer.parseInt(lineTwo[0]);
            querry[i][1] = Integer.parseInt(lineTwo[1]);
            querry[i][2] = Integer.parseInt(lineTwo[2]);
        }

        for (int i = 0; i < t; i++) {
            int type = querry[i][0];
            if (type == 1) {
                update(root, querry[i][1], querry[i][2]);
            }
            if (type == 2) {
                long kq = get_max(root, querry[i][1], querry[i][2]);
                System.out.println(kq);
            }

        }
    }
}
