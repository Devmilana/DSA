/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *					                                            						 *
 * LAST EDITED: 11/10/23                                                                 *
 *								                                            			 *
 * DESCRIPTION: Menu for adding and deleting hashtable entries, checking load factor,	 *
 *		        importing and exporting hashtable as a csv	               				 *                                                                  
 * **************************************************************************************/
import java.util.*;

public class MenuHashTable 
{
    public static void main(String[] args) 
    {
        DSAHashTable table = new DSAHashTable(10);
        
        Scanner sc = new Scanner(System.in);
        
        int choice;
        String addKey, delKey, findKey, fileName; 
        Object value;
        
        boolean exitMenu = false;

        /** ============================  Hashtable Main Menu  ================================= **/
        while (!exitMenu) 
        {
            try 
            {
                System.out.println("\n========================================");
                System.out.println("\n\t    Hashtable Menu         	      ");
                System.out.println("\n========================================");
                System.out.println("\nSelect an option:");
                System.out.println("\n\t[ 1 ] Add an entry");
                System.out.println("\t[ 2 ] Delete an entry");
                System.out.println("\t[ 3 ] Get value by key");
                System.out.println("\t[ 4 ] Get current table Load Factor");
                System.out.println("\t[ 5 ] Import csv file to table");
                System.out.println("\t[ 6 ] Export table to csv file");
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
                        
                        // Add an entry
                        case 1:
                    	    System.out.print("\nEnter key: ");
                    	    addKey = sc.nextLine();
                    	    
                    	    try
                    	    {
                    	    	if(table.hasKey(addKey))
                    	    	{
                    	             throw new IllegalArgumentException("Key " + addKey + " already exists in table");
                    	        }
                    	        else
                    	        {
                                     System.out.print("\nEnter value: ");
                                     value = sc.nextLine();
                                    
                                     try 
                                     {
                                         table.put(addKey, value);
                                         System.out.println("\nNew entry added successfully");
                                     } 
                                     catch(IllegalArgumentException e) 
                                     {
                                         System.out.println(e.getMessage());
                                     }
                                }
                            }
                            catch(IllegalArgumentException e)
                            {
                                System.out.println("Key " + addKey + " already exists in table");
                            }
			    
		                    break;
                        
                        // Delete an entry
                        case 2:
                            System.out.print("\nEnter key to delete: ");
		                    delKey = sc.nextLine();
		            
		                    try 
                            {
                                table.remove(delKey);
                                System.out.println("\nEntry deleted successfully");
                            } 
                            catch(NoSuchElementException e) 
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
		            	    
                        // Retreive value from key
                        case 3:
                            System.out.print("\nEnter key to retrieve value: ");
                            findKey = sc.nextLine();
                            
                            try 
                            {
                                value = table.get(findKey);
                                System.out.println("\nValue of key " + findKey + " is: " + value);
                            } 
                            catch(NoSuchElementException e) 
                            {
                                System.out.println(e.getMessage());
                            }
                            break;                  
                        
                        // Display current table's load factor
                        case 4:
                            System.out.printf("Current table load factor: %.5f\n", table.getLoadFactor());
                    	    break;
                    	
                    	// Import data from csv
                    	case 5:
                            System.out.print("\nEnter name of csv file: ");
                            fileName = sc.nextLine();

                            try
                            {
                                System.out.println("\nImporting csv data to hashtable...");
                                DSAHashTableIO.loadCSV(table, fileName);
                                System.out.println("\nFile imported successfully");
                            }
                            catch(Exception e)
                            {
                                System.out.println("\nImporting csv file failed! ");
                            }
                    	    break;
                    	                        	    
                    	// Export table to csv
                    	case 6:
                            System.out.print("\nEnter name of new csv file: ");
                            fileName = sc.nextLine();

                            try
                            {
                                System.out.println("\nExporting hashtable to new csv file...");
                                DSAHashTableIO.saveCSV(table, fileName);
                                System.out.println("\nFile exported successfully");
                            }
                            catch(Exception e)
                            {
                                System.out.println("\n\nExporting csv file failed!");
                            }
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
