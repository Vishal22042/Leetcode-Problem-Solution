class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        while (i < n && s.charAt(i) == ' '){
            i++;
        }
        int sign = 1;
        if(i < n && s.charAt(i) == '-'){
            sign = -1;
            i++;
        }
        else if (i < n && s.charAt(i) == '+'){
            i++;
        }
        long num = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            num = num * 10 + (s.charAt(i) - '0');

            if(sign * num > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            if(sign * num < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int)(sign * num);
    }
}