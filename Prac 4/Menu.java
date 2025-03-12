/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *
 *
 * DATE CREATED: 26/08/23                                                                *
 *
 * LAST EDITED: 01/09/23                                                                 *
 *
 * DESCRIPTION: Menu for creating stacks and queues as linked lists and perfroming       *
 *              operations on them                                                       *
 ****************************************************************************************/
import java.util.*;

public class Menu 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean exitMenu = false;

        while (!exitMenu) // Display first selection menu for stack or queue selection
        {
            System.out.println("\n----------------------------------------");
            System.out.println("           Linked List Menu             ");
            System.out.println("----------------------------------------");
            System.out.println("\nSelect an option:");
            System.out.println("\n\t[ 1 ] Create a Linked List Stack");
            System.out.println("\t[ 2 ] Create a Linked List Queue");
            System.out.println("\t[ 0 ] Exit");
            System.out.print("\nEnter your choice: ");

            int choice = getUserChoice(sc);

            switch (choice) 
            {
                case 0:
                    System.out.println("\nExiting program...\n");
                    exitMenu = true;
                    break;
                
                case 1:
                    DSAStack stack = new DSAStack(); // Creates new empty stack and passes it to stack handling method
                    stackOperations(stack, sc);
                    break;
                
                case 2:
                    DSAQueue queue = new DSAQueue(); // Creates new empty queue and passes it to queue handling method
                    queueOperations(queue, sc);
                    break;
                
                default:
                    System.out.println("\nInvalid option. Please enter a valid input number.");
                    break;
            }
        }

        sc.close();
    }

    // Method for validating user input
    private static int getUserChoice(Scanner sc) 
    {
        int choice = -1;
        boolean validInput = false;
        
        while (!validInput) 
        {
            try 
            {
                choice = Integer.parseInt(sc.nextLine());
                validInput = true;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("\nInvalid input. Please enter a number.");
            }
        }
        
        return choice;
    }

    // Menu for printing and calling stack operations
    private static void stackOperations(DSAStack stack, Scanner sc) 
    {
        boolean exitStack = false;

        while (!exitStack) 
        {
            System.out.println("\n\n----------------------------------------");
            System.out.println("           Stack Operations             ");
            System.out.println("----------------------------------------");
            System.out.println("\n\t[ 1 ] Insert Stack element");
            System.out.println("\t[ 2 ] Remove Stack element");
            System.out.println("\t[ 3 ] Peek top of Stack");
            System.out.println("\t[ 4 ] Display Current Stack");
            System.out.println("\t[ 0 ] Exit to Previous Menu");
            System.out.print("\nEnter your choice: ");

            int choice = getUserChoice(sc);
            
            try 
            {
                switch (choice)
                {
                    case 0:
                        exitStack = true;
                        break;
                    
                    case 1: // Adds object to stack 
                        System.out.print("\nAdding an element..." +
                                         "\nEnter a value to insert to the Stack: ");
                        Object insertValue = sc.nextLine();
                        stack.push(insertValue);
                        System.out.println("Value added successfully!");
                        break;
                    
                    case 2: // Removes object from stack
                        Object removedValue = stack.pop();
                        System.out.println("\nRemoving an element..." +
                                           "\nRemoved " + removedValue + " from the Stack successfully!");
                        break;
                    
                    case 3: // Peeks value at top of stack
                        Object peekedValue = stack.top();
                        System.out.println("\nValue at top of Stack: " + peekedValue);
                        break;
                    
                    case 4: // Prints current stack contents
                        System.out.print("\nCurrent Stack contents: ");
                        stack.getStack().displayList();
                        break;
                    
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                        break;
                }
            } 
            catch (IllegalStateException e) 
            {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }

    // Method for printing and calling queue operations
    private static void queueOperations(DSAQueue queue, Scanner sc) 
    {
        boolean exitQueue = false;

        while (!exitQueue) 
        {
            System.out.println("\n\n----------------------------------------");
            System.out.println("           Queue Operations             ");
            System.out.println("----------------------------------------");
            System.out.println("\n\t[ 1 ] Insert at start of Queue");
            System.out.println("\t[ 2 ] Insert at end of Queue");
            System.out.println("\t[ 3 ] Remove from start of Queue");
            System.out.println("\t[ 4 ] Remove from end of Queue");
            System.out.println("\t[ 5 ] Peek First in Queue");
            System.out.println("\t[ 6 ] Peek Last in Queue");
            System.out.println("\t[ 7 ] Display Current Queue");
            System.out.println("\t[ 0 ] Exit to Previous Menu");
            System.out.print("\nEnter your choice: ");

            int choice = getUserChoice(sc);

            try 
            {
                switch (choice) 
                {
                    case 0:
                        exitQueue = true;
                        break;
                    
                    case 1: // Adds object to front of queue
                        System.out.print("\nAdding an element..." + 
                                         "\nEnter a value to insert at the front of the Queue: ");
                        Object insertValue = sc.nextLine();
                        queue.enqueue(insertValue);
                        break;
                    
                    case 2: // Adds object to end of queue
                        System.out.print("\nAdding an element..." +
                                         "\nEnter a value to insert at the end of the Queue: ");
                        insertValue = sc.nextLine();
                        queue.enqueue(insertValue);
                        break;
                    
                    case 3: // Removes object from front of queue
                        Object removedValue = queue.dequeue();
                        System.out.println("\nRemoving an element..." +
                                           "\nRemoved " + removedValue + " from the front of the Queue successfully!");
                        break;
                    
                    case 4: // Removes object from end of queue
                        removedValue = queue.removeLast();
                        System.out.println("\nRemoving an element..." +
                                           "\nRemoved " + removedValue + " from the back of the Queue successfully!");
                        break;
                    
                    case 5: // Peeks object at front of queue
                        Object peekedValue = queue.peek();
                        System.out.println("\nValue at the front of the Queue: " + peekedValue);
                        break;
                    
                    case 6: // Peeks object at end of queue
                        peekedValue = queue.peekLast();
                        System.out.println("\nValue at the end of the Queue: " + peekedValue);
                        break;
                    
                    case 7: // Prints current queue contents
                        System.out.print("\nCurrent Queue contents: ");
                        queue.getQueue().displayList();
                        break;
                    
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                        break;
                }
            } 
            catch (IllegalStateException e) 
            {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }
}
