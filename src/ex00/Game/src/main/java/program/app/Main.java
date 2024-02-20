package program.app;

import com.beust.jcommander.ParameterException;

public class Main {
    public static void main(String[] args) {
        try {
            Menu menu = new Menu(args);
            menu.run();
        } catch (ParameterException e) {System.out.println(e.getMessage());}
    }
}