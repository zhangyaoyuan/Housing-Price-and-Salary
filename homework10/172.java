public class Solution {
    public int trailingZeroes(int n) {
        int num_zero = 0;
        while(n>1)num_zero += (n/=5);
        return num_zero;
    }
}