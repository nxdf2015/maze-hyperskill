package maze;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    String   board;
    private Scanner scanner;


    public App() {
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        while(true) {
            String options = getOptions();
            System.out.println(options);
            int option;
            do {
                option = Integer.parseInt(scanner.nextLine());
            } while (!valid(option));
            switch (option) {
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                case 1:
                    System.out.println("Enter the size of the new maze");
                    int size = Integer.parseInt(scanner.nextLine());
                    Maze maze = new Maze(size, size);
                    maze.make();
                    board = maze.getMaze();
                    System.out.println(board);
                    break;
                case 2:
                    load();
                    break;
                    case 3:
                        save();
                        break;
                case 4:
                    System.out.println(board);



            }
        }

    }

    private void save() {
        String nameFile = scanner.nextLine();
        try(PrintWriter out = new PrintWriter(new File(nameFile))) {
            out.print(board);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void load(){

        String namefile = scanner.nextLine();
        try(BufferedReader in = new BufferedReader(new FileReader(new File(namefile)))) {

           board = in.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (FileNotFoundException e) {
            System.out.printf("The file %s does not exist",namefile);
        } catch (IOException e) {
            System.out.println("Cannot loadthe maze. It has an invalid format");
        }
    }


    private boolean valid(int option) {
        int maxOption = 2;
        if (board != null) {
            maxOption = 4;
        }
           boolean validOption  =  option >=0 && option <= maxOption;
        if (!validOption){
            System.out.println("Incorrect option. Please try again");
        }
        return validOption;

    }

    private String getOptions() {
        StringBuilder builder = new StringBuilder();
        builder.append("===Menu===")
                .append(System.lineSeparator())
                .append("1. Generate a new Maze")
                .append(System.lineSeparator())
                .append("2. Load a maze")
                .append(System.lineSeparator());
        if (board != null){
            builder.append("3. Save the maze")
                    .append(System.lineSeparator())
                    .append("4. Display the maze")
                    .append(System.lineSeparator());
            builder.append("0. Exit");

        }
        return builder.toString();

    }
}
