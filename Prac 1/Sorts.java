/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
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

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts calss
