


//*********************************************************************
//* UniversityLibrary.java     Author: Tamir Aviv     ID: 305652000   *
//*                                                                   *
//*           This class Represents the university library            *
//*********************************************************************


public class UniversityLibrary
{
    static private BooksRedBlackTree _Books = new BooksRedBlackTree();
    static private IDRedBlackTree _IDs = new IDRedBlackTree();
    
    public static void main(String[] args)
    {
                
        //first set of testing with 14 people and 25 books
        insertBook("Aviv",307579000,"AL4875");
        insertBook("Rashti",486512348,"AN4897");
        insertBook("Vinkler",754813549,"AX4651");
        insertBook("Berman",164823517,"BR7849");
        insertBook("Venezia",486521567,"BT1643");
        insertBook("Dagan",123481534,"NH3542");
        insertBook("Buzaglo",342587912,"KG2564");
        insertBook("Froim",658143578,"FR6514");
        insertBook("Yosef",654949357,"AB1593");
        insertBook("Raul",156423581,"HT1548");
        insertBook("Nagler",654235318,"CF3516");
        insertBook("Alon",784913548,"DD6481");
        insertBook("Nergal",356849127,"VD1534");
        insertBook("Levy",159167629,"ZY1648");
        insertBook("Aviv",307579000,"ME4678");
        insertBook("Vinkler",754813549,"VY7815");
        insertBook("Venezia",486521567,"AL4879");
        insertBook("Aviv",307579000,"CS4848");
        insertBook("Vinkler",754813549,"EF4675");
        insertBook("Venezia",486521567,"GF4615");
        insertBook("Aviv",307579000,"IJ1531");
        insertBook("Froim",658143578,"JA1537");
        insertBook("Froim",658143578,"LC4897");
        insertBook("Vinkler",754813549,"MT1593");
        insertBook("Venezia",486521567,"AT2582");
        
        
        // printing the current IDs tree structure from smallest node to highest
        System.out.println ("");
        _IDs.printTree();
        
        // printing the current Books tree structure from smallest node to highest
        System.out.println ("");
        _Books.printTree();
        
        
        //checking who has the book with this code
        System.out.println ("");
        _Books.whoHasThisBook("IJ1531");
        
        //checking how many books have to this person
        System.out.println ("");
        _IDs.howManyBooks(307579000);
        
        
        
        // checking who are the people that have the most book in their possession
        System.out.println ("");
        mostBook();
        
        // checking which books are in the possession of this ID
        System.out.println ("");
        whichBooks(307579000);
        
        
        
        // Return books to the University Library
        
        System.out.println ("");
        removeBook("IJ1531");
        mostBook();

        
        // removing people from the University Library
        System.out.println ("");
        removeMember(486521567);
        _IDs.howManyBooks(486521567);
        _Books.printTree();
        
        
        
        /*
        //second set of testing with 10 people and 25 books
        insertMember("Aviv",307579000);
        insertMember("Rashti",486512348);
        insertMember("Vinkler",754813549);
        insertMember("Berman",164823517);
        insertMember("Venezia",486521567);
        insertMember("Dagan",123481534);
        insertMember("Buzaglo",342587912);
        insertMember("Froim",658143578);
        insertMember("Yosef",654949357);
        insertMember("Raul",156423581);
        insertBook("Froim",658143578,"CF3516");
        insertBook("Raul",156423581,"DD6481");
        insertBook("Yosef",654949357,"VD1534");
        insertBook("Raul",156423581,"ZY1648");
        insertBook("Aviv",307579000,"ME4678");
        insertBook("Vinkler",754813549,"VY7815");
        insertBook("Venezia",486521567,"AL4879");
        insertBook("Aviv",307579000,"CS4848");
        insertBook("Vinkler",754813549,"EF4675");
        insertBook("Venezia",486521567,"GF4615");
        insertBook("Aviv",307579000,"IJ1531");
        insertBook("Froim",658143578,"JA1537");
        insertBook("Froim",658143578,"LC4897");
        insertBook("Vinkler",754813549,"MT1593");
        insertBook("Venezia",486521567,"AT2582");
        
        
        // printing the current IDs tree structure from smallest node to highest
        System.out.println ("");
        _IDs.printTree();
        
        // printing the current Books tree structure from smallest node to highest
        System.out.println ("");
        _Books.printTree();
        
        
        //checking who has the book with this code
        System.out.println ("");
        _Books.whoHasThisBook("IJ1531");
        
        //checking how many books have to this person
        System.out.println ("");
        _IDs.howManyBooks(658143578);
        
        
        
        // checking who are the people that have the most book in their possession
        System.out.println ("");
        mostBook();
        
        // checking which books are in the possession of this ID
        System.out.println ("");
        whichBooks(156423581);
        
        
        
        // Return books to the University Library
        
        System.out.println ("");
        removeBook("IJ1531");
        mostBook();
        
        
        // removing people from the University Library
        System.out.println ("");
        removeMember(156423581);
        _IDs.howManyBooks(156423581);
        _Books.printTree();
        
        */
        
        
        
        
        
    }
    
    
    
