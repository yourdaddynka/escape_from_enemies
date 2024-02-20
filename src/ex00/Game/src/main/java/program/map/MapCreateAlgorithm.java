package program.map;

import java.util.AbstractMap.SimpleEntry;
import algorithm.bfs.AlgorithmBFS;
import program.parse.MapFigure;
import program.parse.ParsingInputParameters;

import java.util.*;

public class MapCreateAlgorithm {

    private final char[][] mapArray;
    private final int mapSize, enemiesCount, wallsCount;
    private final char figureEnemy, figureWall, figurePlayer, figureGoal, figureEmpty;
    private final MapFigure mapFigure;
    private final Random random;
    private final List<SimpleEntry<Integer, Integer>> occupiedPoints;
    private SimpleEntry<Integer, Integer> personageCoordinate;
    private SimpleEntry<Integer, Integer> destinationCoordinate;
    private final AlgorithmBFS algorithmBFS;

    private static MapCreateAlgorithm instance;

    private MapCreateAlgorithm(ParsingInputParameters parsInPar, MapFigure mapFigure) {
        this.mapSize = parsInPar.getSize();
        this.enemiesCount = parsInPar.getEnemies();
        this.wallsCount = parsInPar.getWalls();
        this.figureEnemy = mapFigure.getEnemiesSymbol();
        this.figureWall = mapFigure.getWallSymbol();
        this.figurePlayer = mapFigure.getPlayerSymbol();
        this.figureGoal = mapFigure.getGoalSymbol();
        this.figureEmpty = mapFigure.getEmptySymbol();
        this.mapFigure = mapFigure;
        mapArray = new char[mapSize][mapSize];
        for (char[] chars : mapArray) {
            Arrays.fill(chars, figureEmpty);
        }
        occupiedPoints = new ArrayList<>();
        random = new Random();
        algorithmBFS = new AlgorithmBFS();
    }

    public static MapCreateAlgorithm getInstance(ParsingInputParameters parsInPar, MapFigure mapFigure) {
        if (instance == null) {
            instance = new MapCreateAlgorithm(parsInPar, mapFigure);
        }
        return instance;
    }

    public void mapCreate() {
        initPointList();
        createPeople();
        createExit();
        createEnemies();
        createWalls();
    }

    private void createPeople() {
        createNewPoint(figurePlayer);
    }

    private void createExit() {
        createNewPoint(figureGoal);
    }

    private void createEnemies() {
        removePoint();
        for (int i = 0; i < enemiesCount; i++) {
            createNewPoint(figureEnemy);
        }
    }

    private void createWalls() {
        cleanEnemiesRadius();
        for (int i = 0; i < wallsCount; i++) {
            createNewPoint(figureWall);
        }
    }

    private void createNewPoint(char symbol) {
        SimpleEntry<Integer, Integer> personPoint = createPoint(symbol);
        updateMap(personPoint.getKey(), personPoint.getValue(), symbol);
    }

    private SimpleEntry<Integer, Integer> createPoint(char symbol) {
        if (symbol == figurePlayer) {
            return getPeoplePoint();
        } else if (symbol == figureGoal) {
            return getDestinationPoint();
        } else if (symbol == figureEnemy) {
            return getEnemiesPoint();
        } else {
            return getWallsPoint();
        }
    }

    private void cleanEnemiesRadius() {
        occupiedPoints.clear();
        initPointList();
    }

    private SimpleEntry<Integer, Integer> getPeoplePoint() {
        personageCoordinate = occupiedPoints.get(random.nextInt(occupiedPoints.size()));
        return personageCoordinate;
    }

    private SimpleEntry<Integer, Integer> getDestinationPoint() {
        while (true) {
            SimpleEntry<Integer, Integer> randomPoint = occupiedPoints.get(random.nextInt(occupiedPoints.size()));
            if (radius(randomPoint) >= (mapSize / 5)) {
                destinationCoordinate = randomPoint;
                return randomPoint;
            }
        }
    }

    private void removePoint() {
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                SimpleEntry<Integer, Integer> temp = new SimpleEntry<>(i, j);
                if (occupiedPoints.contains(temp) && radius(temp) <= mapSize / 4) {
                    occupiedPoints.remove(temp);
                }
            }
        }
    }

    private SimpleEntry<Integer, Integer> getEnemiesPoint() {
        if (!occupiedPoints.isEmpty()) {
            return occupiedPoints.get(random.nextInt(occupiedPoints.size()));
        } else {
            throw new IllegalArgumentException("Неправильное количество зомби!");
        }
    }

    private SimpleEntry<Integer, Integer> getWallsPoint() {
        SimpleEntry<Integer, Integer> randomPoint = occupiedPoints.get(random.nextInt(occupiedPoints.size()));
        if (algorithmBFS.isPathExists(mapArray, personageCoordinate, randomPoint, mapFigure.getPlayerSymbol(), mapFigure.getEmptySymbol()) && algorithmBFS.isPathExists(mapArray, randomPoint, destinationCoordinate, mapFigure.getPlayerSymbol(), mapFigure.getEmptySymbol())) {
            return randomPoint;
        } else if (!occupiedPoints.isEmpty()) {
            return occupiedPoints.get(random.nextInt(occupiedPoints.size()));
        } else {
            throw new IllegalArgumentException("Неправильное количество стен!");
        }
    }

    private void updateMap(int x, int y, char symbol) {
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (x == i && y == j) {
                    mapArray[i][j] = symbol;
                    occupiedPoints.remove(new SimpleEntry<>(i, j));
                }
            }
        }
    }

    private void initPointList() {
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (mapArray[i][j] == figureEmpty) {
                    occupiedPoints.add(new SimpleEntry<>(i, j));
                }
            }
        }
    }

    private int radius(SimpleEntry<Integer, Integer> randomPoint) {
        return (int) Math.sqrt(Math.pow(personageCoordinate.getKey() - randomPoint.getKey(), 2) + Math.pow(personageCoordinate.getValue() - randomPoint.getValue(), 2));
    }

    public char[][] getMapArray() {
        return mapArray;
    }
}