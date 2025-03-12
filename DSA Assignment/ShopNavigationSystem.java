/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Main menu program for a Shop Finding and Navigation System which uses    *
 *              a combination of linkedlists, stacks, queues, hashtables and heaps       *
 *              to provide the necessary functionalities                                 *
 *****************************************************************************************/
import java.util.*;

/** =======================  Shop Navigaion System Class  ========================== **/
public class ShopNavigationSystem 
{
    private static DSAGraph graph;
    private static DSAHashTable table;
    private static DSAHeap heap;

    /** =======================  Main Method ========================== **/
    public static void main(String[] args) 
    {
        graph = new DSAGraph(); // Initialise new graph
        table = new DSAHashTable(31); // Initialise hashtable with a sufficiently large prime size
        heap = new DSAHeap(501); // Initialise heap with a sufficiently large prime size
        
        Scanner sc = new Scanner(System.in);

        
        /*  // FOR TESTING PURPOSES! - UNCOMMENT TO AUTO-POPULATE GRAPH AND HASHTABLES WITH TEST VALUES IN CSV
        DataLoader loader = new DataLoader(); // Uses the data loader class file which contains file IO methods
        loader.loadShops(graph, table, "TestDetails.csv"); // Reads in csv file with test data and populates graph and hashtable
        loader.loadPathways(graph, "TestEdges.csv"); // Reads in csv file with test data and populates graph edges
        //  */

        
        // Calls main menu
        displayMainMenu(sc);
        
        sc.close();
    }

