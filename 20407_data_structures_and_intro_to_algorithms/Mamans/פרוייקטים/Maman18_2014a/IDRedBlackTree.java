/**
 * Red Black Tree - by key ID.
 * 
 * @author Tamir Aviv 
 * @version 1.0
 */

public class IDRedBlackTree
{

    private Library _root;


    /**
     * Constructor
     */
    public IDRedBlackTree ()
    {
        _root = null;
    }

    /**
     * Reset root
     */
    public IDRedBlackTree(Library person)
    {
        _root = new Library(person);
    }


    /**
     * get -root method
     */
    public Library getRoot()
    {
        return _root;
    }


    /**
     * LEFT-ROTATE algorithm which is mentioned in the book page 234.
     * Assumes right son of x is not null.
     */
    public void leftRotate(Library x)
    { 
        Library y = x.getRightSon();
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
    public void rightRotate(Library x)
    { //assumes left son is not null

        Library y = x.getLeftSon();
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
    public void insert(Library toInsert)
    { 
        Library z = search(toInsert.getID());
        if( z!=null )
        {
            z.addBook( new WordNode(toInsert.getBooks().getHead().getWord(),toInsert.getID(),null)      );
        }
        else
        {
            Library x = _root;
            Library y = null;
            while(x != null)
            {
                y = x;
                if(toInsert.getID() < x.getID())
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
            else if(toInsert.getID() < y.getID())
            {
                y.setLeftSon(toInsert);
            }
            else
            {
                y.setRightSon(toInsert);
            }

            toInsert.setLeftSon(null);
            toInsert.setRightSon(null);
            toInsert.setColor(1); //red
            insertFixUp(toInsert);
        }
    }

    /**
     * search ID in the Tree 
     * return the Library Node if it exict, and NULL otherwise
     */
    public Library search(int ID)
    {
        Library x = _root;
        while(x != null)
        {
            if(ID < x.getID())
            {
                x = x.getLeftSon();
            }
            else if (ID == x.getID())
            {
                return x;
            }
            else
            {
                x = x.getRightSon();
            }
        }
        return null;
        
    }

    /**
     * RB-INSERT-FIXUP algorithm which is mentioned in the book page 236.
     */
    private void insertFixUp(Library z)
    {
        Library y = null;
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
                    if (z == z.getParent().getLeftSon())
                    {
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
    public void delete(Library z)
    {
        
        Library y = null;
        Library x = null;
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
            z.setID(y.getID());
        }
        if(y.isBlack())
        {
            deleteFixUp(x);
        }
    }

    /**
     * RB-DELETE-FIXUP algorithm which is mentioned in the book page 243.
     */
    private void deleteFixUp(Library x)
    {
        Library temp = null;
        while (x != null && x != _root && x.isBlack())
        {
            if (x == x.getParent().getLeftSon())
            {
                temp = x.getParent().getRightSon();
                if (!temp.isBlack()){
                    temp.setColor(0); 
                    x.getParent().setColor(1);
                    leftRotate(x.getParent()); 
                    temp = x.getParent().getRightSon(); 
                }

                if (temp.getLeftSon().isBlack()  && temp.getRightSon().isBlack() ){
                    temp.setColor(1);
                    x = x.getParent(); 
                }
                else{
                    if (temp.getRightSon().isBlack()){
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
            else{
                temp = x.getParent().getLeftSon();
                if (!temp.isBlack()){
                    temp.setColor(0); 
                    x.getParent().setColor(1); 
                    rightRotate(x.getParent()); 
                    temp = x.getParent().getLeftSon(); 
                }

                if (temp.getLeftSon().isBlack() && temp.getRightSon().isBlack()){
                    temp.setColor(1); 
                    x = x.getParent(); 
                }

                else {
                    if (temp.getLeftSon().isBlack()){
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
     * returns the successor Library of a certain LibraryFrom.
     */
    public Library getSuccessor(Library LibraryFrom)
    {
        if(LibraryFrom.getRightSon() != null)
        {
            return getMin(LibraryFrom.getRightSon());
        }
        Library temp = LibraryFrom.getParent();
        while ((temp != null) && (LibraryFrom == temp.getRightSon()))
        {
            LibraryFrom = temp;
            temp = temp.getParent();
        }
        return temp;
    }

    /**
     * Get the minimum Valued Library from a given subtree
     */
    public Library getMin(Library LibraryFrom)
    {
        while (LibraryFrom.getLeftSon() != null)
        {
            LibraryFrom = LibraryFrom.getLeftSon();
        }
        return LibraryFrom;
    }

    /**
     * Get the maximum Valued Library from a given subtree
     */
    public Library getMax(Library LibraryFrom)
    {
        while (LibraryFrom.getRightSon() != null)
        {
            LibraryFrom = LibraryFrom.getRightSon();
        }
        return LibraryFrom;
    }

    /**
     * check how many books this user have.
     */
    public void howManyBooks(int ID)
    {
        Library x = search(ID);
        if(x==null)
        {
            System.out.println("there is no such person");
        }
        else
        {
            System.out.println("this person: "+ID+" have: "+x.getNumOfBooks()+ " books in his possession");
        }
    }

    /**
     * print the people in the tree from the smallest ID to the biggest.
     */
    public void printTree()
    {
        Library tempLibrary  = getMin(getRoot());
        System.out.print("The people in the tree from the smallest ID to the biggest are: ");
        while (tempLibrary != null)
        {
            System.out.print (tempLibrary.getID() + " -> ");
            tempLibrary = getSuccessor(tempLibrary);
        }
        System.out.println ("end");
    }
}
