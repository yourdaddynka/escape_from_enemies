package program.parse;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ParseArgs {
    @Parameter(names ="--enemiesCount",description = "счетчик врагов" )     private String enemiesCount;
    @Parameter(names ="--wallsCount",description = "счетчик препятствий" )     private String wallsCount;
    @Parameter(names ="--size",description = "размер карты" )     private String mapSize;
    @Parameter(names ="--profile",description = "режим запуска" )     private String mod;

    public String getEnemiesCount() {
        return enemiesCount;
    }

    public String getWallsCount() {
        return wallsCount;
    }

    public String getMapSize() {
        return mapSize;
    }

    public String getMod() {return mod;}
}