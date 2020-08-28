
public class DB 
{
	
	private MembersTree _members;               
	private BooksTree _books;                   
	private DoublyLinkedList[] _list;   
	private final int _maxBooks=10;
	
	
	//constructors-
	
	/**
	 * This constructor is a default one. 
	 */
	public DB()
	{
		this._members = new MembersTree();
		this._books = new BooksTree();
		this._list = new DoublyLinkedList[this._maxBooks+1];//plus one for 10 books.
		this.initialize_list(this._list);
	}
	
	
	
	//Methods -
	
	
	/**
	 * This method control the flow of the Data Base structure.
	 * It calls the right methods according the input String,represent by str String parameter.
	 * @param str - The input String that by it,the method decide to call the appropriate method. 
	 */
	public void dbDriver(String str)
	{
		int index=0;
		String name="",id="",code="",temp="";
		str = str.trim();
		String[] input = str.split("\\s+");
		
		System.out.println("dbDriver: |\""+str+"\"|");
		
		if(str.charAt(index)=='?')//if true, the str represent some query.
		{
			//System.out.println("dbDriver: ? .\n");
			
			index++;//index is now 1,to check the second char and decide by it, which query been asked,by the user.
			
			if(str.charAt(index)=='!')//call the mostBooks query (method).
				this.mostBooks();
			else if(Character.isUpperCase(str.charAt(index)) && ( Character.isUpperCase(str.charAt(index+1)) ) )//index is 1.
			{												   
				//the first two characters in the substring(after '?') is an UpperCase.
				code=str.substring(index);//index is 1. put in "code" the remaining of the String, without '?'.
				//code will contain something like:"AX1234". 
				
				code=code.trim();
				
				if( code.substring(index+1).matches("\\d+") )//true if the code substring contain only digits.
				{
					//get to here if the two first characters is upper case and after it, only digits.
					this.readBook(code);//call the readBook query (method).
				}
				else//after the two upper case characters, the code is illegal.
				{
					System.out.println("dbDriver: the book code illegal.");
				}
			}
			else if(Character.isDigit(str.charAt(index)))//call the booksOfReader query (method).
			{
				id = str.substring(index);
				id = id.trim();
				
				if(id.matches("\\d+"))//if only contain digits !.
				{
					this.booksOfMember(id);
				}
				else//the id contain non-digits.
				{
					System.out.println("dbDriver: id contain some non-digits.");
				}
			}
			else
			{
				System.out.println("dbDriver: Something went worng.");
			}
		}
		else if( (str.charAt(index) == '+') || ( str.charAt(index) == '-' ) )//adding reader or remove reader operation.
		{
			index++;
			name=input[index++];
			id=input[index];
			name=name.trim();
			id=id.trim();
			index=0;
			
			//System.out.println("dbDriver: +/- at first char. \n");
			
			if( str.charAt(index) == '+' )//adding member.
			{
				//System.out.println("dbDriver: adding member.");
				if(id.matches("\\d+"))
				{
					//checking only the id.
					this.addMember(id,name);
				}
				else
				{
					System.out.println("dbDriver: id contain some non-digits.");
				}
			
			}
			else if(str.charAt(index) =='-')//remove member.
			{
				//System.out.println("dbDriver: remove member.");
				if(id.matches("\\d+"))
				{
					//checking only the id.
					this.removeMember(id, name);
				}
				else
				{
					System.out.println("dbDriver: id contain some non-digits.");
				}
			}
			
		}
		else
		{
			//Borrow or Returning a book to the library.last operation possible,
			//thus not checking the +/- sign, before checking the id,name and code.
			index=0;
			
			name = input[index++];
			id = input[index++];
			code = input[index];
			
			name = name.trim();
			id = id.trim();
			code = code.trim();
			str = str.trim();
			
			index=0;
			
			if( (id.matches("\\d+")) && ( Character.isUpperCase(code.charAt(index++)) ) && ( Character.isUpperCase(code.charAt(index++)) )   )
			{
				//Here, if the id contain only digits and the first two characters in code is upper case.
				temp=code.substring(index);
				temp=temp.trim();
				
				if( temp.matches("\\d+") )//check if after the two characters of code,it contain only digits.
				{
					index = str.length()-1;//the last char. needed to be '-' for returning a book.'+' for borrow a book.
					
					if( str.charAt(index)=='+' )//Borrow a book.
					{
						//System.out.println("dbDriver calling borrowBook");
						this.borrowBook(name, id, code);
					}
					else if(str.charAt(index)=='-')//returning a book.
					{
						//System.out.println("dbDriver calling returnBook");
						this.returnBook(name, id, code);
					}
						
				}
			}
			else//the id contain some non-digits or the code first two characters isn't upper case.
			{
				System.out.println("dbDriver: id contain some non-digits.");
			}
			
		}
		
	}//End of dbDriver.
	
	
	
	
	/**
	 * This method insert a book to the current data base structure, only if it isn't exist already in it.
	 * @param code - The code of the new book,been inserted.
	 */
	public void addBook(String code)
	{
		code=code.trim();
		
		BookNode book = new BookNode(code);
		
		if( this._books.search(code) == this._books.getNill() )//if true - didnt find this book code. else - false.
		{
			this._books.insert(book);
		}
		
	}//End of addBook.
	
	
	/**
	 * This method, erase a book from this data base structure, with the given code, as parameter.
	 * @param code - The deleted book code.
	 */
	public void deleteBook(String code)
	{
		code=code.trim();
		
		if( this._books.search(code) != this._books.getNill() )//true, if search for it and find not nil.
		{
			this._books.delete(code);
		}
	}//End of deleteBook.
	
	
	/**
	 * This method, add a reader to the data base, only if he isn't exist already in it.
	 * @param id - The new reader id.
	 * @param name - The new reader name.
	 */
	public void addMember(String id,String name)
	{
		id=id.trim();
		name=name.trim();
		
		MemberNode member = new MemberNode(id,name);//create a new node that holds the new reader.
		
		if( this._members.search(id) == this._members.getNill() )//enter only if didn't find this id .
		{

			ListNode node = new ListNode(member);//create a new node in the linked-list for this reader.				
												 //will not been erase from memory until the reader will leave the library.									 
			 									 //Erase, in DoublyLinkedList only remove it from current position & return it.
			
			
			member.setParent(this._members.getNill());
			member.setRightSon(this._members.getNill());
			member.setLeftSon(this._members.getNill());
			
			member.setPtr(node);//sets the pointer of member to the node.
			
			this._members.insert(member);//insert the member node to the members tree.
			
			//insert list node to first empty slot in the array of the DB!.not in member node.
			this._list[member.getNoOfBooks()].insertNodeDLL(node);
		}
		else
		{
			System.out.println("Something went wrong");
		}
		
		//System.out.println("addReader: end.\n");
	}//End of addMember.
	
	
	
