/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *                                                                                       *
 * LAST EDITED: 11/11/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for creating and manipulating a linked list                   *
 *		        Repurposed from my DSALinkedList file submitted to prac 4                *                                                                  
 * **************************************************************************************/
import java.util.*;
import java.io.*;

public class DSALinkedList implements Iterable
{
    /** ================== DSAListNode ================== **/
    private class DSAListNode
    {
        private Object m_value;
        private DSAListNode m_next;
        private DSAListNode m_prev;

        // Node constructor
        public DSAListNode(Object inValue) 
        {
            m_value = inValue;
            m_next = null;
            m_prev = null;
        }

        // Get value of node
        public Object getValue()
        {
            return m_value;
        }

        // Set node value
        public void setValue(Object inValue)
        {
            m_value = inValue;
        }

        // Get next node
        public DSAListNode getNext()
        {
            return m_next;
        }

        // Set next node
        public void setNext(DSAListNode newNext)
        {
            m_next = newNext;
        }

        // Get previous node
        public DSAListNode getPrev()
        {
            return m_prev;
        }

        // Set previous node
        public void setPrev(DSAListNode newPrev)
        {
            m_prev = newPrev;
        }
    }
    
    /** ================== DSALinkedListIterator ================== **/
    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;

        // Constructor
        public DSALinkedListIterator()
        {
            iterNext = head;
        }

        public boolean hasNext()
        {
            return iterNext != null;
        }

        public Object next()
        {
            Object value;

            if( iterNext == null )
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue(); //Get the value in node
                    
                iterNext = iterNext.getNext();
            }

            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException( "Not supported" );
        }
    }
    
    /** ================== DSALinkedList ================== **/
    private DSAListNode head; // Reference to the first node
    private DSAListNode tail; // Reference to the last node
    private int count;

    public DSALinkedList() 
    {
        head = null;
        tail = null;
    }

    // Check if the list is empty
    public boolean isEmpty() 
    {
        return head == null;
    }

    // Peek at the value of the first node without removing it
    public Object peekFirst() throws IllegalStateException 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The list is empty");
        } 
        else 
        {
            return head.getValue();
        }
    }

    // Peek at the value of the last node without removing it
    public Object peekLast() throws IllegalStateException
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The list is empty");
        } 
        else 
        {
            return tail.getValue();
        }
    }
    
    // Insert a new node with the given value at the beginning of the list
    public void insertFirst(Object newValue) 
    {
        DSAListNode newNd = new DSAListNode(newValue);

        if (isEmpty()) 
        {
            head = newNd;
            tail = newNd;
        } 
        else 
        {
            newNd.setNext(head); // Set 'next' reference of newNd to point to the value pointed by the current head
            head.setPrev(newNd); // Set 'prev' reference of the current head to point to newNd.
            head = newNd; // Updates head reference to point to newNd
        }
    }

    // Insert a new node with the given value at the end of the list
    public void insertLast(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);

        if (isEmpty()) 
        {
            head = newNd;
            tail = newNd;
        } 
        else 
        {
            newNd.setPrev(tail); // Set 'prev' reference of newNd to point to the current tail
            tail.setNext(newNd); // Set 'next' reference of the current tail (last value) to point to newNd.
            tail = newNd; // Updates tail reference to point to newNd
        }
    }

    // Remove and return the value of the first node
    public Object removeFirst() throws IllegalStateException
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The list is empty");
        } 
        else 
        {
            DSAListNode nodeToRemove = head;
            if (head == tail) // For a one-item list
            {
                head = null;
                tail = null;
            } 
            else 
            {

                head = head.getNext(); // Updates head reference to point to the next node in the list 
                head.setPrev(null); // Set prev reference of the new head (the second node) to null
            }
            
            return nodeToRemove.getValue(); // Return value of removed node
        }
    }
    
    // Remove a node from list
    public boolean remove(Object newValue) 
    {
	boolean removed = false;

	if (isEmpty()) 
        {
            throw new IllegalStateException("The list is empty");
        } 
	else
	{
            DSAListNode currNode = head;
            
            while (currNode != null && !currNode.getValue().equals(newValue)) 
            {
                currNode = currNode.getNext();
            }

            if (currNode != null) 
            {
                // Found the node to remove
                if (currNode == head) 
                {
                    removeFirst();
                } 
                else if (currNode == tail) 
                {
                    removeLast();
                } 
                else 
                {
                    // If it's neither head nor tail
                    currNode.getPrev().setNext(currNode.getNext());
                    currNode.getNext().setPrev(currNode.getPrev());
                }
                
                removed = true;
            }
	 }

	 return removed;
    }

    // Remove and return the value of the last node
    public Object removeLast() throws IllegalStateException 
    {
        if (isEmpty()) 
        {
            throw new IllegalStateException("The list is empty");
        } 
        else
        {
            DSAListNode nodeToRemove = tail;
            if (head == tail) // For a one-item list
            {
                head = null;
                tail = null;
            } 
            else 
            {
                tail = tail.getPrev(); // Updates tail reference to point to the one-before-last node in the list
                tail.setNext(null); // Set next reference of the new tail (the last node) to null
            }
            
            return nodeToRemove.getValue(); // Return value of removed node
        }
    }
	
    // Method for displaying list elements
    public void displayList() 
    {
        if (isEmpty()) 
        {
            System.out.println("The list is empty");
        } 
        else 
        {
            DSAListNode currNode = head;
            
            while (currNode != null) // Iterates through list until current node is null
            {
                System.out.print(currNode.getValue() + ", "); // Prints the value of current node 
                currNode = currNode.getNext(); // Gets the next value of the current node and sets it as the new current node
            }
            System.out.println();
        }
    }
    
    // Returns a new instance of DSALinkedListIterator
    public Iterator iterator()
    {
        return new DSALinkedListIterator();
    }
}
