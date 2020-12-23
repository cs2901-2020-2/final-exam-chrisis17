package cslab;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Logger;

public class Blockudoku {

    static final Logger logger = Logger.getLogger(Blockudoku.class.getName());

    public static int[][] generateBoard(){
        int[][] board = new int[9][9];

        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                board[row][column] = 0;
            }
        }
        return board;
    }

    public static char getPiece(){
        char[] pieces = {'a','b','c','d','e','f','g','h'};
        SecureRandom random = new SecureRandom();
        int randomChar = random.nextInt(9);
        return pieces[randomChar];
    }

    public static int[][] doMovement(char piece, int inputCoord, int[][] board){
        for (int col=8; col>1; col--){
            if (piece == 'a' || piece == 'c' || piece == 'e'){
                if (board[inputCoord][col] == 0 && board[inputCoord-1][col] == 0 && board[inputCoord-2][col] == 0){
                    board[inputCoord][col] = 1;
                    board[inputCoord-1][col] = 1;
                    board[inputCoord-2][col] = 1;
                    if (piece == 'a'){
                        board[inputCoord-2][col-1] = 1;
                        board[inputCoord-2][col-2] = 1;
                    }
                    if (piece == 'e'){
                        board[inputCoord-1][col-1] = 1;
                        board[inputCoord-1][col-2] = 1;
                    }
                    return board;
                }
            }
            else if(piece == 'b' || piece == 'd' || piece == 'f' || piece == 'h'){
                if (board[inputCoord][col] == 0){
                    if (piece == 'b'){
                        board[inputCoord][col] = 1;
                        board[inputCoord][col-1] = 1;
                        board[inputCoord][col-2] = 1;
                        board[inputCoord-1][col-2] = 1;
                        board[inputCoord-2][col-2] = 1;
                    }
                    if (piece == 'd'){
                        board[inputCoord][col] = 1;
                        board[inputCoord][col-1] = 1;
                        board[inputCoord][col-2] = 1;
                    }
                    if (piece == 'f'){
                        board[inputCoord][col] = 1;
                        board[inputCoord][col-1] = 1;
                        board[inputCoord][col-2] = 1;
                        board[inputCoord-1][col-2] = 1;
                        board[inputCoord+1][col-2] = 1;
                    }
                    if (piece == 'h'){
                        board[inputCoord][col] = 1;
                        board[inputCoord][col-1] = 1;
                        board[inputCoord][col-2] = 1;
                        board[inputCoord+1][col-2] = 1;
                    }
                    return board;
                }
            }
            else{
                if (board[inputCoord][col] == 0 && board[inputCoord-1][col] == 0){
                    board[inputCoord][col] = 1;
                    board[inputCoord-1][col] = 1;
                    board[inputCoord-1][col-1] = 1;
                    board[inputCoord-1][col-2] = 1;
                    return board;
                }
            }
        }
        return board;
    }

    public static int winPoints(int[][] board){
        int points = 0;
        for (int row = 0; row <9; row++){
            boolean winColumn = true;
            for (int col = 0; col < 9; col++){
                if (board[row][col] == 0)
                    winColumn = false;
            }
            if (winColumn){
                points += 120;
                for (int col = 0; col < 9; col++){
                    board[row][col] = 0;
                }
            }
        }
        for (int col = 0; col <9; col++){
            boolean winRow = true;
            for (int row = 0; row < 9; row++){
                if (board[row][col] == 0)
                    winRow = false;
            }
            if (winRow){
                points += 120;
                for (int row = 0; row < 9; row++){
                    board[row][col] = 0;
                }
            }
        }

        return points;
    }

    public static boolean checkBoard(int[][] newBoard, int[][] oldBoard){
        for (int row=0; row<9; row++){
            for (int col=0; col<9; col++){
                if (newBoard[row][col] != oldBoard[row][col])
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args){
        logger.info("Iniciando Blockudoku...");
        int[][] board = generateBoard();
        int points = 0;
        boolean gaming = true;
        while (gaming) {
            char piece = getPiece();
            Scanner input = new Scanner(System.in);
            int inputCoord = input.nextInt();
            int[][] newBoard = doMovement(piece, inputCoord, board);
            if (checkBoard(newBoard, board))
                gaming = false;
            points += winPoints(newBoard);
        }
        logger.info("Game Over");
    }
}
