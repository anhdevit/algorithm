import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    static int f(int x, int n) {
        return n / x;
    }

    static int findGCD(int x, int y) {
        int r = 0, a, b;
        a = (x > y) ? x : y; // a is greater number
        b = (x < y) ? x : y; // b is smaller number
        r = b;
        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }

    static int lcm(int number1, int number2) {
        return number1 / (findGCD(number1, number2)) * number2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = new String();
        while ((str = br.readLine()) != null && str.length() != 0) {
            String lineOne[] = str.split(" ");
            int n = Integer.parseInt(lineOne[0]);
            if (n < 1) {
                return;
            }
            int p = Integer.parseInt(lineOne[1]);
            int q = Integer.parseInt(lineOne[2]);
            int r = Integer.parseInt(lineOne[3]);
            int kq = f(lcm(p, q), n) + f(lcm(p, r), n) + f(lcm(q, r), n) - 3 * f(lcm(lcm(p, q), r), n);

            System.out.println(kq);
        }

    }
}
