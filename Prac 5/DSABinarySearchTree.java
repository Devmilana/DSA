/*****************************************************************************************
 * AUTHOR: PRASHANTHA FERNANDO                                                           *  
 *
 * LAST EDITED: 14/09/23                                                                 *
 *
 * DESCRIPTION: Class file for creating and manipulating a binary search tree            *                                                                  
 * **************************************************************************************/

import java.util.*;

public class DSABinarySearchTree
{
    private class DSATreeNode 
    {
        /* ===========================  DSATreeNode   =================================**/
        
        
        /* Private classfields */
        private int m_key; 
        private Object m_value;
        private DSATreeNode m_leftChild, m_rightChild;

        
        /* Constructor */
        /* -------------------------------------------------------------------------------
         * 
         * Import: inKey(int), inVal (Object)
         * Export: Memory address of new DSATreeNode
         *
         * Sets left and right child to null, sets key and value of node to
         * given values
         *
        */
        public DSATreeNode(int inKey, Object inVal) 
        {
            m_key = inKey;
            m_value = inVal;
            m_rightChild = null;
            m_leftChild = null;
        }

        
        /* Constructor */
        /* -------------------------------------------------------------------------------
         * 
         * Import: none
         * Export: m_key(int)
         *
         * Returns key of node
         *
        */
        public int getKey() 
        {
            return m_key;
        }

        
        /* getValue */
        /* -------------------------------------------------------------------------------
         * 
         * Import: none
         * Export: m_value(Object)
         *
         * Returns value of node
         *
        */
        public Object getValue() 
        {
            return m_value;
        }

        
        /* getLeft */
        /* -------------------------------------------------------------------------------
         * 
         * Import: none
         * Export: m_leftChild (DSATreeNode)
         *
         * Returns left child reference
         *
        */
        public DSATreeNode getLeft() 
        {
            return m_leftChild;
        }

        
        /* getRight */
        /* -------------------------------------------------------------------------------
         * 
         * Import: none
         * Export: m_rightChild (DSATreeNode)
         *
         * Returns right child reference
         *
        */
        public DSATreeNode getRight() 
        {
            return m_rightChild;
        }

        
        /* setLeft */
        /* -------------------------------------------------------------------------------
         * 
         * Import: newLeft (DSATreeNode)
         * Export: none
         *
         * Sets left node of the current node
         *
        */
        public void setLeft(DSATreeNode newLeft) 
        {
            m_leftChild = newLeft;
        }

        
        /* setRight */
        /* -------------------------------------------------------------------------------
         * 
         * Import: newRight (DSATreeNode)
         * Export: none
         *
         * Sets right node of the current node
         *
        */
        public void setRight(DSATreeNode newRight) 
        {
            m_rightChild = newRight;
        }
    }
    
    /* =========================  DSABinarySearchTree   ===========================**/

