package tictactoe;

public class MediumAI implements Player {
    @Override
    public void move(Table table) {
        System.out.println(table);
        System.out.println("Making move level \"medium\"");

        if (tryWin(table)) return;
        if (tryDefend(table)) return;

        moveRandomly(table);
    }

    private boolean tryWin(Table table) {
        char chars = table.nextChar();
        return checkNextTurnWin(table, chars);
    }

    private boolean tryDefend(Table table) {
        char chars = table.nextChar();
        if (chars == 'X') chars = 'O';
        else if (chars == 'O') chars = 'X';
        return checkNextTurnWin(table, chars);
    }
}
