/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *									                                            		 *
 * LAST EDITED: 12/10/23                                                                 *
 *										                                                 *
 * DESCRIPTION: Class file for creating and manipulating a Hash Table			         *                                                                
 * **************************************************************************************/
import java.util.*;

public class DSAHashTable
{
    /** ================== DSAHashEntry ================== **/
    private class DSAHashEntry
    {
        private String key; // Entry key 
        private Object value; // Entry value
        private int state; // Entry state: 0 for free, 1 for used, -1 for previously used but currently not in use

        // Constructor for empty entry
        private DSAHashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }

        // Constructor
        private DSAHashEntry(String inKey, Object inValue)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("The key cannot be a null value");
            }

            key = inKey;
            value = inValue;
            state = 1;
        }
        
        // Get key
        public String getKey()
        {
            return key;
        }
        
        // Get key value
        public Object getValue()
        {
            return value;
        }
        
        // Get entry state
        public int getState()
        {
            return state;
        }

        // Set entry key
        public void setKey(String inKey)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("The key cannot be a null value");
            }
            
            key = inKey;
        }

        // Set entry value
        public void setValue(Object inValue)
        {
            value = inValue;
        }
        
        // Set entry state
        public void setState(int inState)
        {
            state = inState;
        }

        // String output of key, value and state
        public String toString()
        {
            return ("Key: " + key + ", Value: " + value + ", State: " + state);
        }
    }

    /** ================== DSAHashTable ================== **/
    private int count;
    private DSAHashEntry[] hashArray;
    
    private static final double LOWER_LF = 0.4; // Lower boundary for load factor
    private static final double UPPER_LF = 0.7; // Upper boundary for load factor
    private static final int MAX_STEP = 5; // Prime number for double hash

    // Create hashtable
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
        int newCount = 0;

        hashArray = new DSAHashEntry[size]; // Initialise hasharray with prime size
        
        for(int i = 0; i < size; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }

        for(int i = 0; i < oldArr.length; i++)
        {
            if(oldArr[i].state == 1)
            {
                put(oldArr[i].key, oldArr[i].value);
                newCount++;
            }
        }

        count = newCount;
    }
    
    // Check if entry key exists
    public boolean hasKey(String inKey)
    {
        int hashIndex = hash(inKey);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;

        while (!found && !giveUp)
        {
            if (hashArray[hashIndex].getState() == 0)
            {
                giveUp = true; // Key is found
            }
            else if (hashArray[hashIndex].getKey().equals(inKey))
            {
                found = true; // Key is not found
            }
            else
            {
                // Double hash
                hashIndex = ((hashIndex + stepHash(originalIndex)) % hashArray.length);
                
                if (hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no key found
                }
            }
        }
        
        return found;
    }

    // Get table load factor
    public double getLoadFactor()
    {
        return ((double)count / (double)hashArray.length);
    }

    // Get entry key value
    public Object get(String inKey)
    {
        int hashIndex = hash(inKey);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIndex].getState() == 0)
            {
                giveUp = true; // Value cannot be found
            }
            else if(hashArray[hashIndex].getKey().equals(inKey))
            {
                found = true; // Value is found in key
            }
            else
            {
                // Double hash
                hashIndex = (hashIndex + stepHash(originalIndex) % hashArray.length);
                
                if(hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no key found
                }
            }
        }

        if(!found) // No key found
        {
            throw new NoSuchElementException("No such entry for key " + inKey);
        }

        return hashArray[hashIndex].getValue();
    }

    // Add entry key
    public void put(String inKey, Object inValue)
    {
        int hashIndex = hash(inKey);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIndex].getState() == 0 || hashArray[hashIndex].getState() == -1) // Checking for available spots to insert key
            {
                found = true;
            }
            else if(hashArray[hashIndex].getState() == 1)
            {
                if(hashArray[hashIndex].getKey().equals(inKey)) // Checking for duplicate keys
                {
                    throw new IllegalArgumentException("Key " + inKey + " already exists in table");
                }

                // Double hash
                hashIndex = ((hashIndex +  stepHash(originalIndex)) % hashArray.length);
                
                if(hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no spot to enter key
                }
            }
        }

        if(!found) // No space to enter key
        {
            throw new NoSuchElementException("No space found to enter key " + inKey);
        }

        hashArray[hashIndex] = new DSAHashEntry(inKey, inValue);
        count++;

        // Resize table if above upper load factor
        if(getLoadFactor() > UPPER_LF)
        {
            resize(hashArray.length * 2);
        }
    }

    // Remove entry key
    public void remove(String inKey)
    {
        int hashIndex = hash(inKey);
        int originalIndex = hashIndex;
        boolean found = false, giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIndex].getState() == 0)
            {
                giveUp = true; // No value at key
            }
            else if(hashArray[hashIndex].getKey().equals(inKey))
            {
                found = true; // Value found at key
                count--;
            }
            else
            {
                // Double hash
                hashIndex = (hashIndex + stepHash(originalIndex) % hashArray.length);
                
                if(hashIndex == originalIndex)
                {
                    giveUp = true; // Index wrapped around and no key found
                }
            }
        }

        if(!found) // No entry found to delete
        {
            throw new NoSuchElementException("No entry found for deletion for key " + inKey);
        }

        // Update removed entry to state -1 (previously used but currently not in use)
        hashArray[hashIndex].setState(-1);
        hashArray[hashIndex].setKey("");
        hashArray[hashIndex].setValue(null);

        // Resize table if below lower load factor
        if(getLoadFactor() < LOWER_LF)
        {
            resize(hashArray.length / 2);
        }
    }

    // Hash function
    private int hash(String inKey)
    {
        int a = 63689;
        int b = 378551;
        int hashIndex = 0;

        for(int i = 0; i < inKey.length(); i++)
        {
            hashIndex = ((hashIndex * a) + inKey.charAt(i));
            a *= b;
        }

        return Math.abs(hashIndex % hashArray.length);
    }
    
    // Double hash function
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
    
    // Export state as a string array
    public String[] export()
    {
        String[] entries = new String[count];
        int j = 0;
        
        for(int i = 0; i < hashArray.length; i++)
        {
            if(hashArray[i].getState() == 1) // Check for entries that are in use
            {
                entries[j] = (hashArray[i].getKey() + "," + hashArray[i].getValue());
                j++;
            }
        }

        return entries;
    }  
}
