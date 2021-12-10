import java.util.Scanner;
import java.util.ArrayList;
public class practice {
    public static boolean isSafeToPlaceNumber(char[][] board, int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++) {
            if (board[r][i] - '0' == num)
                return false;
        }

        // col
        for (int i = 0; i < 9; i++) {
            if (board[i][c] - '0' == num)
                return false;
        }

        // mat
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num)
                    return false;
            }
        }

        return true;
    }

    public static boolean solveSudoku(char[][] board, ArrayList<Integer> emptyIndex, int idx) {
        if (idx == emptyIndex.size()) {
            return true;
        }

        int cell = emptyIndex.get(idx);
        int r = cell / 9;
        int c = cell % 9;

        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, emptyIndex, idx + 1))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    public static void solveSudoku(char[][] board) {
        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    emptyIndex.add(i * 9 + j);
            }
        }

        solveSudoku(board, emptyIndex, 0);
    }

    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        int t;
        t = scn.nextInt();
        char[][] board = new char[9][9];
        while (t-- > 0) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; i < 9; j++) {
                    board[i][j] = scn.next().charAt(0);
                }
            }
            solveSudoku(board);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
        scn.close();
    }
    
}
