class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        boolean[] broken = new boolean[26];


        for (int i = 0; i < brokenLetters.length(); i++) {
            char c = brokenLetters.charAt(i);
            broken[c - 'a'] = true;
        }

        int count = 0;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean valid = true;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (broken[c - 'a']) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}
