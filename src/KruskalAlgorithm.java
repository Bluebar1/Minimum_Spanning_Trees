import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KruskalAlgorithm {

    public int n; // number of nodes
    ArrayList<Edge> edges;
    ArrayList<Edge> T;


    public KruskalAlgorithm(File infile, boolean verbose) {

        edges = new ArrayList<>();

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

        long tick = System.currentTimeMillis();

        // Sort the edges into non-decreasing order of their costs
        Collections.sort(edges);

        // Initialize T to the empty set. At the end of the algorithm, T will contain all the edges in an MST
        T = new ArrayList<>();

        // Initially, each of the n nodes is in a component by itself. (Thus, there are n components at this step)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        while (n != 1) {
            // Remove the first edge from the sorted list; let (i,j) denote this edge
            Edge e = edges.get(0);
            edges.remove(0);
            int i = find(parent, e.i);
            int j = find(parent, e.j);

            // if (Nodes i and j are in different components)
            if (i != j) {
                // Add the edge (i,j) to T
                T.add(e);
                // Merge the components containing i and j into a single component
                int i_parent = find(parent, i);
                int j_parent = find(parent, j);
                parent[j_parent] = i_parent;
                // Decrement the number of components by 1.
                n--;
            }
        }

        long tock = System.currentTimeMillis();

        System.out.println("Kruskalls Algorithm on File: "+ infile.getName() + " took " + (tock-tick) + "ms" );

        // Output the total cost of the edges in T.
        int mstWeight = 0;
        for (Edge edge : T) {
            mstWeight+=edge.cost;
        }
        System.out.println("MST Weight : " + mstWeight);

        // Output the edges in T, if required
        if (verbose)
            System.out.println(T);

    }

    public int find(int [] parent, int vertex){
        if(parent[vertex]!=vertex)
            return find(parent, parent[vertex]);;
        return vertex;
    }
}
