import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WordFrequency {

    private static String readFileIntoString(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private static void countWordFrequency(String fileName) throws IOException {
        String[] words = readFileIntoString(fileName)
                .replaceAll("\\n", " ")
                .replaceAll("\\s+", " ")
                .split(" ");

        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            int newValue = counter.getOrDefault(word, 0) + 1;
            counter.put(word, newValue);
        }

        Map<String, Integer> sortedMap = counter.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        sortedMap.entrySet().forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        countWordFrequency("src/words.txt");
    }
}