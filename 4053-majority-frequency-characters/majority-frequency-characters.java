class Solution {
    public String majorityFrequencyGroup(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int[] groupSize = new int[s.length() + 1];

        for (int f : freq) {
            if (f > 0) {
                groupSize[f]++;
            }
        }

        int bestFreq = 0;
        int maxSize = 0;

        for (int k = 1; k <= s.length(); k++) {
            if (groupSize[k] > maxSize ||
                (groupSize[k] == maxSize && k > bestFreq)) {
                maxSize = groupSize[k];
                bestFreq = k;
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            if (freq[i] == bestFreq) {
                ans.append((char) ('a' + i));
            }
        }

        return ans.toString();
    }
}