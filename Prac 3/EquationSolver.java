/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO 								 *
 *
 * DATE CREATED: 18/08/23      								 *
 *
 * LAST EDITED: 23/08/23       								 *
 *
 * DESCRIPTION: Class file for converting a given equation from infix to postfix form    *
 *              and evaluating the postfix result					 *
 * **************************************************************************************/
import java.util.*;

public class EquationSolver 
{
    private DSAStack opStack;
    private DSAQueue postfixQueue;

    public EquationSolver() // Initialises opStack and postfixQueue
    {
        opStack = new DSAStack();
        postfixQueue = new DSAQueue();
    }

    public double solve(String equation) // Takes in equation, passes it to infix to postfix converter and returns evaluated result
    {
        DSAQueue postfix = parseInfixToPostfix(equation);
        return evaluatePostfix(postfix);
    }

    private DSAQueue parseInfixToPostfix(String equation) // Infix to postfix converter
    {
        DSAQueue postfix = new DSAQueue(); // Initialised empty postfix to store postfix expression

        String[] terms = equation.split(" "); // Splits equation into an array of terms with spaces as seperators
        DSAStack operatorStack = new DSAStack(); // Initialised empty operator stack to store operators

        int numTerms = terms.length;
        for (int i = 0; i < numTerms; i++) // Iterates through terms in array
        {
            String term = terms[i];
            term = term.trim(); // Removes whitespaces in terms

            if (!term.equals("")) 
            {
                if (term.equals("(")) 
                {
                    operatorStack.push(term);
                } 
                else if (term.equals(")")) 
                {
                    while (!operatorStack.isEmpty() && !operatorStack.top().equals("(")) 
                    {
                        postfixQueue.enqueue(operatorStack.pop()); // Pops operators from stack and enqueues them to postfixQueue
                    }
                    operatorStack.pop(); // Pop the '('
                } 
                else if (term.equals("+") || term.equals("-") || term.equals("*") || term.equals("/")) 
                {
                    while (!operatorStack.isEmpty() && !operatorStack.top().equals("(") && precedenceOf((char) operatorStack.top()) >= precedenceOf(term.charAt(0))) 
                    {
                        postfixQueue.enqueue(operatorStack.pop()); // Pops operators from stack to queue depending on precedence
                    }
                    operatorStack.push(term.charAt(0)); // Pushes operators to stack depending on precedence and current value at top of stack
                } 
                else 
                {
                    postfixQueue.enqueue(Double.valueOf(term)); // If an operand, value is directly enqueued to postfixQueue
                }
            }
        }

        while (!operatorStack.isEmpty()) 
        {
            postfixQueue.enqueue(operatorStack.pop());
        }

        while (!postfixQueue.isEmpty()) 
        {
            postfix.enqueue(postfixQueue.dequeue());
        }

        return postfix; 
    }

    private double evaluatePostfix(DSAQueue postfixQueue) // Evaluates postfixQueue terms
    {
        DSAStack operandStack = new DSAStack(); // Initialises empty stack called operandStack to store operands

        while (!postfixQueue.isEmpty()) 
        {
            Object term = postfixQueue.dequeue(); // Dequeues elements in postfixQueue as terms

            if (term instanceof Character) // If term is a character, pops two operands, performs operation and pushes result back to operandStack
            {
                char op = (char) term;
                double op2 = (double) operandStack.pop();
                double op1 = (double) operandStack.pop();
                operandStack.push(executeOperation(op, op1, op2));
            } 
            else if (term instanceof Double) // If term is a operand, value is simply pushed into operandStack
            {
                operandStack.push(term);
            }
        }

        return (double) operandStack.pop(); // Final result popped from operandStack and returned
    }

    private int precedenceOf(char theOp) // Checks precedence of operators
    {
        if (theOp == '+' || theOp == '-') 
        {
            return 1;
        } 
        else if (theOp == '*' || theOp == '/') 
        {
            return 2;
        }
        
        return 0;
    }

    private double executeOperation(char op, double op1, double op2) // Executes arithmetic operations on operands based on operator term
    {
        switch (op) 
        {
            case '+':
                return op1 + op2;

            case '-':
                return op1 - op2;

            case '*':
                return op1 * op2;

            case '/':
                return op1 / op2;

            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }
}
