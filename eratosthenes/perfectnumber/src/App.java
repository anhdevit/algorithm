import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int l = Integer.parseInt(lineOne[0]);
        String[] lineTwo = br.readLine().split(" ");
        int d = Integer.parseInt(lineTwo[0]);
        int[] dd = new int[l + 1];
        for (int i = 1; i < l; i++) {
            for (int j = 2 * i; j < l; j += i) {
                dd[j] += i;
            }
        }

        int count = 0;

        for (int i = 1; i < l; i++) {
            if (Math.abs(dd[i] - i) <= d) {
                count += 1;
            }
        }

        System.out.println(count);
    }
}
