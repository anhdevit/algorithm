import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int[] N = new int[n];

        String[] lineTwo = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(lineTwo[i]);
        }

        int kq = 0;

        ArrayDeque<Integer> st = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {

            while (st.isEmpty() == false && st.getLast() <= N[i]) {
                st.removeLast();

            }
            if (st.isEmpty() == false) {
                kq = Math.max(kq, st.getLast() ^ N[i]);
            }

            st.add(N[i]);
        }
        ArrayDeque<Integer> newSt = new ArrayDeque<Integer>();

        for (int j = n - 1; j >= 0; j--) {
            while (!newSt.isEmpty() && newSt.getLast() <= N[j]) {
                newSt.removeLast();
            }

            if (newSt.isEmpty() == false) {
                kq = Math.max(kq, newSt.getLast() ^ N[j]);

            }
            newSt.add(N[j]);
        }
        System.out.println(kq);
    }
}
