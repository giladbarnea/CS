/**
 * This class builds nodes used in BookList class and BooksRedBlackTree class.
 * this node contain details on the book and his user.
 * 
 * @author Tamir Aviv
 * @version 1.0
 */

public class WordNode
{
    private int color=0; // black is 0 red is 1.
    private WordNode rightSon, leftSon, parent; 

    private String _word;
    private int _ID;
    private WordNode _next;

   
    /**
     * Constructor
     */
    public WordNode(String word,int ID, WordNode n)
    {
        _ID = ID;
        _word = word;
        _next = n;
    }

    /**
     * Copy Constructor
     */    
    public WordNode(WordNode word)
    {
        _ID = word._ID;
        _word = word._word;
        _next = word._next;
    }

    //get Method
    
    /**
     * get the pointer of the node ( the next node in the list )
     */      
    public WordNode getNext()
    { 
        return _next; 
    }
    /**
     * get the word of the node (the bookCode)
     */        
    public String getWord()
    { 
        return _word; 
    }
    /**
     * get the ID of the persone
     */        
    public int getID()
    { 
        return _ID; 
    }
    
    
    public WordNode getRightSon()
    {
        return rightSon;
    }
    public WordNode getLeftSon()
    {
        return leftSon;
    }
    public WordNode getParent()
    {
        return parent;
    }
    public int getColor()
    {
        return color;
    }
    
    //set Method
        
    /**
     * set the pointer of the node ( set to point on the next node in the list)
     */    
    public void setNext(WordNode node)
    { 
        _next = node; 
    }
    /**
     * set the word of the node (the bookCode)
     */    
    public void setWord(String word)
    { 
        _word = word; 
    }
    /**
     * set the ID of the node
     */    
    public void setID(int ID)
    { 
        _ID = ID; 
    }

    public void setRightSon(WordNode toSet)
    {
        rightSon = toSet;
    }
    public void setLeftSon(WordNode toSet)
    {
        leftSon = toSet;
    }
    public void setParent(WordNode toSet )
    {
        parent = toSet;
    }
    public void setColor(int toSet)
    {
        if(toSet == 1 || toSet == 0)
        {
            color = toSet;
        }
    }
    
    
    /**
     * return the bookCode
     */
    public String toString()
    {
        return _word;
    }
    
    /**
     * cheak if the node is Black
     */
    public boolean isBlack()
    {
        return color==0;
    }
    
    
    /**
     * check if the current Node is lexicographic After the other Node
     */
    public boolean lexicographicAfter(WordNode other)
    {
        if(_word.compareTo(other._word)>=0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * check if the current Node is lexicographic Before the other Node
     */
    public boolean lexicographicBefore(WordNode other)
    {
        if(_word.compareTo(other._word)<0)
        {
            return true;
        }
        return false;
    }

}
