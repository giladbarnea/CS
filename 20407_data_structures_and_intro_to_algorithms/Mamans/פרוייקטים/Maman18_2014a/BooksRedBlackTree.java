/**
 * Red Black Tree - by key bookCode (String).
 * 
 * @author Tamir Aviv 
 * @version 1.0
 */

public class BooksRedBlackTree
{

    private WordNode _root;

    /**
     * Constructor
     */
    public BooksRedBlackTree ()
    {
        _root = null;
    }

    /**
     * Reset root
     */
    public BooksRedBlackTree(WordNode book)
    {
        _root = new WordNode(book);
    }

    /**
     * get -root method
     */
    public WordNode getRoot()
    {
        return _root;
    }

    /**
     * LEFT-ROTATE algorithm which is mentioned in the book page 234.
     * Assumes right son of x is not null.
     */
    public void leftRotate(WordNode x)
    { 
        WordNode y = x.getRightSon();
        x.setRightSon(y.getLeftSon());
        if(y.getLeftSon() != null)
        {
            y.getLeftSon().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null)
        {
            _root = y;
        }
        else if( x == x.getParent().getLeftSon())
        {
            x.getParent().setLeftSon(y);
        }
        else
        {
            x.getParent().setRightSon(y);
        }
        y.setLeftSon(x);
        x.setParent(y);
    }

    /**
     * RIGHT-ROTATE algorithm which is mentioned in the book page 234.
     * Assumes right son of x is not null.
     */
    public void rightRotate(WordNode x)
    { //assumes left son is not null
        WordNode y = x.getLeftSon();
        x.setLeftSon(y.getRightSon());
        if(y.getRightSon() != null)
        {
            y.getRightSon().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null)
        {
            _root = y;
        }
        else if( x == x.getParent().getRightSon())
        {
            x.getParent().setRightSon(y);
        }
        else
        {
            x.getParent().setLeftSon(y);
        }
        y.setRightSon(x);
        x.setParent(y);
    }

    /**
     * RB-INSERT algorithm which is mentioned in the book page 236.
     */
    public void insert(WordNode toInsert)
    { 
        WordNode x = _root;
        WordNode y = null;
        while(x != null){
            y = x;
            if(toInsert.lexicographicBefore(x))
            {
                x = x.getLeftSon();
            }
            else
            {
                x = x.getRightSon();
            }
        }
        toInsert.setParent(y);
        if(y == null)
        {
            _root = toInsert;
        }
        else if(toInsert.lexicographicBefore(y))
        {
            y.setLeftSon(toInsert);
        }
        else
        {
            y.setRightSon(toInsert);
        }

        toInsert.setLeftSon(null);
        toInsert.setRightSon(null);
        toInsert.setColor(1); // red
        insertFixUp(toInsert);

    }

    /**
     * RB-INSERT-FIXUP algorithm which is mentioned in the book page 236.
     */
    private void insertFixUp(WordNode z)
    {
        WordNode y = null;
        while (z != _root && z.getParent().getColor() == 1)
        {
            if(z.getParent() == z.getParent().getParent().getLeftSon())
            {
                y = z.getParent().getParent().getRightSon();
                if(y != null && y.getColor()== 1)
                {
                    z.getParent().setColor(0);
                    y.setColor(0);
                    z = z.getParent().getParent();
                }
                else 
                {
                    if (z == z.getParent().getRightSon())
                    {
                        z = z.getParent();
                        this.leftRotate(z);
                    }
                    z.getParent().setColor(0);
                    z.getParent().getParent().setColor(1);
                    rightRotate(z.getParent().getParent());
                }
            }
            else 
            {
                y = z.getParent().getParent().getLeftSon();
                if(y != null && y.getColor() == 1)
                {
                    z.getParent().setColor(0);
                    y.setColor(0);
                    z = z.getParent().getParent();
                }
                else 
                {
                    if (z == z.getParent().getLeftSon()){
                        z = z.getParent();
                        this.rightRotate(z);
                    }
                    z.getParent().setColor(0);
                    z.getParent().getParent().setColor(1);
                    this.leftRotate(z.getParent().getParent());
                }
            }

        }
        _root.setColor(0);
    }
    

    /**
     * RB-DELETE algorithm which is mentioned in the book page 242.
     */
    public void delete(WordNode z)
    {
        WordNode y = null;
        WordNode x = null;
        if(z.getLeftSon() == null || z.getRightSon() == null)
        {
            y = z;
        }
        else
        {
            y = getSuccessor(z);
        }

        if(y.getLeftSon() != null)
        {
            x = y.getLeftSon();
        }
        else
        {
            x=y.getRightSon();
        }

        if (x != null && y != null)
        {
            x.setParent(y.getParent());
        }

        if(y.getParent() == null)
        {
            _root = x;
        }
        else if(y == y.getParent().getLeftSon())
        {
            y.getParent().setLeftSon(x);
        }
        else
        {
            y.getParent().setRightSon(x);
        }

        if(y != z)
        {
            z.setWord(y.getWord());
        }
        if(y.isBlack())
        {
            deleteFixUp(x);
        }
    }

