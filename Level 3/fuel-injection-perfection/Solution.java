import java.math.BigInteger;
public class Solution {
    public static int solution(String x) {
        int count=0;
        BigInteger num = new BigInteger(x);        
        BigInteger zero = new BigInteger("0"); 
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");   
        BigInteger three = new BigInteger("3");
        BigInteger four = new BigInteger("4");     
        do{
            if (num.compareTo(one)==0) {
                break;
            }
            else if (num.mod(two).compareTo(zero)==0) {
                num = num.divide(two);
            }
            else if(num.compareTo(three)==0 || num.mod(four).compareTo(one)==0){
                num = num.subtract(one);
            }
            else{
                num = num.add(one);
            }
            count++;        
        }while(true);
        return count;
    }
}
