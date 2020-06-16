package tictactoe;

public class Table {
    private char[][] table;
    private boolean isNext;
    private char win = 'N';

    public Table() {
        isNext = true;
        table = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    boolean isEmpty(int x, int y) {
        int column = x - 1;
        int row = 3 - y;
        return table[row][column] == ' ';
    }

    boolean isEmpty(Point point) {
        return isEmpty(point.getX(), point.getY());
    }

    void setXO(int x, int y) {
        if (!isEmpty(x, y) || win != 'N') {
            throw new IllegalStateException();
        }
        int column = x - 1;
        int row = 3 - y;
        table[row][column] = nextChar();
        isNext = !isNext;
        win = checkWinner();
    }

    void set(Point point) {
        int x = point.getX();
        int y = point.getY();
        setXO(x,y);
    }

    void unset(int x, int y) {
        if (isEmpty(x, y)) {
            throw new IllegalStateException();
        }

        int column = x - 1;
        int row = 3 - y;

        table[row][column] = ' ';
        isNext = !isNext;

        win = checkWinner();
    }

    char checkWinner() {
        if (checkRows('X') || checkColumns('X') || checkDiagonals('X')) {
            return 'X';
        } else if (checkRows('O') || checkColumns('O') || checkDiagonals('O')) {
            return 'O';
        } else if (isFilled()) {
            return ' ';
        }
        return 'N';
    }

    boolean willWin(int x, int y, char chars) {
        int column = x - 1;
        int row = 3 - y;

        if (isEmpty(x, y)) {
            char before = table[row][column];
            table[row][column] = chars;

            boolean won = checkWinner() == chars;

            table[row][column] = before;
            return won;
        }
        return false;
    }

    char nextChar() {
        return isNext ? 'X' : 'O';
    }

    private boolean checkRows(char chars) {
        for (int i = 0; i < 3; i++) {
            if (table[i][0] == chars && table[i][1] == chars && chars == table[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char chars) {
        for (int i = 0; i < 3; i++) {
            if (table[0][i] == chars && table[1][i] == chars && table[2][i] == chars) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char chars) {
        return (table[1][1] == chars) &&
                (((table[0][0] == chars) && (table[2][2] == chars)) ||
                        ((table[0][2] == chars) && (table[2][0] == chars)));
    }

    private boolean isFilled() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------").append(System.lineSeparator());
        for (int i = 0; i < 3; i++) {
            stringBuilder.append("| ");
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(table[i][j]).append(" ");
            }
            stringBuilder.append("|").append(System.lineSeparator());
        }
        stringBuilder.append("---------").append(System.lineSeparator());
        return stringBuilder.toString();
    }

    public boolean continues() {
        return win == 'N';
    }

    public char getWin() {
        return win;
    }

}