    /** =======================  Shop Navigaion System Main Menu  ========================== **/
    public static void displayMainMenu(Scanner sc) 
    {      
        int choice;
        boolean exitMenu = false;

        while (!exitMenu)
        {
            try 
            {
            	// Print welcome statement and menu selection screen
                System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
                System.out.println(Color.YELLOW + "\n\t            SHOP FINDING & NAVIGATION SYSTEM   " + Color.RESET);
                System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
                System.out.println("\nWelcome to the Shop Navigation System!");
                System.out.println("\nPlease select an option:");
                System.out.println("\n\t[ 1 ] Add a Shop");
                System.out.println("\t[ 2 ] Delete a Shop");
                System.out.println("\t[ 3 ] Update a Shop");
                System.out.println("\t[ 4 ] Add Pathway Between Shops");
                System.out.println("\t[ 5 ] Delete Pathway Between Shops");
                System.out.println("\t[ 6 ] Display Shops By Category");
                System.out.println("\t[ 7 ] Find Shortest Path Between Two Shops");
                System.out.println("\t[ 8 ] Display adjacency list and matrix");
                System.out.println("\t[ 9 ] Display hashtable load factor");
                System.out.println("\t[ 0 ] Exit");

                System.out.print(Color.YELLOW + "\nEnter your choice: " + Color.RESET);

                if (sc.hasNextInt()) // Validate and take in next integer input
                {
                    choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch(choice) 
                    {
                        case 1:
                            // Calls addShop function
                            addShop(sc);
                            break;
                        
                        case 2:
                            // Calls deleteShop function
                            deleteShop(sc);
                            break;
                        
                        case 3:
                            // Calls updateShop function
                            updateShop(sc);
                            break;
                        
                        case 4:
                            // Calls addPathway function
                            addPathway(sc);
                            break;
                        
                        case 5:
                            // Calls removePathway function
                            removePathway(sc);
                            break;
                        
                        case 6:
                            // Calls displayShopsByCategory function
                            displayShopsByCategory(sc);
                            break;
                        
                        case 7:
                            // Calls findShortestPath function
                            findShortestPath(sc);
                            break;
                            
                        case 8:
                            // Calls displayListAndMatrix function
                            displayListAndMatrix();
                            break;
                            
                        case 9:
                            // Calls displayLoadFactor function
                            displayLoadFactor();
                            break;
                        
                        case 0:
                            // Exist program after printing thank you statement
                            System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
                            System.out.println(Color.YELLOW + "\n\tThank you for using the Shop Navigation System! Exiting now..." + Color.RESET);
                            System.out.println(Color.GREEN + "\n==============================================================================\n" + Color.RESET);
                            exitMenu = true;
                            break;
                       
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
                else
                {
                    System.out.println("\nInvalid input. Please enter a valid number.");
                    sc.nextLine();
                }
            }
            // Exception handling for invalid menu inputs 
            catch (NoSuchElementException e) 
            {
                System.out.println("\nSystem error: " + e.getMessage());
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("\nSystem error: " + e.getMessage());
            } 
            catch (Exception e) 
            {
                System.out.println("\nSystem error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    /** =======================  Function for adding a new shop to system  ========================== **/
    public static void addShop(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            System.out.println("\nAdding shop to system...");
            
            // Takes in and validates a shop number from user
            System.out.print(Color.YELLOW + "\nEnter shop number: " + Color.RESET);
            String shopNumber = sc.nextLine();
            validateNotEmpty(shopNumber, "Shop number");
            validateShopNumber(shopNumber);
            if (graph.doesShopNumberExist(shopNumber)) // Checks if provided shop number already exists
            {
                throw new IllegalArgumentException("\nShop with the provided number already exists!" + Color.RESET);
            }

            // Takes in and validates a shop name from user
            System.out.print(Color.YELLOW + "Enter shop name: " + Color.RESET);
            String shopName = sc.nextLine();
            validateNotEmpty(shopName, "Shop name");

            // Takes in and validates a shop category from user
            System.out.print(Color.YELLOW + "Enter category: " + Color.RESET);
            String category = sc.nextLine().toUpperCase();
            validateNotEmpty(category, "Category");
            validateShopCategory(category);

            // Takes in and validates a shop location from user
            System.out.print(Color.YELLOW + "Enter location: " + Color.RESET);
            String location = sc.nextLine();
            validateNotEmpty(location, "Location");

	        // Takes in and validates a shop rating from user
            System.out.print(Color.YELLOW + "Enter rating (1-5): " + Color.RESET);
            String ratingInput = sc.nextLine();
            validateNotEmpty(ratingInput, "Rating");
            validateRatingInput(ratingInput);
            int rating = Integer.parseInt(ratingInput);

            Shop newShop = new Shop(shopNumber, shopName, category, location, rating); // Creates new shop object with given parameters
            graph.addShop(newShop); // Adds new shop object to graph
            table.put(category, newShop); // Adds new shop object to hashtable

            System.out.println("\nShop " + shopName + " added successfully!"); 
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }

    /** =======================  Function for deleting an existing shop from system  ========================== **/
    public static void deleteShop(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            int count = graph.getVertexCount(); // Get number of vertices present in graph

            if (graph.getVertexCount() == 0) // If no vertices present, graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! Cannot delete");
            }
            
            System.out.println("\nDeleting shop from system...");

            // Takes in and validates a shop number to delete from user
            System.out.print(Color.YELLOW + "\nEnter shop number to delete: " + Color.RESET);
            String shopNumber = sc.nextLine();
            validateNotEmpty(shopNumber, "Shop number");

            if(!graph.doesShopNumberExist(shopNumber)) // Check if provided shop number exists
            {
                throw new IllegalArgumentException("\nShop with shop number " + shopNumber + " does not exist!");
            }

            Shop shopToDelete = graph.getShopByShopNumber(shopNumber); // Retrieves shop object to delete using given shop number
            graph.removeShop(shopNumber); // Removes shop from graph
            table.remove(shopToDelete.getShopCategory(), shopToDelete); // Removes shop from hashtable

            System.out.println("\nShop " + shopNumber + " deleted successfully!");
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }

    /** =======================  Function for updating an existing shop in system  ========================== **/
    public static void updateShop(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try
        {
            if (graph.getVertexCount() == 0) // Check if the graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! Cannot update");
            }
            
            System.out.println("\nUpdating shop in system...");

            // Takes in and validates a shop number to update from user
            System.out.print(Color.YELLOW + "\nEnter the shop number of shop to update: " + Color.RESET);
            String shopNumber = sc.nextLine();
            validateNotEmpty(shopNumber, "Shop number");

            if(!graph.doesShopNumberExist(shopNumber)) // Check if provided shop number exists in graph
            {
                throw new IllegalArgumentException("\nShop " + shopNumber + " does not exist!");
            }

            Shop oldShop = graph.getShopByShopNumber(shopNumber); // Retrieves shop object of the shop to update using given number
            
            table.remove(oldShop.getShopCategory(), oldShop); // Removes old shop from hashtable

            // Takes in and validates a shop name from user
            System.out.print(Color.YELLOW + "Enter new shop name (or press Enter to skip): " + Color.RESET);
            String newShopName = sc.nextLine();
            if (!newShopName.trim().isEmpty()) // If given input is not empty, new parameter set to field
            {
                oldShop.setShopName(newShopName);
            }

            // Takes in and validates a shop category from user
            System.out.print(Color.YELLOW + "Enter new shop category (or press Enter to skip): " + Color.RESET);
            String newCategory = sc.nextLine().toUpperCase();
            if (!newCategory.trim().isEmpty()) // If given input is not empty, new parameter set to field
            {
                validateShopCategory(newCategory);
                oldShop.setShopCategory(newCategory);
            }
            
            // Takes in and validates a shop category from user
            System.out.print(Color.YELLOW + "Enter new shop location (or press Enter to skip): " + Color.RESET);
            String newLocation = sc.nextLine(); // If given input is not empty, new parameter set to field
            if (!newLocation.trim().isEmpty()) 
            {
                oldShop.setShopLocation(newLocation);
            }

            // Takes in and validates a shop rating from user
            System.out.print(Color.YELLOW + "Enter new shop rating (or press Enter to skip): " + Color.RESET);
            String ratingInput = sc.nextLine(); // If given input is not empty, new parameter set to field
            if (!ratingInput.trim().isEmpty()) 
            {
                int newRating = validateRatingInput(ratingInput);
                oldShop.setShopRating(newRating);
            }
            
            Shop updatedShop = graph.getShopByShopNumber(shopNumber); // Retrieves new updated shop object from graph
            table.put(updatedShop.getShopCategory(), updatedShop); // Adds new updated shop object to hashtable entry

            System.out.println("\nShop " + shopNumber + " updated successfully!");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }
 
    /** =======================  Function for adding a pathway between two existing shops in system  ========================== **/
    public static void addPathway(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            if (graph.getVertexCount() == 0) // Check if the graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! Cannot add pathway");
            }
            else if (graph.getVertexCount() < 2) // Check if there are more than two vertices in graph
            {
                throw new IllegalArgumentException("\nOnly one shop present in system. Minimum of two shops required for pathway");
            }
            
            System.out.println("\nAdding pathway between shops in system...");

            // Takes in and validates first shop number
            System.out.print(Color.YELLOW + "\nEnter shop number 1: " + Color.RESET);
            String shopNumber1 = sc.nextLine();
            validateNotEmpty(shopNumber1, "Shop number 1");
            if(!graph.doesShopNumberExist(shopNumber1)) // Checks if provided shop number exists in graph
            {
                throw new IllegalArgumentException("\nShop number " + shopNumber1 + " does not exist! Unable to create pathway");
            }

            // Takes in and validates second shop number
            System.out.print(Color.YELLOW + "Enter shop number 2: " + Color.RESET);
            String shopNumber2 = sc.nextLine();
            validateNotEmpty(shopNumber2, "Shop number 2");
            if(!graph.doesShopNumberExist(shopNumber2)) // Checks if provided shop number exists in graph
            {
                throw new IllegalArgumentException("\nShop number " + shopNumber2 + " does not exist! Unable to create pathway");
            }

            if(graph.isAdjacent(graph.getVertexByShopNumber(shopNumber1), graph.getVertexByShopNumber(shopNumber2))) // Checks if a path already exists
            {
                throw new IllegalArgumentException("\nPathway already exists between shop " + shopNumber1 + " and shop " + shopNumber2);
            }

            graph.addPathway(shopNumber1, shopNumber2); // Adds edge between the vertices of the two shops

            System.out.println("\nPathway between shops " + shopNumber1 + " and " + shopNumber2 + " added successfully!");
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }

    /** =======================  Function for deleting a pathway between two existing shops in system  ========================== **/
    public static void removePathway(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            if (graph.getVertexCount() == 0) // Check if the graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! Cannot remove pathway");
            }
            else if (graph.getVertexCount() < 2) // Check if there are more than two vertices in graph
            {
                throw new IllegalArgumentException("\nOnly one shop present in system. Minimum of two shops required for pathway");
            }
            
            System.out.println("\nDeleting pathway between shops in system...");

            // Takes in and validates first shop number
            System.out.print(Color.YELLOW + "\nEnter shop number 1: " + Color.RESET);
            String shopNumber1 = sc.nextLine();
            validateNotEmpty(shopNumber1, "Shop number 1");
            if(!graph.doesShopNumberExist(shopNumber1)) // Checks if provided shop number exists in graph
            {
                throw new IllegalArgumentException("\nShop number " + shopNumber1 + " does not exist!");
            }

            // Takes in and validates second shop number
            System.out.print(Color.YELLOW + "Enter shop number 2: " + Color.RESET);
            String shopNumber2 = sc.nextLine();
            validateNotEmpty(shopNumber2, "Shop number 2"); // Checks if provided shop number exists in graph
            if(!graph.doesShopNumberExist(shopNumber2))
            {
                throw new IllegalArgumentException("\nShop number " + shopNumber2 + " does not exist!");
            }

            if(!graph.isAdjacent(graph.getVertexByShopNumber(shopNumber1), graph.getVertexByShopNumber(shopNumber2))) // Check if no path exists
            {
                throw new IllegalArgumentException("\nNo pathway exists between shop " + shopNumber1 + " and shop " + shopNumber2);
            }

            graph.removePathway(shopNumber1, shopNumber2); // Deletes edge between the vertices of the two shops

            System.out.println("\nPathway between shops " + shopNumber1 + " and " + shopNumber2 + " removed successfully!");
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }

    /** =======================  Function for displaying shops in system based on category  ========================== **/
    public static void displayShopsByCategory(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            if (graph.getVertexCount() == 0) // Check if the graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! No shops to display");
            }

            table.displayCategories(); // Displays list of available shop categories currently present in system

            // Takes in and validates a shop category from user
            System.out.print(Color.YELLOW + "\nEnter shop category to display shop list: " + Color.RESET);
            String category = sc.nextLine().toUpperCase();
            validateNotEmpty(category, "Category");

            displaySortedShopsByRating(category); // Displays sorted list of shops within category entered by user
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }

    /** =======================  Helper function for displaying shops in system based on category  ========================== **/
    public static void displaySortedShopsByRating(String category)
    {
        DSALinkedList shopsList = table.get(category);

        Iterator iter = shopsList.iterator();

        // Counting the number of shops for the category
        int shopCount = 0;
        while (iter.hasNext())
        {
            iter.next();
            shopCount++;
        }

        // Reset iterator for the next loop
        iter = shopsList.iterator();

        DSAHeap heap = new DSAHeap(shopCount);
        while(iter.hasNext()) // Iterates through shop list and adds shops to heap
        {
            Shop shop = (Shop) iter.next();
            heap.addShop(shop);
        } 

        Shop[] sortedShops = heap.heapSortAndGetShops(); // Calls heap sorting and retrieves sorted shops in an array form

        System.out.println("\nShops in category '" + category + "' in descending order of ratings:\n");
        for (int i = 0; i < sortedShops.length; i++) // Loops through sorted array and prints shop details in formatted order
        {
            Shop shop = sortedShops[i];
            String formattedOutput = String.format("Shop Number: %-3s | Shop Name: %-17s | Shop Location: %-17s | Shop Rating: %s", // Spaced formatting for better visuals
                                                    shop.getShopNumber(),
                                                    shop.getShopName(),
                                                    shop.getShopLocation(),
                                                    shop.getShopRating());

            System.out.println(formattedOutput);
        }
    }

    /** =======================  Finds shortest pathway between two existing shops  ========================== **/
    public static void findShortestPath(Scanner sc) 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            if (graph.getVertexCount() == 0) // Check if the graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! No shops to find a path betwen");
            }
            else if (graph.getVertexCount() < 2) // Check if there are more than two vertices in graph
            {
                throw new IllegalArgumentException("\nOnly one shop present in system. Minimum of two shops required for pathway");
            }
            
            System.out.println("\nFinding shortest path between two shops in system...");

            // Takes in and validates start shop number
            System.out.print(Color.YELLOW + "\nEnter starting shop number: " + Color.RESET);
            String shopNumber1 = sc.nextLine();
            validateNotEmpty(shopNumber1, "Starting shop number");
            if(!graph.doesShopNumberExist(shopNumber1)) // Check if provided shop number exists
            {
                throw new IllegalArgumentException("\nStarting shop number " + shopNumber1 + " does not exist!");
            }

            // Takes in and validates destination shop number
            System.out.print(Color.YELLOW + "Enter destination shop number: " + Color.RESET);
            String shopNumber2 = sc.nextLine();
            validateNotEmpty(shopNumber2, "Destination shop number");
            if(!graph.doesShopNumberExist(shopNumber2)) // Check if provided shop number exists
            {
                throw new IllegalArgumentException("\nDestination shop number " + shopNumber2 + " does not exist!");
            }

            try 
            {
                System.out.println("\nRunning Breadth First Search on the graph...");
                System.out.println(graph.breadthFirstSearch(shopNumber1, shopNumber2)); // Runs bfs between given two shops
            } 
            catch (NoSuchElementException e)
            {
                System.out.println(e.getMessage());
            }

            try 
            {
                System.out.println("\nRunning Depth First Search on the graph...");
                System.out.println(graph.depthFirstSearch(shopNumber1, shopNumber2)); // Runs dfs between given two shops
            } 
            catch (NoSuchElementException e) 
            {
                System.out.println(e.getMessage());
            }

            graph.compareTraversal(shopNumber1, shopNumber2); // Compares bfs and dfs outputs and prints dfs, bfs and shortest path
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }
    
