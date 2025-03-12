/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for creating and manipulating a hashtable data structure      *
 *              Repurposed from my DSAHashTable file submitted to practicals             *
 *****************************************************************************************/
import java.util.*;

public class DSAHashTable 
{
    /** ================== DSAHashEntry ================== **/
    private class DSAHashEntry 
    {
        private String key; // Key for entry
        private DSALinkedList valueList; // List of values for key
        private int state; // State of entry where 0 = empty, 1 = used, -1 = previously used but currently unused

        // Constructor for empty entry
        private DSAHashEntry() 
        {
            key = "";
            valueList = new DSALinkedList();
            state = 0;
        }

        // Constructor for hash entry with parameters
        private DSAHashEntry(String inKey, Shop inValue) 
        {
            if(inKey == null) // Check if key input is null
            {
                throw new IllegalArgumentException("The key cannot be a null value");
            }

            key = inKey;
            valueList = new DSALinkedList();
            valueList.insertLast(inValue); // Add shop to list
            state = 1; // Sets state to used
        }

        // Returns entry key
        public String getKey() 
        {
            return key;
        }

        // Returns list of values in key
        public DSALinkedList getValueList() 
        {
            return valueList;
        }

        // Returns entry state
        public int getState() 
        {
            return state;
        }

        // Sets entry key
        public void setKey(String inKey)
        {
            key = inKey;
        }

        // Sets entry value
        public void setValue(Shop inValue)
        {
            valueList.insertLast(inValue);
        }

        // Sets entry state
        public void setState(int inState) 
        {
            state = inState;
        }

        // Returns string output of entry values and state
        public String toString() 
        {
            return valueList.toString() + ", State: " + state;
        } 
    }

    /** ================== DSAHashTable ================== **/
    private int count;
    private DSAHashEntry[] hashArray;

    private static final double LOWER_LF = 0.4; // Lower boundary for load factor
    private static final double UPPER_LF = 0.7; // Upper boundary for load factor
    private static final int MAX_STEP = 5; // Prime number for double hash

    // Creates hashtable
    public DSAHashTable(int tableSize) 
    {
        int actualSize = nextPrime(tableSize); // Finds the actual size of array to be the next prime of given size
        hashArray = new DSAHashEntry[actualSize]; // Initialises hasharray with next prime size
        
        count = 0;

        for(int i = 0; i < actualSize; i++) 
        {
            hashArray[i] = new DSAHashEntry(); // Creates blank entries within hasharray
        }
    }
    
    // Resize array to the size of the next prime number from given size
    private void resize(int size)
    {
        DSAHashEntry[] oldArr = hashArray;
        size = nextPrime(size); // Sets size of the array to be the next prime number of given size

        hashArray = new DSAHashEntry[size]; // Initialise hasharray with newly found prime size
        
        for(int i = 0; i < size; i++)
        {
            hashArray[i] = new DSAHashEntry(); // Initialise array with empty entries
        }

        for(int i = 0; i < oldArr.length; i++) // Iterate over the old array to reinsert its elements into the new array
        {
            if(oldArr[i].getState() == 1) // Only consider entries which are occupied
            {
                DSALinkedList oldList = oldArr[i].getValueList(); // Gets list of shops for the current entry from the old array
                Iterator iter = oldList.iterator();
                
                while(iter.hasNext())
                {
                    put(oldArr[i].getKey(), (Shop)iter.next()); // Iterate over the list and reinsert each shop into the new hash table
                }
            }
        }
    }

    // Get table load factor
    public double getLoadFactor()
    {
        return ((double)count / (double)hashArray.length);
    }

    // Check if entry key exists
    public boolean hasKey(String category) 
    {
        return get(category) != null;
    }

