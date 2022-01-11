import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static class Node {
        public int l;
        public int r;
        public Node left;
        public Node right;
        public long value;

        public Node() {

        }

        public Node(int l, int r, Node left, Node right, long value) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    static void update(Node p, int i, long v) {
        if (p.l == p.r) {
            p.value = v;
            return;
        }
        if (p.left.l <= i && i <= p.left.r) {
            update(p.left, i, v);
        } else {
            update(p.right, i, v);
        }
        p.value = p.left.value + p.right.value;
    }

    static void update2(Node p, int i, long v) {
        if (p.l == p.r) {
            p.value = v * i;
            return;
        }
        if (p.left.l <= i && i <= p.left.r) {
            update2(p.left, i, v);
        } else {
            update2(p.right, i, v);
        }
        p.value = p.left.value + p.right.value;
    }

    static long get(Node p, int i, int j) {
        if (i <= p.l && p.r <= j) {
            return p.value;
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

    static Node build(long[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, a[l]);
        }
        int mid = (l + r) / 2;
        Node left = build(a, l, mid);
        Node right = build(a, mid + 1, r);
        return new Node(l, r, left, right, left.value + right.value);
    }

    static Node build2(long[] a, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, a[l] * l);
        }
        int mid = (l + r) / 2;
        Node left = build2(a, l, mid);
        Node right = build2(a, mid + 1, r);
        return new Node(l, r, left, right, left.value + right.value);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int K = Integer.parseInt(lineOne[1]);

        String[] lineTwo = br.readLine().split(" ");

        long[] arr = new long[n + 1];

        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(lineTwo[i]);
        }

        Node st1 = build(arr, 1, n);
        Node st2 = build2(arr, 1, n);

        String[] lineThree = br.readLine().split(" ");
        int q = Integer.parseInt(lineThree[0]);

        for (int i = 0; i < q; i++) {
            String[] lineQuerry = br.readLine().split(" ");
            int queryType = Integer.parseInt(lineQuerry[0]);

            if (queryType == 2) {
                int l = Integer.parseInt(lineQuerry[1]);
                int r = Integer.parseInt(lineQuerry[2]);
                int m = Integer.parseInt(lineQuerry[3]);
                int k = l + m - 1;
                int h = r - m + 1;
                if (k > h) {
                    m = r - l - m + 2;
                    k = l + m - 1;
                    h = r - m + 1;
                }

                long kq = get(st1, k, h) * m + (1 - l) * get(st1, l, k - 1) + get(st2, l, k - 1)
                        + (r + 1) * get(st1, h + 1, r) - get(st2, h + 1, r);
                System.out.println(kq);
            } else {
                int[] indexList = new int[K + 1];
                for (int j = 1; j <= K; j++) {
                    indexList[j] = Integer.parseInt(lineQuerry[j]);
                }
                int firstValue = (int) arr[indexList[1]];
                for (int j = 1; j <= K - 1; j++) {
                    update(st1, indexList[j], arr[indexList[j + 1]]);
                    update2(st2, indexList[j], arr[indexList[j + 1]]);
                    arr[indexList[j]] = arr[indexList[j + 1]];
                }
                update(st1, indexList[K], firstValue);
                update2(st2, indexList[K], firstValue);
                arr[indexList[K]] = firstValue;
            }

        }

    }
}
