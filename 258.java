public class Solution {
    public int addDigits(int num) {
        //因为从1到无穷的num值，经过此规则处理后是重复的1-9.对于mod 9运算
        //只会得到0-8，所以num-1后进行mod 9运算，然后加1
        return (num-1)%9 + 1;
    }
}