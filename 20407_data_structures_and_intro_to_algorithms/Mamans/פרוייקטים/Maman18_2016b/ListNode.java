
public class ListNode 
{
	private MemberNode _pointer;  //holds a pointer to the relevant MemberNode.
	private ListNode _prev,_next; //pointers.
	
	//Constructors-
	
	/**
	 * Default constructor.
	 */
	public ListNode ()
	{
		this._pointer=null;
		this._prev=this._next=null;
	}
	
	
	/**
	 * constructor, that get a Member node.
	 * It will point to the given member.
	 * @param point - The member node, whode the node will point to.
	 */
	public ListNode(MemberNode point)
	{
		this._pointer = point;
		this._prev=this._next=null;
	}
	
	
	
	//getters-
	
	/**
	 * This method, return the id of the person, which this node points to.
	 * @return - String that contain the id of the member.
	 */
	public String getkey()
	{
		return this._pointer.getKey();
	}//End of getkey.
	
	/**
	 * This method, return the name of the person, which this node points to.
	 * @return - String that contain the name of the member.
	 */
	public String getName()
	{
		return this._pointer.getName();
	}//End of getName.
	
	/**
	 * Gets the next node in the list.
	 * @return - The next node in the list.
	 */
	public ListNode getNext()
	{
		return this._next;
	}//End of getNext.
	
	/**
	 * Gets the previous node in the list.
	 * @return - The previous node in the list.
	 */
	public ListNode getPrev()
	{
		return this._prev;
	}//End of getPrev.
	
	
	//setters-
	
	/**
	 * Sets the next node in the list.
	 * @param next - The next node, to the current one.
	 */
	public void setNext(ListNode next)
	{
		this._next=next;
	}//End of toString.
	
	/**
	 * Sets the previous node in the list.
	 * @param prev - The previous node, to the current one.
	 */
	public void setPrev(ListNode prev)
	{
		this._prev=prev;
	}//End of toString.
	
	/**
	 * Sets the Member which the current node points to.
	 * @param other - The MemberNode node, that the current node will point to.
	 */
	public void setPointer(MemberNode other)
	{
		this._pointer=other;
	}//End of toString.
	
	//Methods-
	
	
	/**
	 * return the Member id, which the node is point to.
	 */
	public String toString()
	{
		return "point to Member: "+ this._pointer.getKey();
	}//End of toString.
	
	
}
