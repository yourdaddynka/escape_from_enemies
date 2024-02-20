package program.app;

import program.map.MapUpdateAfterMove;


public class Menu {

    private final MapUpdateAfterMove mapUpdateAfterMove;

    public Menu(String[] args) {
        mapUpdateAfterMove = new MapUpdateAfterMove(args);
    }

    public void run() {
        mapUpdateAfterMove.mod();
    }

}
