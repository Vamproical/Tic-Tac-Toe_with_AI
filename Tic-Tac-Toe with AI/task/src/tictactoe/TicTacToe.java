package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    final Scanner scanner = new Scanner(System.in);
    String initLine;

    public void startGame() {
        System.out.println("Enter cells: ");
        initLine = scanner.nextLine();

    }
}
