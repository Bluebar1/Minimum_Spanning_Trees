// Edge class to store and analyze data from text input files
// implements comparable to be able to use Collections.sort()
public class Edge implements Comparable {

    public int i;
    public int j;
    public int cost;

    public Edge(int i, int j, int cost) {
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return i + " " + j + " " + cost;
    }

    @Override
    public int compareTo(Object o) {
        int compareCost = ((Edge) o).cost;
        return this.cost - compareCost;
    }
}
