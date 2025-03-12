/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *
 * LAST EDITED: 14/09/23                                                                 *
 *
 * DESCRIPTION: Test harness to check functionality of DSABinarySearchTree class         *                                                                  
 * **************************************************************************************/
import java.util.*;

public class BSTTestHarness 
{
    public static void main(String[] args) 
    {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        
        Scanner sc = new Scanner(System.in);
        
        int choice;
        
        boolean exitMenu = false;

        /** =======================  Test Harness Main Menu  ========================== **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n==============================================================================");
                System.out.println("\n\t            BINARY SEARCH TREE TEST HARNESS   ");
                System.out.println("\n==============================================================================");
                System.out.println("\nSelect an option:");
                System.out.println("\n\t[ 1 ] Run Test Harness");
                System.out.println("\t[ 2 ] Exit");

                System.out.print("\nEnter your choice: ");

                if (sc.hasNextInt()) 
                {
                    choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (choice) 
                    {
                        // Run test harness
                        case 1:
                            testHarness(tree);
                            break;
                        
                        // Exit program
                        case 2:
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
    
    /* testHarness */
    /* -------------------------------------------------------------------------------
     * 
     * Import: tree (DSABinarySearchTree)
     * Export: none
     *
     * Populates tree with hardcoded values and calls test functions to run on it
     *
    */
    public static void testHarness(DSABinarySearchTree tree)
    {  
        System.out.println("\n==============================================================================");
        System.out.println("\nPopulating tree...");
        
        System.out.println("\nINSERTING KEY 5 - VALUE A " + 
        		           "\nINSERTING KEY 8 - VALUE B " + 
        		           "\nINSERTING KEY 2 - VALUE C " + 
        		           "\nINSERTING KEY 7 - VALUE D " +
        		           "\nINSERTING KEY 13 - VALUE E " +
        		           "\nINSERTING KEY 1 - VALUE F " +
        		           "\nINSERTING KEY 3 - VALUE G ");
        
        // Insert hard coded values into tree
        tree.insert(5, "A");
        tree.insert(8, "B");
        tree.insert(2, "C");
        tree.insert(7, "D");
        tree.insert(13, "E");
        tree.insert(1, "F");
        tree.insert(3, "G");
        
        System.out.println();
        tree.display(); 

        // Tests find function
        System.out.println("\n==============================================================================");
        System.out.println( "\nTesting find()");
        testFind(tree);

        // Tests height function
        System.out.println("\n==============================================================================");
        System.out.println("\nTesting height()");
        testHeight(tree);

        // Tests insert function
        System.out.println("\n==============================================================================");
        System.out.println("\nTesting insert()");
        testInsert(tree);

        // Tests delete function
        System.out.println("\n==============================================================================");
        System.out.println("\nTesting delete()");
        testDelete(tree);

        // Tests tree order traversal display
        System.out.println("\n==============================================================================");
        System.out.println("\nTesting displayInorder(), displayPreorder() and displayPostorder()");
        testDisplayOrders(tree);

        // Tests balance function
        System.out.println("\n==============================================================================");
        System.out.println("\nTesting balance()");
        testBalance(tree);
        System.out.println("\n==============================================================================");
        
        System.out.println("\n\n\t================   TESTING COMPLETE   ================\n");
    }
    
    
    /* testFind */
    /* -------------------------------------------------------------------------------
     * 
     * Import: tree (DSABinarySearchTree)
     * Export: none
     *
     * Tests if the find function performs as expected
     *
    */
    public static void testFind(DSABinarySearchTree tree)
    {
        Object foundValue;

        System.out.println("\nSearching for key present in tree");
        System.out.print("Searching for key 8...");
        foundValue = tree.find(8);
        System.out.println("found value " + foundValue + " at key 8");
        System.out.println("Expected value: B ");
        
        System.out.println("\nSearching for key not in tree");
        System.out.println("\nSearching for key 12...");
        try
        {
            tree.find(12);
            System.out.println("\nTest failed - no exception thrown");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("\nException successfully thrown");
        }
    }
    
    
    /* testHeight */
    /* -------------------------------------------------------------------------------
     * 
     * Import: tree (DSABinarySearchTree)
     * Export: none
     *
     * Tests if the height function performs as expected
     *
    */
    public static void testHeight(DSABinarySearchTree tree)
    {
        int height;

        height = tree.height();
        System.out.println("\nTree height is " + height);
        System.out.println("Expected value: 2 ");
    }
    
    
    /* testInsert */
    /* -------------------------------------------------------------------------------
     * 
     * Import: tree (DSABinarySearchTree)
     * Export: none
     *
     * Tests if the insert function performs as expected
     *
    */
    public static void testInsert(DSABinarySearchTree tree)
    {
        int height, newHeight;
        Object value;

        System.out.println("\nSearching for key 12...");
        try
        {
            tree.find(12);
            System.out.println("Test failed - no exception thrown");
        }
        catch(Exception e)
        {
            System.out.println("Exception successfully thrown as key 12 is not present");
        }
        
        height = tree.height();
        System.out.println("\nCurrent tree height: " + height);

        System.out.println("\nAdding key 12 with value H...");
        tree.insert(12, "H");
        
        System.out.println("Finding if key 12 is now present...");
        try
        {
            value = tree.find(12);
            System.out.println("\nKey 12 found successfully with no exceptions thrown. Value of key 12: " + value);
            System.out.println("Expected value: H ");
        }
        catch(Exception e)
        {
            System.out.println("\nTest failed - exception was thrown");
        }
        
        newHeight = tree.height();
        System.out.println("\nNew tree height: " + newHeight);
   }
   
   
   /* testDelete */
   /* -------------------------------------------------------------------------------
    * 
    * Import: tree (DSABinarySearchTree)
    * Export: none
    *
    * Tests if the delete function performs as expected
    *
    */
   public static void testDelete(DSABinarySearchTree tree)
   {
        System.out.print("\nTree before key deletion: ");
        tree.display();
        
        System.out.println("\nDeleting key 12 (Value H)");
        tree.delete(12);
        
        System.out.println("\nTree after key 12 deletion: ");
        tree.display();
    }
    
    
    /* testDisplayOrders */
    /* -------------------------------------------------------------------------------
     * 
     * Import: tree (DSABinarySearchTree)
     * Export: none
     *
     * Tests if the tree traversal display functions perform as expected
     *
    */
    public static void testDisplayOrders(DSABinarySearchTree tree)
    {
    	System.out.println("\nTesting displayInorder()...");
    	System.out.print("Inorder traversal: ");
    	tree.displayInorder();
    	System.out.println("\nExpected order: 1, 2, 3, 5, 7, 8, 13");
    	
    	System.out.println("\nTesting displayPreorder()...");
    	System.out.print("Preorder traversal: " );
    	tree.displayPreorder();
    	System.out.println("\nExpected order: 5, 2, 1, 3, 8, 7, 13");
    	
    	System.out.println("\nTesting displayPostorder()...");
    	System.out.print("Postorder traversal: " );
    	tree.displayPostorder();
    	System.out.println("\nExpected order: 1, 3, 2, 7, 13, 8, 5");
    }
    
    
    /* testBalance */
    /* -------------------------------------------------------------------------------
     * 
     * Import: tree (DSABinarySearchTree)
     * Export: none
     *
     * Tests if the balance function performs as expected
     *
    */
    public static void testBalance(DSABinarySearchTree tree)
    {
    	String balance;
    	
    	System.out.println("\nCurrent tree is a complete tree");
    	System.out.println("Expected tree balance: 100.00%");
    	
    	balance = tree.balance();
    	
    	System.out.println("Actual tree balance: " + balance);
    }
}
