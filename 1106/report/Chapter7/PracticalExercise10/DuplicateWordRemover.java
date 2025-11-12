package PracticalExercise10;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays; 

public class DuplicateWordRemover {

    private ArrayList<String> wordsList;

    public DuplicateWordRemover() {
        wordsList = new ArrayList<>();
    }

    private void searchAndRemove(String src, int j) {
        while (j < wordsList.size()) {
            String dest = wordsList.get(j);
            
            if (src.equals(dest)) {
                wordsList.remove(j);

            } else {
                j++;
            }
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("문자열들을 입력하세요>>");
            String line = sc.nextLine();

            if (line.equals("그만")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            wordsList.clear();

            String[] words = line.split(" ");
            wordsList.addAll(Arrays.asList(words));

            int i = 0;
            while (i < wordsList.size()) {
                String wordToCompare = wordsList.get(i);
                searchAndRemove(wordToCompare, i + 1);
                
                i++;
            }

            for (String word : wordsList) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        sc.close();
    }

    public static void main(String[] args) {
    	DuplicateWordRemover remover = new DuplicateWordRemover();
        remover.run();
    }
}
