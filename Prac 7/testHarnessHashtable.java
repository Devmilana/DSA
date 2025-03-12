/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *		                                            									 *
 * LAST EDITED: 11/10/23                                                                 *
 *										                                            	 *
 * DESCRIPTION: Menu program that runs a test harness on the DSAHashTable class	         *                                                                  
 * **************************************************************************************/
import java.util.*;

public class testHarnessHashtable 
{
    public static void main(String[] args) 
    {
    	DSAHashTable table = new DSAHashTable(7000);
           
        Scanner sc = new Scanner(System.in);
        
        int choice;
        
        boolean exitMenu = false;

        /** =======================  Test Harness Main Menu  ========================== **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n==============================================================================");
                System.out.println("\n\t                DSA HASH TABLE HARNESS   ");
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
                            testHarness(table);
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
    
    public static void testHarness(DSAHashTable table)
    {  
        System.out.println("\n==============================================================================");
	    System.out.println("\nPopulating table...\n");
        populate(table, "RandomNames7000.csv");

        System.out.println("\n==============================================================================");
	    System.out.println("\nTesting get() function...\n");
        testGet(table);

        System.out.println("\n==============================================================================");
	    System.out.println("\nTesting hasKey() function...\n");
        testHasKey(table);

        System.out.println("\n==============================================================================");
	    System.out.println("\nTesting put(), remove() and getLoadFactor() functions...\n");
        testAddRemoveLoad(table);

        System.out.println("\n==============================================================================");
	    System.out.println("\nTesting export() function...\n");
        testExport(table);

        System.out.println("\n==============================================================================");
	
	    System.out.println("\n\n\t================   TESTING COMPLETE   ================\n");
    }
    
    // Populate hashtable with csv values
    public static void populate(DSAHashTable table, String fileName)
    {
        System.out.println("Loading " + fileName);
        DSAHashTableIO.loadCSV(table, fileName);

        System.out.println("\nLoaded file " + fileName + " successfully");
    }

    // Test getting an entry value from hashtable
    public static void testGet(DSAHashTable table)
    {
        System.out.println("Testing for existing key 14495655");
        System.out.println("Actual value found: " + table.get("14495655"));
        System.out.println("Expected value: Sofia Bonfiglio");

        System.out.println("\nTesting for non existent key 99999999");
        try
        {
            table.get("99999999");
            System.out.println("Test failed - No exception thrown");
        }
        catch(Exception e)
        {
            System.out.println("Test successful - Exception thrown as intended");
        }
    }

    // Test checking if entry exists in hashtable
    public static void testHasKey(DSAHashTable table)
    {
        System.out.println("Testing is key 14490422 exists");
        System.out.println("Actual output: " + table.hasKey("14490422"));
        System.out.println("Expected output: true");

        System.out.println("\nTesting for non existent key 99999999");
        System.out.println("Actual output: " + table.hasKey("99999999"));
        System.out.println("Expected output: false");
    }

    // Test adding entry, removing entry and displaying load factor of hashtable
    public static void testAddRemoveLoad(DSAHashTable table)
    {
        System.out.printf("Initial load factor: %.5f\n", table.getLoadFactor());

        System.out.println("\nAdding new entry with Key: 1, Value: Prasha");
        table.put("1", "Prasha");
        
        System.out.println("\nTesting for new key 1");
        System.out.println("Actual value found: " + table.get("1"));
        System.out.println("Expected value: Prasha");
        
        System.out.printf("\nNew load factor: %.5f\n", table.getLoadFactor());

        System.out.println("\nAttempting to duplicate entry");
        try
        {
            table.put("1", "Prasha");
            System.out.println("Test failed - No exception thrown");
        }
        catch(Exception e)
        {
            System.out.println("Test successful - Exception thrown as intended");
        }

        System.out.println("\nDeleting entry 1 Prasha");
        table.remove("1");
        
        System.out.printf("\nLoad factor after deletion: %.5f\n", table.getLoadFactor());
    }

    // Test exporting hashtable to new CSV file
    public static void testExport(DSAHashTable table)
    {
        System.out.println("Exporting hashtable to new csv file TestExport.csv");

        try
        {
            DSAHashTableIO.saveCSV(table, "TestExport.csv");
            System.out.println("\nFile exported successfully");
        }
        catch(Exception e)
        {
            System.out.println("Exporting csv file failed! ");
        }      
    }
}
