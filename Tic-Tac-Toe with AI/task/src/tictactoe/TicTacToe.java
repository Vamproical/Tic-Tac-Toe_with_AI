package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    final Random random = new Random();
    final Scanner scanner = new Scanner(System.in);
    private final char[][] table = new char[3][3];

    private void setNum(char player) {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the coordinates: ");
            String x1 = scanner.next();
            int x, y;
            try {
                x = Integer.parseInt(x1) - 1;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                return;
            }
            String y1 = scanner.next();
            try {
                y = Integer.parseInt(y1) - 1;
                if (x > 2 || x < 0 || y < 0 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (table[2 - y][x] == ' ') {
                        table[2 - y][x] = player;
                        printTable();
                        flag = false;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                return;
            }
        }
    }

    private void setNum() {
        boolean flag = true;
        while (flag) {
            int x = random.nextInt(3), y = random.nextInt(3);
            if (table[2 - y][x] == ' ') {
                System.out.println("Making move level \"easy\"");
                table[2 - y][x] = 'O';
                printTable();
                flag = false;
            }
        }
    }

    private String checkRes() {
        if (isWin('X') && isWin('O') || isImpossible()) {
            return "Impossible";
        } else if (!isWin('X') && !isWin('O') && isEmpty()) {
            return "Game not finished";
        } else if (!isEmpty() && !isWin('X') && !isWin('O')) {
            return "Draw";
        }
        if (isWin('O')) {
            return "O wins";
        } else if (isWin('X')) {
            return "X wins";
        }
        return "";
    }

    private void printTable() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private boolean isEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWin(char value) {
        boolean horizontal = (value == table[0][0] && value == table[0][1] && value == table[0][2]) ||
                (value == table[1][0] && value == table[1][1] && value == table[1][2]) ||
                (value == table[2][0] && value == table[2][1] && value == table[2][2]);
        boolean vertical = (value == table[0][0] && value == table[1][0] && value == table[2][0]) ||
                (value == table[0][1] && value == table[1][1] && value == table[2][1]) ||
                (value == table[0][2] && value == table[1][2] && value == table[2][2]);
        boolean diagonal = (value == table[0][0] && value == table[1][1] && value == table[2][2]) ||
                (value == table[0][2] && value == table[1][1] && value == table[2][0]);
        return horizontal || vertical || diagonal;
    }

    private boolean isImpossible() {
        int countX = 0, countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 'X') {
                    ++countX;
                } else if (table[i][j] == 'O') {
                    ++countO;
                }
            }
        }
        return countX - countO >= 2 || countO - countX >= 2;
    }

    public void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
        printTable();
        char player;
        player = 'X';
        while (true) {
            setNum(player);
            if (!checkRes().equals("Game not finished")) break;
            setNum();
            if (!checkRes().equals("Game not finished")) break;
        }
        System.out.println(checkRes());
    }
}
