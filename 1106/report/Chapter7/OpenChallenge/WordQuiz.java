package OpenChallenge;

import java.util.Vector;
import java.util.Scanner;
import java.util.ArrayList;

public class WordQuiz {

    private Vector<Word> words;
    private Scanner sc;

    public WordQuiz() {
        words = new Vector<>();
        sc = new Scanner(System.in);

        words.add(new Word("painting", "그림"));
        words.add(new Word("emotion", "감정"));
        words.add(new Word("baby", "아기"));
        words.add(new Word("error", "오류"));
        words.add(new Word("society", "사회"));
        words.add(new Word("bear", "곰"));
        words.add(new Word("eye", "눈"));
        words.add(new Word("picture", "사진"));
        words.add(new Word("human", "인간"));

    }


    public void run() {
        System.out.println("\"명품영어\"의 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
        System.out.println("현재 " + words.size() + "개의 단어가 들어 있습니다.");

        while (true) {
            int correctIndex = (int)(Math.random() * words.size());
            Word correctWord = words.get(correctIndex);

            int correctPosition = (int)(Math.random() * 4);
            ArrayList<Integer> decoyIndices = new ArrayList<>();
            while (decoyIndices.size() < 3) {
                int decoyIndex = (int)(Math.random() * words.size());
                if (decoyIndex != correctIndex && !decoyIndices.contains(decoyIndex)) {
                    decoyIndices.add(decoyIndex);
                }
            }
            String[] options = new String[4];
            options[correctPosition] = correctWord.getKorean();
            int decoyCursor = 0;
            for (int i = 0; i < 4; i++) {
                if (i == correctPosition) {
                    continue; 
                }
                int decoyWordIndex = decoyIndices.get(decoyCursor++);
                options[i] = words.get(decoyWordIndex).getKorean();
            }
            System.out.println(correctWord.getEnglish() + "?");
            for (int i = 0; i < 4; i++) {
                System.out.print("(" + (i + 1) + ")" + options[i] + " ");
            }
            System.out.print(">>");
            try {
                int userAnswer = sc.nextInt();

                if (userAnswer == -1) {
                    System.out.println("\"명품영어\"를 종료합니다...");
                    break;
                }
                if (userAnswer == correctPosition + 1) {
                    System.out.println("Excellent !!");
                } else {
                    System.out.println("No. !!");
                }
            } catch (Exception e) {
                System.out.println("숫자를 입력하세요!");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        WordQuiz quiz = new WordQuiz();
        quiz.run();
    }
}