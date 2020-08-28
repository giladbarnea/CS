
public class MemberNode extends RBNode
{
	private String _lastname;
	private final int _maxBooks = 10;                       //max books can borrowed.
	private BookNode[] _books = new BookNode[_maxBooks];  //contain 10 pointers to books.
	private int _noOfBooks;                                 //how many books the member currently read.
	private ListNode _ptr; //point to a node in the list.(in linked list).
	
	
	//constructors-
	
	/**
	 * This constructor is a default one. 
	 */
	public MemberNode()
	{
		super("");
		this._lastname="";
		this._ptr=null;
		this._noOfBooks=0;
		this.initialize_books(this._books);
	}
	
	/**
	 * This constructor takes the id(key) and name of the reader & build a Member node for it.
	 * @param key
	 * @param lastname
	 */
	public MemberNode(String id,String lastname)
	{
		super(id);
		this._lastname=lastname;
		this._noOfBooks=0;
		this._ptr=null;
		this.initialize_books(this._books);
	}
	
	
	
	//getters-
	
	/**
	 * GetName - return the last name of the member.
	 * @return The name of the reader.
	 */
	public String getName()
	{
		return this._lastname;
	}//End of getName.
	
	/**
	 * GetPtr - 
	 * @return The pointer to the linked list node,that points back to it.
	 */
	public ListNode getptr()
	{
		return this._ptr;
	}//End of getptr.
	
	/**
	 * GetNoOfBooks - 
	 * @return - The number of books, that the reader take so far.
	 */
	public int getNoOfBooks()
	{
		return this._noOfBooks;
	}//End of getNoOfBooks.
	
	/**
	 * GetMaxBooks -
	 * @return - The max numbers of books that one can take.final value.
	 */
	public int getMaxBooks()
	{
		return this._maxBooks;
	}//End of getMaxBooks.
	
	
	
	//setters-
	
	/**
	 * This method, sets the last name of the member.
	 * @param name - The new name of the member.
	 */
	public void setName(String name)
	{
		name=name.trim();
		this._lastname=name;
		
	}//End of setName.
	
	/**
	 * SetNoOfBooks -
	 * This method, sets the number of books that a current reader take from the library.
	 * @param num - The new number of books.
	 */
	public void setNoOfBooks(int num)
	{
		if( num >=0 && num <= this._maxBooks)
		{
			this._noOfBooks=num;
		}
		
	}//End of setNoOfBooks.
	
	
	/**
	 * This method, changes one of the book pointers, that the _books array stores.
	 * It Search the element in the array that point to a particular book ('from' parameter).
	 * And make it point to another book node('to' parameter).
	 * It uses if the book node, changes its address.
	 * @param from - The old book node.
	 * @param to - The new book node.
	 */
	public void setBookAddress(BookNode from,BookNode to)
	{
		//Address. from the first to the second.
		//make the member to point a different book node.
		
		int index=0;
		for( ; (index < this._noOfBooks) && (this._books[index] != from) ; index++)
			;
		
		if( (index < this._noOfBooks) && (this._books[index] == from) )
			this._books[index]=to;
			
	}//End of setBookAddress.
	
	
	/**
	 * This method, copy the books from other member to dest Member.
	 * After this method, the dest member _books array will contain pointers to all other member borrowed books.
	 * And all the books will point to dest member node.Meaning the Books will point to dest member node.
	 * @param dest - The destination of the books. 
	 * @param other - The former member that borrowed the books.
	 */
	public void setBooks(MemberNode dest,MemberNode other)
	{
		for(int index=0 ; index < other.getNoOfBooks() ; index++)
		{
			dest._books[index] = other._books[index];
			other._books[index].setMember(dest);
		}
	}//End of setBooks.
	
	
	/**
	 * This method, sets the pointer to linked list node,to other.
	 * @param other - The new listNode.
	 */
	public void setPtr(ListNode other)
	{
		this._ptr = other;
	}//End of setPtr.
	
	
	//methods-
	
	
	
	/**
	 * This method, insert a new book to _books array of current(this) member.
	 * Meaning the member borrow a new book.
	 * Only if one, can borrow more books.
	 * @param other - The new borrowed book. 
	 */
	public void insertBook(BookNode other)
	{
		if(this._noOfBooks>this._maxBooks)
		{
			//cant added. reader take the max number of books already.
			return;
		}
		
		//when insert a books,it will be insert to the former index.(because cell 0).
		//and only than the noOfbooks will be the proper number.
		//this results that the index is always one step ahead.
		
		this._books[this._noOfBooks] = other;
		this._noOfBooks++;
	}//End of insertBook.
	
	
	/**
	 * This method, extract a book from the _books array.
	 * Meaning the member return a book to the library.
	 * The method, don't change the book pointer or the list node.
	 * used when a member return a book.
	 * @param other - The book been returned.
	 */
	public void extractBook(BookNode other)
	{
		//the index will point to one cell ahead.
		//need to reduce it by one and than delete.
		int index=0;
		
		if(this._noOfBooks > 0)
			this._noOfBooks--;//it will point to the last book in the array.
		
		for( ; (index <= this._noOfBooks) && (this._books[index] != other ) ; index++ )
			;
		
		if( (index <= this._noOfBooks) && (this._books[index]==other) )
		{
			this._books[index]=this._books[this._noOfBooks];//index is the element index in the array,that hold the wanted book.
			this._books[this._noOfBooks]=null;
		}
	}//End of extractBook.
	
	
	/**
	 * This method, prints all the Member's borrowed books.
	 * @return - A string, contain all the Member's borrowed books.
	 */
	public String printBooks()
	{
		String str="Books of Member:"+ this._lastname + ",id:" + this.getKey() +" is:\n";
		
		for(int index=0; index < this._noOfBooks ; index++)
		{
			//if(this._books[index].getMember() == this)
			str += this._books[index].getKey() + "\t";
		}
		
		return str+"\n****\n";
	}//End of printBooks.
	
	
	/**
	 * This method, initialize the _books array elements to null.
	 * @param books - The array been initialize.
	 */
	public void initialize_books(BookNode[] books)
	{
		for(int index = 0 ; index < this._maxBooks-1 ; index++)
		{
			this._books[index] = null;
		}
	}//End of initialize_books.
	
	
	
	/**
	 * MemberNode toString method.
	 * Prints the member name and id.
	 */
	public String toString()
	{
		return "Member name: "+this._lastname+", Member ID: "+this.getKey() ;
	}//End of toString.
	
	
	
}
