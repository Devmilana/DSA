/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *
 * LAST EDITED: 14/09/23                                                                 *
 *
 * DESCRIPTION: Menu for creating, manipulating and displaying a binary search tree      *                                                                  
 * **************************************************************************************/
import java.util.*;

public class BSTMenu 
{
    public static void main(String[] args) 
    {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        
        Scanner sc = new Scanner(System.in);
             
        int addKey, deleteKey, findKey, minValue, maxValue, treeHeight, choice;
        String addValue, treeBalance;
        Object foundValue;
        
        boolean exitMenu = false;

        /** ============================  Main Menu  ================================= **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n========================================");
                System.out.println("\n        Binary Search Tree Menu         ");
                System.out.println("\n========================================");
                System.out.println("\nSelect an option:");
                System.out.println("\n\t[ 1 ] Add a node");
                System.out.println("\t[ 2 ] Delete a node");
                System.out.println("\t[ 3 ] Find a node");
                System.out.println("\t[ 4 ] Find min key");
                System.out.println("\t[ 5 ] Find max key");
                System.out.println("\t[ 6 ] Find tree height");
                System.out.println("\t[ 7 ] Find tree balance");
                System.out.println("\t[ 8 ] Display current tree");
                System.out.println("\t[ 9 ] Tree traversal menu");
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
                        
                        // Add a node
                        case 1:
                            System.out.print("\nEnter a key: ");
                            addKey = sc.nextInt();
                            sc.nextLine();
                            
                            System.out.print("Enter a value: ");
                            addValue = sc.nextLine();
                            tree.insert(addKey, addValue);
                            
                            System.out.println("Node added successfully!");
                            break;
                        
                        // Delete a node
                        case 2:
                            System.out.print("\nEnter a key to delete: ");
                            deleteKey = sc.nextInt();
                            tree.delete(deleteKey);
                            
                            System.out.println("Node deleted successfully!");
                            break;
                        
                        // Find a node
                        case 3:
                            System.out.print("\nEnter a key to find its value: ");
                            findKey = sc.nextInt();
                            foundValue = tree.find(findKey);
                            
                            System.out.println("Found node value " + foundValue + " at key " + findKey);
                            break;
                        
                        // Find min key in tree
                        case 4:
                            minValue = tree.min();
                            
                            System.out.println("Minimum key: " + minValue);
                            break;
                        
                        // Find max key in tree
                        case 5:
                            maxValue = tree.max();
                            
                            System.out.println("Maximum key: " + maxValue);
                            break;
                        
			            // Find tree height
                        case 6:
                            treeHeight = tree.height();
                            
                            System.out.println("Height of the tree: " + treeHeight);
                            break;
                        
                        // Find tree balance
                        case 7:
                            treeBalance = tree.balance();
                            
                            System.out.println("Tree balance: " + treeBalance);
                            break;
                        
                        // Display tree in Inorder
                        case 8:
                            tree.display();
                            break;
                        
                        // Enter order traversal sub menu
                        case 9:
                            displayTreeSubMenu(tree, sc);
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

    private static void displayTreeSubMenu(DSABinarySearchTree tree, Scanner sc) 
    {
        boolean exitSubMenu = false;
        int choice;

        /** ========================  Tree Traversal Menu  =========================== **/
        while (!exitSubMenu) 
        {
            try 
            {
                System.out.println("\nDisplay Tree Menu:");
                System.out.println("\n\t[ 1 ] Inorder Traversal");
                System.out.println("\t[ 2 ] Preorder Traversal");
                System.out.println("\t[ 3 ] Postorder Traversal");
                System.out.println("\t[ 0 ] Back to Main Menu");
                System.out.print("\nEnter your choice: ");

                if (sc.hasNextInt()) 
                {
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) 
                    {
                        // Return to main menu
                        case 0:
                            exitSubMenu = true;
                            break;
                        
                        // Display tree in Inorder traversal
                        case 1:
                            System.out.print("Inorder Traversal: ");
                            tree.displayInorder();
                            System.out.println();
                            break;
                        
                        // Display tree in Preorder traversal
                        case 2:
                            System.out.print("Preorder Traversal: ");
                            tree.displayPreorder();
                            System.out.println();
                            break;
                        
                        // Display tree in Postorder traversal
                        case 3:
                            System.out.print("Postorder Traversal: ");
                            tree.displayPostorder();
                            System.out.println();
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
    }
}
