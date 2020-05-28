package maze;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please, enter the size of a maze");
      Scanner scanner = new Scanner(System.in);
      int rows = scanner.nextInt();
      int cols = scanner.nextInt();


      Maze m = new Maze(rows,cols);

       m.make();
       m.show();

    }



}

