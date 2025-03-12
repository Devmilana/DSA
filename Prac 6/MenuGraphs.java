/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *
 * LAST EDITED: 21/09/23                                                                 *
 *
 * DESCRIPTION: Menu for creating, manipulating and displaying a graph		         *                                                                  
 * **************************************************************************************/
import java.util.*;

public class MenuGraphs 
{
    public static void main(String[] args) 
    {
        DSAGraph graph = new DSAGraph();
        
        Scanner sc = new Scanner(System.in);
        
        int choice;
        String vAdd, vDel, startEdge, endEdge;
        
        boolean exitMenu = false;

        /** ============================  Main Menu  ================================= **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n========================================");
                System.out.println("\n\t       Graph Menu         	      ");
                System.out.println("\n========================================");
                System.out.println("\nSelect an option:");
                System.out.println("\n\t[ 1 ] Add a vertex");
                System.out.println("\t[ 2 ] Add edge");
                System.out.println("\t[ 3 ] Delete a vertex");
                System.out.println("\t[ 4 ] Delete edge");
                System.out.println("\t[ 5 ] Display as List");
                System.out.println("\t[ 6 ] Display as Matrix");
                System.out.println("\t[ 7 ] Breadth First Search");
                System.out.println("\t[ 8 ] Depth First Search");
                System.out.println("\t[ 0 ] Exit");
                
                System.out.print("\nEnter your choice: ");

                if (sc.hasNextInt()) 
                {
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) 
                    {
                        // Exit program
                        case 0:
                            System.out.println("\nExiting program...\n");
                            exitMenu = true;
                            break;
                        
                        // Add a vertex
                        case 1:
                    	    System.out.print("\nEnter a vertex label to add: ");
                    	    vAdd = sc.next();
                    	    graph.addVertex(vAdd);
                            
                            System.out.println("\nNode " + vAdd + " added successfully!");
                            break;
                        
                        // Add an edge
                        case 2:
                            System.out.print("\nEnter starting vertex label: ");
                    	    startEdge = sc.next();
                    	    
                    	    System.out.print("Enter ending vertex label: ");
                    	    endEdge = sc.next();
                    	    
                    	    graph.addEdge(startEdge, endEdge);
                    	    
                    	    System.out.println("\nAdded an edge between " + startEdge + " and " + endEdge + " successfully!");
                    	    break;
                    	    
                        // Delete a vertex
                        case 3:
                            System.out.print("\nEnter a vertex label to delete: ");
                    	    vDel = sc.next();
                    	    graph.deleteVertex(vDel);
                            
                            System.out.println("\nNode " + vDel + " deleted successfully!");
                            break;                   
                        
                        // Delete an edge
                        case 4:
                            System.out.print("\nEnter starting vertex label: ");
                    	    startEdge = sc.next();
                    	    
                    	    System.out.print("Enter ending vertex label: ");
                    	    endEdge = sc.next();
                    	    
                    	    graph.deleteEdge(startEdge, endEdge);
                    	    
                    	    System.out.println("\nDeleted edge between " + startEdge + " and " + endEdge + " successfully!");
                    	    break;
                        
                        // Display graph as an adjacency list
                        case 5:
                            
                            System.out.println("\nGraph as an adjacency list\n");
                            graph.displayAsList();
                            
                            break;
                        
			// Display graph as an adjacency list
                        case 6:
                            System.out.println("\nGraph as an adjacency matrix\n");
                            graph.displayAsMatrix();
                            
                            break;
                        
                        // Display Breadth-first Search
                        case 7:
                            System.out.println("\nBreadth First Search: " + graph.breadthFirstSearch());
                            break;
                        
                        // Display Depth-first Search
                        case 8:
                            System.out.println("\nDepth First Search: " + graph.depthFirstSearch());
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
}
