public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len < 3)return result;
        Arrays.sort(nums);
        for(int i = 0; i < len; i++) {
        if(i > 0 && nums[i] == nums[i - 1]) continue;           // 跳过重复的
        int target = 0 - nums[i];
        int j = i + 1, k = len - 1;
        while(j < k) {
            if(nums[j] + nums[k] == target) {
                result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                while(j < k && nums[j] == nums[j + 1]) j++;     // 跳过重复的
                while(j < k && nums[k] == nums[k - 1]) k--;     // 跳过重复的
                j ++; k--;
            } else if(nums[j] + nums[k] < target) {
                j ++;
            } else {
                k --;
            }
        }
    }
        return result;
    }
}