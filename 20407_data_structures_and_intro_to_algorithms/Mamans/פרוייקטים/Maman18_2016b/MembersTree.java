
public class MembersTree extends RBTree
{
	//constructors-
	
	/**
	 * Defualt constructor.
	 */
	public MembersTree()
	{
		this.setRoot(this.getRoot());
	}
	
	/**
	 * Constructor, get as a parameter node that will be the root of the created Tree.
	 * @param node - The root of the created tree.
	 */
	public MembersTree(MemberNode node)
	{
		this.setRoot(node);
	}
	
	
	//Methods -
	
	
	/**
	 * This method,insert a Member node to current(this) tree.
	 * @param node - The node been inserted.
	 */
	public void insert(MemberNode node)
	{
		if( this.getRoot()==this.getNill() )//ifNill return true,if the parameter is _nil node.and false -otherwise.
		{
			node.setParent(this.getRoot());
			node.setLeftSon(this.getRoot());
			node.setRightSon(this.getRoot());
			
			this.setRoot(node);
			this.getRoot().setColor(RBNode.Color.BLACK);
		}
		else
		{
			this.RBInsert(node);
		}
	}//End of insert.
	
	
	/**
	 * This method delete the node its key is given as parameter.
	 * Its search the node with the given key,and delete it.
	 * @param key - The key, of the deleted node.
	 */
	public void delete(String key)
	{
		RBNode curr = this.search(key);
		
		this.RBDelete(curr);
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
		super.moveNodeData(dest, source);
		
		MemberNode destM,sourceM;
		
		if( dest instanceof MemberNode && source instanceof MemberNode )
		{
			destM=(MemberNode)dest;
			sourceM=(MemberNode)source;
			
			destM.setName(sourceM.getName());
			destM.setNoOfBooks(sourceM.getNoOfBooks());
			destM.setPtr(sourceM.getptr());
			
			destM.setBooks(destM, sourceM);
			destM.getptr().setPointer(destM);
		}
		else//went worng.
		{
			
		}
		
	}//End of moveNodeData.
	
	
}
