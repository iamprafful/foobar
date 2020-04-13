import java.util.ArrayList;
public class Solution {
    public static int solution(int[] l) {
        int count = 0;
        ArrayList<Integer> duplet= new ArrayList<>();
        ArrayList<Integer> ind= new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            for (int j = i+1; j < l.length; j++) {
                if (l[j]%l[i]==0) {
                    duplet.add(l[j]);
                    ind.add(j);
                }
            }
        }
        for (int i = 0; i < duplet.size(); i++) {
            for (int j = ind.get(i)+1; j < l.length; j++) {
                 if (l[j]%duplet.get(i)==0) {
                    count++;
                }
            }
        }
        return count;
    }
}
