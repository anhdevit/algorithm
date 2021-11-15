import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int k = Integer.parseInt(lineOne[1]);

        int canK = (int) Math.sqrt(k);
        HashMap<Integer, Integer> arr = new HashMap<Integer, Integer>();
        for (int i = 2; i <= canK; i++) {
            if (k % i == 0) {
                int count = 0;

                while (k % i == 0) {
                    k = k / i;
                    count++;
                }
                if (count != 0) {
                    arr.put(i, count);
                }
            }
        }

        if (k > 1) {
            arr.put(k, 1);
        }

        int kq = 1000000000;

        for (Map.Entry me : arr.entrySet()) {
            int p = (int) me.getKey();
            int v = (int) me.getValue();
            int u = 0;

            int pi = 1 * p;
            while (pi <= n) {
                u += n / pi;
                pi *= p;
            }
            kq = Math.min(kq, u / v);
        }
        System.out.println(kq);
    }
}
