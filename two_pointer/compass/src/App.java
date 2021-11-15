import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            String[] position = br.readLine().split(" ");
            x[i] = Integer.parseInt(position[0]);
            y[i] = Integer.parseInt(position[1]);
        }

        ArrayList<int[]> s = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                s.add(new int[] { i, j, (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]) });
            }
        }
        s.sort((a, b) -> a[2] - b[2]);

        ArrayList<Double> kq = new ArrayList<>();

        for (int i = 0; i < s.size(); i++) {
            int j = i;
            HashSet<Integer> m = new HashSet<>();

            while (j < s.size() && s.get(i)[2] == s.get(j)[2]) {
                m.add(s.get(j)[0]);
                m.add(s.get(j)[1]);
                j += 1;
            }
            if (m.size() == n) {
                kq.add(Math.sqrt(s.get(i)[2]));
            }
            i = j - 1;
        }

        System.out.println(kq.size());
        for (int i = 0; i < kq.size(); i++) {
            System.out.println(kq.get(i));
        }
    }
}
