
public class BookNode extends RBNode
{
	private MemberNode _pReader;//points to the reader node.
	
	
	//constructors-
	
	/**
	 * Default constructor.
	 */
	public BookNode()
	{
		super();
		this._pReader=null;
	}
	
	/**
	 * constructor, get a String "key",that will be the key of the created node to the tree.
	 * @param key - The key of the created node.
	 */
	public BookNode(String key)
	{
		super(key);
		this._pReader=null;
	}
	
	
	
	//Getters -
	
	/**
	 * GetMember, return the member who borrow the current(this) book.
	 * @return - The member who borrow the current(this) book. null, if no member borrow it.
	 */
	public MemberNode getMember()
	{
		return this._pReader;
	}//End of getMember.
	
	/**
	 * GetMemberName, return the member last name.
	 * @return - The member last name,who borrow the current book.empty String if no member borrow it.
	 */
	public String getMemberName()
	{
		if(this._pReader!=null)
			return this._pReader.getName();
		return "";
	}//End of getMemberName.
	
	/**
	 * GetMemberID, return the member ID, who borrow the current book.
	 * @return - The member ID , who borrow the current book.empty String if no member borrow it.
	 */
	public String getMemberID()
	{
		if(this._pReader!=null)
			return this._pReader.getKey();
		return "";
	}//End of getMemberID.
	
	
	//Setters-
	
	/**
	 * SetMember, sets the member who borrow the current(this) book.
	 * @param other
	 */
	public void setMember(MemberNode other)
	{
		this._pReader=other;
	}//End of setMember.
	
	
}
