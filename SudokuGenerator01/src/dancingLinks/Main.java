package dancingLinks;

import GUI.StartingMenu;
import GUI.MainWindow;


public class Main {

    static int version = 3;

    public static void main(String[] args) {

        if (version == 0) {
            Sudoku sudoku = new Sudoku();
            sudoku.readField();
            sudoku.game();
            sudoku.printSolution(sudoku.convertSolutionToArray());
        } else if (version == 1) {
           // Generator generator = new Generator();
        } else if (version == 2){
            //MainWindow gui = new MainWindow();
        } else {
            StartingMenu menu = new StartingMenu();
        }
    }
}
