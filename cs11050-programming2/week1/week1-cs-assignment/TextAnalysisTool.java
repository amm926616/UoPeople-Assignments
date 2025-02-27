import java.util.*;

public class TextAnalysisTool {
    
    // Method to calculate the total number of characters in the text
    public static int getCharacterCount(String text) {
        return text.replaceAll("\\s", "").length();
    }

    // Method to calculate the total number of words in the text
    public static int getWordCount(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Method to find the most common character
    public static char getMostCommonCharacter(String text) {
        text = text.toLowerCase(); // make it case insensitive
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }
        return Collections.max(charCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Method to calculate the frequency of a specific character
    public static int getCharacterFrequency(String text, char character) {
        text = text.toLowerCase(); // make it case insensitive
        int frequency = 0;
        for (char c : text.toCharArray()) {
            if (Character.toLowerCase(c) == character) {
                frequency++;
            }
        }
        return frequency;
    }

    // Method to calculate the frequency of a specific word
    public static int getWordFrequency(String text, String word) {
        text = text.toLowerCase(); // make it case insensitive
        String[] words = text.split("\\s+");
        int frequency = 0;
        for (String w : words) {
            if (w.equals(word.toLowerCase())) {
                frequency++;
            }
        }
        return frequency;
    }

    // Method to calculate the number of unique words in the text
    public static int getUniqueWordsCount(String text) {
        text = text.toLowerCase(); // make it case insensitive
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(text.split("\\s+")));
        return uniqueWords.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Asking the user to input a paragraph
        System.out.println("Enter a paragraph or text:");
        String text = scanner.nextLine();

        // Character count
        int characterCount = getCharacterCount(text);
        System.out.println("Total number of characters (excluding spaces): " + characterCount);

        // Word count
        int wordCount = getWordCount(text);
        System.out.println("Total number of words: " + wordCount);

        // Most common character
        char mostCommonChar = getMostCommonCharacter(text);
        System.out.println("Most common character: " + mostCommonChar);

        // Character frequency
        System.out.print("Enter a character to check its frequency: ");
        char charToCheck = scanner.next().charAt(0);
        int charFrequency = getCharacterFrequency(text, charToCheck);
        System.out.println("Frequency of character '" + charToCheck + "': " + charFrequency);

        // Word frequency
        scanner.nextLine(); // to consume the leftover newline
        System.out.print("Enter a word to check its frequency: ");
        String wordToCheck = scanner.nextLine();
        int wordFrequency = getWordFrequency(text, wordToCheck);
        System.out.println("Frequency of word '" + wordToCheck + "': " + wordFrequency);

        // Unique words count
        int uniqueWordsCount = getUniqueWordsCount(text);
        System.out.println("Number of unique words: " + uniqueWordsCount);

        scanner.close();
    }
}

