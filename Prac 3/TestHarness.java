/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *
 * DATE CREATED: 20/08/23                                                                *
 *
 * LAST EDITED: 23/08/23                                                                 *
 *
 * DESCRIPTION: Test harness for evaluating functionality of stacks, queues		 *
 *              and equation solver files                                                *
 *****************************************************************************************/
import java.util.*;

public class TestHarness 
{
    public static void main(String[] args)
    {
        
        welcomeScreen();
        stackTesting();
        queueTesting();
        shufflingQueueTesting();
        circularQueueTesting();
        equationSolverTesting();
    }

    // Starts testing DSAStack file and calls all test methods
    private static void stackTesting()
    {
        System.out.println("\n*****************************************************************************************" + 
                           "\n************************ TESTING OPERATIONS OF DSAStack *********************************" +
                           "\n*****************************************************************************************");
        
        System.out.println("\nCurrent initialised stack contents: \n");
        DSAStack stack = initializeStack();
        printCurrentStack(stack);

        testGetCount(stack);
        testIsEmpty(stack);
        testIsFull(stack);
        testTop(stack);
        testPush(stack);
        testPop(stack);
        testIsEmptyAfterPop(stack);
    }

    // Starts testing DSAQueue file and calls all test methods
    private static void queueTesting()
    {
        System.out.println();
        System.out.println("\n\n*****************************************************************************************" + 
                           "\n************************ TESTING OPERATIONS OF DSAQueue *********************************" +
                           "\n*****************************************************************************************");
        
        System.out.println("\nCurrent initialised queue contents: \n");
        DSAQueue queue = initializeQueue();
        printCurrentQueue(queue);
        
        testGetCount(queue);
        testIsEmpty(queue);
        testIsFull(queue);
        testPeek(queue);
        testDequeue(queue);
        testIsEmptyAfterDequeue(queue);
        testEnqueue(queue);
    }

    // Starts testing DSAShufflingQueue file and calls all test methods
    private static void shufflingQueueTesting()
    {
        System.out.println();
        System.out.println("\n\n*****************************************************************************************" + 
                           "\n********************* TESTING OPERATIONS OF DSAShuffling Queue **************************" +
                           "\n*****************************************************************************************");

        System.out.println("\nCurrent initialised shuffling queue contents: \n");
        DSAShufflingQueue shufflingQueue = initializeShufflingQueue();
        printCurrentShufflingQueue(shufflingQueue);

        testShufflingGetCount(shufflingQueue);
        testShufflingIsEmpty(shufflingQueue);
        testShufflingIsFull(shufflingQueue);
        testShufflingDequeue(shufflingQueue);
        testShufflingEnqueue(shufflingQueue);
    }

    // Starts testing DSACircularQueue file and calls all test methods
    private static void circularQueueTesting()
    {
        System.out.println();
        System.out.println("\n\n*****************************************************************************************" +
                           "\n********************* TESTING OPERATIONS OF DSACircular Queue ***************************" +
                           "\n*****************************************************************************************");

        System.out.println("\nCurrent initialised circular queue contents: \n");
        DSACircularQueue circularQueue = initializeCircularQueue();
        printCurrentCircularQueue(circularQueue);

        testCircularGetCount(circularQueue);
        testCircularIsEmpty(circularQueue);
        testCircularIsFull(circularQueue);
        testCircularPeek(circularQueue);
        testCircularDequeue(circularQueue);
        testCircularIsEmptyAfterDequeue(circularQueue);
        testCircularEnqueue(circularQueue);
    }

    // Starts testing Equation Solver file by providing two equations to solve
    private static void equationSolverTesting()
    {
        System.out.println();
        System.out.println("\n\n*****************************************************************************************" +
                           "\n***************************** TESTING EQUATION SOLVER ***********************************" +
                           "\n*****************************************************************************************");

        String equation1 = "32 + ( 4 * 2 ) / ( 1 - 5 )";
        double expectedResult1 = 30.0;
        testEquationSolver(equation1, expectedResult1);

        System.out.println();
        String equation2 = "5 * ( 2 + 3 )";
        double expectedResult2 = 25.0;
        testEquationSolver(equation2, expectedResult2);
        System.out.println();
    }

