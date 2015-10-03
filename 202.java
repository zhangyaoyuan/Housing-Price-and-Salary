public class Solution {
    public boolean isHappy(int n) {
        if(n <= 0)return false;
        if(n == 1)return true;
        int sum = 0;
        //利用set记录循环中出现的数
        HashSet<Integer> hs = new HashSet<Integer>();
        while(true){
            sum = squareSum(n);
            if(sum == 1)return true;
            //如果平方和重复出现了，则输出false
            else if(hs.contains(sum))return false;
            hs.add(sum);
            n = sum;
        }
    }
    //计算平方和
    public int squareSum(int n){
        int result = 0;
        int digits = 0;
        while(n != 0){
            digits = n%10;
            n /= 10;
            result += (digits*digits);
        }
        return result;
    }
}