    // Get list of values in entry key
    public DSALinkedList get(String category) 
    {
        int hashIndex = hash(category);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIndex].getState() == 0)
            {
                giveUp = true; // Value cannot be found
            }
            else if(hashArray[hashIndex].getKey().equals(category))
            {
                found = true; // Value is found in key
            }
            else
            {
                // Double hash
                hashIndex = (hashIndex + stepHash(originalIndex)) % hashArray.length;
                if(hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no key found
                }
            }
        }

        if(!found) // No key found
        {
            throw new NoSuchElementException("No shop found with the category " + category);
        }

        return hashArray[hashIndex].getValueList(); // Return list of values in entry
    }

    // Add entry key
    public void put(String category, Shop shop) 
    {
        int hashIndex = hash(category);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIndex].getState() == 0 || hashArray[hashIndex].getState() == -1) // Checking for available spots to insert key
            {
                found = true;
                hashArray[hashIndex] = new DSAHashEntry(category, shop); // Creates a new entry when inserting a shop with a new category
                hashArray[hashIndex].setState(1);
                count++;
            }
            else if(hashArray[hashIndex].getKey().equals(category)) // Check if category of new shop currently exists
            {
                found = true;
                hashArray[hashIndex].setValue(shop);  // Update the shop information if the category already exists
            }
            else // Category not found at this index
            {
                // Double hash
                hashIndex = (hashIndex + stepHash(originalIndex)) % hashArray.length;
                if(hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no spot to enter key
                }
            }
        }

        if(giveUp) // No space to enter key
        {
            throw new RuntimeException("Failed to put shop into the hash table.");
        }
        
        // Resize table if above upper load factor
        if(getLoadFactor() > UPPER_LF)
        {
            resize(hashArray.length * 2);
        }
    }

    // Remove entry key
    public void remove(String category, Shop shop)
    {
        int hashIndex = hash(category);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;
        int loopCount = 0;

        while(!found && !giveUp)
        {
            if(hashArray[hashIndex].getState() == 0)
            {
                giveUp = true; // Empty state therefore category does not exist
            }
            else if(hashArray[hashIndex].getKey().equals(category)) // Matching category found
            {
                DSALinkedList list = hashArray[hashIndex].getValueList(); // Retrieve list of shops
                Iterator iter = list.iterator();

                while(iter.hasNext() && !found) // Iterate through list of shops to find the shop to remove
                {
                    Shop currentShop = (Shop) iter.next();
                    if(currentShop.equals(shop))
                    {
                        list.remove(shop); // Remove shop from list if shop is found
                        found = true;
                    }
                }

                if(list.isEmpty()) // If list is empty after removing, update the hash entry state to mark it as deleted
                {
                    hashArray[hashIndex].setState(-1);
                    hashArray[hashIndex].setKey("");
                    count--;
                }
            }
            else // Category not found at this index
            {
                // Double hash
                hashIndex = (hashIndex + stepHash(originalIndex)) % hashArray.length;
                if(hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no key found
                }
            }
            
            // Prevent infinite loop
            loopCount++;
            if(loopCount > hashArray.length)
            {
                giveUp = true; 
            }
        }

        if(!found) // No category and shop found
        {
            throw new NoSuchElementException("No shop found with the category " + category + " to remove.");
        }

        // Resize table if below lower load factor
        if(getLoadFactor() < LOWER_LF)
        {
            resize(hashArray.length / 2);
        }
    }
 
    // Hash function
    private int hash(String category)
    {
        int a = 63689;
        int b = 378551;
        int hashIndex = 0;

        for(int i = 0; i < category.length(); i++)
        {
            hashIndex = ((hashIndex * a) + category.charAt(i));
            a *= b;
        }

        return Math.abs(hashIndex % hashArray.length);
    }

    // Step hash function
    private int stepHash(int key) 
    {
        return MAX_STEP - (key % MAX_STEP);
    }

    // Find the next prime number
    private int nextPrime(int inNum)
    {
        int i, prime = inNum;
        double rootVal;
        boolean isPrime = false;
        
        if(inNum % 2 == 0) // Even numbers are never prime therefore make it odd
        {
            prime--;
        }

        // Validate prime number
        do
        {
            prime += 2;
            i = 3;
            isPrime = true;
            rootVal = Math.sqrt((double)prime);
            
            do
            {
                if(prime % i == 0) // Failed prime number check
                {
                    isPrime = false;
                }
                else
                {
                    i += 2; // Skip testing with even numbers
                }
                
            } while (((double)i < rootVal) && isPrime);
            
        } while(!isPrime);

        return prime;
    }

    // Returns an array of shops sorted by rating for a given category 
    public Shop[] getSortedShopsByCategory(String category)
    {
        if (!hasKey(category)) // Check if category exists
        {
            throw new NoSuchElementException("No shops found for the category: " + category);
        }

        DSALinkedList shopList = get(category); // Retrieve list of shops for given category
        Iterator iter = shopList.iterator();

        
        int shopCount = 0;
        while (iter.hasNext()) // Counts total number of shops in category
        {
            iter.next();
            shopCount++;
        }

        iter = shopList.iterator(); // Reset iterator for next loop

        DSAHeap shopHeap = new DSAHeap(shopCount); // Creates new heap with size determined from number of shops

        while (iter.hasNext()) // Adds shops from list to heap one by one
        {
            Shop currentShop = (Shop) iter.next();
            shopHeap.addShop(currentShop);
        }

        return shopHeap.heapSortAndGetShops(); // Sorts and returns sorted array of shops
    }

    // Prints the categories (keys) within the hashtable
    public void displayCategories()
    {
        System.out.println("\nCurrent Shop Categories:");
        for(int i = 0; i < hashArray.length; i++)
        {
            if(hashArray[i].getState() == 1)  // Checks if the entry is in 'used' state
            {
                System.out.println(hashArray[i].getKey()); // Prints category
            }
        }
    }
}
