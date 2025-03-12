/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO 								 *
 *
 * DATE CREATED: 16/08/23      								 *
 *
 * LAST EDITED: 23/08/23       								 *
 *
 * DESCRIPTION: Class file for creating and manipulating an array structured as  	 *
 *              a shuffling queue extending methods from DSAQueue			 *
 * **************************************************************************************/
public class DSAShufflingQueue extends DSAQueue
{
    // Default constructor
    public DSAShufflingQueue()
    {
        super(); 
    }

    // Alternate constructor
    public DSAShufflingQueue(int maxCapacity) 
    {
        super(maxCapacity);
    }

    // Mutators
    public void enqueue(Object value)
    {
        if (isFull())
        {
            throw new IllegalStateException("The queue is full");
        } 
        else 
        {
            // Shift all elements up by one
            for (int i = getCount(); i > 0; i--) 
            {
                getQueue()[i] = getQueue()[i - 1];
            }

            // Add the new value at the front
            getQueue()[0] = value;
            
            incrementCount();
        }
    }

    public Object dequeue()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The queue is empty");
        } 
        else 
        {
            Object frontValue = getQueue()[getCount() - 1]; // Get the front value
            getQueue()[getCount() - 1] = null; // Clear the last element
            
            decrementCount();
            
            return frontValue;
        }
    }
}
