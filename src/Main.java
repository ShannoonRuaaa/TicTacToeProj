import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isvalid = false;
        while (!isvalid) {
            System.out.println("select player mode: 1 player mode (1) / 2 player mode (2)");
            String mode = scanner.next();
            if (mode.equals("2")) {
                isvalid = true;
                boolean game = true;
                int term = 0;
                boolean[][] board1 = new boolean[3][3];
                boolean[][] board2 = new boolean[3][3];
                int at = 0;
                while (game) {
                    System.out.println("Player " + (at + 1) + ", please enter your move");
                    boolean valid = false;
                    while (!valid) {
                        int y = scanner.nextInt();
                        int x = scanner.nextInt();
                        term++;
                        x--;
                        y--;
                        if (x > 2 || y > 2) {
                            System.out.println("Invalid move, please try again");
                            term--;
                        } else if (board1[x][y] || board2[x][y]) {
                            System.out.println("Invalid move, please try again");
                            term--;
                        } else if (at == 0) {
                            board1[x][y] = true;
                            valid = true;
                        } else if (at == 1) {
                            board2[x][y] = true;
                            valid = true;
                        }
                    }
                    at++;
                    at %= 2;
                    printBoard(board1, board2);
                    if (!testwin(board1, board2)) {
                        break;
                    } else if (term == 9) {
                        System.out.println("Tie");
                        break;
                    }
                }
            } else if (mode.equals("1")) {
                isvalid = true;
                boolean game = true;
                int term = 0;
                boolean[][] board1 = new boolean[3][3];
                boolean[][] board2 = new boolean[3][3];
                while (game) {
                    System.out.println("Player 1, please enter your move");
                    boolean valid = false;
                    while (!valid) {
                        int y = scanner.nextInt();
                        int x = scanner.nextInt();
                        x--;
                        y--;
                        term++;
                        if (x > 2 || y > 2) {
                            System.out.println("Invalid move, please try again");
                            term--;
                        } else if (board1[x][y] || board2[x][y]) {
                            System.out.println("Invalid move, please try again");
                            term--;
                        } else {
                            board1[x][y] = true;
                            valid = true;
                        }
                    }
                    printBoard(board1, board2);
                    if (!testwin(board1, board2)) {
                        break;
                    } else if (term == 9) {
                        System.out.println("Tie");
                        break;
                    }
                    System.out.println("Computer's turn");
                    boolean valid2 = false;
                    while (!valid2) {
                        int x = (int) (Math.random() * 3);
                        int y = (int) (Math.random() * 3);
                        term++;
                        if (board1[x][y] || board2[x][y]) {
                            term--;
                        } else {
                            board2[x][y] = true;
                            valid2 = true;
                        }
                    }
                    printBoard(board1, board2);
                    if (!testwin(board1, board2)) {
                        break;
                    } else if (term == 9) {
                        System.out.println("Tie");
                        break;
                    }
                }
            } else {
                System.out.println("Invalid input, please try again");
            }
//        System.out.println("Hello world!");
        }

    }
    public static void printBoard(boolean[][] board1, boolean[][] board2){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board1[i][j] == true){
                    System.out.print("X");
                }else if(board2[i][j] == true){
                    System.out.print("O");
                }else{
                    System.out.print(" ");
                }
                if(j != 2){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i != 2){
                System.out.println("-----");
            }
        }
    }

    public static boolean winningboard(boolean[][] board){
        boolean horizontal = false;
        boolean vertical = false;
        boolean diagonal = false;
        for (int i = 0; i< 3; i++){
            boolean temp = true;
            for (int j = 0; j< 3; j++){
                temp = temp && board[i][j];
            }
            horizontal = horizontal || temp;
        }
        for (int i = 0; i< 3; i++){
            boolean temp = true;
            for (int j = 0; j< 3; j++){
                temp = temp && board[j][i];
            }
            vertical = vertical || temp;
        }
            boolean temp = true;
            for (int j = 0; j< 3; j++){
                temp = temp && board[j][j];
            }
            diagonal = diagonal || temp;
            temp = true;
            for (int j = 0; j< 3; j++){
                temp = temp && board[j][2-j];
            }
            diagonal = diagonal || temp;
        return horizontal || vertical || diagonal;
    }
    public static boolean testwin( boolean[][]board1, boolean[][]board2){
        boolean game;
        if (winningboard(board1)){
            System.out.println("Player 1 wins!"); game = false;
        }else if (winningboard(board2)){
            System.out.println("Player 2 wins!"); game = false;
        }else {game = true;}
//        if (board1[0][0] && board1[0][1] && board1[0][2]){
//            System.out.println("Player 1 wins!"); game = false;
//        }else if (board1[1][0] && board1[1][1] && board1[1][2]){
//            System.out.println("Player 1 wins!"); game = false;
//        } else if (board1[2][0] && board1[2][1] && board1[2][2]) {
//            System.out.println("Player 1 wins!"); game = false;
//        } else if (board1[0][0] && board1[1][0] && board1[2][0]) {
//            System.out.println("Player 1 wins!");  game = false;
//        } else if (board1[0][1] && board1[1][1] && board1[2][1]) {
//            System.out.println("Player 1 wins!"); game = false;
//        } else if (board1[0][2] && board1[1][2] && board1[2][2]) {
//            System.out.println("Player 1 wins!"); game = false;
//        } else if (board1[0][0] && board1[1][1] && board1[2][2]) {
//            System.out.println("Player 1 wins!"); game = false;
//        } else if (board1[0][2] && board1[1][1] && board1[2][0]) {
//            System.out.println("Player 1 wins!"); game = false;
//        } else if (board2[0][0] && board2[0][1] && board2[0][2]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[1][0] && board2[1][1] && board2[1][2]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[2][0] && board2[2][1] && board2[2][2]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[0][0] && board2[1][0] && board2[2][0]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[0][1] && board2[1][1] && board2[2][1]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[0][2] && board2[1][2] && board2[2][2]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[0][0] && board2[1][1] && board2[2][2]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else if (board2[0][2] && board2[1][1] && board2[2][0]) {
//            System.out.println("Player 2 wins!"); game = false;
//        } else {
//            game = true;
//        }
        return game;
    }
}