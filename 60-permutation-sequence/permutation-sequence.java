import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
        
        k--;
        
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int blockCount = factorial[i - 1];
            int index = k / blockCount;
            sb.append(numbers.get(index));
            numbers.remove(index); 
            k %= blockCount;
        }
        
        return sb.toString();
    }
}
