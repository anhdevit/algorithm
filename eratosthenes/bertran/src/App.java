import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        int[] dd = new int[2 * n];
        for (int i = 2; i < 2 * n; i++) {
            if (dd[i] != 1) {
                for (int j = 2 * i; j < 2 * n; j += i) {
                    dd[j] = 1;
                }
            }
        }
        int count = 0;

        for (int i = n + 1; i < 2 * n; i++) {
            if (dd[i] == 0) {
                count += 1;
            }
        }
        System.out.println(count);

    }
}
