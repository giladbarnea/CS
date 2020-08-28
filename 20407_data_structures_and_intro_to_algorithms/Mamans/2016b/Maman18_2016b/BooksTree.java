
public class BooksTree extends RBTree
{
	
	/**
	 * Defualt constructor.
	 */
	public BooksTree()
	{
		this.setRoot(this.getRoot());
	}
	
	
	/**
	 * Constructor, get as a parameter node that will be the root of the created Tree.
	 * @param node - The root of the created tree.
	 */
	public BooksTree(BookNode node)
	{
		this.setRoot(node);
	}
	
	
	//methods-
	
	/**
	 * This method, insert a book to current(this) tree, by a given code.
	 * It gets a String (code) and insert a node with this code to the current(this) tree.
	 * It uses, insert method, that insert a book node to current tree.
	 * @param code - The new book code.
	 */
	public void insert(String code)
	{
		BookNode added = new BookNode(code);
		this.insert(added);
		
	}//End of insert.
	
	
	/**
	 * This method, insert a book node to current(this) tree.
	 * @param book - The node been inserted.
	 */
	public void insert(BookNode book)
	{
		if( this.getRoot() == this.getNill()  )//ifNill return true,if the parameter is _nil node.and false -otherwise.
		{
			book.setParent(this.getRoot());
			book.setLeftSon(this.getRoot());
			book.setRightSon(this.getRoot());
			
			this.setRoot(book);
			this.getRoot().setColor(RBNode.Color.BLACK);
		}
		else
		{
			this.RBInsert(book);
		}
	}//End of insert.
	
	
	
	/**
	 * This method delete the node its key is given as parameter.
	 * Its search the node with the given key, and delete it.
	 * @param key - The key, of the deleted node.
	 */
	public void delete(String key)
	{
		RBNode book = this.search(key);
		this.RBDelete(book);
	}//End of delete.
	
	
	
	/**
	 * This method, copy all the data from source node to dest node.
	 * It makes sure that all data which points to source node, will point to dest node.
	 * It uses during the deletion of a node in red-black tree.
	 * @param dest - The new node, that replace the old one.
	 * @param source - The old node, been erase.
	 */
	public void moveNodeData(RBNode dest,RBNode source)//source to dest.
	{
		super.moveNodeData(dest, source);//change the key(code).
		
		BookNode destB,sourceB;
		
		if( dest instanceof BookNode && source instanceof BookNode )
		{
			destB=(BookNode)dest;
			sourceB=(BookNode)source;
			
			destB.setMember(sourceB.getMember());//pointer to a member.
			destB.getMember().setBookAddress(sourceB, destB);//changes the book address in the member node.
			
		}
		else//went worng.
		{
			
		}
		
	}//End of moveNodeData.
	
}
