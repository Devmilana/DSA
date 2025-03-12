/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO 								 *	
 *
 * DATE CREATED: 16/08/23      								 *
 *
 * LAST EDITED: 23/08/23      	 							 *
 *
 * DESCRIPTION: Class file for creating and manipulating an array structured as  	 *
 *              a stack									 *
 * **************************************************************************************/
public class DSAStack 
{
    private Object[] stack;
    private int count;
    public static final int DEFAULT_CAPACITY = 100;

    // Default constructor
    public DSAStack() 
    {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    // ALternate constructor
    public DSAStack(int maxCapacity) 
    {
        stack = new Object[maxCapacity];
        count = 0;
    }

    // Accessors
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
        return count == stack.length;
    }

    public Object top()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The stack is empty");
        }
        else
        {
            return stack[count - 1];
        }
    }

    // Mutators
    public void push(Object value) 
    {
        if (isFull()) 
        {
            throw new IllegalStateException("The stack is full");
        } 
        else 
        {
            stack[count] = value;
            count++;
        }
    }

    public Object pop() 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The stack is empty");
        } 
        else 
        {
            Object topVal = top();
            count--;
            return topVal;
        }
    }
}
