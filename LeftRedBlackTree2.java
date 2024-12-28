//Code taken from Algorithms by Sedgewick and Wayne and ChatGPT, I edited portions of it
import java.util.List;
import java.util.ArrayList;
//This class creates a left leaning red-black tree along with helper methods to maintain the left leaning red-black tree properties, methods to put key/value pairs into the tree, methods to get the values of specific keys from the tree, methods to iterate through and store (in order) each key in the tree, methods to get the color of each node of the tree, and methods to check if the tree contains a given key.
public class LeftRedBlackTree2<Key extends Comparable<Key>, Value> {
	//instance variable for the root node
	private Node root;
    //instance variables for node colors
	private static final boolean RED = true;
    private static final boolean BLACK = false;
    //this class creates the nodes that store the data in the binary search tree
    private class Node {
    	//instance variables for key and value
    	Key key;
        Value val;
        //instance variables for subtrees
        Node left, right;
        //instance variable for color of link from parent to this node
        boolean color;
        //constructor
        Node(Key key, Value val, boolean color) {
        	this.key = key;
            this.val = val;
            this.color = color;
        } 
    }
    //this method checks if a given node is red
    private boolean isRed(Node x) {
    	//return false if the given node is null
        if (x == null) return false;
        //return true if the given node is red, false otherwise
        return x.color == RED;
    }
    //This method performs a left rotation on a given node, restructuring the tree to maintain the left leaning red-black tree properties after an element is put in the tree. It rotates the given node to the left, making its right child the new parent node then changes the tree's colors accordingly.
    private Node RotateLeft(Node h) {
        //store the right child of the given node as x
    	Node x = h.right;
    	//update the right child of the given node h to be the left child of x
        h.right = x.left;
        //sets the left child of x to h
        x.left = h;
        //transfer the color of h to x
        x.color = h.color;
        //set the color of h to red
        h.color = RED;
        //return the root of the rotated subtree (which is x)
        return x;
    }
    //This method performs a right rotation on a given node, restructuring the tree to maintain the left leaning red-black tree properties after an element is put in the tree. It rotates the given node to the right, making its left child the new parent node then changes the tree's colors accordingly.
    private Node RotateRight(Node h) {
        //store the left child of the given node as x
    	Node x = h.left;
    	//update the left child of the given node h to be the right child of x
        h.left = x.right;
        //sets the right child of x to h
        x.right = h;
        //transfer the color of h to x
        x.color = h.color;
        //set the color of h to red
        h.color = RED;
        //return the root of the rotated subtree (which is x)
        return x;
    }
    //This method flips the colors of a red-black tree node and its children to maintain the left leaning red-black tree color properties after an element is put in the tree. It inverts the colors of the given node and its left and right children.
    private void flipColors(Node h) {
    	//set the color of the given node to red
    	h.color = RED;
    	//set the colors of the given node's left and right children to black
    	h.left.color = BLACK;
    	h.right.color = BLACK;
    }
    //This method is the public put method, so it is the method that is called in the driver to put a key/value pair into the tree. It just calls the private put method with the given key/value pair and the root of the tree and sets the color of the root of the tree to black. It is essentially just a public call to the private put method that actually puts the key/value pair in the tree that also stores the updated root and ensures that it remains black.
    public void put(Key key, Value val) { 
    	//call the private put method to put the key/value pair into the tree and store the updated/modified root node
        root = put(root, key, val);
        //ensure the root node is always black
        root.color = BLACK;
    }
    //This method does all the heavy lifting for the public put method, it is the method that actually puts a given key/value pair in the tree. To do this, it recursively searches for a given key/value pair. It updates value of the key if the key is found; otherwise it grows tree if the key is new (not found). If the key is new, this method utilizes the RotateRight, RotateLeft, and flipColors methods to maintain the properties of the left leaning red-black tree after this new key is put in the tree.
    private Node put(Node h, Key key, Value val) {
    	//base case; once correct empty node is found, do standard insert with red link to parent
    	if (h == null)  
           return new Node(key, val, RED);
    	//compare the given key to the key of the current node (the root key), store the value returned by comparison
        int cmp = key.compareTo(h.key);
        //recursively traverse the left subtree if the key to be inserted is less than the root key
        if      (cmp < 0) h.left  = put(h.left,  key, val);
        //recursively traverse the right subtree if the key to be inserted is greater than the root key
        else if (cmp > 0) h.right = put(h.right, key, val);
        //the given key already exists in the tree, so update its value
        else h.val = val;
        //if the root has a red right child, rotate left to maintain left leaning red-black tree properties
        if (isRed(h.right) && !isRed(h.left))    h = RotateLeft(h);
        //if the root has two consecutive red left children, rotate right to maintain left leaning red-black tree properties
        if (isRed(h.left) && isRed(h.left.left)) h = RotateRight(h);
        //if both the root's right and left child are red, flip colors to maintain left leaning red-black tree properties
        if (isRed(h.left) && isRed(h.right))     flipColors(h);
        //return updated/modified root node
        return h; 
    }
    //This method is the public get method, so it is the method that is called in the driver to get a given key's value. It is essentially just a public call to the private get method that actually gets the given key's value; it simply calls this private get method with the root node and the given key and returns the value obtained from the private get method.
    public Value get(Key key) {
    	//call the private get method with the root node and the given key; return the value obtained from the private get method
    	return get(root, key);  
    }
    //This method does all the heavy lifting for the public get method, it is the method that actually gets the given key's value. To do this, it recursively searches the tree for the given key and returns its value if the key is found, otherwise it returns null.
     private Value get(Node x, Key key) {  
        //base case; if root node is null tree is empty or has been searched completely, key was not found, return null
        if (x == null) return null;
        //compare the given key to the key of the current node (the root key), store the value returned by comparison
        int cmp = key.compareTo(x.key);
        //recursively traverse/search the left subtree if the given key is less than the root key
        if      (cmp < 0) return get(x.left, key);
        //recursively traverse/search the right subtree if the given key is greater than the root key
        else if (cmp > 0) return get(x.right, key);
        //key was found, return its value
        else return x.val;
    }
    //This method is the public keys method, so it is the method that is called in the driver to return an iterable of keys in ascending order. It is essentially just a public call to the private keys method that actually traverses the tree to create the ordered iterable of keys. It initializes an arraylist to store the collected keys, calls the private keys method with the root node and the arraylist, and returns the arraylist after it is filled by the private keys method.
    public Iterable<Key> keys() {
    	//initialize an arraylist to store the collected keys
        List<Key> keys = new ArrayList<>();
        //call the keys method with the root node and the arraylist to fill the arraylist with keys in the proper order
        keys(root, keys);
        //return the arraylist which is now an iterable of keys in ascending order
        return keys;
    }
    //This method does all the heavy lifting for the public keys method, it is the method that actually creates the iterable of keys in ascending order. To do this, it recursively traverses the tree and adds each key (in ascending order (in-order traversal))to the arraylist.
    private void keys(Node x, List<Key> keys) {
    	//base case; if root node is null either tree is empty or entire tree has been traversed, return
        if (x == null) return;
        //recursively call this method for the node to the left of the root (left subtree)
        keys(x.left, keys);
        //add the key to the arraylist when the furthest left node of each "branch" of the tree is reached
        keys.add(x.key);
        //recursively call this method for the node to the right of the root (right subtree)
        keys(x.right, keys);
    }
    //This method is the public getColor method, so it is the method that is called in the driver to get the color of a given key's tree node and return it as a string. It is essentially just a public call to the private getColor method that actually gets the color of a given key's tree node, it simply calls the private getColor method with the root node and the given key and returns the color it returns.
    public String getColor(Key key) {
        return getColor(root, key);
    }
    //This method does all the heavy lifting for the public getColor method, it is the method that actually gets the color of a given key's tree node as a string. To do this, it recursively searches the tree for the given key and returns its color as a string if it is found, otherwise it returns null.
    private String getColor(Node x, Key key) {
    	//base case; if root node is null either tree is empty or has been searched completely, key was not found, return null
        if (x == null) return null;
        //compare the given key to the key of the current node (the root key), store the value returned by comparison
        int cmp = key.compareTo(x.key);
        //recursively traverse/search the left subtree if the given key is less than the root key
        if (cmp < 0) return getColor(x.left, key);
        //recursively traverse/search the right subtree if the given key is greater than the root key
        else if (cmp > 0) return getColor(x.right, key);
        //key is found, return "Red" if the key's tree node is RED, otherwise "Black" if the key's tree node is BLACK
        else return x.color ? "Red" : "Black";
    }
    /**
     * this method checks if the tree contains a given key
     * @param key to search for
     * @return true if the key is found in the tree, false otherwise
     * @throws N/A
     */
    public boolean contains(Key key) {
    	//sets the root to the returned root of containsRec and calls containsRec for this root and the key given by the input
        return containsRec(root, key);
    }

    /**
     * this method puts in the heavy lifting for the contains method; it recursively traverses the tree to check if the tree contains a given key
     * @param root; node of the tree being checked
     * @param key; the key to search for
     * @return true if the key is found in the subtree rooted at the current node, false otherwise
     * @throws N/A
     */
    private boolean containsRec(Node root, Key key) {
    	//if the root is null, the key is not in the tree
    	if (root == null) {
    		return false;
    	}
    	//instance variable; stores the value of the comparison
    	int comparison = key.compareTo(root.key);
        //if the key matches the current node's key, it's found, return true
        if (comparison == 0) {
            return true;
        } 
        //if the key is less than the current node's key, recursively search the left subtree
        else if (comparison < 0) {
            return containsRec(root.left, key);
        } 
        //if the key is greater than the current node's key, recursively search the right subtree
        else {
            return containsRec(root.right, key);
        }
    }
}


