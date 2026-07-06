import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] closestPrimes(int left, int right) {
        boolean[] isPrime = new boolean[right + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        if (right >= 1) isPrime[1] = false;

        for (int p = 2; p * p <= right; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= right; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        int[] ans = new int[]{-1, -1};
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < primes.size() - 1; i++) {
            int num1 = primes.get(i);
            int num2 = primes.get(i + 1);
            int diff = num2 - num1;

            if (diff < minDiff) {
                minDiff = diff;
                ans[0] = num1;
                ans[1] = num2;
            }
        }

        return ans;
    }
}
