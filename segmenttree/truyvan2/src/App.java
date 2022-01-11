import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class App {

    static class Node {
        long sum;
        int l, r;
        Node left, right;

        public Node() {
            this.sum = 0;
            this.l = 0;
            this.r = 0;
            this.left = null;
            this.right = null;
        };

        public Node(long sum, int l, int r, Node left, Node right) {
            this.sum = sum;
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
        }
    }

    static Node buildSegmentTree(int l, int r, long[] arr, boolean shouldTimeIndex) {
        if (l == r) {
            int time = shouldTimeIndex ? l : 1;
            return new Node(arr[l] * time, l, r, null, null);
        }
        int mid = (r + l) / 2;
        Node left = buildSegmentTree(l, mid, arr, shouldTimeIndex);
        Node right = buildSegmentTree(mid + 1, r, arr, shouldTimeIndex);
        return new Node(left.sum + right.sum, l, r, left, right);
    }

    static void update(Node currentNode, int index, long value, boolean shouldTimeIndex) {
        if (currentNode.l == currentNode.r) {
            int time = shouldTimeIndex ? index : 1;
            currentNode.sum = value * time;
            return;
        }
        if (currentNode.left.l <= index && index <= currentNode.left.r) {
            update(currentNode.left, index, value, shouldTimeIndex);
        } else {
            update(currentNode.right, index, value, shouldTimeIndex);
        }
        currentNode.sum = currentNode.left.sum + currentNode.right.sum;
    }

    static long getSum(Node currentNode, long i, long j) {
        if (i > j)
            return 0;
        if (i <= currentNode.l && currentNode.r <= j) {
            return currentNode.sum;
        }
        long maxLeft = 0;
        if (currentNode.left.r >= i) {
            maxLeft = getSum(currentNode.left, i, j);
        }
        long maxRight = 0;
        if (currentNode.right.l <= j) {
            maxRight = getSum(currentNode.right, i, j);
        }
        return maxLeft + maxRight;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        int K = Integer.parseInt(nLine[1]);
        String[] arrLine = br.readLine().split(" ");
        long[] arr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(arrLine[i]);
        }
        String[] qLine = br.readLine().split(" ");
        int q = Integer.parseInt(qLine[0]);

        Node normalTree = buildSegmentTree(1, n, arr, false);
        Node treeWithIndex = buildSegmentTree(1, n, arr, true);

        for (int i = 0; i < q; i++) {
            String[] line = br.readLine().split(" ");
            int queryType = Integer.parseInt(line[0]);
            if (queryType == 1) {
                int[] indexList = new int[K + 1];
                for (int j = 1; j <= K; j++) {
                    indexList[j] = Integer.parseInt(line[j]);
                }
                long tempOfFirstValue = arr[indexList[1]];
                for (int j = 1; j <= K - 1; j++) {
                    update(normalTree, indexList[j], arr[indexList[j + 1]], false);
                    update(treeWithIndex, indexList[j], arr[indexList[j + 1]], true);
                    arr[indexList[j]] = arr[indexList[j + 1]];
                }

                update(normalTree, indexList[K], tempOfFirstValue, false);
                update(treeWithIndex, indexList[K], tempOfFirstValue, true);
                arr[indexList[K]] = tempOfFirstValue;
                System.out.println();
            } else {
                int l = Integer.parseInt(line[1]);
                int r = Integer.parseInt(line[2]);
                int m = Integer.parseInt(line[3]);
                int k = l + m - 1;
                int h = r - m + 1;
                if (k > h) {
                    m = r - l - m + 2;
                    k = l + m - 1;
                    h = r - m + 1;
                }

                long result = getSum(normalTree, k, h) * m + (1 - l) * getSum(normalTree, l, k - 1)
                        + getSum(treeWithIndex, l, k - 1) + (r + 1) * getSum(normalTree, h + 1, r)
                        - getSum(treeWithIndex, h + 1, r);
                System.out.println(result);
            }
        }

    }
}