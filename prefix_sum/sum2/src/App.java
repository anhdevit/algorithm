import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n + 1];
        long sums[] = new long[n + 1];
        sums[0] = 0;
        for (int i = 0; i < n; i++) {
            nums[i + 1] = sc.nextInt();
            sums[i + 1] = nums[i + 1] + sums[i];
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(sums[r] - sums[l - 1]);
        }
    }
}
