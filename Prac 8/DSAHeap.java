/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *									                                            		 *
 * LAST EDITED: 11/11/23                                                                 *
 *										                                                 *
 * DESCRIPTION: Class file for creating and manipulating a Heap         		         *                                                                
 * **************************************************************************************/
import java.util.*;

public class DSAHeap
{
    /** ================== DSAHeapEntry ================== **/
    private class DSAHeapEntry
    {
        private int priority; // Priority value of entry
        private Object value; // Entry value

        // Constructor
        public DSAHeapEntry(int inPriority, Object inValue)
        {
            priority = inPriority;
            value = inValue;
        }
        
        // Get priority value
        private int getPriority()
        {
            return priority;
        }

        // Set priority value
        private void setPriority(int inPriority)
        {
            priority = inPriority;
        }

        // Get entry value
        private Object getValue()
        {
            return value;
        }

        // Set entry value
        private void setValue(Object inValue)
        {
            value = inValue;
        }

        //  Return priority and value as a string
        public String toString()
        {
            return (priority + "," + value);
        }
    }

    /** ================== DSAHeap ================== **/
    private DSAHeapEntry[] heap;
    private int count;

    public DSAHeap(int maxSize)
    {
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }

    // Add a heap entry
    public void add(int priority, Object value)
    {
        if (count >= heap.length) 
        {
            throw new IllegalStateException("Heap is full. Cannot add more elements.");
        }

        heap[count] = new DSAHeapEntry(priority, value);

        trickleUp(count);
        count++;
    }

    // Remove a heap entry
    public Object remove()
    {
        if (count <= 0) 
        {
            throw new NoSuchElementException("Heap is empty. Cannot remove elements.");
        }

        Object removedVal = heap[0].value;
        count--;

        heap[0] = heap[count];
        heap[count] = null;
        trickleDown(0);

        return removedVal;
    }

    // Get number of entries in heap
    public int getCount()
    {
        return count;
    }

    // Return heap as an object array
    public Object[][] getHeapAsArray()
    {
        Object[][] heapArray = new Object[count][2];
        for (int i = 0; i < count; i++)
        {
            heapArray[i][0] = heap[i].getPriority();
            heapArray[i][1] = heap[i].getValue();
        }
        return heapArray;
    }

    // Heapify the array
    public void heapify()
    {
        for(int i = (count/2) - 1; i >= 0; i--)
        {
            trickleDown(i);
        }
    }

    // Perform heapsort on heap array
    public DSAHeapEntry[] heapSort(Object[][] array)
    {
        if (array == null || array.length == 0) 
        {
            throw new IllegalArgumentException("Array is null or empty.");
        }

        DSAHeapEntry temp;
        count = array.length;
        heap = new DSAHeapEntry[count];

        // Check if array is a 2 element array of correct type
        if(array[0][0] instanceof Integer)
        {
            for(int i = 0; i < count; i++)
            {
                heap[i] = new DSAHeapEntry((int)array[i][0], array[i][1]);
            }
        }
	else
        {
            throw new IllegalArgumentException("Heap sort import must be an array of priority integers and Object values");
        }

        // Call heapify function to convert array to heap format
        heapify();

        // Heap sort array
        for(int i = count - 1; i >= 1; i--)
        {
            // Swap top entry with i'th entry
            temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            // Reduces size of heap by 1
            count = i;

            // Trickle down the top entry
            trickleDown(0);
        }

        count = array.length; // Sets the count back to the size of the array

        return heap;
    }

    // TrickleUp function for moving entry upwards through heap
    private void trickleUp(int currentIdx)
    {
        int parentIdx = getParent(currentIdx);
        DSAHeapEntry temp;

        // Check if current index has a higher priority than its parent
        if((currentIdx > 0) && (heap[currentIdx].getPriority() > heap[parentIdx].getPriority()))
        {
            // Swap current index with parent
            temp = heap[parentIdx];
            heap[parentIdx] = heap[currentIdx];
            heap[currentIdx] = temp;

            // Recurse
            trickleUp(parentIdx);
        }
    }

    // TrickleDown function to move entry down through heap
    private void trickleDown(int currentIdx)
    {
        int leftChildIdx = getLeftChild(currentIdx);
        int rightChildIdx = getRightChild(currentIdx);
        int largeIdx;
        DSAHeapEntry temp;

        if(leftChildIdx < count)
        {
            largeIdx = leftChildIdx;
            if(rightChildIdx < count)
            {
                // Check which of the two children has the highest priority
                if(heap[leftChildIdx].getPriority() < heap[rightChildIdx].getPriority())
                {
                    largeIdx = rightChildIdx;
                }
            }
            // Check if priority of largest child is greater than the priority of current index
            if(heap[largeIdx].getPriority() > heap[currentIdx].getPriority())
            {
                // Swap current index with larger child
                temp = heap[largeIdx];
                heap[largeIdx] = heap[currentIdx];
                heap[currentIdx] = temp;

                // Recurse
                trickleDown(largeIdx);
            }
        }
    }

    // Get index of left child
    private int getLeftChild(int currentIdx)
    {
        return (currentIdx * 2) + 1;
    }

    // Get index of right child
    private int getRightChild(int currentIdx)
    {
        return (currentIdx * 2) + 2;
    }

    // Get index of parent
    private int getParent(int currentIdx)
    {
        return (currentIdx - 1) / 2;
    }
    
    // Export heap as a string array
    public String[] export()
    {
        if (count <= 0) 
        {
            throw new NoSuchElementException("Heap is empty. Cannot export to CSV.");
        }

        String[] exportArray = new String[count];
        for(int i = 0; i < count; i++)
        {
            exportArray[i] = heap[i].getPriority() + "," + heap[i].getValue().toString();
        }
        return exportArray;
    }
}
