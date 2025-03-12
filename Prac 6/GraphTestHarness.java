/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *
 * LAST EDITED: 21/09/23                                                                 *
 *
 * DESCRIPTION: Menu program that runs a test harness on the DSAGraph class	         *                                                                  
 * **************************************************************************************/
import java.util.*;

public class GraphTestHarness 
{
    public static void main(String[] args) 
    {
        DSAGraph graph = new DSAGraph();
        DSAGraph graph2 = new DSAGraph();
        
        Scanner sc = new Scanner(System.in);
        
        int choice;
        
        boolean exitMenu = false;

        /** =======================  Test Harness Main Menu  ========================== **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n==============================================================================");
                System.out.println("\n\t                DSA GRAPH TEST HARNESS   ");
                System.out.println("\n==============================================================================");
                System.out.println("\nSelect an option:");
                System.out.println("\n\t[ 1 ] Run Test Harness for graph 1");
                System.out.println("\t[ 2 ] Run Test Harness for graph 2");
                System.out.println("\t[ 3 ] Exit");

                System.out.print("\nEnter your choice: ");

                if (sc.hasNextInt()) 
                {
                    choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (choice) 
                    {
                        // Run test harness
                        case 1:
                            testHarness(graph);
                            break;
                            
                        // Run test harness
                        case 2:
                            testHarnessTwo(graph2);
                            break;
                        
                        // Exit program
                        case 3:
                            System.out.println("\nExiting test harness...\n");
                            exitMenu = true;
                            break;
                        
                        default:
                            System.out.println("\nInvalid option. Please enter a valid input.");
                            break;
                    }
                } 
                else 
                {
                    System.out.println("\nInvalid input. Please enter a valid number.");
                    sc.nextLine();
                }
            } 
            catch (NoSuchElementException e) 
            {
                System.out.println("\nError: " + e.getMessage());
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("\nError: " + e.getMessage());
            } 
            catch (Exception e) 
            {
                System.out.println("\nError: " + e.getMessage());
                sc.nextLine();
            }
        }

        sc.close();
    }
    
    public static void testHarness(DSAGraph graph)
    {  
        System.out.println("\n==============================================================================");
        System.out.println("\nPopulating graph 1 with hard coded values...\n");

        // Inserting vertices
        System.out.println("\nAdding vertex A...");
        graph.addVertex("A");
        System.out.println("\nAdding vertex B...");
        graph.addVertex("B");
        System.out.println("\nAdding vertex C...");
        graph.addVertex("C");
        System.out.println("\nAdding vertex D...");
        graph.addVertex("D");
        System.out.println("\nAdding vertex E...");
        graph.addVertex("E");
        System.out.println("\nAdding vertex F...");        
        graph.addVertex("F");
        System.out.println("\nAdding vertex G...\n");
        graph.addVertex("G");
        System.out.println("\nVertices successfully added. Now adding edges...\n");
        
        // Inserting edges
        System.out.println("\nAdding edge A - B...");
        graph.addEdge("A", "B");
        System.out.println("\nAdding edge A - C...");
        graph.addEdge("A", "C");
        System.out.println("\nAdding edge A - D...");
        graph.addEdge("A", "D");
        System.out.println("\nAdding edge B - E...");
        graph.addEdge("B", "E");
        System.out.println("\nAdding edge C - D...");
        graph.addEdge("C", "D");
        System.out.println("\nAdding edge D - F...");
        graph.addEdge("D", "F");
        System.out.println("\nAdding edge E - F...");
        graph.addEdge("E", "F");
        System.out.println("\nAdding edge E - G...");
        graph.addEdge("E", "G");
        System.out.println("\nAdding edge F - G...\n");
        graph.addEdge("F", "G");
        System.out.println("\nEdges successfully added.");
        System.out.println("\n==============================================================================");
        
        // Test list display
        System.out.println("\nGraph Populated. Displaying the graph as a list...");
        graph.displayAsList();

        // Test matrix display
        System.out.println("\nDisplaying the graph as a matrix...");
        graph.displayAsMatrix();

        // Test BFS
        System.out.println("\nRunning Breadth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph.breadthFirstSearch());
        System.out.println("Expected output: (A,B), (A,C), (A,D), (B,E), (D,F), (E,G)");
        
	// Test DFS
        System.out.println("\nRunning Depth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph.depthFirstSearch());
        System.out.println("Expected output: (A,B), (B,E), (E,F), (F,D), (D,C), (F,G)");
        
        // Test deleteVertex and deleteEdge
        System.out.println("\n==============================================================================");
        System.out.println("\nPerforming operations on graph...");
        System.out.println("\nDeleting vertex B...then deleting edge C - D...");
        graph.deleteVertex("B");
        graph.deleteEdge("C", "D");
        
        System.out.println("\nGraph Updated. Displaying updated graph as a list...");
        graph.displayAsList();

        System.out.println("\nDisplaying updated graph as a matrix...");
        graph.displayAsMatrix();

        System.out.println("\nRunning Breadth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph.breadthFirstSearch());
        System.out.println("Expected output: (A,C), (A,D), (D,F), (F,E), (F,G)");

        System.out.println("\nRunning Depth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph.depthFirstSearch());
        System.out.println("Expected output: (A,C), (A,D), (D,F), (F,E), (E,G)");
        
        System.out.println("\n==============================================================================");
        
        System.out.println("\n\n\t================   TESTING COMPLETE   ================\n");
    }
    
    public static void testHarnessTwo(DSAGraph graph2)
    {  
        System.out.println("\n==============================================================================");
        System.out.println("\nPopulating graph 2 with hard coded values...\n");

        // Inserting vertices
        System.out.println("\nAdding vertex A...");
        graph2.addVertex("A");
        System.out.println("\nAdding vertex B...");
        graph2.addVertex("B");
        System.out.println("\nAdding vertex C...");
        graph2.addVertex("C");
        System.out.println("\nAdding vertex D...");
        graph2.addVertex("D");
        System.out.println("\nAdding vertex E...");
        graph2.addVertex("E");
        System.out.println("\nAdding vertex F...");        
        graph2.addVertex("F");
        System.out.println("\nAdding vertex G...");
        graph2.addVertex("G");
        System.out.println("\nAdding vertex H...");
        graph2.addVertex("H");
        System.out.println("\nAdding vertex I...");
        graph2.addVertex("I");
        System.out.println("\nAdding vertex J...\n");
        graph2.addVertex("J");
        System.out.println("\nVertices successfully added. Now adding edges...\n");
        
        // Inserting edges
        System.out.println("\nAdding edge A - B...");
        graph2.addEdge("A", "B");
        System.out.println("\nAdding edge A - C...");
        graph2.addEdge("A", "C");
        System.out.println("\nAdding edge A - D...");
        graph2.addEdge("A", "D");
        System.out.println("\nAdding edge B - E...");
        graph2.addEdge("B", "E");
        System.out.println("\nAdding edge C - F...");
        graph2.addEdge("C", "F");
        System.out.println("\nAdding edge D - E...");
        graph2.addEdge("D", "E");
        System.out.println("\nAdding edge D - F...");
        graph2.addEdge("D", "F");
        System.out.println("\nAdding edge D - H...");
        graph2.addEdge("D", "H");
        System.out.println("\nAdding edge E - G...");
        graph2.addEdge("E", "G");
        System.out.println("\nAdding edge F - I...");
        graph2.addEdge("F", "I");
        System.out.println("\nAdding edge G - H...");
        graph2.addEdge("G", "H");
        System.out.println("\nAdding edge G - J...");
        graph2.addEdge("G", "J");
        System.out.println("\nAdding edge H - I...");
        graph2.addEdge("H", "I");
        System.out.println("\nAdding edge H - J...");
        graph2.addEdge("H", "J");
        System.out.println("\nAdding edge I - J...\n");
        graph2.addEdge("I", "J");
        System.out.println("\nEdges successfully added.");
        System.out.println("\n==============================================================================");
        
        // Test list display
        System.out.println("\nGraph Populated. Displaying the graph as a list...");
        graph2.displayAsList();

        // Test matrix display
        System.out.println("\nDisplaying the graph as a matrix...");
        graph2.displayAsMatrix();

        // Test BFS
        System.out.println("\nRunning Breadth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph2.breadthFirstSearch());
        System.out.println("Expected output: (A,B), (A,C), (A,D), (B,E), (C,F), (D,H), (E,G), (F,I), (H,J)");
        
	// Test DFS
        System.out.println("\nRunning Depth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph2.depthFirstSearch());
        System.out.println("Expected output: (A,B), (B,E), (E,D), (D,F), (F,C), (F,I), (I,H), (H,G), (G,J)");
        
        // Test deleteVertex and deleteEdge
        System.out.println("\n==============================================================================");
        System.out.println("\nPerforming operations on graph...");
        System.out.println("\nDeleting vertex D...then deleting edge H - J...");
        graph2.deleteVertex("D");
        graph2.deleteEdge("H", "J");
        
        System.out.println("\nGraph Updated. Displaying updated graph as a list...");
        graph2.displayAsList();

        System.out.println("\nDisplaying updated graph as a matrix...");
        graph2.displayAsMatrix();

        System.out.println("\nRunning Breadth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph2.breadthFirstSearch());
        System.out.println("Expected output: (A,B), (A,C), (B,E), (C,F), (E,G), (F,I), (G,H), (G,J)");

        System.out.println("\nRunning Depth First Search on the graph...");
        System.out.print("Actual output: ");
        System.out.println(graph2.depthFirstSearch());
        System.out.println("Expected output: (A,B), (B,E), (E,G), (G,H), (H,I), (I,F), (F,C), (I,J)");
        
        System.out.println("\n==============================================================================");
        
        System.out.println("\n\n\t================   TESTING COMPLETE   ================\n");
    }
}
