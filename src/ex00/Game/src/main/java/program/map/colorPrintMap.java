package program.map;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import program.parse.MapFigure;

public class colorPrintMap {
    ColoredPrinter coloredPrinter = new ColoredPrinter();


    private static colorPrintMap instance;

    public static colorPrintMap getInstance() {
        if (instance == null) {
            instance = new colorPrintMap();
        }
        return instance;
    }

    public void PrintMap(char[][] map, MapFigure mapFigure) {
        for (char[] chars : map) {
            for (char aChar : chars) {
                char figure = mapFigure.getEmptySymbol();
                String figureColor = mapFigure.getEmptyColor();
                if (aChar == mapFigure.getEnemiesSymbol()) {
                    figure = mapFigure.getEnemiesSymbol();
                    figureColor = mapFigure.getEnemiesColor();
                } else if (aChar == mapFigure.getWallSymbol()) {
                    figure = mapFigure.getWallSymbol();
                    figureColor = mapFigure.getWallColor();
                } else if (aChar == mapFigure.getPlayerSymbol()) {
                    figure = mapFigure.getPlayerSymbol();
                    figureColor = mapFigure.getPlayerColor();
                } else if (aChar == mapFigure.getGoalSymbol()) {
                    figure = mapFigure.getGoalSymbol();
                    figureColor = mapFigure.getGoalColor();
                }
                coloredPrinter.print(figure, Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.valueOf(figureColor));
            }
            System.out.println();
        }
    }
}
