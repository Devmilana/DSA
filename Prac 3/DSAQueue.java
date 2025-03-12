/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *
 * DATE CREATED: 16/08/23                                                                *
 *
 * LAST EDITED: 23/08/23                                                                 *
 *
 * DESCRIPTION: Class file for creating and manipulating an array structured as          *
 *              a queue									 *
 * **************************************************************************************/
public class DSAQueue 
{
    private Object[] queue;
    private int count;
    public static final int DEFAULT_CAPACITY = 100;

    // Default constructor
    public DSAQueue() 
    {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    // Alternate constructor
    public DSAQueue(int maxCapacity) 
    {
        queue = new Object[maxCapacity];
        count = 0;
    }

    // Accessors
    public Object[] getQueue()
    {
        return queue;
    }

    public int getCount() 
    {
        return count;
    }

    public boolean isEmpty() 
    {
        return count == 0;
    }

    public boolean isFull() 
    {
        return count == queue.length;
    }

    public Object peek()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The queue is empty");
        }
        else
        {
            return queue[0];
        }
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
            queue[count] = value;
            count++;
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
            Object frontValue = queue[0];
            
            for (int i = 1; i < count; i++) 
            {
                queue[i - 1] = queue[i];
            }
            
            count--;
            
            return frontValue;
        }
    }
    
    public void incrementCount()
    {
    	count++;
    }
    
    public void decrementCount()
    {
    	count--;
    }
}
