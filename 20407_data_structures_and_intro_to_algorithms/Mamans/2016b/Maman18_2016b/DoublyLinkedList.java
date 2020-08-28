
public class DoublyLinkedList 
{
	private ListNode _head;
	
	//constructors-
	
	/**
	 * Default constructor.
	 */
	public DoublyLinkedList()
	{
		this._head = null;
	}
	
	
	//Getters-
	/**
	 * This method, return the head of the current linked list.
	 * @return - The head of the current linked list
	 */
	public ListNode getHead()
	{
		return this._head;
	}//End of getHead.
	
	
	//Methods-
	
	/**
	 * This method, insert a node to a Doubly Linked List (DLL).
	 * @param node - The node who inserted.
	 */
	public void insertNodeDLL(ListNode node)
	{
		if(this._head==null)
		{
			this._head = node;
			node.setNext(null);
			node.setPrev(null);
		}
		else
		{
			//insert to the beginning of the list.
			node.setPrev(null);
			node.setNext(this._head);
			this._head.setPrev(node);
			this._head=node;
		}
		
	}//End of insertNodeDLL.
	
	
	/**
	 * This method, delete a node from a Doubly Linked List (DLL).
	 * Return it, for a future use.(If you want to move it to other list).
	 * 
	 * @param node - The node to deleted.
	 * @return - The node that deleted from the list.
	 */
	public ListNode deleteNodeDLL(ListNode node)
	{
		if(node==this._head)//if this is the only node in the list.
		{
			if(node.getNext()==null)
			{
				this._head.setNext(null);
				this._head.setPrev(null);
				this._head=null;
			}
			else
			{
				this._head=this._head.getNext();
				this._head.setPrev(null);
			}
			
		}
		else
		{
			if(node.getNext() == null)//if node is the last node of the linked list.
			{
				node.getPrev().setNext(null);
				node.setPrev(null);
			}
			else//if the node is in the middle of the list.
			{
				node.getNext().setPrev(node.getPrev());
				node.getPrev().setNext(node.getNext());
			}
		}
		
		return node;
	}//End of deleteNodeDLL.
	
	
	/**
	 * This method prints the all list.
	 */
	public void printList()
	{
		String str="";
		
		for(ListNode curr=this._head ; curr != null ; curr=curr.getNext() )
		{
			str = str + "Name: " + curr.getName() +", ID: " + curr.getkey() +" .\n";
		}
		System.out.print(str);
	}//End of printList.
	
	
}
