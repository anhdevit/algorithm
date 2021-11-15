import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    static long getBit(long p, int i) {
        return (p >> i) & 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        if (n == 1) {
            System.out.println(0);
            return;
        }
        int _n = Integer.parseInt(lineOne[0]);

        int canN = (int) Math.sqrt(n);
        ArrayList<Integer> v = new ArrayList<Integer>();

        for (int i = 2; i <= canN; i++) {
            if (n % i == 0) {
                v.add(i);
            }

            while (n % i == 0) {
                n = n / i;
            }
        }

        if (n > 1) {
            v.add(n);
        }

        long kq = 0;

        for (int mask = 1; mask < 1 << v.size(); mask++) {
            long lcm = 1;
            int count = 0;
            for (int i = 0; i < v.size(); i++) {
                if (getBit(mask, i) == 1) {
                    lcm *= v.get(i);
                    count++;
                }
            }
            if (count % 2 == 1) {
                kq += _n / lcm;

            } else {
                kq -= _n / lcm;
            }
        }
        System.out.println(_n - kq);
    }
}
