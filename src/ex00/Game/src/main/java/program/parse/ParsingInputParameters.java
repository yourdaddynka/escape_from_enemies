package program.parse;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class ParsingInputParameters {
    private static int enemies;
    private static int walls;
    private static int size;
    private static String profileMod;

    private static ParsingInputParameters instance;

    private ParsingInputParameters(String[] args) {
        parseArgs(args);
    }

    public static ParsingInputParameters getInstance(String[] args) {
        if (instance == null) {
            instance = new ParsingInputParameters(args);
        }
        return instance;
    }

    private void parseArgs(String[] inputArgs) {
        try {
            ParseArgs parseArgs = new ParseArgs();
            JCommander.newBuilder().addObject(parseArgs).build().parse(inputArgs);
            enemies = Integer.parseInt(parseArgs.getEnemiesCount());
            walls = Integer.parseInt(parseArgs.getWallsCount());
            size = Integer.parseInt(parseArgs.getMapSize());
            profileMod = parseArgs.getMod();
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getEnemies() {
        return enemies;
    }

    public int getWalls() { return walls; }

    public int getSize() {
        return size;
    }

    public String getProfileMod() {
        return profileMod;
    }
}
