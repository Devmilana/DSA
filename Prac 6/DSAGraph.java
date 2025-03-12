/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *
 * LAST EDITED: 21/09/23                                                                 *
 *
 * DESCRIPTION: Class file for creating and manipulating a graph                         *                                                                  
 * **************************************************************************************/
import java.util.*;

public class DSAGraph 
{
    // Inner class for vertex edge linked list
    private class DSAGraphVertex 
    {
        /** ================== DSAGraphVertex ================== **/
        private String label;          // Label of the vertex
        private DSALinkedList adjList; // List of adjacent vertices
        private boolean visited;       // Flag for traversal methods

        // Constructor for inner vertex list
        public DSAGraphVertex(String inLabel) 
        {
            label = inLabel;
            adjList = new DSALinkedList();
            visited = false;
        }

        // Accessor for label
        private String getLabel() 
        {
            return label;
        }

        // Accessor for adjacent vertices
        private DSALinkedList getAdjacent() 
        {
            return adjList;
        }

        // Check if vertex has been visited
        private boolean getVisited()
        {
            return visited;
        }
        
        // Standard toString method
        public String toString()
        {
            return label;
        }

        // Checks if two vertices are equal based on their label
        public boolean equals(DSAGraphVertex inVertex)
        {
            return (label.equals(inVertex.getLabel()));
        }

        // Adds an edge to the adjacency list of this vertex
        private void addEdge(DSAGraphVertex vertex) 
        {
            adjList.insertLast(vertex);
        }
        
        // Remove an edge from the adjacency list of this vertex
        private void removeEdge(DSAGraphVertex vertex) 
	    {
    	    adjList.remove(vertex);  
    	}

        // Mark vertex as visited
        private void setVisited()
        {
            visited = true;
        }

        // Reset visited status of vertex
        private void resetVisited()
        {
       	    visited = false;
       	}
    }
    
    /** ===================== DSAGraph ===================== **/
    private DSALinkedList vertices; // List containing all vertices in the graph            

    // Constructor for outer vertex list
    public DSAGraph() 
    {
        vertices = new DSALinkedList();
    }

    // Add a new vertex to the graph using a label
    public void addVertex(String inLabel)
    {
        if(hasVertex(inLabel)) 
        {
            throw new IllegalArgumentException("Vertex " + inLabel + " already exists in graph");
        }

        vertices.insertLast(new DSAGraphVertex(inLabel));
    }

    // Add a new vertex to the graph using a vertex object
    public void addVertex(DSAGraphVertex inVertex)
    {
        if(hasVertex(inVertex))
        {
            throw new IllegalArgumentException("Vertex " + inVertex.getLabel() + " already exists in graph");
        }

        vertices.insertLast(inVertex);
    }

    // Add an edge between two vertices using their labels
    public void addEdge(String label1, String label2)
    {
    	if (isAdjacent(label1, label2))
    	{
            throw new IllegalArgumentException("Vertices " + label1 + " and " + label2 + " are already connected");
        }
    		
        addEdge(getVertex(label1), getVertex(label2));
    }

    // Add an edge between two vertex objects
    public void addEdge(DSAGraphVertex vertex1, DSAGraphVertex vertex2)
    {
        if(isAdjacent(vertex1, vertex2))
        {
            throw new IllegalArgumentException("Vertices " + vertex1 + " and " + vertex2 + " are already connected");
        }

        vertex1.addEdge(vertex2);
        vertex2.addEdge(vertex1);
    }
    
    // Delete a vertex from the graph using its label
    public void deleteVertex(String label)
    {
        DSAGraphVertex targetVertex = getVertex(label);
        
        if (targetVertex == null)
        {
            throw new NoSuchElementException("Vertex " + label + " not found");
        }
        else
        {
            // Remove all the edges associated with this vertex
            Iterator iter = targetVertex.getAdjacent().iterator();
            while (iter.hasNext())
            {
                DSAGraphVertex adjacentVertex = (DSAGraphVertex) iter.next();
                adjacentVertex.removeEdge(targetVertex); // remove edge from adjacent vertex
            }

            // Remove the vertex from the list of vertices
            vertices.remove(targetVertex);
        }     
    }

    // Delete a vertex from the graph using the vertex object
    public void deleteVertex(DSAGraphVertex inVertex)
    {
        if (!hasVertex(inVertex))
        {
            throw new IllegalArgumentException("Vertex not found in graph");
        }
        else
        {
            // Remove all the edges associated with this vertex
            Iterator iter = inVertex.getAdjacent().iterator();
            while (iter.hasNext())
            {
                DSAGraphVertex adjacentVertex = (DSAGraphVertex) iter.next();
                adjacentVertex.removeEdge(inVertex); // remove edge from adjacent vertex
            }

            // Remove the vertex from the list of vertices
            vertices.remove(inVertex);
        }
    }
    
