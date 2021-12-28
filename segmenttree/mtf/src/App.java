import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class App {
    static class Node {
        public int l;
        public int r;
        public Node left;
        public Node right;
        public long sum;

        public Node() {
            this.sum = 0;
            this.l = 0;
            this.r = 0;
            this.left = null;
            this.right = null;
        }

        public Node(int l, int r, Node left, Node right, long sum) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
    }

    static void update1(Node p, int i, int v) {
        if (p.l == p.r) {
            p.sum += v;
            return;
        }
        if (p.left.l <= i && i <= p.left.r) {
            update1(p.left, i, v);
        } else {
            update1(p.right, i, v);
        }
        p.sum = p.left.sum + p.right.sum;
    }

    static long get1(Node p, long i, long j) {
        if (p == null) {
            return 0;
        }

        if (i > j) {
            return 0;
        }

        if (i <= p.l && p.r <= j) {
            return p.sum;
        }
        long max_left = 0;
        if (p.left.r >= i) {
            max_left = get1(p.left, i, j);
        }
        long max_right = 0;
        if (p.right.l <= j) {
            max_right = get1(p.right, i, j);
        }
        return max_left + max_right;
    }

    static void update2(Node p, long i, long v) {
        if (p.l == p.r) {
            p.sum += v;
        } else {
            int mid = (p.l + p.r) / 2;
            if (i <= mid) {
                if (p.left == null) {
                    p.left = new Node(p.l, mid, null, null, 0);
                }
                update2(p.left, i, v);

            } else {
                if (p.right == null) {
                    p.right = new Node(mid + 1, p.r, null, null, 0);
                }
                update2(p.right, i, v);
            }
            long maxLeft = p.left != null ? p.left.sum : 0;
            long maxRight = p.right != null ? p.right.sum : 0;
            p.sum = maxLeft + maxRight;
        }

    }

    static long get2(Node p, long i, long j) {
        if (p == null) {
            return 0;
        }

        if (i > j) {
            return 0;

        }

        if (p.l >= i && p.r <= j) {
            return p.sum;
        }

        int mid = (p.l + p.r) / 2;
        int sum = 0;
        if (mid >= i) {
            sum += get2(p.left, i, j);
        }

        if (mid + 1 <= j) {
            sum += get2(p.right, i, j);
        }

        return sum;
    }

    static Node build(int[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, a[l]);
        }
        int mid = (l + r) / 2;
        Node left = build(a, l, mid);
        Node right = build(a, mid + 1, r);
        return new Node(l, r, left, right, left.sum + right.sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);

        long[] a = new long[n + 1];

        int[] N = new int[n + 1];
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();

        Node root1 = build(N, 0, n);
        Node root2 = new Node(1, 1000000000, null, null, 0);

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i + 1] = Integer.parseInt(lineTwo[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (map.getOrDefault(a[i], 0) != 0) {
                int prev = map.get(a[i]);
                long s = get1(root1, prev + 1, i - 1);
                System.out.print(s + 1);
                System.out.print(" ");
                update1(root1, prev, -1);
                update1(root1, i, 1);
            } else {
                long s = get2(root2, a[i] + 1, 1000000000);
                System.out.print(s + a[i]);
                System.out.print(" ");
                update2(root2, a[i], 1);
                update1(root1, i, 1);
            }
            map.put(a[i], i);
        }
    }
}
