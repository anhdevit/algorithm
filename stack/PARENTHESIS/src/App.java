import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split("");
        HashMap<String, Integer> arr1 = new HashMap<String, Integer>();
        arr1.put("(", 1);
        arr1.put("[", 1);
        arr1.put("{", 1);

        HashMap<String, String> arr2 = new HashMap<String, String>();
        arr2.put(")", "(");
        arr2.put("]", "[");
        arr2.put("}", "{");
        ArrayList<String> set = new ArrayList<String>();

        for (int i = 0; i < lineOne.length; i++) {
            String line = lineOne[i];
            if (arr1.get(line) != null || set.size() == 0) {
                set.add(line);
            } else {
                String x = arr2.get(line);
                String j = set.get(set.size() - 1);
                if (arr1.get(x) == arr1.get(j)) {
                    set.remove(set.size() - 1);
                } else {
                    set.add(line);
                }
            }
        }

        if (set.size() > 0) {
            System.out.println(0);
            return;
        }
        System.out.println(1);

    }
}
