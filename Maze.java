package maze;

public class Maze {

    private int[][] maze;

    public Maze() {
        this.maze = new int[10][10];

    }

    public void make(){
        maze = new int[][] {
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,1,0,1,0,1,0,0,1},
                {1,0,1,0,0,0,1,1,0,1},
                {1,0,0,0,1,1,1,1,0,0},
                {1,0,1,1,1,1,1,1,0,1},
                {1,0,0,0,0,0,0,1,0,1},
                {1,0,1,1,0,1,1,1,0,1},
                {1,0,0,1,0,0,0,0,0,1},
                {1,0,1,1,0,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
        };
    }

    public void render(){
        String result = "";
        for(int[] row : maze){
            for(int cell : row){
                if (cell == 0){
                    result += Cell.SPACE;
                }
                else {
                    result += Cell.WALL;
                }
            }
            result += System.lineSeparator();
        }
        System.out.println(result);
    }
}
