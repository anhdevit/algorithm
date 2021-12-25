import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);

        int[][] ab = new int[n][2];

        int lengthN = 2 * n + 1;
        int[] N = new int[lengthN];

        Node root = build(N, 0, 2 * n);

        long kq = 0;
        for (int i = 0; i < n; i++) {
            String[] lineTwo = br.readLine().split(" ");
            int a = Integer.parseInt(lineTwo[0]);
            int b = Integer.parseInt(lineTwo[1]);
            if (a > b) {
                ab[i][0] = b;
                ab[i][1] = a;
            } else {
                ab[i][0] = a;
                ab[i][1] = b;
            }

        }

        Arrays.sort(ab, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            kq += get(root, ab[i][0], ab[i][1]);
            update(root, ab[i][1]);
        }
        System.out.println(kq);
    }
}