    // Initialises stack of 10 with 5 elements
    private static DSAStack initializeStack()
    {
        DSAStack stack = new DSAStack(10);
        stack.push(42);
        stack.push(55);
        stack.push(12);
        stack.push(37);
        stack.push(88);
        return stack;
    }

    // Initialises queue of 10 with 5 elements
    private static DSAQueue initializeQueue()
    {
        DSAQueue queue = new DSAQueue(10);
        queue.enqueue(42);
        queue.enqueue(55);
        queue.enqueue(12);
        queue.enqueue(37);
        queue.enqueue(88);
        return queue;
    }

    // Initialises shuffling queue of 10 with 5 elements
    private static DSAShufflingQueue initializeShufflingQueue()
    {
        DSAShufflingQueue shufflingQueue = new DSAShufflingQueue(10);
        shufflingQueue.enqueue(42);
        shufflingQueue.enqueue(55);
        shufflingQueue.enqueue(12);
        shufflingQueue.enqueue(37);
        shufflingQueue.enqueue(88);
        return shufflingQueue;
    }

    // Initialises circular queue of 10 with 5 elements
    private static DSACircularQueue initializeCircularQueue()
    {
	    DSACircularQueue circularQueue = new DSACircularQueue(10);
        circularQueue.enqueue(42);
        circularQueue.enqueue(55);
        circularQueue.enqueue(12);
        circularQueue.enqueue(37);
        circularQueue.enqueue(88);
        return circularQueue;
    }

    // DSAStack testing methods
    private static void testGetCount(DSAStack stack) 
    {
        int expectedCount = 5;
        int actualCount = stack.getCount();
        printTestResults("getCount()", expectedCount, actualCount);
    }

