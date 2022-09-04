import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserConverter {

    public static void convertTxtToJson(String sourceFile, String destinationFile) {
        File usersFileFrom = new File(sourceFile);
        ArrayList<User> users = getUsersFrom(usersFileFrom);
        writeUsersToJsonFile(users, destinationFile);
    }


    // отримуємо нового Юзера з 2-елементного масиву стрінгів
    private static User createUserFromLine(String nextLine) {
        String[] strings = nextLine.split(" ");
        return new User(strings[0], Integer.parseInt(strings[1]));
    }


    // отримуємо колекцію Юзерів з переданого файлу за допомогою createUserFromLine
    private static ArrayList<User> getUsersFrom(File sourceFile) {

        ArrayList<User> usersArrayList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(sourceFile))) {
            boolean isHeader = true;
            while (scanner.hasNext()) {

                if (isHeader) {
                    scanner.nextLine();
                    isHeader = false;
                    continue;
                }
                usersArrayList.add(createUserFromLine(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersArrayList;
    }

    // записуємо колекцію ЮзерУ у json
    private static void writeUsersToJsonFile(ArrayList<User> users, String destinationFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);

        try (FileWriter fileWriter = new FileWriter(destinationFile)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        convertTxtToJson("src/users.txt", "src/users.json");
    }
}


class User implements Serializable {
    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

