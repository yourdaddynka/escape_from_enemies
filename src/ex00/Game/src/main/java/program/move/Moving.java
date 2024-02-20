package program.move;

import java.util.AbstractMap.SimpleEntry;
import java.util.Scanner;

public class Moving {
    private static Scanner in;

    public Moving() {
        in = new Scanner(System.in);
    }

    public char getMoveSymbol() {
        return in.next().charAt(0);
    }

    public SimpleEntry<Integer, Integer> moveInput(char symbol, SimpleEntry<Integer, Integer> personPoint, int mapSize) {
        if (symbol == 'w' || symbol == 'W') {
            return moveUp(personPoint);
        } else if (symbol == 's' || symbol == 'S') {
            return moveDown(personPoint, mapSize);
        } else if (symbol == 'a' || symbol == 'A') {
            return moveLeft(personPoint);
        } else if (symbol == 'd' || symbol == 'D') {
            return moveRight(personPoint, mapSize);
        } else {
            return personPoint;
        }
    }


    private SimpleEntry<Integer, Integer> moveUp(SimpleEntry<Integer, Integer> point) {
        if (point.getKey() == 0) return point;
        return new SimpleEntry<>(point.getKey() - 1, point.getValue());
    }

    private SimpleEntry<Integer, Integer> moveDown(SimpleEntry<Integer, Integer> point, int mapSize) {
        if (point.getKey() == mapSize - 1) return point;
        return new SimpleEntry<>(point.getKey() + 1, point.getValue());
    }

    private SimpleEntry<Integer, Integer> moveLeft(SimpleEntry<Integer, Integer> point) {
        if (point.getValue() == 0) return point;
        return new SimpleEntry<>(point.getKey(), point.getValue() - 1);
    }

    private SimpleEntry<Integer, Integer> moveRight(SimpleEntry<Integer, Integer> point, int mapSize) {
        if (point.getValue() == mapSize - 1) return point;
        return new SimpleEntry<>(point.getKey(), point.getValue() + 1);
    }


}
