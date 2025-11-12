package PracticalExercise10;

import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.Arrays;

public class DuplicateRemover {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("문자열들을 입력하세요>>");
            String line = sc.nextLine();

            if (line.equals("그만")) break;

            String[] words = line.split(" ");

            LinkedHashSet<String> uniqueWords = new LinkedHashSet<>( Arrays.asList(words) );

            for (String word : uniqueWords) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