    /**
     * insert book to the tree.
     * if the person exict - add to the Booklist of this person and add the book to BooksRedBlackTree
     * otherwise - add the book to BooksRedBlackTree, and add the Library Node to the IDRedBlackTree
     */
    public static void insertBook(String lastName,int ID,String book)
    {
        Library x = _IDs.search(ID);
        if(x!=null)
        {
            x.addBook( new WordNode(book,ID,null)  );
            _Books.insert( new WordNode(book,ID,null) ) ;
        }
        else
        {
            Library y = new Library(lastName,ID,book);
            _IDs.insert(y);
            _Books.insert( new WordNode(book,ID,null) ) ;
            
        }
        System.out.println(lastName + ", with the ID: " + ID + ", has borrow a book, whose code is: "+book);
    }
    
    /**
     * remove book to the tree.
     * if it exict - remove from Book list
     * otherwise - do nothing
     */
    public static void removeBook(String book)
    {
        WordNode x =_Books.getRoot();
        int ID;
        Library y;
        while( x!= null)
        {
            if(book.compareTo(x.getWord()) <0)
            {
                x = x.getLeftSon();
            }
            else if (book.compareTo(x.getWord()) >0)
            {
                x = x.getRightSon();
            }
            else //found it;
            {
                ID = x.getID();
                y = _IDs.search(ID);
                String lastName = y.getLastName();
                
                _Books.delete(x);
                y.removeBook(book);

                System.out.println(lastName + ", with the ID: " + ID + ", has returned a book, whose code is: "+book);
                x=null; //break the loop
            }
        }
    }
    
    /**
     * add the new Member to IDRedBlackTree
     * Assumes that he don't exict in the Tree.
     */
    public static void insertMember(String lastName,int ID)
    {
        Library x = new Library(lastName,ID);
        _IDs.insert(x);        
        System.out.println(lastName + ", with the ID: " + ID + ", has join to the University Library");
    }
    
    /**
     * remove Member from the IDRedBlackTree
     * and delete all of his books from BooksRedBlackTree
     */
    public static void removeMember(int ID)
    {
        Library x = _IDs.search(ID);
        if( x!=null)
        {
            String lastName = x.getLastName();
            
            for(WordNode temp = x.getBooks().getHead() ; temp!=null ; temp=temp.getNext() )
            {
                removeBook( temp.getWord() );
            }
            _IDs.delete(x); 
            
            
            System.out.println("\n"+lastName + ", with the ID: " + ID + ", has returned all of his books and remove from the University Library");
            
        }
    }
    
    /**
     * checking who has the most book in the University Library
     */
    public static void mostBook()
    {
        Library tempLibrary  = _IDs.getMin(_IDs.getRoot());
        int mostNumOfBooks=0, curNumOfBooks;
        
        while (tempLibrary != null)
        {
            curNumOfBooks=tempLibrary.getNumOfBooks();
            mostNumOfBooks = Math.max(mostNumOfBooks,curNumOfBooks);
            
            tempLibrary = _IDs.getSuccessor(tempLibrary);
        }
        
        String s="the people with the most number of books are: ";
        tempLibrary = _IDs.getMin(_IDs.getRoot());
        while (tempLibrary != null)
        {
            if(tempLibrary.getNumOfBooks() == mostNumOfBooks)
            {
                s+= tempLibrary.getID() + " -> ";
            }
            
            tempLibrary = _IDs.getSuccessor(tempLibrary);
        }
        s+= "end";
        
        System.out.println(s);

    }
    
    /**
     * check which books are in the possession of this ID
     */
    public static void whichBooks(int ID)
    {
        Library x = _IDs.search(ID);
        if(x!=null)
        {
            x.printAllBooks();
        }
    }
}
