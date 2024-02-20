package program.parse;

import java.util.AbstractMap.SimpleEntry;

public class MapFigure {
    SetColorAndChar colorChar;

    private enum TYPE {ENEMIES, PLAYER, WALL, GOAL, EMPTY}

    private final SimpleEntry<TYPE, SimpleEntry<Character, String>> enemies;
    private final SimpleEntry<TYPE, SimpleEntry<Character, String>> player;
    private final SimpleEntry<TYPE, SimpleEntry<Character, String>> wall;
    private final SimpleEntry<TYPE, SimpleEntry<Character, String>> goal;
    private final SimpleEntry<TYPE, SimpleEntry<Character, String>> empty;


    private static MapFigure instance;

    public static MapFigure getInstance(String mod) {
        if (instance == null) {
            instance = new MapFigure(mod);
        }
        return instance;
    }

    private MapFigure(String mod) {
        colorChar = SetColorAndChar.getInstance(mod);
        colorChar.setParamProperties();
        this.enemies = new SimpleEntry<>(TYPE.ENEMIES, new SimpleEntry<>(colorChar.get("enemy.char").charAt(0), colorChar.get("enemy.color")));
        this.player = new SimpleEntry<>(TYPE.PLAYER, new SimpleEntry<>(colorChar.get("player.char").charAt(0), colorChar.get("player.color")));
        this.wall = new SimpleEntry<>(TYPE.WALL, new SimpleEntry<>(colorChar.get("wall.char").charAt(0), colorChar.get("wall.color")));
        this.goal = new SimpleEntry<>(TYPE.GOAL, new SimpleEntry<>(colorChar.get("goal.char").charAt(0), colorChar.get("goal.color")));
        this.empty = new SimpleEntry<>(TYPE.EMPTY, new SimpleEntry<>(colorChar.get("empty.char").isEmpty() ? ' ' : colorChar.get("empty.char").charAt(0), colorChar.get("empty.color")));
    }

    public String getEnemiesColor() {
        return enemies.getValue().getValue();
    }

    public String getPlayerColor() {
        return player.getValue().getValue();
    }

    public String getWallColor() {
        return wall.getValue().getValue();
    }

    public String getGoalColor() {
        return goal.getValue().getValue();
    }

    public String getEmptyColor() {
        return empty.getValue().getValue();
    }

    public Character getEnemiesSymbol() {
        return enemies.getValue().getKey();
    }

    public Character getPlayerSymbol() {
        return player.getValue().getKey();
    }

    public Character getWallSymbol() {
        return wall.getValue().getKey();
    }

    public Character getGoalSymbol() {
        return goal.getValue().getKey();
    }

    public Character getEmptySymbol() {
        return empty.getValue().getKey();
    }
}


