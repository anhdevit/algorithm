import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static class Node {
        public int l;
        public int r;
        public Node left;
        public Node right;
        public long max;
        public long lazy;

        public Node() {

        }

        public Node(int l, int r, Node left, Node right, long max, long lazy) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.max = max;
            this.lazy = lazy;
        }
    }

    static void propagate(Node p) {
        p.left.max += p.lazy;
        p.left.lazy += p.lazy;
        p.right.max += p.lazy;
        p.right.lazy += p.lazy;
        p.lazy = 0;
    }

    static void update(Node p, int x, int y, int v) {
        if (x <= p.l && p.r <= y) {
            p.max += v;
            p.lazy += v;
        } else {
            if (p.left == null || p.right == null)
                return;
            propagate(p);
            if (p.left.r >= x) {
                update(p.left, x, y, v);
            }
            if (p.left.l <= y) {
                update(p.right, x, y, v);
            }
            p.max = Math.max(p.left.max, p.right.max);

        }
    }

    static long get(Node p, int i, int j) {
        if (i > j)
            return 0;
        if (i <= p.l && p.r <= j) {
            return p.max;
        }
        propagate(p);
        long max_left = Long.MIN_VALUE;
        if (p.left.r >= i) {
            max_left = get(p.left, i, j);
        }
        long max_right = Long.MIN_VALUE;
        if (p.right.l <= j) {
            max_right = get(p.right, i, j);
        }
        return Math.max(max_left, max_right);
    }

    static Node build(int[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, 0, 0);
        }
        int mid = (l + r) / 2;
        Node left = build(a, l, mid);
        Node right = build(a, mid + 1, r);
        return new Node(l, r, left, right, 0, 0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int m = Integer.parseInt(lineOne[1]);
        int z = Integer.parseInt(lineOne[2]);

        int[] N = new int[n + 1];

        Node root = build(N, 1, n);

        for (int i = 0; i < z; i++) {
            String[] lineQuestion = br.readLine().split(" ");
            int u = Integer.parseInt(lineQuestion[0]);
            int v = Integer.parseInt(lineQuestion[1]) - 1;
            int w = Integer.parseInt(lineQuestion[2]);
            update(root, u, v, w);
            if (get(root, u, v) > m) {
                update(root, u, v, -w);
                System.out.println("N");
            } else {
                System.out.println("T");
            }

        }
    }
}