	/**
	 *  This method, remove a member from the data base, only if he returned all his borrowed books!..
	 * @param id - The removable member id.
	 * @param name - The removable reader name.
	 */
	public void removeMember(String id,String name)
	{
		//System.out.println("removeMember:start");
		
		MemberNode member ;
		RBNode temp ;
		
		id=id.trim();
		name=name.trim();
		
		temp = this._members.search(id);//temp will hold the member, if exist. if not - _nil.
		
		if(  (this._members.getNill() != temp) && ( temp instanceof MemberNode ) )//checks if find it.maybe temp _nil. if _nil-true.
		{
			//here, if temp is not nill, meaning found a node with the key(id).
			member=(MemberNode)temp;
			
			if( (member.getName().equals(name)) && (member.getNoOfBooks()==0 ))//If the name match and 0 books borrow: true. else false. 
			{
				this._list[member.getNoOfBooks()].deleteNodeDLL(member.getptr());//delete the node from the linked list.
				
				this._members.RBDelete(member);//delete the member.
			}
			else//if member name doesn't match or he didn't return all his borrowed books.
			{
				System.out.println("Member name doesn't match his id.");
			}
		}
		else//something went wrong.
		{
			System.out.println("Something went wrong");
		}
		
		//System.out.println("removeMember :" + member);
		//System.out.println("removeMember:end");
		
	}//End of removeMember.
	
	
	
