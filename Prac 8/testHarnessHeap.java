/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *		                                            									 *
 * LAST EDITED: 11/11/23                                                                 *
 *										                                            	 *
 * DESCRIPTION: Menu program that runs a test harness on the DSAHeap class	         *                                                                  
 * **************************************************************************************/
import java.util.*;

public class testHarnessHeap 
{
    public static void main(String[] args) 
    {
    	DSAHeap heap = new DSAHeap(8000);
    	DSAHeap heap2 = new DSAHeap(100);
           
        Scanner sc = new Scanner(System.in);
        
        int choice;
        
        boolean exitMenu = false;

        /** =======================  Test Harness Main Menu  ========================== **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n==============================================================================");
                System.out.println("\n\t                DSA HEAP TEST HARNESS   ");
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
                            testHarness(heap, heap2);
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
    
    public static void testHarness(DSAHeap heap, DSAHeap heap2)
    {  
        System.out.println("\n==============================================================================");
	System.out.println("\nPopulating heap using RandomNames7000.csv...");
        populate(heap, "RandomNames7000.csv");

        System.out.println("\n==============================================================================");
	System.out.println("\nTesting add(), remove() and getCount() functions...");
        testFunctions(heap);

        System.out.println("\n==============================================================================");
	System.out.println("\nTesting heapSort() function...");
        testHeapsort(heap);

        System.out.println("\n==============================================================================");
	System.out.println("\nVisualising sorting...");
        visualiseSort(heap2, "Visualiser.csv");
        
        System.out.println("\n==============================================================================");
	System.out.println("\nTesting export() function...");
        testExport(heap);

        System.out.println("\n==============================================================================");
	
	System.out.println("\n\n\t================   TESTING COMPLETE   ================\n");
    }
    
    // Populate heap with csv values
    public static void populate(DSAHeap heap, String fileName)
    {
        System.out.println("\nLoading " + fileName);
        DSAHeapIO.loadCSV(heap, fileName);

        System.out.println("\nLoaded file " + fileName + " successfully");
    }

    // Test add, remove and getCount functions of DSAHeap
    public static void testFunctions(DSAHeap heap)
    {
        System.out.println("\nInitial count of heap entries: " + heap.getCount());
	System.out.println("Expected count: 7000");
        
        System.out.println("\nAdding new entry with maximum priority: 99999999, Test Entry");
        heap.add(99999999, "Test Entry");
        
        System.out.println("\nEntry count after new addition: " + heap.getCount());
	System.out.println("Expected count: 7001\n");
	
        System.out.println("\nRemoving the highest priority entry");
        System.out.println("Removed entry: " + heap.remove());
        System.out.println("Expected removal: Test Entry");
        
        System.out.println("\nEntry count after removal: " + heap.getCount());
	System.out.println("Expected count: 7000");
    }

    // Test heapsorting
    public static void testHeapsort(DSAHeap heap)
    {
        Object[][] unsortedArray, sortedArray;
        
        System.out.println("\nSorting entries in RandomNames7000");
        
        System.out.println("\nDisplaying entries before sorting:");
        unsortedArray = heap.getHeapAsArray();

        System.out.println("\nDisplaying first 5 entries:");
        for(int i = 0; i < 5 && i < heap.getCount(); i++)
        {
            System.out.println(unsortedArray[i][0] + " , " + unsortedArray[i][1]);
        }

        System.out.println("\nDisplaying last 5 entries:");
        for(int i = heap.getCount() - 5; i < heap.getCount(); i++)
        {
            System.out.println(unsortedArray[i][0] + " , " + unsortedArray[i][1]);
        }
        
        
        System.out.println("\n\nDisplaying entries after heap sorting:");
        heap.heapSort(heap.getHeapAsArray());
        sortedArray = heap.getHeapAsArray();

        System.out.println("\nDisplaying first 5 entries:");
        for(int i = 0; i < 5 && i < heap.getCount(); i++)
        {
            System.out.println(sortedArray[i][0] + " , " + sortedArray[i][1]);
        }

        System.out.println("\nDisplaying last 5 entries:");
        for(int i = heap.getCount() - 5; i < heap.getCount(); i++)
        {
            System.out.println(sortedArray[i][0] + " , " + sortedArray[i][1]);
        }
        
    }

    // Visualise heapsorting for a new heap
    public static void visualiseSort(DSAHeap heap2, String fileName)
    {
        Object[][] heapEntries, heapEntries2;
        
        System.out.println("\nLoading Visualiser.csv to visualise heapsorting");
        
        DSAHeapIO.loadCSV(heap2, fileName);

        System.out.println("\nLoaded file " + fileName + " successfully");
          
        
        System.out.println("\nDisplaying entries before sorting:");
        heapEntries = heap2.getHeapAsArray(); 
                            
        for(int i = 0; i < heap2.getCount(); i++)
        {
           if (i >= 0)
           {
               System.out.println(heapEntries[i][0] + " , " + heapEntries[i][1]);
           }
        }
        
        
        heap2.heapSort(heap2.getHeapAsArray());
        System.out.println("\nHeapsort completed successfully");
        
        System.out.println("\nDisplaying entries after heap sorting:");
        heapEntries2 = heap2.getHeapAsArray(); 
                            
        for(int i = 0; i < heap2.getCount(); i++)
        {
           if (i >= 0)
           {
               System.out.println(heapEntries2[i][0] + " , " + heapEntries2[i][1]);
           }
        }
        
    }

    // Test exporting heap to new CSV file
    public static void testExport(DSAHeap heap)
    {
        System.out.println("\nExporting RandomNames7000 heap to new csv file TestExport.csv");

        try
        {
            DSAHeapIO.saveCSV(heap, "TestExport.csv");
            System.out.println("\nFile exported successfully");
        }
        catch(Exception e)
        {
            System.out.println("\nExporting csv file failed! ");
        }      
    }
}
