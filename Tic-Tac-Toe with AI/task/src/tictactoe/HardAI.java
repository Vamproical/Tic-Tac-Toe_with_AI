package tictactoe;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HardAI implements Player {
    final List<Point> iterationOrder;

    public HardAI() {
        this.iterationOrder = List.of(
                new Point(2, 2),

                new Point(1, 3),
                new Point(3, 1),
                new Point(1, 1),
                new Point(3, 3),

                new Point(2, 3),
                new Point(3, 2),
                new Point(2, 1),
                new Point(1, 2)
        );
    }

    @Override
    public void move(Table table) {
        System.out.println(table);
        System.out.println("Making move level \"hard\"");

        char symbol = table.nextChar();

        Point next = getBestMove(table, symbol);

        table.set(next);
    }

    private Point getBestMove(Table table, char symbol) {
        Stream<Point> stream = iterationOrder.stream().filter(table::isEmpty);
        Comparator<Point> comparator = Comparator.comparing(point -> getScore(table, point, symbol));

        return (symbol == 'X' ? stream.max(comparator) : stream.min(comparator)).orElseThrow();
    }

    private int getScore(Table table, Point point, char symbol) {
        return getScore(table, point.getX(), point.getY(), symbol);
    }

    private int getScore(Table table, int x, int y, char symbol) {
        table.setXO(x, y);


        if (!table.continues()) {
            char winner = table.getWin();
            table.unset(x, y);

            if (winner == ' ') {
                return 0;
            }
            return winner == 'X' ? 1 : -1;
        } else {
            char opposite;
            if (symbol == 'X') opposite = 'O';
            else opposite = 'X';

            IntStream stream = iterationOrder.stream()
                    .filter(table::isEmpty)
                    .mapToInt(point -> getScore(table, point, opposite));
            int score = (opposite == 'X' ? stream.max() : stream.min()).orElseThrow();

            table.unset(x, y);
            return score;
        }
    }
}
