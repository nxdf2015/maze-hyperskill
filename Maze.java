package maze;




import java.util.*;
import java.util.function.Supplier;

public class Maze {
    private final int cols;
    private final int rows;
    private final Cell[][] grid;
    private boolean evenRow;
    public List<Edge> edges;

    private final Random generator;
    private int[] ids;

    private   boolean evenCol;

    {
        generator = new Random();
        edges = new ArrayList<>();
        evenRow = false;
        evenCol = false;
    }

    public Maze(int rows, int cols) {
        if (rows%2==0){
            rows--;
            evenRow=true;
        }
        if (cols % 2 == 0){
            cols--;
            evenCol = true;
        }

        this.cols=cols ;
        this.rows= rows;


        grid = new Cell[rows][cols];
        ids = new int[rows * cols + 1];
    }


    private boolean isEdge(int row,int col){
        return (row % 2 == 0 && col % 2 == 1) || (row % 2 == 1 && col % 2 == 0 );
    }

    public void setGrid(){
        for(int row = 0 ; row < rows ; row++ ) {
            for (int col = 0; col < cols; col++) {
                Cell value = Cell.SPACE;
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1){
                    value = Cell.WALL;
                }
                else if (row % 2 == 0 && col % 2 == 0){
                    value = Cell.WALL;
                }
                grid[row][col] = value;
            }
        }
        grid[0][1]=Cell.SPACE;
        grid[rows-2][cols-1]=Cell.SPACE;
    }

    public void setEdges(){
        Supplier<Integer> weight = () -> generator.nextInt(50);
        for(int row = 0 ; row < rows ; row++ ){
            for(int col = 0 ; col < cols ; col++ ){
                if (isEdge(row,col)){
                    edges.add(new Edge(row,col,weight.get()));
                }
            }
        }
    }

    public void setId(){
        int count = 0;
        for(int row = 0 ; row < rows ; row++ ) {
            for (int col = 0; col < cols; col++) {
                if(row == 0 || row== rows -1 || col == 0 || col == cols - 1 ){
                    ids[count] = 0;
                }
                else {
                    ids[count] = count;
                }
                count++;
            }
        }

        ids[1]=1;
    }

    public void make(){
        setGrid();
        setEdges();
        setId();
        sortEdgesByWeight();
        for(Edge edge : edges){
            if(!isCycle(edge)){
                addWall(edge);
            }
        }

    }

    public boolean isCycle(Edge edge){
        int id_source = ids[toId(edge.source())];
        int id_target = ids[toId(edge.target())];
        return id_source == id_target;
    }

    public void addWall(Edge edge){
        int  source = toId(edge.source());
        int  target = toId(edge.target());

        int m = Math.min(ids[source],ids[target]);
        int M = Math.max(ids[source],ids[target]);
        for(int i = 0 ; i < ids.length ; i++ ){
            if (ids[i] == M){
                ids[i] = m;
            }
        }

        ids[source] = m;
        ids[target] = m;
        grid[edge.row][edge.col]=Cell.WALL;
    }

    private int toId(int[] point) {
        return toId(point[0],point[1]);
    }

    private int toId(int row,int col){
        return row * cols + col ;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void sortEdgesByWeight() {
        Collections.sort(edges);
    }
    public String getMaze(){
        int d_col = 1;
        int d_row = 1;
        String result = "";
        for(int row = 0 ; row < rows ; row++){
            for(int col = 0 ; col < cols ; col++ ) {
                if(evenCol && d_col == col) {

                     result += grid[row][col];
                }
                result += grid[row][col];
            }
            if (evenRow && row == d_col){
                result += System.lineSeparator();
                for(int col = 0 ; col < cols ; col++ ) {
                    if (evenCol && col == d_col)
                       result += grid[row][col];
                   result += grid[row][col];
                }
            }
            result+= System.lineSeparator();
        }
       return result;
    }
    public  void show(){
        System.out.println(getMaze());
//        int d_col = 1;
//        int d_row = 1;
//
//       for(int row = 0 ; row < rows ; row++){
//           for(int col = 0 ; col < cols ; col++ ) {
//               if(evenCol && d_col == col) {
//
//                   System.out.print(grid[row][col]);
//               }
//               System.out.print(grid[row][col]);
//           }
//           if (evenRow && row == d_col){
//               System.out.println();
//               for(int col = 0 ; col < cols ; col++ ) {
//                   if (evenCol && col == d_col)
//                       System.out.print(grid[row][col]);
//                   System.out.print(grid[row][col]);
//               }
//           }
//           System.out.println();
//       }
    }
}
