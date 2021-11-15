import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]) + 1;

        // Array n
        int[][] twoD = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            String[] subTwoD = br.readLine().split(" ");
            for (int j = 0; j < n - 1; j++) {
                twoD[i][0] = 0;
                twoD[0][j] = 0;
                twoD[i + 1][j + 1] = Integer.parseInt(subTwoD[j]);
            }
        }

        String[] mLine = br.readLine().split(" ");
        int m = Integer.parseInt(mLine[0]);

        // Array M
        int[][] position = new int[m][4];
        for (int i = 0; i < m; i++) {
            String[] mRow = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                position[i][j] = Integer.parseInt(mRow[j]);
            }
        }

        long[][] prefixTwoD = new long[n][n];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // prefixTwoD[i][0] = twoD[i - 1][0] + twoD[i][0];
                // prefixTwoD[0][j] = twoD[0][j - 1] + twoD[0][j];
                prefixTwoD[i][j] = twoD[i][j] + prefixTwoD[i - 1][j] + prefixTwoD[i][j - 1] - prefixTwoD[i - 1][j - 1];
            }
        }
        // s = s[r2][c2] - s[r -1][c2] - s[r2 - 1][c1]
        for (int i = 0; i < m; i++) {
            long s = prefixTwoD[position[i][2]][position[i][3]] - prefixTwoD[position[i][0] - 1][position[i][3]]
                    - prefixTwoD[position[i][2]][position[i][1] - 1]
                    + prefixTwoD[position[i][0] - 1][position[i][1] - 1];
            System.out.println(s);
        }
    }
}

// 1 2 3 4
// 4 3 2 1
// 3 2 4 1
// 1 4 2 3

// 1 3