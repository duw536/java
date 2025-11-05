package PracticalExercise10;

import java.util.*;

public class WordGame2 {
    public static void main(String[] args) {
        String[] words = {"happy","morning","package","together","love","sunny","nation","connect","present"};
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("10초 안에 단어를 맞추세요!!");

        while (true) {
            int idx = r.nextInt(words.length);
            String word = words[idx];

            char[] arr = word.toCharArray();
            for (int i = 0; i < 50; i++) {
                int x = r.nextInt(arr.length);
                int y = r.nextInt(arr.length);
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }

            System.out.println(arr);
            System.out.print(">>");

            long start = System.currentTimeMillis();
            String input = sc.nextLine();
            if (input.equals("그만")) break;
            long end = System.currentTimeMillis();
            double sec = (end - start) / 1000.0;

            if (input.equals(word) && sec <= 10) {
                System.out.printf("성공!!! %.3f초 경과\n", sec);
            } else if (sec > 10) {
                System.out.printf("실패!!! 10초 초과, %.3f초 경과\n", sec);
            } else {
                System.out.println("실패!!! " + word + " 입니다." + sec + "초 경과");
            }
        }
        sc.close();
    }
}