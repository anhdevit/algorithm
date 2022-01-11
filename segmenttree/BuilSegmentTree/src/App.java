import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static class Node {
        public int l;
        public int r;
        public Node right;
        public Node left;
        public long max;

        public Node() {

        }

        public Node(int l, int r, Node right, Node left, long max) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.max = max;
        }
    }

    static long get(Node p, int i, int j) {
        if (p == null) {
            return 0;
        }
        if (i <= p.l && p.r <= j) {
            return p.max;
        }

        if (p.left == null) {
            return 0;
        }
        long max_left = 0;
        if (p.left.r >= i) {
            max_left = get(p.left, i, j);
        }

        if (p.right == null) {
            return 0;
        }
        long max_right = 0;
        if (p.right.l <= j) {
            max_right = get(p.right, i, j);
        }
        return Math.max(max_left, max_right);
    }

    static void update(Node p, int i, long v) {
        if (p.l == p.r) {
            p.max = v;
            return;
        }
        if (p.left.l <= i && i <= p.left.r) {
            update(p.left, i, v);
        } else {
            update(p.right, i, v);
        }
        p.max = Math.max(p.left.max, p.right.max);
    }

    static Node build(int[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, a[l]);
        }
        int mid = (l + r) / 2;
        Node left = build(a, l, mid);
        Node right = build(a, mid + 1, r);
        return new Node(l, r, left, right, Math.max(left.max, right.max));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);

        int[] N = new int[n + 1];

        Node root = build(N, 0, n);

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int ai = Integer.parseInt(lineTwo[i]);
            update(root, i, ai);
            System.out.println(get(root, 0, i));

        }
    }
}
