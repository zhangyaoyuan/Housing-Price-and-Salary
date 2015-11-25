public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0)return 0;
        //记录第i天以前的最低价
        int minPrice = prices[0];
        //记录最大利益
        int maxProfit = 0;
        for(int i=1; i<len; i++){
            //更新最低价
            if(prices[i-1] < minPrice){
                minPrice = prices[i-1];
            }
            //更新最大利益
            if(maxProfit < prices[i] - minPrice){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
