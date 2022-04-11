
// Node class used in Prinns Algorithm to track if the node has been visited or not
public class Node {

    public int index;
    public boolean visited;

    public Node(int index, boolean visited) {
        this.index = index;
        this.visited = visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
