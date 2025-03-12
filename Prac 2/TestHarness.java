/*************************************************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO *
 * DATE CREATED: 12/08/2023 *
 * LAST EDITED: 19/08/2023 *
 *
 * FUNCTION: This is a test harness designed to run and display the outputs and
 *           average runtimes of programs using recursive and iterative functions *
 ************************************************************************************************************************/
import java.util.*;

public class TestHarness 
{
    private static final int REPEATS = 5;

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        if (args.length < 1) 
        {
            System.out.println("\nTest harness for recursive and iterative programs");
            System.out.println("\nEnter name of the program to be tested");
            System.out.println("\nUsage: java TestHarness <test_program>");
            System.out.println("Programs that can be tested: fib, fac, gcd, con, han");
            System.out.println("\nExample run method: java TestHarness fib");
            
            System.out.println("\nfib - Finding the fibonacci value at a given term" +
                               "\nfac - Finding the factorial of a given number" +
                               "\ngcd - Finding the greatest common denominator of two values" +
                               "\ncon - Converting a given decimal value to any base from 2 to 16" +
                               "\nhan - Displays the Towers of Hanoi solution for a given number of disks\n");
        }
        else
        {
            String testProgram = args[0];

            switch (testProgram) 
            {
                case "fib":
                    int fibNum;

                    try 
                    {
                        System.out.print("\nEnter the term (n) for which you want to calculate the Fibonacci sequence: ");
                        fibNum = sc.nextInt();

                        if (fibNum < 0) 
                        {
                            throw new IllegalArgumentException("Input must not be negative\n");
                        }

                        System.out.println("\nTesting Iterative and Recursive Fibonacci Programs...");
                        calcFibonacciIterative(fibNum);
                        calcFibonacciRecursive(fibNum);
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("\nError: Invalid input. Please enter a valid non-negative integer.");
                    } 
                    catch (IllegalArgumentException e) 
                    {
                        System.out.println("\nError: " + e.getMessage());
                    }
                    break;

                case "fac":
                    int facN;
                    long iterativeResult, recursiveResult;

                    try 
                    {
                        System.out.print("\nEnter a non-negative integer to calculate its factorial: ");
                        facN = sc.nextInt();

                        if (facN < 0 || facN > 31) 
                        {
                            throw new IllegalArgumentException("Input must be a non-negative integer between 0 and 31\n");
                        }

                        System.out.println("\nTesting Iterative and Recursive Factorial Programs...");
                        calcFactorialIterative(facN);
                        calcFactorialRecursive(facN);
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("\nError: Invalid input. Please enter a valid non-negative integer.");
                    } 
                    catch (IllegalArgumentException e) 
                    {
                        System.out.println("\nError: " + e.getMessage());
                    }
                    break;
               
                case "gcd":
                     int num1, num2, result;

                     try
                     {
                         System.out.print("\nEnter the first integer: ");
                         num1 = sc.nextInt();
                         
                         System.out.print("\nEnter the second integer: ");
                         num2 = sc.nextInt();

                         if (num1 < 0 || num2 < 0)
                         {
                             throw new IllegalArgumentException("Input must be a non-negative integer\n");
                         }

                         System.out.println("\nTesting Greatest Common Denominator Recursive Program...");
                         findGCD(num1, num2);
                     }
                     catch (InputMismatchException e)
                     {
                         System.out.println("\nError: Invalid input. Please enter a valid non-negative integer.");
                     }
                     catch (IllegalArgumentException e)
                     {
                         System.out.println("\nError: " + e.getMessage());
                     }
                     break;

                case "con":
                    int decNum, base;

                    try
                    {
                        System.out.print("\nEnter the decimal number (base 10): ");
                        decNum = sc.nextInt();

                        System.out.print("\nEnter the base (between 2 and 16): ");
                        base = sc.nextInt();

                        if (base < 2 || base > 16)
                        {
                            throw new IllegalArgumentException("Base must be between 2 and 16.");
                        }

                        System.out.println("\nTesting Decimal To Any Base Conversion Program...");
                        decimalToAnyBaseRecursive(decNum, base);
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("\nError: Invalid input. Please enter a valid non-negative integer.");
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println("\nError: " + e.getMessage());
                    }
                    break;

                case "han":
                    int numDisks;

                    try
                    {
                        System.out.print("\nEnter the number of disks to solve: ");
                        numDisks = sc.nextInt();

                        if (numDisks < 1)
                        {
                            throw new IllegalArgumentException("Number of disks must be greater than or equal to 1");
                        }

                        System.out.println("\nTesting Towers Of Hanoi Program...\n" + 
                                           "\ntowers(" + numDisks + ", 1, 3)\n");

                        towersOfHanoi(numDisks, 1, 3, 2, 1);
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("\nError: Invalid input. Please enter a valid non-negative integer.");
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println("\nError: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("\nInvalid test type. Available programs to test: fib, fac, gcd, con, han\n");
            }
        }
    }

    /****************************************************************************************************
    METHOD: calcFactorialIterative

    IMPORTS: integer (n)

    EXPORTS: None

    FUNCTION: This method calculates the factorial of a given non-negative integer 'n' iteratively. 
              It repeats the calculation multiple times (REPEATS) and calculates 
              the average time taken for the iterative factorial program.
    ****************************************************************************************************/
    private static void calcFactorialIterative(int n) 
    {
        long facResult = 1, totalTime = 0;

        for (int i = 0; i < REPEATS; i++) 
        {
            facResult = 1;
            long startTime = System.nanoTime();

            for (int j = 1; j <= n; j++) 
            {
                facResult *= j;
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            totalTime += elapsedTime;
        }

        System.out.println("\nITERATIVE PROGRAM - Factorial of " + n + ": " + facResult);
        System.out.println("Average Time for Factorial Iterative program: " + (totalTime / REPEATS) + " ns\n");
    }

    /************************************************************************************************
    METHOD: calcFactorialRecursive

    IMPORTS: integer (n)

    EXPORTS: None

    FUNCTION: This method calculates the factorial of a given non-negative integer 'n' recursively.
              It repeats the calculation multiple times (REPEATS) and calculates 
              the average time taken for the recursive factorial program.
    ************************************************************************************************/
    private static void calcFactorialRecursive(int n)
    {
        long facResult = 1, totalTime = 0;

        for (int i = 0; i < REPEATS; i++)
        {
            long startTime = System.nanoTime();
            
            facResult = facRecursive(n);
            
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            totalTime += elapsedTime;
        }

        System.out.println("\nRECURSIVE PROGRAM - Factorial of " + n + ": " + facResult);
        System.out.println("Average Time for Factorial Recursive program: " + (totalTime / REPEATS) + " ns\n");
    }
    
    /************************************************************************************************************
    METHOD: facRecursive

    IMPORTS: integer (n)

    EXPORTS: long (factorial)

    FUNCTION: This is a recursive helper method for calculating the factorial of a non-negative integer 'n'.
              It returns the factorial value of 'n'. If 'n' is negative, it throws an IllegalArgumentException.
    ************************************************************************************************************/
    private static long facRecursive(int n)
    {
        long factorial = 1;

        if (n < 0)
        {
            throw new IllegalArgumentException("Input must not be negative");
        }
        else if (n == 0)
        {
            factorial = 1;
        }
        else
        {
            factorial = n * facRecursive(n - 1);
        }

        return factorial;
    }

    /*****************************************************************************************************************
    METHOD: calcFibonacciIterative

    IMPORTS: integer (n)

    EXPORTS: None

    FUNCTION: This method calculates the Fibonacci sequence at a given term 'n' using an iterative approach. 
              It repeats the calculation multiple times (REPEATS) and calculates the average time 
              taken for the iterative Fibonacci program.
    *****************************************************************************************************************/
    private static void calcFibonacciIterative(int n) 
    {
        long totalTime = 0;
        int FibResult = 0;

        for (int i = 0; i < REPEATS; i++) 
        {
            long startTime = System.nanoTime();

            if (n == 0) 
            {
                FibResult = 0;
            } 
            else if (n == 1) 
            {
                FibResult = 1;
            }
            else 
            {
                int lastVal = 0;
                int currVal = 1;

                for (int j = 2; j <= n; j++) 
                {
                    FibResult = currVal + lastVal;
                    lastVal = currVal;
                    currVal = FibResult;
                }
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            
            totalTime += elapsedTime;
        }

        System.out.println("\nITERATIVE PROGRAM - Fibonacci at term " + n + ": " + FibResult);
        System.out.println("Average Time for Fibonacci Iterative program: " + (totalTime / REPEATS) + " ns\n");
    }

    /*******************************************************************************************************
    METHOD: calcFibonacciRecursive

    IMPORTS: integer (n)

    EXPORTS: None

    FUNCTION: This method calculates the Fibonacci sequence at a given term 'n' using a recursive approach. 
              It repeats the calculation multiple times (REPEATS) and calculates 
              the average time taken for the recursive Fibonacci program.
    *******************************************************************************************************/
    private static void calcFibonacciRecursive(int n) 
    {
        long totalTime = 0;
        int FibResult = 0;

        for (int i = 0; i < REPEATS; i++) 
        {
            long startTime = System.nanoTime();
            
            FibResult = fibRecursive(n);
            
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
           
            totalTime += elapsedTime;
        }

        System.out.println("\nRECURSIVE PROGRAM - Fibonacci at term " + n + ": " + FibResult);
        System.out.println("Average Time for Fibonacci Recursive program: " + (totalTime / REPEATS) + " ns\n");
    }

    /***********************************************************************************************************
    METHOD: fibRecursive

    IMPORTS: integer (n)

    EXPORTS: integer (fibVal)

    FUNCTION: This is a recursive helper method for calculating the Fibonacci sequence at a given term 'n'.
              It returns the Fibonacci value at term 'n'. If 'n' is negative, 
              it throws an IllegalArgumentException.
    **********************************************************************************************************/
    private static int fibRecursive(int n)
    {
        long totalTime = 0;
        int fibVal = 0;

        if (n == 0)
        {
            fibVal = 0;
        }
        else if (n == 1)
        {
            fibVal = 1;
        }
        else
        {
            fibVal = fibRecursive(n - 1) + fibRecursive(n - 2);
        }

        return fibVal;
    }

    /****************************************************************************************************************
    METHOD: findGCD

    IMPORTS: integer (num1), integer (num2)

    EXPORTS: None

    FUNCTION: This method finds the greatest common denominator (GCD) of two integers 'num1' and 
              'num2' using a recursive approach. It repeats the calculation multiple times (REPEATS)
              and calculates the average time taken for the GCD program.
    ***************************************************************************************************************/
    private static void findGCD(int num1, int num2)
    {
        long totalTime = 0;
        int gcdResult = 0;

        for (int i = 0; i < REPEATS; i++)
        {
            long startTime = System.nanoTime();

            gcdResult = gcdRecursive(num1, num2);

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            totalTime += elapsedTime;
        }

        System.out.println("\nGreatest Common Denominator of " + num1 + " and " + num2 + ": " + gcdResult);
        System.out.println("Average Time for Greatest Common Denominator program: " + (totalTime / REPEATS) + " ns\n");
    }

    /******************************************************************************************************************
    METHOD: gcdRecursive

    IMPORTS: integer (num1), integer (num2)

    EXPORTS: integer (gcd)

    FUNCTION: This is a recursive helper method for finding the greatest common denominator (GCD) 
              of two integers 'num1' and 'num2'. It returns the GCD value. If 'num2' is zero, 
              it returns 'num1'. It uses a recursive algorithm to find the GCD.
    ******************************************************************************************************************/
    private static int gcdRecursive(int num1, int num2) // With reference from: https://www.javatpoint.com/java-program-to-find-gcd-of-two-numbers
    {
        int gcd = 1;

        if (num2 == 0)
        {
            gcd = num1;
        }
        else
        {
            gcd = gcdRecursive(num2, num1 % num2);
        }

        return gcd;
    }

    /**********************************************************************************************************************
    METHOD: decimalToAnyBaseRecursive

    IMPORTS: integer (decNum), integer (base)

    EXPORTS: None

    FUNCTION: This method converts a given decimal number 'decNum' to a specified base 'base'
              (between 2 and 16) using a recursive approach. It repeats the conversion multiple times (REPEATS)
              and calculates the average time taken for the conversion program.
    *********************************************************************************************************************/
    private static void decimalToAnyBaseRecursive(int decNum, int base)
    {
        long totalTime = 0;
        String convResult = "";

        for (int i = 0; i < REPEATS; i++)
        {
            long startTime = System.nanoTime();

            convResult = convRecursive(decNum, base);

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            totalTime += elapsedTime;
        }

        System.out.println("\nValue of " + decNum + " in base " + base + ": " + convResult);
        System.out.println("Average Time for Decimal To Any Base Conversion Program: " + (totalTime / REPEATS) + " ms\n");
    }

    /********************************************************************************************************************
    METHOD: convRecursive

    IMPORTS: integer (decNum), integer (base)

    EXPORTS: String (result)

    FUNCTION: This is a recursive helper method for converting a decimal number 'decNum' to a 
              specified base 'base'. It returns the converted value as a string. 
              It uses a recursive algorithm to perform the conversion.
    ********************************************************************************************************************/
    private static String convRecursive(int decNum, int base)
    {
        int quotient = decNum / base;
        int remainder = decNum % base;
        String result;

        char[] hexChars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        if (decNum == 0)
        {
            result = "0";
        }
        else if (quotient == 0)
        {
            result = Character.toString(hexChars[remainder]);
        }
        else
        {
            result = convRecursive(quotient, base) + Character.toString(hexChars[remainder]);
        }
        
        return result;
    }

    /*******************************************************************************************************************
    METHOD: towersOfHanoi

    IMPORTS: integer (numDisks), integer (sourcePeg), integer (destinationPeg),
             integer (auxiliaryPeg), integer (recLevel)

    EXPORTS: None

    FUNCTION: This method initiates the Towers of Hanoi problem-solving process. It takes the number 
              of disks 'numDisks' and other parameters, and then calls the 'solveTowers' method to solve the problem. 
              It measures the time taken for the solution.
    *******************************************************************************************************************/
    private static void towersOfHanoi(int numDisks, int sourcePeg, int destinationPeg, int auxiliaryPeg, int recLevel)
    {
        long startTime = System.currentTimeMillis();

        solveTowers(numDisks, sourcePeg, destinationPeg, auxiliaryPeg, recLevel);
        
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken for Towers Of Hanoi program: " + elapsedTime + " ms\n");

    }

    /*******************************************************************************************************************
    METHOD: solveTowers

    IMPORTS: integer (numDisks), integer (sourcePeg), integer (destinationPeg),
             integer (auxiliaryPeg), integer (recLevel)

    EXPORTS: None

    FUNCTION: This is a recursive method for solving the Towers of Hanoi problem.
              It takes the number of disks 'numDisks', source peg, destination peg, auxiliary peg, and recursion level. 
              It recursively moves the disks from source to destination.
    *******************************************************************************************************************/
    private static void solveTowers(int numDisks, int sourcePeg, int destinationPeg, int auxiliaryPeg, int recLevel)
    {           
        if (numDisks == 1)
        {
            diskShift(recLevel, 1, sourcePeg, destinationPeg);
        }
        else
        {
            solveTowers(numDisks - 1, sourcePeg, auxiliaryPeg, destinationPeg, recLevel + 1); 
            diskShift(recLevel, numDisks, sourcePeg, destinationPeg);
            solveTowers(numDisks - 1, auxiliaryPeg, destinationPeg, sourcePeg, recLevel + 1);
        }
    }

    /*************************************************************************************************************
    METHOD: diskShift

    IMPORTS: integer (recLevel), integer (diskNumber), integer (src), integer (dest)

    EXPORTS: None

    FUNCTION: This method prints information about the current move and recursion level with indentation.
              It is used within the Towers of Hanoi problem-solving process to display the steps of the solution.
    *************************************************************************************************************/
    public static void diskShift(int recLevel, int diskNumber, int src, int dest) 
    {
        String indentation = "";
        for (int i = 0; i < recLevel; i++) 
        {
            indentation += "    ";
        }

        System.out.println(indentation+ "Recursion Level=" + recLevel +
                           "\n" + indentation + "Moving Disk " + diskNumber + " from Source " + src + " to Destination " + dest +
                           "\n" + indentation + "n=" + diskNumber + ", src=" + src + ", dest=" + dest);
        System.out.println();
    }
}
