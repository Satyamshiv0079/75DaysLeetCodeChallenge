class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words){
            wordCount.merge(word, 1, Integer::sum);
        }

        List<Integer> result = new ArrayList<>();
        int stringLength = s.length();
        int wordArrayLength = words.length;
        int wordLength = words[0].length();

        for (int startPos = 0; startPos < wordLength; startPos++) {
            int left = startPos;
            int right = startPos;
            Map<String, Integer> currentWindowCount = new HashMap<>();

            while (right + wordLength <= stringLength){
                String currentWord = s.substring(right, right + wordLength);
                right += wordLength;

                if (!wordCount.containsKey(currentWord)) {
                    currentWindowCount.clear();
                    left = right;
                    continue;
                }

                currentWindowCount.merge(currentWord, 1, Integer::sum);

                while (currentWindowCount.get(currentWord) > wordCount.get(currentWord)) {

                    String leftWord = s.substring(left, left + wordLength);

                    if (currentWindowCount.merge(leftWord, -1, Integer::sum) == 0) {
                        currentWindowCount.remove(leftWord);
                    }

                    left += wordLength;
                }

                if (right - left == wordArrayLength * wordLength) {
                    result.add(left);
                }
            }
        }
        return result;
    }
}