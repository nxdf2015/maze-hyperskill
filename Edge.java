package maze;

import org.jetbrains.annotations.NotNull;

public class Edge implements  Comparable<Edge> {
    public final int row;
    public final int col;
    private final int weight;


    public Edge(int row, int col, int weight) {
        this.row = row;
        this.col = col;
        this.weight = weight;

    }




    @Override
    public String toString() {
        return "Edge{" +
                "row=" + row +
                ", col=" + col +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(@NotNull Edge edge) {
        return weight < edge.weight ? -1 : weight == edge.weight ? 0 : 1;
    }

    public int[] source() {
        if (row % 2 == 0){
            return new int[]{row , col - 1};
        }
        else {
            return new int[]{row - 1 , col };
        }
    }

    public int[] target() {
        if (row % 2 == 0){
            return new int[]{row , col + 1};
        }
        else {
            return new int[]{row + 1 , col };
        }
    }
}
