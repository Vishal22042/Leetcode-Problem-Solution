class Solution {
    private List<String> ans = new ArrayList<>();
    private String[] map = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return ans;
        }

        backtrack(digits, 0, new StringBuilder());
        return ans;
    }

    private void backtrack(String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            sb.append(ch);
            backtrack(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}