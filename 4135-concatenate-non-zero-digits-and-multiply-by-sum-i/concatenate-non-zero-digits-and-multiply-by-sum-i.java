class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        int sum = 0;

        if (n == 0) {
            return 0;
        }

        String s = String.valueOf(n);

        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                x = x * 10 + d;
                sum += d;
            }
        }

        return x * sum;
    }
}