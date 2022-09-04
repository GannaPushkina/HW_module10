import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ValidPhoneNumbers {

    private static void printCorrectPhoneNumbers(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            ArrayList<String> phoneNumbers = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                phoneNumbers.add(line);
            }

            System.out.println(phoneNumbers); // дивимось все, що містить файл

            for (String number : phoneNumbers) {
                if (isLengthCorrect(number) && (checkLength12(number) | checkLength14(number))) {
                    System.out.println(number);
                    }
                }
            }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isCharFigure(char s) {
        return s == '0' ||
               s == '1' ||
               s == '2' ||
               s == '3' ||
               s == '4' ||
               s == '5' ||
               s == '6' ||
               s == '7' ||
               s == '8' ||
               s == '9';
    }

    private static boolean isLengthCorrect(String s) {
        return s.length() == 14 || s.length() == 12;
    }

    private static boolean checkLength12 (String s) {
        return isCharFigure(s.charAt(0)) &&
                isCharFigure(s.charAt(1)) &&
                isCharFigure(s.charAt(2)) &&
                s.charAt(3) == '-' &&
                isCharFigure(s.charAt(4)) &&
                isCharFigure(s.charAt(5)) &&
                isCharFigure(s.charAt(6)) &&
                s.charAt(7) == '-' &&
                isCharFigure(s.charAt(8)) &&
                isCharFigure(s.charAt(9)) &&
                isCharFigure(s.charAt(10)) &&
                isCharFigure(s.charAt(11));
    }

    private static boolean checkLength14 (String s) {
        return s.charAt(0) == '(' &&
                isCharFigure(s.charAt(1)) &&
                isCharFigure(s.charAt(2)) &&
                isCharFigure(s.charAt(3)) &&
                s.charAt(4) == ')' &&
                s.charAt(5) == ' ' &&
                isCharFigure(s.charAt(6)) &&
                isCharFigure(s.charAt(7)) &&
                isCharFigure(s.charAt(8)) &&
                s.charAt(9) == '-' &&
                isCharFigure(s.charAt(10)) &&
                isCharFigure(s.charAt(11)) &&
                isCharFigure(s.charAt(12)) &&
                isCharFigure(s.charAt(13));
    }

    public static void main(String[] args) {
        printCorrectPhoneNumbers("src/phoneNumbers.txt");
    }
}
