class Solution {
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        int i = 0, n = s.length();
        
        while (i < n && s.charAt(i) == ' ') i++;
        
        if (i == n) return 0;
        
        int sign = 1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            sign = (s.charAt(i++) == '-') ? -1 : 1;
        }
        
        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i++) - '0');
            
            if (sign * result <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
            if (sign * result >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        
        return (int) (sign * result);
    }
}
