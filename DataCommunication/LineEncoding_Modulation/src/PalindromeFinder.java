public class PalindromeFinder {

    public static void main(String[] args ) {
        // String input = "-1 0 1 1 0 -1 1 0 0 1 -1";
        // String longestPalindrome = findLongestPalindrome(input);
        // System.out.println("Longest Palindromic Substring: " + longestPalindrome);
    }

    public static String findLongestPalindrome(String input) {
        // Remove spaces from the input string
        String cleanInput = input.replace(" ", "");

        int start = 0, maxLength = 1; // Start index and max length of longest palindrome found

        for (int i = 0; i < cleanInput.length(); i++) {
            // Expand around the center for odd length palindrome
            int len1 = expandAroundCenter(cleanInput, i, i);
            // Expand around the center for even length palindrome
            int len2 = expandAroundCenter(cleanInput, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > maxLength) {
                maxLength = len;
                start = i - (len - 1) / 2;
            }
        }

        return cleanInput.substring(start, start + maxLength);
    }

    // Helper function to expand around center and return length of palindrome
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of the palindrome
    }
}
