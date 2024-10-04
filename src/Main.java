import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int moves=0;
    public static void main(String[] args) {
        System.out.println("\nWelcome to Tic-Tac-Toe!");
        char[][] board= new char[3][3];
        for(int i=0;i<3;i++){
            Arrays.fill(board[i],'_');
        }
        displayBoard(board);
        outer: while(true){
            int rem=moves%2;
            switch (rem){
                case 0 ->{
                    System.out.println("\nPlayer X turn");
                    getMoves(board, 'X');
                    if(checkWinner(board, 'X')){
                        break outer;
                    }
                }
                case 1 ->{
                    System.out.println("\nPlayer O turn");
                    getMoves(board, 'O');
                    if(checkWinner(board, 'O')){
                        break outer;
                    }
                }
            }
            displayBoard(board);
//            breaking the loop when the board fills
            if(moves==9){
                break;
            }
        }
//        check winner in the final move
        boolean xWin=checkWinner(board, 'X');
        boolean oWin=checkWinner(board, 'O');
        if(xWin){
            System.out.println("\nPlayer X wins!");
        }
        else if(oWin){
            System.out.println("\nPlayer O wins!");
        }
        else{
            System.out.println("\nGame Draw!");
        }
        System.out.println("\nFinal Board:");
        displayBoard(board);

    }

    private static boolean checkWinner(char[][] board, char x) {
        boolean isWinnerRow=true;
        boolean isWinnerCol=true;
        boolean isWinnerDiagonal=false;
        for(int i=0;i<3;i++){
//            check winner in a row
            if(isWinnerRow) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != x) {
                        isWinnerRow = false;
                        break;
                    }
                }
                if(isWinnerRow){
                    return true;
                }
            }
        }

        for(int i=0;i<3;i++){
//            check winner in a column
            if(isWinnerCol) {
                for(int j=0;j<3;j++){
                    if(board[j][i]!=x){
                        isWinnerCol=false;
                        break;
                    }
                }
                if(isWinnerCol){
                    return true;
                }
            }
        }

//        check winner in diagonal
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[2][2]==x ||
                board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[2][0]==x){
            isWinnerDiagonal=true;
        }
        return isWinnerDiagonal;
    }

//    getting moves form player
    public static void getMoves(char[][] board, char player){
        System.out.println("Enter your move (row and column): ");
        int row, col;
//        validating moves
        while(true) {
            try {
                row = sc.nextInt();
                col = sc.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input");
                sc.nextLine();
                continue;
            }

            if((row<0 || col<0) || (row>2 || col>2)){
                System.out.println("Input should be 0, 1, or 2");
            }
            else if(board[row][col] != '_'){
                System.out.println("Move already taken!");
            }
            else if (board[row][col] == '_'){
                board[row][col] = player;
                moves++;
                break;
            }
        }
    }

//    displaying the board
    public static void displayBoard(char[][] board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
    }
}