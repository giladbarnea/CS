
/**
 * this Node contain details on the user and his books
 * 
 * @author Tamir Aviv 
 * @version 1.0
 */
public class Library
{
    private int color=0; // black is 0 red is 1.
    private Library rightSon, leftSon, parent; 

    private String _lastName;
    private int _ID;
    private BookList _Books = new BookList();
    private int _numOfBooks;

    final int MAX_NUM_OF_BOOKS = 10;

    /**
     * Constructor
     */
    public Library(String lastName, int ID)
    {
        _lastName = lastName;
        _ID = ID;
        _numOfBooks = 0;
    }

    /**
     * Constructor
     */
    public Library(String lastName, int ID,String bookCode)
    {
        _lastName = lastName;
        _ID = ID;
        _Books.addToBookList(new WordNode(bookCode,ID,null));
        _numOfBooks=1;

    }

    /**
     * Copy Constructor
     */
    public Library(Library person)
    {
        _lastName=person._lastName;
        _ID = person._ID;
        _Books = person._Books;
        _numOfBooks = person._numOfBooks;

    }

    // Get Method
    
    public Library getRightSon()
    {
        return rightSon;
    }
    public Library getLeftSon()
    {
        return leftSon;
    }
    public Library getParent()
    {
        return parent;
    }
    public int getColor()
    {
        return color;
    }

    public String getLastName()
    {
        return _lastName;
    }
    public int getID()
    {
        return _ID;
    }
    public BookList getBooks()
    {
        return _Books;
    }
    public int getNumOfBooks()
    {
        return _numOfBooks;
    }

    // Set Method

    public void setColor(int toSet)
    {
        if(toSet == 1 || toSet ==0)
        {
            color = toSet;
        }
    }
    public void setRightSon(Library toSet)
    {
        rightSon=toSet;
    }
    public void setLeftSon(Library toSet)
    {
        leftSon=toSet;
    }
    public void setParent(Library toSet)
    {
        parent=toSet;
    }
    
    public void setLastName(String lastName)
    {
        _lastName=lastName;
    }
    public void setID(int ID)
    {
        _ID=ID;
    }


    /**
     * add book to the list of Books, and add one to numOfBooks
     */
    public void addBook(WordNode toAdd)
    {
        if(_numOfBooks<MAX_NUM_OF_BOOKS)
        {
            _Books.addToBookList(toAdd);
            _numOfBooks++;
        }
        else
        {
            System.out.println("You've Lending 10 books, you can not borrow more books");
        }
    }
    
    /**
     * remove book from the list of Books, and decrease one to numOfBooks
     */
    public void removeBook(String bookCode)
    {
        if(_numOfBooks>0)
        {
            _Books.removeFromBookList(bookCode);
            _numOfBooks--;
        }
        else
        {
            System.out.println("You've No books that you need to return");
        }
    }
    
    /**
     * print the list of books.
     */
    public void printAllBooks()
    {
        if(_Books!=null)
        {
            String s="The person with this ID: "+ _ID +" has this books: ";
            s+=_Books.toString();
            System.out.println(s);
        }
    }
    
    /**
     * cheak if the node is Black
     */
    public boolean isBlack()
    {
        return color==0;
    }

}
