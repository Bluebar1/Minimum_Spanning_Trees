# Minimum Spanning Trees
Java implementation of Prinn's and Kruskal's minimum spanning trees algorithms.  

MST algorithms are used to find the most efficient path between a number of nodes in an edge-weighted undirected graph.
They are most commonly used for network design, ensuring the signals have the easiest possible route.  
To understand this concept I found it helpful to research the Tokyo subway system, and how slime mold branches out.  
https://www.wired.com/2010/01/slime-mold-grows-network-just-like-tokyo-rail-system/

## Prinn's Algorithm 
Starts by reading in the number of nodes (n), the edges, and their costs.  

Marks the first node as "visited" and the rest as "unvisited"
```java
nodes.add(new Node(0, true));
for (int i = 1; i < n; i++)
    nodes.add(new Node(i, false));
```
Iterates through each node until all are marked "visitied"
```java
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
```
Finish by outputting the result's total cost of edges

## Kruskal's Algorithm
Starts by reading in the number of nodes (n), the edges, and their costs.
Sorts the edges into increasing order of their costs.  
```java
Collections.sort(edges);
```
This is done by implementing Comparable in the Edge class and overriding its compareTo() method.  
```java
@Override
public int compareTo(Object o) {
    int compareCost = ((Edge) o).cost;
    return this.cost - compareCost;
}
```
Initially, each of the n nodes is in a component by itself. (Thus, there are n components at this step)
```java
int[] parent = new int[n];
for (int i = 0; i < n; i++) 
    parent[i] = i;
```
Then the algorithm will remove the edge with minimum weight, which is always index 0 because the edges were sorted earlier.  
If this removed edge connects two different trees, merge the components and begin the next iteration.  
```java
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
```
Finish by outputting the result's total cost of edges
