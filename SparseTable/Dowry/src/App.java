import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App {

    static class CustomComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            long result = o1.w - o2.w;
            if (result < 0) {
                return -1;
            } else if (result > 0)
                return 1;
            return 0;
        }
    }

    static class Node {
        public long w;
        public long v;

        public Node() {

        }

        public Node(long w, long v) {
            this.w = w;
            this.v = v;
        }
    }

    static long getBit(long p, int i) {
        return (p >> i) & 1;
    }

    static ArrayList<Node> syncP(ArrayList<Node> P) {
        ArrayList<Node> S = new ArrayList<Node>();

        for (long mask = 0; mask < (1l << P.size()); mask++) {
            long sumW = 0;
            long sumV = 0;
            for (int i = 0; i < P.size(); i++) {
                if (getBit(mask, i) == 1) {
                    sumW += P.get(i).w;
                    sumV += P.get(i).v;
                }
            }
            S.add(new Node(sumW, sumV));
        }

        return S;
    }

    static int searchI(ArrayList<Node> S2, long Wx, long L) {
        int l = -1;
        int r = S2.size();
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (S2.get(mid).w >= L - Wx) {
                r = mid;
            } else {
                l = mid;
            }

        }
        return r;
    }

    static int searchJ(ArrayList<Node> S2, long Wx, long R) {
        int l = -1;
        int r = S2.size();
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (S2.get(mid).w <= R - Wx) {
                l = mid;

            } else {
                r = mid;

            }

        }
        return l;
    }

    static long getMaxValue(long[][] st, int i, int j) {
        int k = (int) (Math.log(j - i + 1) / Math.log(2));
        return Math.max(st[i][k], st[j - (1 << k) + 1][k]);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        long L = Long.parseLong(lineOne[1]);
        long R = Long.parseLong(lineOne[2]);

        ArrayList<Node> S = new ArrayList<Node>();

        for (int i = 0; i < n; i++) {
            String[] lineN = br.readLine().split(" ");
            long w = Long.parseLong(lineN[0]);
            long v = Long.parseLong(lineN[1]);
            S.add(new Node(w, v));
        }

        ArrayList<Node> P1 = new ArrayList<Node>();
        ArrayList<Node> P2 = new ArrayList<Node>();

        for (int i = 0; i < n / 2; i++) {
            P1.add(S.get(i));
        }

        for (int i = n / 2; i < n; i++) {
            P2.add(S.get(i));
        }

        ArrayList<Node> S1 = new ArrayList<Node>();
        ArrayList<Node> S2 = new ArrayList<Node>();

        S1 = syncP(P1);
        S2 = syncP(P2);
        Collections.sort(S2, new CustomComparator());

        long[][] st = new long[S2.size() + 1][(int) (Math.log(S2.size()) / Math.log(2) + 1)];
        for (int i = 0; i < S2.size(); i++) {
            st[i][0] = S2.get(i).v;
        }

        for (int j = 1; j <= Math.log(S2.size()) / Math.log(2); j++) {
            for (int i = 0; i + (1 << j - 1) < S2.size(); i++) {
                st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }

        long kq = 0;

        for (int x = 0; x < S1.size(); x++) {
            int i = searchI(S2, S1.get(x).w, L);
            int j = searchJ(S2, S1.get(x).w, R);
            if (i <= j) {
                long Vmax = getMaxValue(st, i, j);
                kq = Math.max(kq, Vmax + S1.get(x).v);

            }
        }

        System.out.println(kq);

    }
}