import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);

        String[] lineTwo = br.readLine().split(" ");
        long maxCurrent = Integer.parseInt(lineTwo[0]);
        long maxGlobal = Integer.parseInt(lineTwo[0]);

        for (int i = 1; i < n; i++) {
            maxCurrent = Math.max(Integer.parseInt(lineTwo[i]), maxCurrent + Integer.parseInt(lineTwo[i]));
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
            }
        }
        System.out.println(maxGlobal);

    }
}
