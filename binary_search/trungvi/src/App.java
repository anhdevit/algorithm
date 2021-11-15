import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    static boolean check(long mid, int[][] data, int r, int c, int h, int w) {
        long[][] prefix2d = new long[r + 2][c + 2];
        for (int i = 1; i < r + 1; i++) {
            for (int j = 1; j < c + 1; j++) {
                if (data[i][j] <= mid) {
                    prefix2d[i][j] = 1;
                } else {
                    prefix2d[i][j] = 0;
                }
                prefix2d[i][j] = prefix2d[i - 1][j] + prefix2d[i][j - 1] - prefix2d[i - 1][j - 1] + prefix2d[i][j];
            }
        }
        for (int i = 1; i <= r + 1 - h; i++) {
            for (int j = 1; j <= c + 1 - w; j++) {
                long total = prefix2d[i + h - 1][j + w - 1] - prefix2d[i - 1][j + w - 1] - prefix2d[i + h - 1][j - 1]
                        + prefix2d[i - 1][j - 1];
                if (total < (h * w + 1) / 2) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");

        int r = Integer.parseInt(lineOne[0]);
        int c = Integer.parseInt(lineOne[1]);
        int h = Integer.parseInt(lineOne[2]);
        int w = Integer.parseInt(lineOne[3]);

        int[][] data = new int[r + 1][c + 1];

        for (int i = 0; i < r; i++) {
            String[] lineRc = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                data[i + 1][j + 1] = Integer.parseInt(lineRc[j]);
            }
        }

        long low = Long.MAX_VALUE;
        long hight = -Long.MAX_VALUE;

        for (int i = 1; i < r + 1; i++) {
            for (int j = 1; j < c + 1; j++) {
                low = Math.min(low, data[i][j]);
                hight = Math.max(hight, data[i][j]);
            }
        }

        while (hight - low > 1) {
            long mid = (hight + low) / 2;
            if (check(mid, data, r, c, h, w)) {
                hight = mid;
            } else {
                low = mid;
            }
        }

        System.out.println(hight);
    }
}
