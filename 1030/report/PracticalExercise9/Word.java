package PracticalExercise9;

import java.util.*;

public class Word {
    static final int SIZE = 5;
    static char[][] board = new char[SIZE][SIZE];
    static Random random = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            initBoard();

            System.out.print("단어>>");
            String word = sc.nextLine();

            if (word.equals("그만")) break;

            placeWord(word);
            fillEmpty();
            printBoard();
        }

        sc.close();
    }

    static void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '\0';
            }
        }
    }


    static void placeWord(String word) {
        int direction = random.nextInt(3);
        int x = random.nextInt(SIZE);
        int y = random.nextInt(SIZE);

        while (!canPlace(word, x, y, direction)) {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }

        for (int i = 0; i < word.length(); i++) {
            board[x][y] = word.charAt(i);
            if (direction == 0) y++;
            else if (direction == 1) x++;
            else { x++; y++; }
        }
    }

    static boolean canPlace(String word, int x, int y, int dir) {
        for (int i = 0; i < word.length(); i++) {
            if (x < 0  || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (board[x][y] != '\0') return false;

            if (dir == 0) y++;
            else if (dir == 1) x++;
            else { x++; y++; }
        }
        return true;
    }
    
    static void fillEmpty() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '\0') {
                    board[i][j] = (char)('a' + random.nextInt(26));
                }
            }
        }
    }

    static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}