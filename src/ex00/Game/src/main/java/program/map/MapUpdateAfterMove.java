package program.map;

import java.util.AbstractMap.SimpleEntry;

import com.beust.jcommander.ParameterException;
import program.move.Moving;
import program.parse.MapFigure;
import program.parse.ParsingInputParameters;
import algorithm.app.AlgorithmChase;

import java.util.ArrayList;

public class MapUpdateAfterMove {
    private ParsingInputParameters parsInPar;
    private MapFigure mapFigure;
    private Moving moving;
    private AlgorithmChase algorithmChase_test;
    private colorPrintMap colorPrintMap;

    private char[][] map;
    private SimpleEntry<Integer, Integer> person;
    private ArrayList<SimpleEntry<Integer, Integer>> enemiesPos;


    public MapUpdateAfterMove(String[] args) {
        parsInPar = ParsingInputParameters.getInstance(args);
        mapFigure = MapFigure.getInstance(parsInPar.getProfileMod());
        mapCreate();
        moving = new Moving();
        enemiesPos = new ArrayList<>();
        algorithmChase_test = new AlgorithmChase();
        colorPrintMap = program.map.colorPrintMap.getInstance();
        setPersonagePoint();

    }

    private void mapCreate() {
        MapCreateAlgorithm mapCreateAlgorithm = MapCreateAlgorithm.getInstance(parsInPar, mapFigure);
        mapCreateAlgorithm.mapCreate();
        map = mapCreateAlgorithm.getMapArray();
    }

    private void setPersonagePoint() {
        for (int i = 0; i < parsInPar.getSize(); i++) {
            for (int j = 0; j < parsInPar.getSize(); j++) {
                if (map[i][j] == mapFigure.getPlayerSymbol()) {
                    person = new SimpleEntry<>(i, j);
                } else if (map[i][j] == mapFigure.getEnemiesSymbol()) enemiesPos.add(new SimpleEntry<>(i, j));
            }
        }
    }

    public void mod() {
        gameCycle(checkMode());
    }

    private void gameCycle(boolean production) {
        if (production) {
            productionMode();
        } else {
            developmentMode();
        }
    }

    private boolean checkMode() {
        if (parsInPar.getProfileMod().equals("development")) {
            return false;
        } else if (parsInPar.getProfileMod().equals("production")) {
            return true;
        }
        incorrectInput();
        return true;
    }

    private void incorrectInput() {
        System.err.println("incorrect string argument: " + parsInPar.getProfileMod());
        System.exit(-1);
    }


    public void productionMode() {
        System.out.println("Чтобы выйти из игры нажмите на q");
        while (true) {
            mapPrint();
            char symbol = moving.getMoveSymbol();
            isGamClose(symbol);
            SimpleEntry<Integer, Integer> myMove = moving.moveInput(symbol, person, parsInPar.getSize());
            myMoveOrGameWins(myMove);
            algorithmChase_test.chaseAlgorithm(map, person, mapFigure.getPlayerSymbol(), mapFigure.getEmptySymbol(), mapFigure.getEnemiesSymbol());
            System.out.print("\033[H\033[J");

        }
    }

    public void developmentMode() {
        System.out.println("Чтобы выйти из игры нажмите на q");
        while (true) {
            mapPrint();
            char symbol = moving.getMoveSymbol();
            isGamClose(symbol);
            System.out.println("Подвтердите ввод нажав на 8:");
            if (moving.getMoveSymbol() == '8') {
                SimpleEntry<Integer, Integer> myMove = moving.moveInput(symbol, person, parsInPar.getSize());
                myMoveOrGameWins(myMove);
                algorithmChase_test.chaseAlgorithm(map, person, mapFigure.getPlayerSymbol(), mapFigure.getEmptySymbol(), mapFigure.getEnemiesSymbol());
                System.out.print("\033[H\033[J");
            }

        }
    }

    private void isGamClose(char symbol) {
        if (symbol == 'q') {
            System.out.println("\u001B[1m\u001B[35mGoodbye!\u001B[0m");
            System.exit(-1);
        }
    }

    private void GameWins() {
        System.out.println("\u001B[1m\u001B[35mCONGRATULATIONS! YOU WIN!\u001B[0m");
        System.exit(-1);
    }

    private void myMoveOrGameWins(SimpleEntry<Integer, Integer> myMove) {
        if (map[myMove.getKey()][myMove.getValue()] == mapFigure.getEmptySymbol()) {
            UpdateMyPoint(person, myMove);
        } else if (map[myMove.getKey()][myMove.getValue()] == mapFigure.getGoalSymbol()) {
            GameWins();
        }
    }

    private void UpdateMyPoint(SimpleEntry<Integer, Integer> oldMe, SimpleEntry<Integer, Integer> newMe) {
        map[oldMe.getKey()][oldMe.getValue()] = mapFigure.getEmptySymbol();
        map[newMe.getKey()][newMe.getValue()] = mapFigure.getPlayerSymbol();
        this.person = newMe;
    }

    private void mapPrint() {
        colorPrintMap.PrintMap(map, mapFigure);
    }
}
