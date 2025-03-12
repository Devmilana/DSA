/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for creating and manipulating a graph data structure          *
 *              Repurposed from my DSAGraph file submitted to practicals                 *
 *****************************************************************************************/
import java.util.*;

public class DSAGraph 
{
    // Inner class for vertex edge linked list
    private class DSAGraphVertex 
    {
        /** ================== DSAGraphVertex ================== **/
        private Shop shopData; // Shop information
        private DSALinkedList adjList; // List of adjacent vertices
        private boolean visited; // Flag for traversal methods
        private DSAGraphVertex parent; // Stores predecessor vertex to identify who did we visit this current vertex from

        // Constructor - Creates a shop vertex
        public DSAGraphVertex(Shop inputShop) 
        {
            shopData = inputShop; // Initialises shop data
            adjList = new DSALinkedList(); // Initialises empty linkedlist to shop vertex
            visited = false; // Sets visited flag as false
            parent = null; // Sets parent flag as null (no parent initially)
        }

        // Returns shop data
        public Shop getShop() 
        {
            return shopData;
        }

        // Returns linked list of a vertex
        public DSALinkedList getAdjacent() 
        {
            return adjList;
        }

        // Returns visited status of a vertex
        public boolean getVisited() 
        {
            return visited;
        }

	    // Returns parent of a vertex
        public DSAGraphVertex getParent()
        {
            return parent;
        }
        
        // Sets the parent vertex of current vertex
        public void setParent(DSAGraphVertex inParent) 
        {
            parent  = inParent;
        }

        // Calls linkedlist method to add an edge to a vertex's adjacency list
        public void addEdge(DSAGraphVertex vertex) 
        {
            adjList.insertLast(vertex);
        }

        // Calls linkedlist method to remove an edge to a vertex's adjacency list
        public void removeEdge(DSAGraphVertex vertex) 
        {
            adjList.remove(vertex);
        }
        
        // Sets visited flag of a vertex to true
        public void setVisited() 
        {
            visited = true;
        }

        // Resets visited flag of a vertex to false (unvisited)
        public void resetVisited() 
        {
            visited = false;
        }

        // Check if the current vertex is equal to another vertex (based on shop number)
        public boolean equals(DSAGraphVertex inVertex) 
        {
            return (shopData.getShopNumber().equals(inVertex.getShop().getShopNumber()));
        }

        // Returns a vertex's shop data as a string
        public String toString() 
        {
            return shopData.toString();
        }
    }

    /** ===================== DSAGraph ===================== **/
    private DSALinkedList vertices; // List containing all vertices in the graph  

    // Constructor for outer vertex list
    public DSAGraph() 
    {
        vertices = new DSALinkedList();
    }

    // Check if shop vertex is present in graph
    public boolean hasShop(DSAGraphVertex vertex)
    {
        boolean found = false;
        Iterator iter = vertices.iterator();

        while(iter.hasNext() && !found)
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            if(currentVertex.equals(vertex))
            {
                found = true;
            }
        }

