/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *                                                                                       *
 * LAST EDITED: 21/10/23                                                                 *
 *                                                                                       *
 * DESCRIPTION: Class file for creating and manipulating a Heap data structure           *
 *              Repurposed from my DSAHeap file submitted to practicals                  *
 *****************************************************************************************/
import java.util.*;

public class DSAHeap
{
    /** ================== DSAHeapEntry ================== **/
    private class DSAHeapEntry
    {
        private Shop shop; // Each entry contains a shop object

        // Constructor - sets a shop object into heap entry
        public DSAHeapEntry(Shop inShop)
        {
            shop = inShop;
        }
        
        // Returns priority value of entry (shop rating)
        public int getPriority()
        {
            return shop.getShopRating();
        }

        // Returns shop object in entry
        public Shop getShop()
        {
            return shop;
        }
    }

    /** ================== DSAHeap ================== **/
    private DSAHeapEntry[] heap; // Instance variable of DSAHeap holding array of heap as heapEntry
    private int count; // Tracks number of heap elements

    // Constructor - Initialises heap with given maximum size
    public DSAHeap(int maxSize)
    {
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }
    
    // Get number of entries in heap
    public int getCount()
    {
        return count;
    }

    // Add shop to heap
    public void addShop(Shop shop)
    {
        if (count >= heap.length) // Checks if heap is full
        {
            throw new IllegalStateException("Heap is full. Cannot add more elements.");
        }

        heap[count] = new DSAHeapEntry(shop);
        trickleUp(count);
        count++;
    }

    // Remove shop at top of heap
    public Shop removeShop()
    {
        if (count <= 0) // Check if heap is empty
        {
            throw new NoSuchElementException("Heap is empty. Cannot remove elements.");
        }

        Shop topShop = heap[0].getShop();
        count--;

        heap[0] = heap[count];
        heap[count] = null;
        trickleDown(0);

        return topShop;
    }

    // Return heap as array
    public DSAHeapEntry[] getHeapAsArray() 
    {
        DSAHeapEntry[] arrayCopy = new DSAHeapEntry[count];
        System.arraycopy(heap, 0, arrayCopy, 0, count);
        return arrayCopy;
    }

    // Heapify the array
    public void heapify()
    {
        for(int i = (count / 2) - 1; i >= 0; i--)
        {
            trickleDown(i);
        }
    }

    // Perform heapsort on heap array
    public DSAHeapEntry[] heapSort(DSAHeapEntry[] array) 
    {
        DSAHeapEntry[] sortedHeap = new DSAHeapEntry[array.length];
        
        // Replace the current heap with the given array and update count
        heap = array;
        count = array.length;

        // Heapify the given heap array
        heapify();

        for(int i = count - 1; i >= 0; i--)
        {
            // Move the highest-rated shop to the sortedHeap
            sortedHeap[i] = heap[0];

            // Swap the top-rated shop with the last shop in the heap
            DSAHeapEntry temp = heap[0];
            heap[0] = heap[count - 1];
            heap[count - 1] = temp;

            // Reduce the size of the unsorted heap
            count--;

            // Restore the heap property for the unsorted portion
            trickleDown(0);
        }

        // Restore the heap to its original state and count
        heap = array;
        count = array.length;

        return sortedHeap;
    }

    // TrickleUp function for moving entry upwards through heap
    private void trickleUp(int currentIdx)
    {
        int parentIdx = getParent(currentIdx);
        DSAHeapEntry temp;

        // Check if current index has a higher priority than its parent
        while(currentIdx > 0 && heap[currentIdx].getPriority() > heap[parentIdx].getPriority())
        {
            // Swap current index with parent
            temp = heap[parentIdx];
            heap[parentIdx] = heap[currentIdx];
            heap[currentIdx] = temp;

            // Recurse
            currentIdx = parentIdx;
            parentIdx = getParent(currentIdx);
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
    
    // Function to heap sort and return sorted array of shops
    public Shop[] heapSortAndGetShops()
    {
        DSAHeapEntry[] sortedHeap = heapSort(heap); // Passes instance variable of heap (array of DSAHeapEntry)
        Shop[] sortedShops = new Shop[sortedHeap.length];
    
        // reverse the order to get descending sorted shops
        for (int i = 0; i < sortedHeap.length; i++) {
            sortedShops[i] = sortedHeap[sortedHeap.length - 1 - i].getShop();
        }
    
        return sortedShops;
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
}
