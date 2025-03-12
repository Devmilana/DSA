/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *                                                                                       *
 * LAST EDITED: 11/11/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Menu for loading heap from csv and performing heap operations            *  
 * **************************************************************************************/
import java.util.*;

public class MenuHeap 
{
    public static void main(String[] args) 
    {
        DSAHeap heap = new DSAHeap(10000); // Initializing heap with maximum size of 8000
        
        Scanner sc = new Scanner(System.in);
        
        int choice, priority;
        String value, fileName; 
        Object[][] heapEntries;
        
        boolean exitMenu = false;

        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n========================================");
                System.out.println("\n\t       Heap Menu         ");
                System.out.println("\n========================================");
                System.out.println("\nSelect an option:");
                System.out.println("\n\t[ 1 ] Add an entry");
                System.out.println("\t[ 2 ] Remove entry");
                System.out.println("\t[ 3 ] Get number of entries");
                System.out.println("\t[ 4 ] Display first 5 entries");
                System.out.println("\t[ 5 ] Display last 5 entries");
                System.out.println("\t[ 6 ] Display all entries");
                System.out.println("\t[ 7 ] Perform heapsort");
                System.out.println("\t[ 8 ] Load from CSV file");
                System.out.println("\t[ 9 ] Export to CSV file");
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
                            
                        // Add entry to heap
                        case 1:
                            System.out.print("\nEnter priority: ");
                            priority = sc.nextInt();
                            
                            System.out.print("\nEnter value: ");
                            value = sc.next();
                            
                            heap.add(priority, value);
                            
                            System.out.println("\nEntry added successfully");
                            break;
                        
                        // Remove max entry of heap
                        case 2:
                            Object removedVal = heap.remove();
                            System.out.println("\nRemoved entry with value: " + removedVal);
                            break;

                        // Display number of entries in heap
                        case 3:
                            System.out.println("\nNumber of entries: " + heap.getCount());
                            break;

                        // Display first 5 entries of heap
                        case 4:
                            heapEntries = heap.getHeapAsArray();
                            System.out.println("\nDisplaying first 5 entries:");
                            
                            for(int i = 0; i < 5 && i < heap.getCount(); i++)
                            {
                                System.out.println(heapEntries[i][0] + " , " + heapEntries[i][1]);
                            }
                            break;

                        // Display last 5 entries of heap
                        case 5:
                            heapEntries = heap.getHeapAsArray();   
                            System.out.println("\nDisplaying last 5 entries:");
                            
                            for(int i = heap.getCount() - 5; i < heap.getCount(); i++)
                            {
                                if (i >= 0)
                                {
                                    System.out.println(heapEntries[i][0] + " , " + heapEntries[i][1]);
                                }
                            }
                            break;
                            
                        // Display all entries in heap
                        case 6:
                            heapEntries = heap.getHeapAsArray();   
                            System.out.println("\nDisplaying all entries:");
                            
                            for(int i = 0; i < heap.getCount(); i++)
                            {
                                if (i >= 0)
                                {
                                    System.out.println(heapEntries[i][0] + " , " + heapEntries[i][1]);
                                }
                            }
                            break;
                        
                        // Sort heap
                        case 7:
                            heap.heapSort(heap.getHeapAsArray());
                            System.out.println("\nHeapsort completed successfully");
                            break;

                        // Load csv file
                        case 8:
                            System.out.print("\nEnter CSV filename: ");
                            fileName = sc.nextLine();
                            
                            DSAHeapIO.loadCSV(heap, fileName);
                            System.out.println("\nLoaded successfully from " + fileName);
                            break;
                        
                        // Export heap to csv file
                        case 9:
                            System.out.print("\nEnter filename to export: ");
                            fileName = sc.nextLine();
                            
                            DSAHeapIO.saveCSV(heap, fileName);
                            System.out.println("\nSaved successfully to " + fileName);
                            break;

                        default:
                            System.out.println("\nInvalid option. Please enter a valid choice.");
                            break;
                    }
                } 
                else 
                {
                    System.out.println("\nInvalid input. Please enter a valid choice.");
                    sc.nextLine();
                }
            } 
            catch (IllegalArgumentException error) 
            {
                System.out.println("\nError: " + error.getMessage());
            }
            catch (Exception error) 
            {
                System.out.println("\nError: " + error.getMessage());
                sc.nextLine();
            }
        }

        sc.close();
    }
}