    /**
     * RB-DELETE-FIXUP algorithm which is mentioned in the book page 243.
     */
    private void deleteFixUp(WordNode x)
    {
        WordNode temp = null;
        while (x != null && x != _root && x.isBlack())
        {
            if (x == x.getParent().getLeftSon())
            {
                temp = x.getParent().getRightSon();
                if (!temp.isBlack())
                {
                    temp.setColor(0); 
                    x.getParent().setColor(1);
                    leftRotate(x.getParent()); 
                    temp = x.getParent().getRightSon(); 
                }

                if (temp.getLeftSon().isBlack()  && temp.getRightSon().isBlack() )
                {
                    temp.setColor(1);
                    x = x.getParent(); 
                }
                else
                {
                    if (temp.getRightSon().isBlack())
                    {
                        temp.getLeftSon().setColor(0); 
                        temp.setColor(1); 
                        rightRotate (temp); 
                        temp = x.getParent().getRightSon(); 
                    }

                    temp.setColor(x.getParent().getColor()); 
                    x.getParent().setColor(0); 
                    temp.getRightSon().setColor(0); 
                    leftRotate (x.getParent()); 
                    x = _root; 
                }
            }
            else
            {
                temp = x.getParent().getLeftSon();
                if (!temp.isBlack())
                {
                    temp.setColor(0); 
                    x.getParent().setColor(1); 
                    rightRotate(x.getParent()); 
                    temp = x.getParent().getLeftSon(); 
                }

                if (temp.getRightSon().isBlack() && temp.getLeftSon().isBlack())
                {
                    temp.setColor(1); 
                    x = x.getParent(); 
                }

                else 
                {
                    if (temp.getLeftSon().isBlack())
                    {
                        temp.getRightSon().setColor(0); 
                        temp.setColor(1); 
                        leftRotate (temp); 
                        temp = x.getParent().getLeftSon(); 
                    }

                    temp.setColor(x.getParent().getColor()); 
                    x.getParent().setColor(0); 
                    temp.getLeftSon().setColor(0); 
                    rightRotate (x.getParent()); 
                    x = _root; 
                }
            }
            x.setColor(0);
        } 
    }

    /**
     * returns the successor WordNode of a certain WordNodeFrom.
     */
    public WordNode getSuccessor(WordNode WordNodeFrom)
    {
        if(WordNodeFrom.getRightSon() != null)
        {
            return getMin(WordNodeFrom.getRightSon());
        }
        WordNode temp = WordNodeFrom.getParent();
        while ((temp != null) && (WordNodeFrom == temp.getRightSon()))
        {
            WordNodeFrom = temp;
            temp = temp.getParent();
        }
        return temp;
    }

    /**
     * Get the minimum Valued WordNode from a given subtree
     */
    public WordNode getMin(WordNode WordNodeFrom)
    {
        while (WordNodeFrom.getLeftSon() != null)
        {
            WordNodeFrom = WordNodeFrom.getLeftSon();
        }
        return WordNodeFrom;
    }

    /**
     * Get the maximum Valued WordNode from a given subtree
     */
    public WordNode getMax(WordNode WordNodeFrom)
    {
        while (WordNodeFrom.getRightSon() != null)
        {
            WordNodeFrom = WordNodeFrom.getRightSon();
        }
        return WordNodeFrom;
    }

    /**
     * search book in the Tree 
     * return the WordNode Node if it exict, and NULL otherwise
     */
    public WordNode treeSearch(String bookCode)
    {
        WordNode temp = _root;
        while (temp != null)
        {
            if (temp.getWord().compareTo(bookCode) < 0)
            {
                temp = temp.getRightSon();
            }
            else if (temp.getWord().compareTo(bookCode) > 0)
            {
                temp = temp.getLeftSon();
            }
            else
            {
                return temp;
            }
        }
        return null;
    }

    /**
     * check who has the book with this bookCode
     */
    public void whoHasThisBook(String bookCode)
    {
        WordNode temp = treeSearch(bookCode);
        if(temp ==null)
        {
            System.out.println("No one have this book:" +bookCode);
        }
        else
        {
            System.out.println("The person with this ID: "+ temp.getID() +" has this book: "+bookCode);
        }
        
    }
    
    /**
     * print the books in the tree from the smallest bookcode to the biggest
     */
    public void printTree()
    {
        WordNode tempWordNode  = getMin(getRoot());
        System.out.print("The books in the tree from the smallest bookcode to the biggest are: ");
        while (tempWordNode != null)
        {
            System.out.print (tempWordNode.getWord() + " -> ");
            tempWordNode = getSuccessor(tempWordNode);
        }
        System.out.println ("end");
    }
}
