import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" "); // n
        int n = Integer.parseInt(nLine[0]); // n
        String[] arrayString = br.readLine().split(" "); // a
        int[] arr = new int[n];
        for (int i = 0; i < arrayString.length; i++) {
            arr[i] = Integer.parseInt(arrayString[i]);
        }

        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        String[] mLine = br.readLine().split(" "); // m
        int m = Integer.parseInt(mLine[0]); // m

        for (int i = 0; i < m; i++) {
            String[] lrLine = br.readLine().split(" "); // n
            int l = Integer.parseInt(lrLine[0]); // l
            int r = Integer.parseInt(lrLine[1]); // r
            int right = r - 1;
            int left = l - 1;
            if (left > 0) {
                System.out.println(arr[right] - arr[left - 1]);
            } else {
                System.out.println(arr[right]);
            }

        }
    }
}

// n 5
// a 5 1 3 2 4
// m 3
// 1 3
// 3 5
// 2 4