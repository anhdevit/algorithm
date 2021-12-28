import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

    static class Node {
        public int l;
        public int r;
        public Node left;
        public Node right;
        public int[] list;

        public Node() {

        }

        public Node(int l, int r, Node left, Node right, int[] arr) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            int[] newList = new int[r - l + 1];
            int index = 0;
            for (int i = l; i <= r; i++) {
                newList[index++] = arr[i];
            }
            Arrays.sort(newList);
            this.list = newList;
        }
    }

    static int bs(int[] list, int value) {
        int l = -1;
        int r = list.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (list[mid] > value) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    static long get(Node p, int i, int j, int value) {
        if (i <= p.l && p.r <= j) {
            return bs(p.list, value);
        }
        long max_left = 0;
        if (p.left.r >= i) {
            max_left = get(p.left, i, j, value);
        }
        long max_right = 0;
        if (p.right.l <= j) {
            max_right = get(p.right, i, j, value);
        }
        return max_left + max_right;
    }

    static Node build(int[] arr, int l, int r) {
        if (l == r) {
            return new Node(l, l, null, null, arr);
        }
        int mid = (l + r) / 2;
        Node left = build(arr, l, mid);
        Node right = build(arr, mid + 1, r);
        return new Node(l, r, left, right, arr);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int M = Integer.parseInt(lineOne[1]);

        int[] arr = new int[n + 1];

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(lineTwo[i - 1]);
        }

        Node root = build(arr, 1, n);

        for (int i = 0; i < M; i++) {
            String[] lineQuestion = br.readLine().split(" ");
            int x = Integer.parseInt(lineQuestion[0]);
            int y = Integer.parseInt(lineQuestion[1]);
            int k = Integer.parseInt(lineQuestion[2]);

            int l = -1000000000 - 1;
            int r = 1000000000;
            System.out.println("");

            while (r - l > 1) {
                int mid = (r - l) / 2 + l;

                int answer = (int) get(root, x, y, mid);
                if (answer < k) {
                    l = mid;
                } else {
                    r = mid;
                }

            }

            System.out.println(r);

        }

    }
}
