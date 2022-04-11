import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PrinnAlgorithm {

    ArrayList<Edge> edges;
    ArrayList<Edge> T;
    ArrayList<Node> nodes;
    int n;

    public PrinnAlgorithm(File infile, boolean verbose) {

        edges = new ArrayList<>();
        nodes = new ArrayList<>();

        // Read in the number of nodes (n), the edges and their costs
        try {
            Scanner sc = new Scanner(infile);
            n = sc.nextInt();
            while (sc.hasNextLine()) {
                sc.nextLine();
                edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Collections.sort(edges);
        long tick = System.currentTimeMillis();

        // Mark node 0 as "visited" and nodes 1 through n-1 as "unvisited"
        nodes.add(new Node(0, true));
        for (int i = 1; i < n; i++)
            nodes.add(new Node(i, false));
        // Initialize T to the empty set.
        T = new ArrayList<>();
        // while (there are nodes marked "unvisited") {
        while (n != 1) {
            // Let (i,j) be an edge of the lowest cost such that node i is marked "visited" and node j is marked "unvisited"
            Edge lowestEdge = findLowest();
            // Add the edge (i,j) to T
            T.add(lowestEdge);
            // Mark node j as "visited"
            if (!nodes.get(lowestEdge.j).visited)
                nodes.get(lowestEdge.j).setVisited(true);
            else
                nodes.get(lowestEdge.i).setVisited(true);
            n--;
        }

        long tock = System.currentTimeMillis();

        System.out.println("Prinns Algorithm on File: "+ infile.getName() + " took " + (tock-tick) + "ms" );

        // Output the total cost of the edges in T.
        int mstWeight = 0;
        for (Edge edge : T) {
            mstWeight+=edge.cost;
        }
        System.out.println("MST Weight : " + mstWeight);
        // Output the edges in T, if required
        if (verbose) {
            Collections.sort(T);
            System.out.println(T);
        }
    }

    private Edge findLowest() {
        int lowestWeight = Integer.MAX_VALUE;
        Edge lowestEdge = new Edge(0,0,0);
        for (Edge edge : edges) {
            if ((nodes.get(edge.i).visited && !nodes.get(edge.j).visited) ||
                    (nodes.get(edge.j).visited && !nodes.get(edge.i).visited)) {
                if (edge.cost < lowestWeight) {
                    lowestWeight = edge.cost;
                    lowestEdge = edge;
                }
            }
        }

        return lowestEdge;
    }


}
