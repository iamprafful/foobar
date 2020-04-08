public class Solution {

    public static int[] solution(int[] data, int n) {
        int[] op;
        if (n == 0) {
            op = new int[0];
            return op;
        }
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            if (max < data[i]) {
                max = data[i];
            }
        }
        int[] count = new int[max + 1];
        for (int i = 0; i < data.length; i++) {
            count[data[i]]++;
        }
        int j = 0, k = 0;
        for (int i = 0; i < data.length; i++) {
            if (count[data[i]] <= n) {
                j++;
            }
        }
        op = new int[j];
        for (int i = 0; i < data.length; i++) {
            if (count[data[i]] <= n) {
                op[k] = data[i];
                k++;
            }
        }
        return op;
    }
}
