import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static class Node {
        public int l;
        public int r;
        public Node left;
        public Node right;
        public long count;

        public Node() {

        }

        public Node(int l, int r, Node left, Node right, long count) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.count = count;
        }
    }

    static void update(Node p, int i) {
        if (p.l == p.r) {
            p.count++;
            return;
        }
        if (p.left.l <= i && i <= p.left.r) {
            update(p.left, i);
        } else {
            update(p.right, i);
        }
        p.count = p.left.count + p.right.count;
    }

    static long get(Node p, int i, int j) {
        if (i <= p.l && p.r <= j) {
            return p.count;
        }
        long max_left = 0;
        if (p.left.r >= i) {
            max_left = get(p.left, i, j);
        }
        long max_right = 0;
        if (p.right.l <= j) {
            max_right = get(p.right, i, j);
        }
        return max_left + max_right;
    }

    static Node build(int[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, a[l]);
        }
        int mid = (l + r) / 2;
        Node left = build(a, l, mid);
        Node right = build(a, mid + 1, r);
        return new Node(l, r, left, right, left.count + right.count);
    }

    static long getBit(long p, int i) {
        return (p >> i) & 1;
    }

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int[] ar = new int[n + 1];

        String[] lineTwo = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            ar[i + 1] = Integer.parseInt(lineTwo[i]);
        }

        String[] lineThree = br.readLine().split(" ");

        int s = Integer.parseInt(lineThree[0]);

        int[][] next = new int[log2(s) + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            next[0][i] = ar[i];
        }

        for (int k = 1; k <= log2(s); k++) {
            for (int i = 1; i <= n; i++) {
                next[k][i] = next[k - 1][next[k - 1][i]];
            }
        }

        int[] ar2 = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int index = i;
            for (int k = 0; k <= log2(s); k++) {
                if (getBit(s, k) == 1) {
                    index = next[k][index];
                }
            }
            ar2[index] = i;

        }

        int[] N = new int[n + 1];

        Node root = build(N, 0, n);

        long kq = 0;
        for (int i = 1; i <= n; i++) {
            if (ar2[i] < n) {
                kq += get(root, ar2[i] + 1, n);
            }
            update(root, ar2[i]);
        }
        System.out.println(kq);
    }
}