    /** =======================  Finds shortest pathway between two existing shops  ========================== **/
    public static void displayListAndMatrix() 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            if (graph.getVertexCount() == 0) // Check if the graph is empty
            {
                throw new IllegalArgumentException("\nSystem Graph is empty! Cannot display list or matrix");
            }

            // Prints adjacency list of current graph
            System.out.println("\nDisplaying shop graph as an adjacency list:\n");
            graph.displayAsList();
            
            // Prints adjacency matrix of current graph
            System.out.println("\n\nDisplaying shop graph as an adjacency matrix:\n");
            graph.displayAsMatrix();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }
    
    /** =======================  Displays current load factor of hashtable  ========================== **/
    public static void displayLoadFactor() 
    {
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
        try 
        {
            System.out.printf("\nDisplaying current hashtable load factor: %.5f\n", table.getLoadFactor());

        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println(Color.GREEN + "\n==============================================================================" + Color.RESET);
    }

    /** =======================  Helper function to validate shop number  ========================== **/
    private static void validateShopNumber(String shopNumberStr)
    {
        int shopNumber;
        try
        {
            shopNumber = Integer.parseInt(shopNumberStr);
            if (shopNumber <= 0 || shopNumber > 99) // Checks if shop number is between 1 and 99
            {
                throw new IllegalArgumentException("\nShop number must be between 1 and 99!");
            }
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("\nShop number must be a valid integer!");
        }
    }

    /** =======================  Helper function to validate shop category  ========================== **/
    private static void validateShopCategory(String category)
    {
        if (category.matches(".*\\d.*")) // Check if entered category contains numbers
        {
            throw new IllegalArgumentException("\nShop category cannot contain numbers!");
        }
    }

    /** =======================  Helper function to validate shop rating input  ========================== **/
    private static int validateRatingInput(String ratingInput) 
    {
        int rating;
        try 
        {
            rating = Integer.parseInt(ratingInput);
        }
        catch (NumberFormatException e) 
        {
            throw new IllegalArgumentException("\nInvalid rating input. Please enter a number between 1 and 5");
        }

        validateShopRating(rating);

        return rating;
    }

    /** =======================  Helper function to validate shop rating parameters  ========================== **/
    private static void validateShopRating(int rating)
    {
        if (rating < 1 || rating > 5) // Checks if rating is between 1 and 5 inclusive
        {
            throw new IllegalArgumentException("\nShop rating must be between 1 and 5!");
        }
    }

    /** =======================  Helper function to validate empty parameters  ========================== **/
    private static void validateNotEmpty(String input, String fieldName)
    {
        if (input.trim().isEmpty()) // Checks if input is empty
        {
            throw new IllegalArgumentException("\n" + fieldName + " cannot be empty");
        }
    }
}
