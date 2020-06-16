package tictactoe;

public class EasyAI implements Player {

    @Override
    public void move(Table table) {
        System.out.println(table);
        System.out.println("Making move level \"easy\"");

        moveRandomly(table);
    }
}
