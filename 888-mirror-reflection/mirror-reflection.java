class Solution {
    public int mirrorReflection(int p, int q) {
        
        int gcd = gcd(p, q);
        
        
        p /= gcd;
        q /= gcd;
        
        
        if (p % 2 == 0) {
            return 2; 
        } else {
            if (q % 2 == 0) {
                return 0;
            } else {
                return 1; 
            }
        }
    }
    
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