        return found;
    }

    // Check if shop number exists in graph
    public boolean doesShopNumberExist(String shopNumber)
    {
        try
        {
            getVertexByShopNumber(shopNumber); // Checks if vertex is present
            
            return true;
        }
        catch(NoSuchElementException e)
        {
            return false; // If exception thrown, shop number does not exist
        }
    }

    // Returns shop using given shop number
    public Shop getShopByShopNumber(String shopNumber)
    {
        DSAGraphVertex vertex = getVertexByShopNumber(shopNumber);
        return vertex.getShop();
    }

    // Returns vertex of a shop using a given shop number
    public DSAGraphVertex getVertexByShopNumber(String shopNumber)
    {
        boolean isFound = false;
        DSAGraphVertex foundVertex = null;
        Iterator iter = vertices.iterator();

        while(iter.hasNext() && !isFound)
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            if(currentVertex.getShop().getShopNumber().equals(shopNumber)) // Check if the current vertex's shop number matches the required one
            {
                isFound = true;
                foundVertex = currentVertex;
            }
        }

        if (!isFound) // Shop with given number does not exist
        {
            throw new NoSuchElementException("Shop with the given number not found");
        }

        return foundVertex;
    }

    // Returns vertex count in graph
    public int getVertexCount()
    {
        int count = 0;
        Iterator iter = vertices.iterator();

        while(iter.hasNext())
        {
            iter.next();
            count++;
        }

        return count;
    }

    // Calculate and return number of edges in graph
    public int getEdgeCount()
    {
        int count = 0;
        Iterator vertexIter = vertices.iterator();

        while(vertexIter.hasNext())
        {
            DSAGraphVertex vertex = (DSAGraphVertex) vertexIter.next();
            Iterator adjIter = vertex.getAdjacent().iterator();

            while(adjIter.hasNext()) // For each vertex, count how many edges it has
            {
                adjIter.next();
                count++;
            }
        }
        return (count / 2); // Divide by 2 because each edge is added twice
    }

    // Check if two vertices are adjacent by their vertices
    public boolean isAdjacent(DSAGraphVertex vertex1, DSAGraphVertex vertex2)
    {
        boolean adjacent = false;

        Iterator iter = vertex1.getAdjacent().iterator();

        while(iter.hasNext() && !adjacent) // Checks in the adjacency list of vertex1 if vertex2 exists
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            if(currentVertex.equals(vertex2))
            {
                adjacent = true;
            }
        }

        return adjacent;
    }

    // Returns next unvisited vertex from adjacency list of given vertex
    private DSAGraphVertex getNextUnvisitedAdjacent(DSAGraphVertex v)
    {
        Iterator iterator = v.getAdjacent().iterator();

        while(iterator.hasNext())
        {
            DSAGraphVertex w = (DSAGraphVertex) iterator.next();
            if (!w.getVisited())
            {
                return w;
            }
        }
        return null;
    }

    // Resets visited status of all vertices in graph
    private void resetVisited()
    {
        Iterator iterator = vertices.iterator();

        while(iterator.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex) iterator.next();
            v.resetVisited();
        }
    }

    // Adds a new shop (vertex) to graph
    public void addShop(Shop shop) 
    {
        if (shop == null || shop.getShopNumber() == null || shop.getShopNumber().trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Invalid Shop or Shop Number");
        }

        DSAGraphVertex newVertex = new DSAGraphVertex(shop);
        if(hasShop(newVertex))
        {
            throw new IllegalArgumentException("Shop already exists in graph");
        }

        vertices.insertLast(newVertex);
    }

    // Removes a shop (vertex) from graph
    public void removeShop(String shopNumber)
	{
	    if (shopNumber == null || shopNumber.trim().isEmpty()) 
        {
		    throw new IllegalArgumentException("Invalid shop number provided");
	    }

        if(!doesShopNumberExist(shopNumber))
        {
            throw new IllegalArgumentException("Shop with provided number does not exist");
        }

	    DSAGraphVertex targetVertex = getVertexByShopNumber(shopNumber);
	    
	    // Remove targetVertex from the adjacency list of all other vertices
	    Iterator iter = vertices.iterator();
	    while (iter.hasNext())
        {
		    DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
		    if (isAdjacent(currentVertex, targetVertex))
            {
		        currentVertex.removeEdge(targetVertex);
		    }
	    }

	    // Remove the target vertex from the main vertices list
	    vertices.remove(targetVertex);
	}
    
    // Adds a pathway (edge) between two shops
    public void addPathway(String shopNumber1, String shopNumber2)
    {
        // Validates shop input
        if (shopNumber1 == null || shopNumber1.trim().isEmpty() || shopNumber2 == null || shopNumber2.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Invalid shop numbers provided");
        }

        // Check if both shops exist in graph
        if(!doesShopNumberExist(shopNumber1))
        {
            throw new IllegalArgumentException("Shop number " + shopNumber1 + " does not exist");
        }
        else if(!doesShopNumberExist(shopNumber2))
        {
            throw new IllegalArgumentException("Shop number " + shopNumber2 + " does not exist");
        }

        DSAGraphVertex vertex1 = getVertexByShopNumber(shopNumber1);
        DSAGraphVertex vertex2 = getVertexByShopNumber(shopNumber2);
        
        if (vertex1 == null || vertex2 == null) 
        {
            throw new NoSuchElementException("One or both shops do not exist");
        }
        
        vertex1.addEdge(vertex2); // Add vertex2 to the adjacency list of vertex1
        vertex2.addEdge(vertex1); // Add vertex1 to the adjacency list of vertex2
    }

    // Removes a pathway (edge) between two shops
    public void removePathway(String shopNumber1, String shopNumber2)
    {
        // Validates shop input
        if (shopNumber1 == null || shopNumber1.trim().isEmpty() || shopNumber2 == null || shopNumber2.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Invalid shop numbers provided");
        }

        // Check if both shops exist in graph
        if(!doesShopNumberExist(shopNumber1))
        {
            throw new IllegalArgumentException("Shop number " + shopNumber1 + " does not exist");
        }
        else if(!doesShopNumberExist(shopNumber2))
        {
            throw new IllegalArgumentException("Shop number " + shopNumber2 + " does not exist");
        }

        DSAGraphVertex vertex1 = getVertexByShopNumber(shopNumber1);
        DSAGraphVertex vertex2 = getVertexByShopNumber(shopNumber2);
        
        if (vertex1 == null || vertex2 == null) 
        {
            throw new NoSuchElementException("One or both shops do not exist");
        } 

        vertex1.removeEdge(vertex2); // Remove vertex2 from the adjacency list of vertex1
        vertex2.removeEdge(vertex1); // Remove vertex1 from the adjacency list of vertex2
    }

    // Display graph as an adjacency list
    public void displayAsList() 
    {
        Iterator vertexIter = vertices.iterator();

        while(vertexIter.hasNext()) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) vertexIter.next();
            System.out.printf("%-3s |", currentVertex.getShop().getShopNumber()); // Assuming max 3 digits
            Iterator adjIter = currentVertex.getAdjacent().iterator();

            while(adjIter.hasNext()) 
            {
                DSAGraphVertex adjVertex = (DSAGraphVertex) adjIter.next();
                System.out.printf("  %-3s", adjVertex.getShop().getShopNumber()); // Assuming max 3 digits
            }

            System.out.println();
        }
    }

    // Display graph as an adjacency matrix
    public void displayAsMatrix() 
    {
        int numVertices = getVertexCount();
        DSAGraphVertex[] arr = new DSAGraphVertex[numVertices];
        int[][] matrix = new int[numVertices][numVertices];

        Iterator iter = vertices.iterator();
        int idx = 0;

        while(iter.hasNext()) 
        {
            arr[idx] = (DSAGraphVertex) iter.next();
            idx++;
        }

        System.out.print("    ");
        for(int i = 0; i < numVertices; i++) 
        {
            System.out.printf("%2s  ", arr[i].getShop().getShopNumber());
        }
        System.out.println();

        for(int i = 0; i < numVertices; i++) 
        {
            System.out.printf("%2s  ", arr[i].getShop().getShopNumber());
            for(int j = 0; j < numVertices; j++) 
            {
                if(isAdjacent(arr[i], arr[j])) 
                {
                    matrix[i][j] = 1;
                } 
                else 
                {
                    matrix[i][j] = 0;
                }
                System.out.printf("%2d  ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // Wrapper method for breadth-first search
    public String breadthFirstSearch(String startShop, String endShop) 
    {
        return BFSRec(startShop, endShop);
    }

    // Wrapper method for depth-first search
    public String depthFirstSearch(String startShop, String endShop) 
    {
        return DFSRec(startShop, endShop);
    }

    // Runs a breadth-first search between a start and destination shop (vertex)
    private String BFSRec(String startShop, String endShop) 
    {
        DSAQueue T = new DSAQueue(); // Initialise new empty queue
        DSAQueue Q = new DSAQueue(); // Initialise new empty stack

        resetVisited(); // Reset visited status of all vertices

        DSAGraphVertex startVertex = getVertexByShopNumber(startShop);
        DSAGraphVertex endVertex = getVertexByShopNumber(endShop);

        startVertex.setVisited();
        Q.enqueue(startVertex); // Begin search from start vertex

        while (!Q.isEmpty() && !endVertex.getVisited()) 
        {
            DSAGraphVertex v = (DSAGraphVertex) Q.dequeue();
            Iterator adjIterator = v.getAdjacent().iterator();

            while(adjIterator.hasNext() && !endVertex.getVisited()) 
            {
                DSAGraphVertex w = (DSAGraphVertex) adjIterator.next();
                if (!w.getVisited()) 
                {
                    w.setParent(v); // Sets parent to vertex where we came from
                    w.setVisited(); // Marks vertex as visited
                    Q.enqueue(w); // Adds vertex to queue
                }
            }
        }

        if (endVertex.getParent() == null) // If end vertex was not reached (no path available)
        {
            throw new NoSuchElementException("No path found between the provided shops using BFS.");
        }

        return extractPath(endVertex); // Extracts and returns path
    }

    // Runs a breadth-first search between a start and destination shop (vertex)
    private String DFSRec(String startShop, String endShop)
    {
        DSAStack stack = new DSAStack(); // Initialises new empty stack

        resetVisited(); // Reset visited status of all vertices

        DSAGraphVertex startVertex = getVertexByShopNumber(startShop);
        DSAGraphVertex endVertex = getVertexByShopNumber(endShop);
        DSAGraphVertex currentVertex;
        
        startVertex.setVisited();
        stack.push(startVertex); // Begin search from start vertex
        
        boolean foundEndVertex = false; // flag to determine if end vertex is found

        while (!stack.isEmpty() && !foundEndVertex) 
        {
            currentVertex = (DSAGraphVertex) stack.pop();
            
            if (currentVertex.equals(endVertex)) 
            {
                foundEndVertex = true;
            }
            else 
            {
                Iterator adjIterator = currentVertex.getAdjacent().iterator();
                while (adjIterator.hasNext()) 
                {
                    DSAGraphVertex adjacentVertex = (DSAGraphVertex) adjIterator.next();
                    
                    if (!adjacentVertex.getVisited()) 
                    {
                        adjacentVertex.setVisited(); // Marks the vertex as visited
                        adjacentVertex.setParent(currentVertex); // Sets parent to vertex where we came from
                        stack.push(adjacentVertex);
                    }
                }
            }
        }

        if (endVertex.getParent() == null) // If end vertex was not reached (no path available)
        {
            throw new NoSuchElementException("No path found between the provided shops using DFS.");
        }

        return extractPath(endVertex); // Extracts and returns path
    }

    // Compares dfs and bfs traversal methods and prints the shortest path of the two
    public void compareTraversal(String startShopNumber, String endShopNumber)
    {
        // Get bfs and dfs paths
        String bfsPath = breadthFirstSearch(startShopNumber, endShopNumber);
        String dfsPath = depthFirstSearch(startShopNumber, endShopNumber);

        // Count edges in both paths
        int bfsEdgeCount = countEdgesInPath(bfsPath);
        int dfsEdgeCount = countEdgesInPath(dfsPath);

        // Get shop names of start and end shops
        String startShopName = getShopByShopNumber(startShopNumber).getShopName();
        String endShopName = getShopByShopNumber(endShopNumber).getShopName();

        // Compares edge count and prints the shortest path
        if (bfsEdgeCount < dfsEdgeCount)
        {
            System.out.println("\nShortest path from " + startShopName + " to " + endShopName + " is: " + bfsPath);
        }
        else if (dfsEdgeCount < bfsEdgeCount)
        {
            System.out.println("\nShortest path from " + startShopName + " to " + endShopName + " is: " + dfsPath);
        }
        else
        {
            System.out.println("\nShortest path from " + startShopName + " to " + endShopName + " is: " + bfsPath);
        }
    }

    // Returns edge count in a given path
    private int countEdgesInPath(String path)
    {
        return path.split(" -> ").length - 1; // The number of edges is one less than the number of vertices in the path
    }

    // Extract the path from a given end vertex by backtracking using parent pointers
    private String extractPath(DSAGraphVertex endVertex) 
    {
	    StringBuilder path = new StringBuilder();
	    DSAGraphVertex currentVertex = endVertex; // Start backtracking from the end vertex

	    while (currentVertex != null) 
        {
            Shop currentShop = currentVertex.getShop();
            path.insert(0, "[Shop " + currentShop.getShopNumber() + ", " + currentShop.getShopName() + ", " + currentShop.getShopLocation() + "]");

		    currentVertex = currentVertex.getParent(); // Move to parent (previous) vertex

            if (currentVertex != null) 
            {
                path.insert(0, " ---> "); // Arrow to represent an edge
            }
	    }
	    
	    return path.toString(); // Returns extracted path as a string output
	}
}