    private DSATreeNode m_root;

    
    /* Constructor */
    /* -------------------------------------------------------------------------------
     * 
     * Import: none
     * Export: Memory address of new DSABinarySearchTree
     *
     * Constructs an empty DSABinarySearchTree
     *
    */
    public DSABinarySearchTree() 
    {
        m_root = null; // Start with an empty tree
    }

    
    /* Insert */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int), value(Object)
     * Export: none
     *
     * Inserts a node with the provided key and value into the binary search tree
     *
    */
    public void insert(int key, Object value) 
    { 
        insertRec(key, value, m_root);
    }

    
    /* Delete */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int)
     * Export: none
     *
     * Deletes a node with the provided key from the binary search tree
     *
    */
    public void delete(int key) 
    {
        deleteRec(key, m_root);
    }

    
    /* Find */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int)
     * Export: value(Object)
     *
     * Finds and returns the value associated with the provided key in the binary search tree
     *
    */
    public Object find(int key) 
    { 
        return findRec(key, m_root);
    }

    
    /* Display */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: none
     *
     * Displays the tree in inorder
     *
    */
    public void display()
    {
        displayInorder();
        System.out.println();
    }

    
    /* Height */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: height(int)
     *
     * Returns the height of the binary search tree
     *
    */
    public int height() 
    {
        return heightRec(m_root);
    }

    
    /* Min */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: minKey(int)
     *
     * Returns the smallest key in the binary search tree
     *
    */
    public int min() 
    {
        return minRec(m_root);
    }

    
    /* Max */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: maxKey(int)
     *
     * Returns the largest key in the binary search tree
     *
    */
    public int max() 
    {
        return maxRec(m_root);
    }

    
    /* Balance */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: balanceFactor(double)
     *
     * Returns the balance factor of the binary search tree
     *
    */
    public String balance()
    {
        int leftHt, rightHt, rootHt, diffHt;
        double balance;

        if(m_root == null)
        {
            balance = 100.0; // Empty tree is perfectly balanced
        }
        else if (m_root.getLeft() == null && m_root.getRight() == null) 
        {
            balance = 100.0; // Single-node tree is perfectly balanced
    	}
        else
        {
            leftHt = heightRec(m_root.getLeft());
            rightHt = heightRec(m_root.getRight());
            rootHt = height();
            diffHt = Math.abs(leftHt - rightHt);
            balance = 1 - ((double)diffHt) / ((double)rootHt);
            balance *= 100.0; // Convert to percentage
        }

        return String.format("%.2f%%", balance);
    }

    
    /* Display Inorder */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: none
     *
     * Displays the tree in inorder
     *
    */
    public void displayInorder() 
    {
        displayInorderRec(m_root);
    }

    
    /* Display Preorder */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: none
     *
     * Displays the tree in preorder
     *
    */
    public void displayPreorder() 
    {  
        displayPreorderRec(m_root);
    }

    
    /* Display Postorder */
    /* -------------------------------------------------------------------------------
     *
     * Import: none
     * Export: none
     *
     * Displays the tree in postorder
     *
    */
    public void displayPostorder() 
    {
        displayPostorderRec(m_root);
    }

    
    /* InsertRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int), value(Object), curNode(DSATreeNode)
     * Export: DSATreeNode
     *
     * Recursive helper method to insert a node with the provided key and value.
     *
    */
    private DSATreeNode insertRec(int key, Object value, DSATreeNode currNode) 
    {
        DSATreeNode updateNode = currNode;

        if (currNode == null) 
        {
            // Base case - found
            updateNode = new DSATreeNode(key, value);
            if(m_root == null)
            {
                m_root = updateNode;
            }
        } 
        else if (key == currNode.getKey()) 
        {
            // Key already exists in the tree
            throw new IllegalArgumentException("Key " + key + " already exists in the tree");
        } 
        else if (key < currNode.getKey()) 
        {
            currNode.setLeft(insertRec(key, value, currNode.getLeft())); // Recurse left
        } 
        else 
        {
            currNode.setRight(insertRec(key, value, currNode.getRight())); // Recurse right
        }

        return updateNode;
    }

    
    /* DeleteRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int), curNode(DSATreeNode)
     * Export: DSATreeNode
     *
     * Recursive helper method to delete a node with the provided key.
     *
    */
    private DSATreeNode deleteRec(int key, DSATreeNode currNode) 
    {
        DSATreeNode updateNode = currNode;

        if (currNode == null) 
        {
            // Empty tree
            throw new NoSuchElementException("Key " + key + " was not found. Cannot delete");
	    }
        else if (key == currNode.getKey()) 
        {
            // base case - key found
            updateNode = deleteNode(key, currNode);
        } 
        else if (key < currNode.getKey()) 
        {
            currNode.setLeft(deleteRec(key, currNode.getLeft())); // Recurse left
        } 
        else 
        {
            currNode.setRight(deleteRec(key, currNode.getRight())); // Recurse right
        }

        return updateNode;
    }

    
    /* DeleteNode */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int), delNode(DSATreeNode)
     * Export: DSATreeNode
     *
     * Deletes the specified node and returns the updated node.
     *
    */
    private DSATreeNode deleteNode(int key, DSATreeNode delNode) 
    {
        DSATreeNode updateNode = null;

        if ((delNode.getLeft() == null) && (delNode.getRight() == null)) 
        {
            // No children
            updateNode = null;
        } 
        else if ((delNode.getLeft() != null) && (delNode.getRight() == null)) 
        {
            // One child - left
            updateNode = delNode.getLeft();
        } 
        else if ((delNode.getLeft() == null) && (delNode.getRight() != null)) 
        {
            // One child - right
            updateNode = delNode.getRight();
        } 
        else 
        {
            // Two children
            updateNode = promoteSuccessor(delNode.getRight());
            if (updateNode != delNode.getRight()) 
            {
                updateNode.setRight(delNode.getRight()); // Update right node
                deleteRec(updateNode.getKey(), delNode.getRight()); // Remove successor from its original position
            }
            
            updateNode.setLeft(delNode.getLeft()); // Update left node
        }

        return updateNode;
    }

    
    /* FindRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: key(int), currNode(DSATreeNode)
     * Export: Object
     *
     * Recursive helper method to find and return the value associated with the provided key.
     *
    */
    private Object findRec(int key, DSATreeNode currNode) 
    {
        Object value = null;

        if (currNode == null)
        {
            // Base case: not found
            throw new NoSuchElementException("Key " + key + " was not found");
        }
        else if (key == currNode.getKey()) 
        {
            // Base case: found
            value = currNode.getValue();
        }
        else if (key < currNode.getKey()) 
        {
            value = findRec(key, currNode.getLeft()); // Recurse left
        }
        else
        {
            value = findRec(key, currNode.getRight()); // Recurse right
        }

        return value;
    }

    
    /* PromoteSuccessor */
    /* -------------------------------------------------------------------------------
     *
     * Import: cur(DSATreeNode)
     * Export: DSATreeNode
     *
     * Finds and promotes the successor node for a given node.
     *
    */
    private DSATreeNode promoteSuccessor(DSATreeNode currNode) 
    {
        DSATreeNode successor = currNode;
        
        if (currNode.getLeft() != null) 
        {
            successor = promoteSuccessor(currNode.getLeft()); // Recurse left to find successor
            if (successor == currNode.getLeft()) 
            {
                currNode.setLeft(successor.getRight()); // If successor is left of current node, set current node as right child of successor
            }
        }

        return successor;
    }

    
    /* HeightRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: curNode(DSATreeNode)
     * Export: int
     *
     * Recursive helper method to compute the height of a subtree rooted at the given node.
     *
    */
    public int heightRec(DSATreeNode currNode) 
    {
        int currentHeight, leftHeight, rightHeight;

        if (currNode == null)
        {
            // Base case – no more along this branch
            currentHeight = -1;
        }
        else 
        {
            leftHeight = heightRec(currNode.getLeft()); // Calc left height
            rightHeight = heightRec(currNode.getRight()); // Calc right height
            currentHeight = Math.max(leftHeight, rightHeight) + 1; // Get highest of left vs right branches
        }

        return currentHeight;
    }

    
    /* MinRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: curNode(DSATreeNode)
     * Export: int
     *
     * Recursive helper method to find the smallest key in a subtree rooted at the given node.
     *
    */
    private int minRec(DSATreeNode currNode) 
    {
        int minKey;

        if (m_root == null) 
        {
            // Empty tree
            throw new NoSuchElementException("The tree is empty. Min key cannot be found");
        }
        else if (currNode.getLeft() != null) 
        {
            minKey = minRec(currNode.getLeft()); // Recurse left
        } 
        else 
        {
            // Base case - min key found
            minKey = currNode.getKey();
        }

        return minKey;
    }

    
    /* MaxRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: curNode(DSATreeNode)
     * Export: int
     *
     * Recursive helper method to find the largest key in a subtree rooted at the given node.
     *
    */
    private int maxRec(DSATreeNode currNode) 
    {
        int maxKey;

        if (m_root == null) 
        {
            // Empty tree
            throw new NoSuchElementException("The tree is empty. Max key cannot be found");
        }
        else if (currNode.getRight() != null) 
        {
            maxKey = maxRec(currNode.getRight()); // Recurse right
        }
        else 
        {
            // Base case - max key found
            maxKey = currNode.getKey();
        }

        return maxKey;
    }

    
    /* DisplayInorderRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: currNode(DSATreeNode)
     * Export: none
     *
     * Recursive helper method to display the tree in inorder.
     *
    */
    private void displayInorderRec(DSATreeNode currNode) // Order of left-node-right
    {
        if (currNode != null) 
        {
            displayInorderRec(currNode.getLeft());
            System.out.print(currNode.getKey() + ", ");
            displayInorderRec(currNode.getRight());
        }
    }

    
    /* DisplayPreorderRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: currNode(DSATreeNode)
     * Export: none
     *
     * Recursive helper method to display the tree in preorder.
     *
    */
    private void displayPreorderRec(DSATreeNode currNode) // Order of node-left-right
    {
        if (currNode != null) 
        {
            System.out.print(currNode.getKey() + ", ");
            displayPreorderRec(currNode.getLeft());
            displayPreorderRec(currNode.getRight());
        }
    }

    
    /* DisplayPostorderRec */
    /* -------------------------------------------------------------------------------
     *
     * Import: currNode(DSATreeNode)
     * Export: none
     *
     * Recursive helper method to display the tree in postorder.
     *
    */
    private void displayPostorderRec(DSATreeNode currNode) // Order of left-right-node
    {
        if (currNode != null) 
        {
            displayPostorderRec(currNode.getLeft());
            displayPostorderRec(currNode.getRight());
            System.out.print(currNode.getKey() + ", ");
        }
    }
}
