/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *
 * LAST EDITED: 21/09/23                                                                 *
 *
 * DESCRIPTION: Class file for creating and manipulating a linked list structured as     *
 *              a queue                                                                  *
 *                                                                                       *
 * This class is a repurposed version of my previous DSAQueue class submitted            *
 * for DSA practical 4                                                                   *
 * **************************************************************************************/
import java.util.*;

public class DSAQueue implements Iterable 
{
    private DSALinkedList queue;

    // Default constructor fpr creating new linked list queue
    public DSAQueue() 
    {
        queue = new DSALinkedList();
    }

    // Check if queue is empty
    public boolean isEmpty() 
    {
        return queue.isEmpty();
    }

    // Check first object in queue
    public Object peek() 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The queue is empty");
        } 
        else 
        {
            return queue.peekFirst(); // Returns first node value (at head)
        }
    }

    // Check last object in queue
    public Object peekLast()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The queue is empty");
        }
        else
        {
            return queue.peekLast(); // Returns last node value (at tail)
        }
    }

    // Add object to queue
    public void enqueue(Object value)
    {
        queue.insertLast(value); // Inserts value at tail of list
    }

    // Remove object from start of queue
    public Object dequeue() 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The queue is empty");
        } 
        else 
        {
            return queue.removeFirst(); // Removes and returns first node value (at head)
        }
    }

    // Remove last object in queue
    public Object removeLast()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The queue is empty");
        }
        else
        {
            return queue.removeLast(); // Removes and returns last node value (at tail)
        }
    }

    // Get the queue (from DSALinkedList)
    public DSALinkedList getQueue()
    {
        return queue;
    }
    
    // Iterates through queue and returns number of elements in queue
    public int getCount()
    {
        int count = 0;

        for(Object o : this)
        {
            count++;
        }

        return count;
    }
    
    // Returns iterator object for queue
    public Iterator iterator()
    {
        return queue.iterator();
    }
}