	/**
	 * This method, adds a new borrowed book to a member who borrowed it.
	 * It stores a pointer to the book node at the _books array, at the member node.
	 * @param name - The name of the member, who borrowed the book.
	 * @param id - The id of the member, who borrowed the book.
	 * @param code - The code of the book, been borrowed, by the member.
	 */
	public void borrowBook(String name,String id,String code)
	{
		int index=0;
		ListNode listnode = null;
		BookNode book = null;
		MemberNode member = null;
		RBNode tempbook=null,tempmember=null;
		
		name = name.trim();
		id = id.trim();
		code = code.trim();
		
		tempbook = this._books.search(code);
		tempmember = this._members.search(id);
		
		//System.out.println("borrowBook: The book found is: " + tempbook.getKey());
		
		if( id.equals(tempmember.getKey()) && (code.equals(tempbook.getKey())) && (tempbook instanceof BookNode) && (tempmember instanceof MemberNode)  )//meaning, find both.
		{
			//if one of the two not found, it will point to nil. nil will never contain a key.only empty string.
			//Here, if all is good.
			book = (BookNode)tempbook;
			member = (MemberNode)tempmember;
		}
		else
		{
			if( tempbook instanceof BookNode && (code.equals(tempbook.getKey())) )
				System.out.println("Sorry: Member not found !.");
			else
				System.out.println("Sorry: Book not found !.");
			
			return;
		}
		
		
		if(member.getName().equals(name))//if the name fit the id in the member node to the name given as parameter.
		{
			if(book.getMember() == null)//if the book is not read by another member,the pointer will be null.return true.
			{
				if(member.getNoOfBooks() < member.getMaxBooks())//if the number of books is between 0 to 10(max).
				{
					index = member.getNoOfBooks();//return the numbers of books. pay attention that it stores in index-1;
					
					member.insertBook(book);//add the book to the members loaned books array _books.
											//The private variable _noOfBooks is added 1.
					
					book.setMember(member);//the book will point to the member node.
					
					//by now the book added to the _books array in the member node & the book points to the member.
					//Also the _noOfBooks grow by 1.
					//need to change the place in the linked list-
					
					listnode = member.getptr();//listnode will point to the match node in the list.
					
					listnode = this._list[index].deleteNodeDLL(listnode);//delete this node, from the list.
					
					index = member.getNoOfBooks();
					
					this._list[index].insertNodeDLL(listnode);
					
					//And by now the place in the linked list as been changed. 
				}
				else
				{
					System.out.println("Sorry: Member cant borrow more books!.");
				}
			}
			else
			{
				System.out.println("Sorry: Book not available!.");
			}
		}
		else
		{
			System.out.println("Sorry: Member name isnt match his id!.");
		}
	}//End of loanbook.
	
	
	
	/**
	 * This method, delete a borrowed book from a member who borrowed it.
	 * It updates the number of books one borrowed and erase the pointer to the book node at the _books array, at the member node.
	 * It makes sure, that the book node will not point any more to the member and updates his list node, 
	 * to the correct place in _list array.
	 * 
	 * @param name - The name of the member, who return the book.
	 * @param id - The id of the member, who return the book.
	 * @param code - The code of the book, been returned, by the member. 
	 */
	public void returnBook(String name,String id,String code)
	{
		int index=0;
		ListNode listnode=null;
		BookNode book = null;
		MemberNode member = null;
		RBNode tempbook=null,tempmember=null;
		
		name=name.trim();
		id=id.trim();
		code=code.trim();
		
		tempbook = this._books.search(code);
		tempmember = this._members.search(id);
		
		if( id.equals(tempmember.getKey()) && (code.equals(tempbook.getKey())) && (tempbook instanceof BookNode) && (tempmember instanceof MemberNode)  )//meaning, find both.
		{
			//if one of the two not found, it will point to nil. nil will never contain a key.only empty string.
			//Here, if all is good.
			
			book = (BookNode)tempbook;
			member = (MemberNode)tempmember;
		}
		else
		{
			if( tempbook instanceof BookNode && (code.equals(tempbook.getKey())) )
				System.out.println("Sorry: Member not found !.");
			else
				System.out.println("Sorry: Book not found !.");
			
			return;
		}
		
		if(member.getName().equals(name))//if the name is equal.
		{
			if(member.getNoOfBooks() > 0)//if the member read 1 book or more.
			{
				if(book.getMember() == member)//if the book is read by this member.
				{
					index = member.getNoOfBooks();//get the current number of books.
					
					member.extractBook(book);//the book erase from the books array of the member.
											 //change the NoOfBooks Value.
					
					book.setMember(null);    //change the pointer of book to null.
					
					listnode = member.getptr();//get the linked list node, that point to the member. needed to move to lower index.
					
					listnode = this._list[index].deleteNodeDLL(listnode);//erase the node from its current position.
					
					index = member.getNoOfBooks();
					
					this._list[index].insertNodeDLL(listnode);//insert the node in its proper index list.
				}
				else//the book is not read by current member. 
				{
					System.out.println("Sorry: The book is not read current member!.");
				}
			}
			else//member do not read any book at this time.
			{
				System.out.println("Sorry: Member do not read any book at this time!.");
			}
		}
		else//the name given as parameter(in the command line) is not equal to the name of the current member.
		{
			System.out.println("Sorry: Member id doesn't equal to the inserted id!.");
		}
		
	}//End of returnBook.
	
	
	
