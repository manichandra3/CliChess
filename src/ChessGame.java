import javax.swing.*;
import java.util.Scanner;
import java.awt.*;

public class ChessGame {
    //public static void undo(char[][])
    public static void movePiece(char[][] board, String startPosition, String finalPosition) {
        int startRow = 8 - Character.getNumericValue(startPosition.charAt(1));
        int startCol = startPosition.charAt(0) - 'a';
        int finalRow = 8 - Character.getNumericValue(finalPosition.charAt(1));
        int finalCol = finalPosition.charAt(0) - 'a';

        char piece = board[startRow][startCol];
        char targetPiece = board[finalRow][finalCol];

        // TODO: Implement move validation for different pieces here
        // For now, let's implement basic validation for pawn moves
        boolean isValidMove = isValidPawnMove(board, startPosition);

        if (isValidMove) {
            board[finalRow][finalCol] = piece;
            board[startRow][startCol] = ' '; // Empty the source square
        } else {
           System.out.println("Invalid move. Try again.");
        }
    }

    public static boolean isValidPawnMove(char[][] board,String start) {
        // Implement basic move validation for pawns
        int startRow = 8 - Character.getNumericValue(start.charAt(1));
        int startCol = start.charAt(0) - 'a';
        return !(board[startRow][startCol] == ' ');// Default to invalid move
    }

    public static void main(String[] args) {
        // Initialize the chessboard
        char[][] board = {
                {'8', 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                {'7', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'6', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'5', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'4', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'3', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'2', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'1', 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {' ', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'}
        };

        boolean isWhiteTurn = true;
        Scanner scanner = new Scanner(System.in);
        boolean isCheckmate = false;
        while (!isCheckmate) {
            // Display the current board
            displayBoard(board);

            // Determine the current player's color
            char currentPlayer = isWhiteTurn ? 'W' : 'B';

            // Prompt the current player for their move
            System.out.println("Player " + currentPlayer + ", enter your move (e.g., 'e2 e4'): ");
            String move = scanner.nextLine();


            String[] moveParts = move.split(" ");
            if (moveParts.length != 2) {
                System.out.println("Invalid move format. Try again.");
                continue;
            }

            String fromSquare = moveParts[0];
            String toSquare = moveParts[1];

            // Call the movePiece method to validate and update the board
            movePiece(board, fromSquare, toSquare);

            // Check for game over conditions (e.g., checkmate, stalemate)
            // TODO: Implement game over conditions here

            // Toggle the turn to the other player
            isWhiteTurn = !isWhiteTurn;
        }
    }

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
