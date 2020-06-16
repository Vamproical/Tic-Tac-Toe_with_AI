package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    final Scanner scanner = new Scanner(System.in);

    private void game(Player x, Player o) {
        Table table = new Table();

        while (table.continues()) {
            if (table.nextChar() == 'X') {
                x.move(table);
            } else {
                o.move(table);
            }
        }
        System.out.println(table);
        char winner = table.checkWinner();
        System.out.println(winner);
        switch (winner) {
            case 'X':
            case 'O':
                System.out.println(winner + " wins");
                break;
            case ' ':
                System.out.println("Draw");
                break;
            default:
                throw new IllegalStateException();
        }
    }

    public void menu() {
        Player[] players = new Player[2];

        while (true) {
            System.out.println("Input command: ");
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("exit")) {
                break;
            } else if (input.length < 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            players[0] = Player.of(input[1]);
            players[1] = Player.of(input[2]);
            if (players[0] == null || players[1] == null) {
                System.out.println("Bad parameters!");
            }
            game(players[0], players[1]);
        }
    }
}
