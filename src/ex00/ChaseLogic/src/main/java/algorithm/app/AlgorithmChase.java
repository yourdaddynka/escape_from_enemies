package algorithm.app;

import algorithm.bfs.AlgorithmBFS;
import java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;

public class AlgorithmChase {
    private final AlgorithmBFS algorithmBFS;

    public AlgorithmChase() {
        algorithmBFS = new AlgorithmBFS();
    }

    public char[][] chaseAlgorithm(char[][] map, SimpleEntry<Integer, Integer> person, char playerSymbol, char emptySymbol, char enemiesSymbol) {
        ArrayList<SimpleEntry<SimpleEntry<Integer, Integer>, Boolean>> zombiePointState = new ArrayList<>();
        initPointList(map, zombiePointState, enemiesSymbol);
        oneStep(map, zombiePointState, person,playerSymbol,emptySymbol,enemiesSymbol);
        return map;
    }


    private void initPointList(char[][] map, ArrayList<SimpleEntry<SimpleEntry<Integer, Integer>, Boolean>> zombiePointState, char enemiesSymbol) {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (map[i][j] == enemiesSymbol) {
                    zombiePointState.add(new SimpleEntry<>(new SimpleEntry<>(i, j), false));
                }
            }
        }
    }


    private void oneStep(char[][] map, ArrayList<SimpleEntry<SimpleEntry<Integer, Integer>, Boolean>> zombiePointState, SimpleEntry<Integer, Integer> person, char playerSymbol, char emptySymbol, char enemiesSymbol) {
        for (int i = 0; i < zombiePointState.size(); i++) {
            SimpleEntry<SimpleEntry<Integer, Integer>, Boolean> zombieState = zombiePointState.get(i);
            SimpleEntry<Integer, Integer> zombie = zombieState.getKey();
            Boolean hasMoved = zombieState.getValue();
            if (!hasMoved) {
                if (algorithmBFS.isPathExists(map, zombie, person, playerSymbol, emptySymbol)) {
                    SimpleEntry<SimpleEntry<Integer, Integer>, Boolean> newZombieState = new SimpleEntry<>(algorithmBFS.getShortestPath(map, zombie, person, playerSymbol, emptySymbol), false);
                    SimpleEntry<Integer, Integer> zombieNew = newZombieState.getKey();
                    checkYouLose(zombieNew, person);
                    zombiePointState.set(i, new SimpleEntry<>(algorithmBFS.getShortestPath(map, zombie, person, playerSymbol, emptySymbol), false));
                    updateMap(zombie.getKey(), zombie.getValue(), zombieNew.getKey(), zombieNew.getValue(), map, emptySymbol, enemiesSymbol);
                } else {
                    zombiePointState.set(i, new SimpleEntry<>(zombie, true));
                }
            }
        }
    }

    private void checkYouLose(SimpleEntry<Integer, Integer> zombieNew, SimpleEntry<Integer, Integer> person) {
        if (zombieNew.getKey().equals(person.getKey()) && zombieNew.getValue().equals(person.getValue())) {
            System.out.println("\u001B[1m\u001B[50mYOULOSE!\u001B[0m");
            System.exit(-1);
        }
    }

    private void updateMap(int xOld, int yOld, int xNew, int yNew, char[][] map, char emptySymbol, char enemiesSymbol) {
        map[xOld][yOld] = emptySymbol;
        map[xNew][yNew] = enemiesSymbol;
    }
}