    private static void testIsEmpty(DSAStack stack)
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = stack.isEmpty();
        printTestResults("isEmpty()", expectedEmpty, actualEmpty);
    }

    private static void testIsFull(DSAStack stack)
    {
        boolean expectedFull = false;
        boolean actualFull = stack.isFull();
        printTestResults("isFull()", expectedFull, actualFull);
    }

    private static void testTop(DSAStack stack)
    {
        Object expectedTop = 88;
        Object actualTop = stack.top();
        printTestResults("top()", expectedTop, actualTop);
    }

    private static void testPush(DSAStack stack)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING push() METHOD\n");

        System.out.println("Current Stack (before pushing):");
        printCurrentStack(stack);

        stack.push(99);
        Object actualTop = stack.top();
        Object expectedTop = 99;

        System.out.println("\nExpected value at top after pushing 99 = "+ expectedTop);
        System.out.println("Actual value at top after pushing 99 = " + actualTop);

        System.out.println("\nCurrent Stack (after pushing):");
        printCurrentStack(stack);

        if (expectedTop.equals(actualTop)) 
        {
            System.out.println("\nTest for push() was successful!");
        }
        else
        {
            System.out.println("\nTest for push() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void testPop(DSAStack stack)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING pop() METHOD\n");

        System.out.println("Current Stack (before popping):");
        printCurrentStack(stack);

        Object poppedValue = stack.pop();
        Object expectedPop = 99;

        System.out.println("\nExpected pop value = "+ expectedPop);
        System.out.println("Actual popped value = " + poppedValue);

        System.out.println("\nCurrent Stack (after popping):");
        printCurrentStack(stack);

        if (expectedPop.equals(poppedValue))
        {
            System.out.println("\nTest for pop() was successful!");
        }
        else
        {
            System.out.println("\nTest for pop() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void testIsEmptyAfterPop(DSAStack stack)
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = stack.isEmpty();
        printTestResults("isEmpty() after pop()", expectedEmpty, actualEmpty);
    }


    // DSAQueue testing methods
    private static void testGetCount(DSAQueue queue)
    {
        int expectedCount = 5;
        int actualCount = queue.getCount();
        printTestResults("getCount()", expectedCount, actualCount);
    }

    private static void testIsEmpty(DSAQueue queue) 
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = queue.isEmpty();
        printTestResults("isEmpty()", expectedEmpty, actualEmpty);
    }

    private static void testIsFull(DSAQueue queue)
    {
        boolean expectedFull = false;
        boolean actualFull = queue.isFull();
        printTestResults("isFull()", expectedFull, actualFull);
    }

    private static void testPeek(DSAQueue queue) 
    {
        Object expectedFront = 42;
        Object actualFront = queue.peek();
        printTestResults("peek()", expectedFront, actualFront);
    }

    private static void testDequeue(DSAQueue queue)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING dequeue() METHOD\n");

        System.out.println("Current queue before dequeuing:");
        printCurrentQueue(queue);
        
        Object expectedFront = 42;
        Object dequeuedValue = queue.dequeue();

        System.out.println("\nExpected dequeue value = " + expectedFront);
        System.out.println("Actual dequeue value = " + dequeuedValue);

        System.out.println("\nCurrent queue after dequeuing:");
        printCurrentQueue(queue);

        if (expectedFront == dequeuedValue)
        {
            System.out.println("\nTest for dequeue() was successful!");
        }
        else
        {
            System.out.println("\nTest for dequeue() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void testIsEmptyAfterDequeue(DSAQueue queue)
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = queue.isEmpty();
        printTestResults("isEmpty() after dequeue()", expectedEmpty, actualEmpty);
    }

    private static void testEnqueue(DSAQueue queue)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING enqueue() METHOD\n");

        System.out.println("Current queue before enqueuing:");
        printCurrentQueue(queue);

        int countBefore = queue.getCount();
        int expectedCount = 5;
        queue.enqueue(25);
        int actualCount = queue.getCount();

        System.out.println("\nQueue count before enqueuing 25 = " + countBefore);
        System.out.println("Expected Queue count after enqueuing 25 = " + expectedCount);
        System.out.println("Actual Queue count after enqueuing 25 = " + actualCount);

        System.out.println("\nCurrent queue after enqueuing:");
        printCurrentQueue(queue);

        if (expectedCount == actualCount)
        {
            System.out.println("\nTest for enqueue() was successful!");
        }
        else
        {
            System.out.println("\nTest for enqueue() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }


    // DSAShufflingQueue testing methods
    private static void testShufflingGetCount(DSAShufflingQueue shufflingQueue)
    {
        int expectedCount = 5;
        int actualCount = shufflingQueue.getCount();
        printTestResults("getCount() for DSAShufflingQueue", expectedCount, actualCount);
    }

    private static void testShufflingIsEmpty(DSAShufflingQueue shufflingQueue)
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = shufflingQueue.isEmpty();
        printTestResults("isEmpty() for DSAShufflingQueue", expectedEmpty, actualEmpty);
    }

    private static void testShufflingIsFull(DSAShufflingQueue shufflingQueue)
    {
        boolean expectedFull = false;
        boolean actualFull = shufflingQueue.isFull();
        printTestResults("isFull() for DSAShufflingQueue", expectedFull, actualFull);
    }

    private static void testShufflingDequeue(DSAShufflingQueue shufflingQueue)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING dequeue() FOR DSAShufflingQueue METHOD\n");

        System.out.println("Current shuffling queue before dequeuing:");
        printCurrentShufflingQueue(shufflingQueue);

        Object expectedFront = 42;
        Object dequeuedValue = shufflingQueue.dequeue();

        System.out.println("\nExpected dequeue value = " + expectedFront);
        System.out.println("Actual dequeue value = " + dequeuedValue);

        System.out.println("\nCurrent shuffling queue after dequeuing:");
        printCurrentShufflingQueue(shufflingQueue);

        if (expectedFront == dequeuedValue)
        {
            System.out.println("\nTest for dequeue() was successful!");
        }
        else
        {
            System.out.println("\nTest for dequeue() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void testShufflingEnqueue(DSAShufflingQueue shufflingQueue)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING enqueue() FOR DSAShufflingQueue METHOD\n");

        System.out.println("Current shuffling queue before enqueuing:");
        printCurrentShufflingQueue(shufflingQueue);

        int countBefore = shufflingQueue.getCount();
        int expectedCount = 5;
        shufflingQueue.enqueue(25);
        int actualCount = shufflingQueue.getCount();

        System.out.println("\nQueue count before enqueuing 25 = " + countBefore);
        System.out.println("Expected Queue count after enqueuing 25 = "+ expectedCount);
        System.out.println("Actual Queue count after enqueuing 25 = " + actualCount);

        System.out.println("\nCurrent shuffling queue after enqueuing:");
        printCurrentShufflingQueue(shufflingQueue);

        if (expectedCount == actualCount)
        {
            System.out.println("\nTest for enqueue() was successful!");
        }
        else
        {
            System.out.println("\nTest for enqueue() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    
    // DSACircularQueue testing methods
    private static void testCircularGetCount(DSACircularQueue circularQueue)
    {
        int expectedCount = 5;
        int actualCount = circularQueue.getCount();
        printTestResults("getCount() for DSACircularQueue", expectedCount, actualCount);
    }

    private static void testCircularIsEmpty(DSACircularQueue circularQueue)
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = circularQueue.isEmpty();
        printTestResults("isEmpty() for DSACircularQueue", expectedEmpty, actualEmpty);
    }

    private static void testCircularIsFull(DSACircularQueue circularQueue)
    {
        boolean expectedFull = false;
        boolean actualFull = circularQueue.isFull();
        printTestResults("isFull() for DSACircularQueue", expectedFull, actualFull);
    }

    private static void testCircularPeek(DSACircularQueue circularQueue)
    {
        Object expectedFront = 42;
        Object actualFront = circularQueue.peek();
        printTestResults("peek() for DSACircularQueue", expectedFront, actualFront);
    }

    private static void testCircularDequeue(DSACircularQueue circularQueue)
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING dequeue() FOR DSACircularQueue METHOD\n");

        System.out.println("Current circular queue before dequeuing:");
        printCurrentCircularQueue(circularQueue);

        Object expectedFront = 42;
        Object dequeuedValue = circularQueue.dequeue();

        System.out.println("\nExpected dequeue value = " + expectedFront);
        System.out.println("Actual dequeue value = " + dequeuedValue);

        System.out.println("\nCurrent circular queue after dequeuing:");
        printCurrentCircularQueue(circularQueue);

        if (expectedFront == dequeuedValue)
        {
            System.out.println("\nTest for dequeue() was successful!");
        }
        else
        {
            System.out.println("\nTest for dequeue() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void testCircularIsEmptyAfterDequeue(DSACircularQueue circularQueue)
    {
        boolean expectedEmpty = false;
        boolean actualEmpty = circularQueue.isEmpty();
        printTestResults("isEmpty() after dequeue() for DSACircularQueue", expectedEmpty, actualEmpty);
    }

    private static void testCircularEnqueue(DSACircularQueue circularQueue) 
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING enqueue() FOR DSACircularQueue METHOD\n");
        
        System.out.println("Current circular queue before enqueuing:");
        printCurrentCircularQueue(circularQueue);

        int countBefore = circularQueue.getCount();
        circularQueue.enqueue(25);
        int actualCount = circularQueue.getCount();
        int expectedCount = 5;

        System.out.println("\nQueue count before enqueuing 25 = " + countBefore);
        System.out.println("Expected Queue count after enqueuing 25 = "+ expectedCount);
        System.out.println("Actual Queue count after enqueuing 25 = " + actualCount);

        System.out.println("\nCurrent circular queue after enqueuing:");
        printCurrentCircularQueue(circularQueue);

        if (expectedCount == actualCount) 
        {
            System.out.println("\nTest for enqueue() was successful!");
        }
        else
        {
            System.out.println("\nTest for enqueue() failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }


    // Equation solver testing
    private static void testEquationSolver(String equation, double expectedResult)
    {
        EquationSolver solver = new EquationSolver();

        System.out.println("\nGiven Equation: " + equation);
        System.out.println("\nExpected Result: " + expectedResult);

        double actualResult = solver.solve(equation);

        System.out.println("Actual Result: " + actualResult);

        if (expectedResult == actualResult)
        {
            System.out.println("Test for Equation Solver was successful!");
        }
        else
        {
            System.out.println("Test for Equation Solver failed!");
        }
    }


    // Welcome display statements
    private static void welcomeScreen() 
    {
        System.out.println("\nWelcome to the DSA Stacks, Queues, and Equation Solver Test Harness\n" +

                "\nFor testing DSAStack, a stack of capacity 10 will be initialised with predetermined variables\n" +
                "For testing DSAQueue, a queue of capacity 10 will be initialised with predetermined variables\n" +
                "Operations will be tested on the respective stacks and queues and their expected and actual outcomes compared\n" +

                "\nThis test will also feed two equations to the Equation Solver Program and compare expected and actual outcomes\n");
    }

    
    // Current stack print
    private static void printCurrentStack(DSAStack stack) 
    {
        DSAStack tempStack = new DSAStack(stack.getCount());

        while (!stack.isEmpty())
        {
            Object value = stack.pop();
            tempStack.push(value);
            System.out.println(value);
        }

        while (!tempStack.isEmpty()) 
        {
            stack.push(tempStack.pop());
        }
    }

    
    // Current queue print
    private static void printCurrentQueue(DSAQueue queue)
    {
        DSAQueue tempQueue = new DSAQueue(queue.getCount());

        while (!queue.isEmpty()) 
        {
            Object value = queue.dequeue();
            tempQueue.enqueue(value);
            System.out.println(value);
        }

        while (!tempQueue.isEmpty())
        {
            queue.enqueue(tempQueue.dequeue());
        }
    }

    // Current shuffling queue print
    private static void printCurrentShufflingQueue(DSAShufflingQueue shufflingQueue)
    {
        DSAShufflingQueue tempQueue = new DSAShufflingQueue(shufflingQueue.getCount());

        while (!shufflingQueue.isEmpty())
        {
            Object value = shufflingQueue.dequeue();
            tempQueue.enqueue(value);
            System.out.println(value);
        }

        while (!tempQueue.isEmpty())
        {
            shufflingQueue.enqueue(tempQueue.dequeue());
        }
    }

    // Current circular queue print
    private static void printCurrentCircularQueue(DSACircularQueue circularQueue)
    {
        DSACircularQueue tempQueue = new DSACircularQueue(circularQueue.getCount());

        while (!circularQueue.isEmpty()) 
        {
            Object value = circularQueue.dequeue();
            tempQueue.enqueue(value);
            System.out.println(value);
        }
        
        while (!tempQueue.isEmpty())
        {
            circularQueue.enqueue(tempQueue.dequeue());
        }
    }
    
    // Print test results
    private static void printTestResults(String methodName, Object expected, Object actual) 
    {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("TESTING " + methodName + " METHOD\n");
        System.out.println("Expected " + methodName + " = " + expected);
        System.out.println("Actual " + methodName + " = " + actual);
        if (expected.equals(actual))
        {
            System.out.println("\nTest for " + methodName + " was successful!");
        } 
        else
        {
            System.out.println("\nTest for " + methodName + " failed!");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
