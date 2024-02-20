package algorithm.bfs;

import java.util.AbstractMap.SimpleEntry;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlgorithmBFS {
    public AlgorithmBFS() {
    }


    public boolean isPathExists(char[][] map, SimpleEntry<Integer, Integer> fromPoint, SimpleEntry<Integer, Integer> toPoint, char playerSymbol, char emptySymbol) {
        int rows = map.length;
        int columns = map[0].length;
        boolean[][] visited = new boolean[rows][columns];
        Queue<SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
        queue.add(fromPoint);
        visited[fromPoint.getKey()][fromPoint.getValue()] = true;
        while (!queue.isEmpty()) {
            SimpleEntry<Integer, Integer> current = queue.poll();
            if (current.equals(toPoint)) {
                return true;
            }
            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{-1, 0, 1, 0};
            for (int i = 0; i < 4; ++i) {
                int newX = current.getKey() + dx[i];
                int newY = current.getValue() + dy[i];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < columns && !visited[newX][newY] && (map[newX][newY] == emptySymbol || map[newX][newY] == playerSymbol)) {
                    queue.add(new SimpleEntry<>(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }
        return false;
    }

    public SimpleEntry<Integer, Integer> getShortestPath(char[][] map, SimpleEntry<Integer, Integer> fromPoint, SimpleEntry<Integer, Integer> toPoint, char playerSymbol, char emptySymbol) {
        int rows = map.length;
        int columns = map[0].length;
        boolean[][] visited = new boolean[rows][columns];
        Queue<SimpleEntry<SimpleEntry<Integer, Integer>, List<SimpleEntry<Integer, Integer>>>> queue = new LinkedList<>();
        queue.add(new SimpleEntry<>(fromPoint, new ArrayList<>()));
        visited[fromPoint.getKey()][fromPoint.getValue()] = true;
        while (!queue.isEmpty()) {
            SimpleEntry<SimpleEntry<Integer, Integer>, List<SimpleEntry<Integer, Integer>>> current = queue.poll();
            if ((current.getKey()).equals(toPoint)) {
                return current.getValue().get(0);
            }
            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{-1, 0, 1, 0};
            for (int i = 0; i < 4; ++i) {
                int newX = (current.getKey()).getKey() + dx[i];
                int newY = (current.getKey()).getValue() + dy[i];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < columns && !visited[newX][newY] && (map[newX][newY] == emptySymbol || map[newX][newY] == playerSymbol)) {
                    List<SimpleEntry<Integer, Integer>> path = new ArrayList<>(current.getValue());
                    path.add(new SimpleEntry<>(newX, newY));
                    queue.add(new SimpleEntry<>(new SimpleEntry<>(newX, newY), path));
                    visited[newX][newY] = true;
                }
            }
        }
        return null;
    }
}