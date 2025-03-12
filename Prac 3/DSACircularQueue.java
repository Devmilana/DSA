/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO 								 *
 *
 * DATE CREATED: 16/08/23      								 *
 *
 * LAST EDITED: 23/08/23       								 *
 *
 * DESCRIPTION: Class file for creating and manipulating an array structured as  	 *
 *              a circular queue extending methods from DSAQueue			 *
 * **************************************************************************************/
public class DSACircularQueue extends DSAQueue 
{
    private int start; // Front of the circular queue
    private int end;   // Rear of the circular queue

    // Default constructor
    public DSACircularQueue() 
    {
        super(); 
        start = 0;
        end = 0;
    }

    // Alternate constructor
    public DSACircularQueue(int maxCapacity) 
    {
        super(maxCapacity); 
        start = 0;
        end = 0;
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
            getQueue()[end] = value;
            end++; // Increment end

            if (end == getQueue().length) 
            {
                end = 0; // Reset end to the beginning if it exceeds array bounds
            }
            
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
            Object frontValue = getQueue()[start];
            start++; // Increment start

            if (start == getQueue().length) 
            {
                start = 0; // Reset start to the beginning if it exceeds array bounds
            }

            decrementCount();
            
            return frontValue;
        }
    }

    // Accessor
    public Object peek() 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The queue is empty");
        } 
        else 
        {
            return getQueue()[start];
        }
    }
}