    // Delete an edge between two vertices using their labels
    public void deleteEdge(String label1, String label2)
    {
        if (!isAdjacent(label1, label2))
        {
            throw new IllegalArgumentException("Vertices " + label1 + " and " + label2 + " are not connected");
        }
        
        deleteEdge(getVertex(label1), getVertex(label2));
    }

    // Delete an edge between two vertex objects
    public void deleteEdge(DSAGraphVertex vertex1, DSAGraphVertex vertex2)
    {
        if (!isAdjacent(vertex1, vertex2))
        {
            throw new IllegalArgumentException("Vertices are not connected");
        }
        
        vertex1.removeEdge(vertex2);
        vertex2.removeEdge(vertex1);
    }

    // Get adjacent vertices for a given vertex label
    public DSALinkedList getAdjacent(String inLabel)
    {
        return getVertex(inLabel).getAdjacent();
    }

    // Calculate and return number of vertices in graph
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
            
            while(adjIter.hasNext()) 
            {
                adjIter.next();
                count++;
            }
        }
        return (count / 2); // Divide by 2 because each edge is added twice
    }

    // Get vertex object from its label
    public DSAGraphVertex getVertex(String inLabel)
    {
        DSAGraphVertex vertex = null;
        boolean isFound = false;

        if(!hasVertex(inLabel))
        {
            throw new NoSuchElementException("Vertex " + inLabel + " not found");
        }

        Iterator iter = vertices.iterator();
        while(iter.hasNext() && !isFound) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            if(currentVertex.getLabel().equals(inLabel)) 
            {
                vertex = currentVertex;
                isFound = true;
            }
        }
        return vertex;
    }

    // Check if a vertex exists in graph by its label
    public boolean hasVertex(String label) 
    {
        boolean found = false;
        
        Iterator iter = vertices.iterator();
        
        while(iter.hasNext() && !found) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            
            if(currentVertex.getLabel().equals(label)) 
            {
                found = true;
            }
        }

        return found;
    }
    
    // Check if a vertex exists in graph by its vertex
    public boolean hasVertex(DSAGraphVertex inVertex)
    {
        boolean found = false;
        
        Iterator iter = vertices.iterator();
        
        while(iter.hasNext() && !found) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            
            if(currentVertex.equals(inVertex)) 
            {
                found = true;
            }
        }

        return found;
    }
    
    // Check if two vertices are adjacent by their labels
    public boolean isAdjacent(String label1, String label2)
    {
        boolean adjacent = false;
        DSAGraphVertex vertex1, vertex2;

        vertex1 = getVertex(label1);
        vertex2 = getVertex(label2);

        Iterator iter = vertex1.getAdjacent().iterator();
        
        while(iter.hasNext() && !adjacent) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            
            if(currentVertex.equals(vertex2)) 
            {
                adjacent = true;
            }
        }

        return adjacent;
    }
    
    // Check if two vertices are adjacent by their vertices
    public boolean isAdjacent(DSAGraphVertex vertex1, DSAGraphVertex vertex2)
    {
        boolean adjacent = false;

        Iterator iter = vertex1.getAdjacent().iterator();
        
        while(iter.hasNext() && !adjacent) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter.next();
            
            if(currentVertex.equals(vertex2)) 
            {
                adjacent = true;
            }
        }

        return adjacent;
    }
    
    // Display graph as an adjacency list
    public void displayAsList()
    {
        Iterator vertexIter = vertices.iterator();
        
        while(vertexIter.hasNext()) 
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) vertexIter.next();
            System.out.print(currentVertex.getLabel() + " |" );
            Iterator adjIter = currentVertex.getAdjacent().iterator();
            
            while(adjIter.hasNext()) 
            {
                DSAGraphVertex adjVertex = (DSAGraphVertex) adjIter.next();
                System.out.print("  " + adjVertex.getLabel());
            }
            
            System.out.println();
        }
    }
    
    // Display graph as an adjacency matrix
    public void displayAsMatrix() 
    {
	    int numVertices = getVertexCount();
	    DSAGraphVertex[] arr;
	    int[][] matrix;
	    int idx = 0;

	    arr = new DSAGraphVertex[numVertices];
	    matrix = new int[numVertices][numVertices];

	    Iterator iter = vertices.iterator();
	    
	    while(iter.hasNext()) 
        {
            arr[idx] = (DSAGraphVertex) iter.next();
            idx++;
	    }
	    
        System.out.print("  ");
	    
        for(int i = 0; i < numVertices; i++) 
        {
		    System.out.print(arr[i].getLabel() + " ");
	    }
	    System.out.println(); 

	    for(int i = 0; i < numVertices; i++) 
        {
            // Print row label
            System.out.print(arr[i].getLabel() + " ");

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
		    
		        System.out.print(matrix[i][j] + " ");
		    }

		    System.out.println();
	    }
	}

    // Iterates through each vertex in the input list and uses a helper function to sort vertexes in order
    private DSALinkedList insertionSort(DSALinkedList list) 
    {
        DSALinkedList sorted = new DSALinkedList(); // Initialises new list to store sorted vertices
        Iterator iterator = list.iterator();
        
        while(iterator.hasNext()) // Iterates through list and inserts them into a sorted order
        {
            DSAGraphVertex currentVertex = (DSAGraphVertex) iterator.next();
            sorted = insertInOrder(sorted, currentVertex);
        }
        
        return sorted;
    }

    // Helper function that iterates through vertices and sorts them in order
    private DSALinkedList insertInOrder(DSALinkedList list, DSAGraphVertex vertex) 
    {
        DSALinkedList newList = new DSALinkedList(); // Creates newList to store sorted vertices
        boolean inserted = false;

        Iterator iterator = list.iterator();
        while(iterator.hasNext()) 
        {
            DSAGraphVertex v = (DSAGraphVertex) iterator.next(); 
            
            // If current vertex 'v' has a label > label of the vertex to be inserted
            if (!inserted && vertex.getLabel().compareTo(v.getLabel()) < 0) 
            {
                newList.insertLast(vertex); // Insert vertex
                inserted = true;
            }
            newList.insertLast(v); // Insert vertex 'v'
        }
        if (!inserted) // After iterating through list, if vertex hasn't been inserted (largest label)
        {
            newList.insertLast(vertex);
        }
        return newList;
    }
    
    // Display result of breadth-first search
    public String breadthFirstSearch() 
    {
        vertices = insertionSort(vertices);
        return BFSRec();
    }
   
    // Helper function for doing a breadth-first traversal of graph
    private String BFSRec() 
    {
        DSAQueue T = new DSAQueue();
        DSAQueue Q = new DSAQueue();
        StringBuilder result = new StringBuilder();

        resetVisited();

        DSAGraphVertex v = (DSAGraphVertex) vertices.peekFirst();
        v.setVisited();
        Q.enqueue(v);

        while (!Q.isEmpty()) 
        {
            v = (DSAGraphVertex) Q.dequeue();
            DSALinkedList adjList = insertionSort(v.getAdjacent());
            
            Iterator adjIterator = adjList.iterator();
            
            while (adjIterator.hasNext()) 
            {
                DSAGraphVertex w = (DSAGraphVertex) adjIterator.next();
                if (!w.getVisited()) 
                {
                    T.enqueue(new String[]{v.getLabel(), w.getLabel()}); // Store as pair
                    w.setVisited();
                    Q.enqueue(w);
                }
            }
        }

        while (!T.isEmpty()) 
        {
            String[] vertexPair = (String[]) T.dequeue();
            result.append("(").append(vertexPair[0]).append(",").append(vertexPair[1]).append("), ");
        }

        if (result.length() > 2) 
        {
            result.setLength(result.length() - 2); // Remove the last ", "
        }

        return result.toString();
    }
    
    // Display result of depth-first search
    public String depthFirstSearch() 
    {
        vertices = insertionSort(vertices);
        return DFSRec();
    }

    // Helper function for doing a depth-first traversal of graph
    private String DFSRec() 
    {
    	DSAQueue T = new DSAQueue();
        DSAStack S = new DSAStack();
        StringBuilder result = new StringBuilder();

        resetVisited();

        DSAGraphVertex v = (DSAGraphVertex) vertices.peekFirst();
        v.setVisited();
        S.push(v);

        while (!S.isEmpty()) 
        {
                DSAGraphVertex w = getNextUnvisitedAdjacent((DSAGraphVertex) S.top());
                if (w == null) 
                {
                    // Pop the top vertex off the stack when backtacking
                    v = (DSAGraphVertex) S.pop();

                    // If the stack isn't empty, update v to the new top
                    if (!S.isEmpty()) 
                    {
                        v = (DSAGraphVertex) S.top();
                    }
                } 
                else 
                {
                    T.enqueue(new String[]{v.getLabel(), w.getLabel()}); // Store as pair
                    w.setVisited();
                    S.push(w);
                    v = w;
                }
        }

        while (!T.isEmpty()) 
        {
            String[] vertexPair = (String[]) T.dequeue();
            result.append("(").append(vertexPair[0]).append(",").append(vertexPair[1]).append("), ");
        }

        if (result.length() > 2) 
        {
            result.setLength(result.length() - 2); // Remove the last ", "
        }

        return result.toString();
    }

    // Returns next unvisited vertex
    private DSAGraphVertex getNextUnvisitedAdjacent(DSAGraphVertex v) 
    {
        DSALinkedList adjList = insertionSort(v.getAdjacent());
        Iterator iterator = adjList.iterator();
        
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

    // Iterates through vertices and flags them as unvisited
    private void resetVisited()
    {
        Iterator iterator = vertices.iterator();
        
        while(iterator.hasNext()) 
        {
            DSAGraphVertex v = (DSAGraphVertex) iterator.next();
            v.resetVisited();
        }
    }
}
