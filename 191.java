public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            //低位为1，那么n&1等于1
            if((n&1) == 1) ++count;
            //无符号右移
            n >>>= 1;
        }
        return count;
    }
}