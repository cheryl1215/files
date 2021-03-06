	static int subArraySum(int arr[], int s) 
    {
		int sum = arr[0], j= 0;
        for (int i = 1; i <= arr.length; i++) 
        {
            while (sum > s && j < i-1){
            	sum -=arr[j++];
            }
            if (sum == s) {
                int p = i-1;
                System.out.println("Sum found between indexes " + j+ " and " + p);
                return 1;
            }
            if (i < arr.length) sum = sum + arr[i];
        }
        System.out.println("No subarray found");
        return 0;
    }
//compress the row or col to let it become a one-dimensional problem, so that we can use the algo above to solve it
//the code below uses O(m^2 * n) time and O(n) space,if m is larger than n,switch them in loops, so the real complexity is
//O(min(m^2*n, n^2*m)) time, O(n or m) space
public class Solution {
    public boolean subMatrixSum(int[][] nums, int k) {
        if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
            return false;
        }
        int m = nums.length;
        int n = nums[0].length;
        for (int i = 0; i < m; i++) {//O(m), i to m are rows that we want to compress
            int[] row = new int[n];//O(n) space, store the compressed rows
            for (int j = i; j < m; j++) {//O(m)
                for (int k = 0; k < n; k++) {//O(n)
                    row[k] += nums[j][k];
                }
                if (subArraySum(row, k)) {//O(n)
                    return true;
                }
            }
        }
        return false;
    }
}
//the process of algo above:
Loop1: Row1: 1, 2, 3 -> Row1+Row2: 5, 7, 9 -> Row1+Row2+Row3: 12, 15, 18
Loop2: Row2: 4, 5, 6 -> Row2+Row3: 11, 13, 15
Loop3: Row3: 7, 8 ,9

1 2 3
4 5 6
7 8 9

3 * 3 matrix, if we compress by rows, then it can be
compress 1 row
Row1: 1, 2, 3
Row2: 4, 5, 6
Row3: 7, 8 ,9

compress 2 rows
Row1+Row2: 5, 7, 9
Row2+Row3: 11, 13, 15

compress 3 rows
Row1+Row2+Row3: 12, 15, 18

For each m * n matrix, if we compress it by rows, it can be compressed by 1 row, 2 rows, 3 rows, ..., m - 1 rows, m rows. That is to say, there are  m + m -1 + m -2 + ... + 2 + 1 = m * (m + 1) / 2 = O(m^2) ways. For each way, we can reuse the linear solution of previous problem. Therefore, the time complexity is O(m ^ 2 * n). Or compressed by columns, it should be O(n ^ 2 * m). It depends which one is smaller...

// if all nums are positive nums, we can use two pointer(silding window, corner case is i==a.length && sum<target ->break)
// if the array has negative nums, we have to use extra space (hashmap)