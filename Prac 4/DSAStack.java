/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO 								                             *	
 *
 * DATE CREATED: 26/08/23      								                             *
 *
 * LAST EDITED: 01/09/23      	 							                             *
 *
 * DESCRIPTION: Class file for creating and manipulating a linked list structured as  	 *
 *              a stack	
 *
 * This class is a repurposed version of my previous DSAStack class submitted            *
 * for DSA practical 3                                                                   *
 * **************************************************************************************/
public class DSAStack 
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
}
