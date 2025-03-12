	/** 
** Software Technology 152
** Class to hold various static sort methods.
*/

import java.util.*;

class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
    	for(int i = 0; i < A.length - 1; i++) 
        {
            for(int j = 0; j < A.length - i - 1; j++) 
            {
                if(A[j] > A[j+1]) 
                {
                    int temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }
        }
    }

    // selection sort
    public static void selectionSort(int[] A)
    {
    	for(int i = 0; i < A.length - 1; i++) 
        {
            int min = i;
            
            for(int j = i + 1; j < A.length; j++) 
            {
                if(A[min] > A[j]) 
                {
                    min = j;
                }
            }

            int temp = A[i];
            A[i] = A[min];
            A[min] = temp;
        }
    }

    // insertion sort
    public static void insertionSort(int[] A)
    {
    	for(int i = 1; i < A.length; i++) 
        {
            int temp = A[i];
            int j = i - 1;

            while(j >= 0 && A[j] > temp) 
            {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = temp;
        }
    }
    
    
    /** MERGESORT ALGORITHM **/

    // mergeSort - wrapper for mergeSorting
    public static void mergeSort(int[] A)
    {
    	mergeSortRecurse(A, 0, A.length - 1);
    }
    
    // mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    	int midIdx;

        if(leftIdx < rightIdx)
        {
            midIdx = (leftIdx + rightIdx) / 2;

            mergeSortRecurse(A, leftIdx, midIdx); // Recurse Left
            mergeSortRecurse(A, midIdx + 1, rightIdx); // Recurse Right
            merge(A, leftIdx, midIdx, rightIdx);
        }
    }
    
    // mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    	int[] tempArr = new int[rightIdx - leftIdx + 1];
        int i = leftIdx;
        int j = midIdx + 1;
        int k = 0;

        while( (i <= midIdx) && (j <= rightIdx))
        {
            if(A[i] <= A[j])
            {
                tempArr[k] = A[i];
                i++;
            }
            else
            {
                tempArr[k] = A[j];
                j++;
            }
            k++;
        }

        for(int ii = i; i <= midIdx; i++)
        {
            // Flush remainder from left sub-array
            tempArr[k] = A[i];
            k++;
        }

        for(int jj = j; j <= rightIdx; j++)
        {
            // Flush remainder from right sub-array
            tempArr[k] = A[j];
            k++;
        }

        for(int kk = leftIdx; kk <= rightIdx; kk++)
        {
            // Place sorted data from tempArr into A
            A[kk] = tempArr[kk - leftIdx];
        }

    }
    
    
    /** QUICKSORT USING LEFT-MOST PIVOT SELECTION **/
    
    // quickSort() - wrapper for left-most pivot selection
    public static void quickSort(int[] A)
    {
    	quickSortRecurse(A, 0, A.length - 1);
    }
    
    // quickSort() - using left-most pivot selection
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    	int pivotIdx;
        int newPivotIdx;

        // Check that the array length is > 1
        if(rightIdx > leftIdx)
        {
            pivotIdx = leftIdx; // Left-most pivot selection
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx - 1); // Recurse Left
            quickSortRecurse(A, newPivotIdx + 1, rightIdx); // Recurse Right
        }
    }
    
    
    /** QUICKSORT USING MEDIAN OF THREE PIVOT SELECTION **/
    
    // quickSort() - wrapper for median of three pivot selection
    public static void quickSortMedian3(int[] A)
    {
    	quickSortMedian3Recurse(A, 0, A.length - 1);
    }
    
    // quickSort() - using median of three selection
    private static void quickSortMedian3Recurse(int[] A, int leftIdx, int rightIdx)
    {
    	int pivotIdx;
        int newPivotIdx;

        // Check that the array length is > 1
        if(rightIdx > leftIdx)
        {
            pivotIdx = medianOf3(A, leftIdx, rightIdx, (leftIdx + rightIdx) / 2); // Median of three pivot selection
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx - 1); // Recurse Left
            quickSortRecurse(A, newPivotIdx + 1, rightIdx); // Recurse Right
        }
    }
    
    // medianOf3() - finds the median of 3 values from a given index
    private static int medianOf3(int[] A, int a, int b, int c)
    { 
        int median;

        if(A[a] > A[b] && A[a] > A[c])
        {
            median = a;
        }
        else if(A[b] > A[a] && A[b] > A[c])
        {
            median = b;
        }
        else
        {
            median = c;
        }

        return median;
    }
    
    
    /** QUICKSORT USING RANDOM PIVOT SELECTION **/
    
    // quickSort() - wrapper for random selection
    public static void quickSortRandom(int[] A)
    {
    	quickSortRandomRecurse(A, 0, A.length - 1);
    }
    
    // quickSort() - using random pivot selection
    private static void quickSortRandomRecurse(int[] A, int leftIdx, int rightIdx)
    {
    	int pivotIdx;
    	int newPivotIdx;
    	Random rand = new Random();

    	// Check that the array length is > 1
    	if (rightIdx > leftIdx)
    	{
            // Random pivot selection
            pivotIdx = leftIdx + rand.nextInt(rightIdx - leftIdx + 1); 
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRandomRecurse(A, leftIdx, newPivotIdx - 1); // Recurse Left
            quickSortRandomRecurse(A, newPivotIdx + 1, rightIdx); // Recurse Right
    	}
    }
    
    
    /** PARTIOTIONING ALGORITHM FOR QUICKSORT **/
    
    // quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
    	int currIdx;
        int pivotVal;
        int temp;
        int newPivotIdx;
        
        // Swap the pivotVal and the right-most element
        pivotVal = A[pivotIdx];
        A[pivotIdx] = A[rightIdx];
        A[rightIdx] = pivotVal;

        // Puts all values smaller than the pivot to the left side
        currIdx = leftIdx;

        for(int i = leftIdx; i <= rightIdx - 1; i++)
        {
            if(A[i] < pivotVal)
            {
                // Swap i'th and currIdx element
                temp = A[i];
                A[i] = A[currIdx];
                A[currIdx] = temp;

                currIdx++;
            }
        }

        newPivotIdx = currIdx;
        A[rightIdx] =  A[newPivotIdx];
        A[newPivotIdx] = pivotVal;

        return newPivotIdx;
    }
}