	/**
	 * This method, prints all the books, that the member with the given id(as parameter) borrowed.
	 * Meaning, prints all the borrowed books, by member with the given id.
	 * @param id - The member id, who his borrowed books will get printed. 
	 */
	public void booksOfMember(String id)
	{
		String str="";
		RBNode temp=null;
		MemberNode member=null;
		
		temp = this._members.search(id);
		
		if(temp != this._members.getNill() && temp instanceof MemberNode)
		{
			member=(MemberNode)temp;
			
			str = member.printBooks();
			
			System.out.println(str);
		}
		else//didnt find the id.
		{
			System.out.print("**\nbooks method in DB didnt find any books.id:"+id + "\n\n**");
		}
	}//booksOfMember
	
	
	
	
	/**
	 * This method, prints the member details, who borrow the book with the given code(as parameter). 
	 * @param code - The code of the book, that its borrowed member details will printed.
	 */
	public void readBook(String code)
	{
		String str="The book code:"+ code +"," ;
		RBNode temp=null;
		BookNode book=null;
		
		temp=this._books.search(code);
		
		if(temp != null && temp instanceof BookNode)
		{
			book=(BookNode)temp;
			
			if(book.getMember() != null)//if the member exist.
				str = str + "is read by: " + book.getMemberName() + ",id: " + book.getMemberID() + ".\n\n";
			else//if the member pointer is null.AKA no one reads this book.
				str = str + "is not read by any reader.\n\n";
				
		}
		else//if the book is not exist.
		{
			str = str + "is not exist.\n\n";
		}
		
		System.out.print(str);
	}//End of readBook.
	
	
	
	
	
	/**
	 * This method search in _list array the biggest element that is not empty,and print the details of the members in it.
	 * _list array is contain 11 linked lists,that each one contain all the members in the Data Base.
	 * Order by the number of books each one take from the library.
	 * 
	 * Meaning: in element indexed 0, there is a linked list that contain all the members that doesnt take any book.
	 * in element indexed 1, there is a linked list that contain all the members that take only 1 book.
	 * in element indexed 10, there is a linked list that contain all the members that take 10 books.
	 */
	public void mostBooks()
	{
		int index = this._list.length-1;
		
		for(  ; (index >= 0) &&  (this._list[index].getHead()==null) ; index-- )
			;
		
		if( (index >= 0) && (this._list[index].getHead() != null) )
		{
			System.out.println("Most Books by:");
			this._list[index].printList();
			System.out.println("each of them, borrow "+index+" books");
		}
			
	}//End of mostBooks.
	
	
	/**
	 * This method, initialize the array of the Doubly Linked List array, _list.
	 * @param list
	 */
	public void initialize_list(DoublyLinkedList[] list)
	{
		for(int index = 0 ; index < this._list.length ; index++)
		{
			this._list[index] = new DoublyLinkedList();
		}
	}//End of initialize_list.
	
	
	
	/**
	 * This method, prints the structure Members tree, in order.
	 */
	public void printMemberTree()
	{
		System.out.println("printMemberTree");
		this._members.printInOrder(this._members.getRoot());
	}//End of MemberTreePrint.
	
	
	/**
	 * This method, prints the structure Books tree, in order.
	 */
	public void printBooksTree()
	{
		System.out.println("printBooksTree");
		this._books.printInOrder(this._books.getRoot());
	}//End of BooksTreePrint.
	
	
	//---------------------------------------------------------------------------------------------
	
}
