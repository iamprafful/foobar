import java.util.ArrayList;
public class Solution {
    public static void main(String[] args) {
        int arr[][]=solution(4, 4);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }

    }
    public static int[][] solution(int num_buns, int num_required) {
        if (num_buns==num_required) {
            int[][] arr = new int[num_buns][1];
            for (int i = 0; i < arr.length; i++) {
                arr[i][0]=i;
            }
            return arr;
        }
        if (num_required==1) {
            int[][] arr = new int[num_buns][1];
            for (int i = 0; i < arr.length; i++) {
                arr[i][0]=0;
            }
            return arr;
        }
        int repeat_times = num_buns - num_required + 1;
        int num_key= factorial(num_buns)/(factorial(repeat_times)*factorial(num_required-1));
        int[] key = new int[num_key];
        for (int i = 0; i < num_key; i++) {
            key[i]=i;
        }
        int num_keyholder= (num_key*(repeat_times))/num_buns;
        int[][] arr = new int[num_buns][num_keyholder];
        for (int i = 0; i < num_buns; i++) {
            for (int j = 0; j < num_keyholder; j++) {
                arr[i][j]=-1;            
            }
        }
        ArrayList<int[]> combination = generate(num_buns, repeat_times);
        int total= num_key*num_required;
        for (int i = 0; i < total/repeat_times; i++) {
            for (int j = 0; j < combination.get(i).length; j++) {
                for (int j2 = 0; j2 < num_keyholder; j2++) {
                    if(arr[combination.get(i)[j]][j2]==-1)
                    {
                        arr[combination.get(i)[j]][j2]=i;
                        break;
                    }                    
                }
            }
        }
        return arr;
    }
    public static int factorial(int n)
    {
        if(n==0)
        {
            return 1;
        }
        for (int i = n-1; i > 0; i--) {
            n = n*i;
        }
        return n;
    }
    public static ArrayList<int[]> generate(int n, int r) {
        ArrayList<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }
     
        while (combination[r - 1] < n) {
            combinations.add(combination.clone());
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }
     
        return combinations;
    }
}
