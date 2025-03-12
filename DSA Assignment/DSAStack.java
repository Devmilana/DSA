/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for creating and manipulating a stack structure               *
 *              Repurposed from my DSAStack file submitted to practicals                 *
 *****************************************************************************************/
import java.util.*;

public class DSAStack implements Iterable
{
    private DSALinkedList stack;

    // Default constructor for creating a linked list stack
    public DSAStack() 
    {
        stack = new DSALinkedList();
    }

    // Check if stack is empty
    public boolean isEmpty() 
    {
        return stack.isEmpty();
    }

    // Check top value
    public Object top() 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The stack is empty");
        } 
        else 
        {
            return stack.peekFirst(); // Returns first node value (at head)
        }
    }

    // Insert object 
    public void push(Object value) 
    {
        stack.insertFirst(value); // Inserts value at head of list
    }

    // Remove object
    public Object pop() 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The stack is empty");
        } 
        else 
        {
            return stack.removeFirst(); // Removes and returns first node value (at head)
        }
    }

    // Get the stack (from DSALinkedList)
    public DSALinkedList getStack()
    {
        return stack;
    }
    
    // Iterates through stack and returns number of elements stack
    public int getCount()
    {
        int count = 0;

        for(Object o : this)
        {
            count++;
        }

        return count;
    }
    
    // Returns iterator object for stack
    public Iterator iterator()
    {
        return stack.iterator();
    }
}
