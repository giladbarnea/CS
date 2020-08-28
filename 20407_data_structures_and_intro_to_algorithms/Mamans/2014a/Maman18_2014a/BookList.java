/**
 * This class represents a Book List using linked list (original methods from class WordNode).
 * 
 * @author Tamir Aviv
 * @version 1.0
 */

public class BookList
{
    private WordNode _head;

    /**
     * Constructs
     */  
    public BookList()
    {
        _head = null;

    }

    /**
     * given a bookCode - add a new node to the list.
     */
    public void addToBookList(WordNode bookCode)
    {
        if(_head==null) // if the list is empty - then add the node to the front - and point on him with the _head.
        {
            WordNode p=new WordNode(bookCode); 
            _head=p;
        }
        else
        {
            bookCode.setNext(_head);
            _head = bookCode;
        }
    }

    /**
     * remove book from the list
     */
    public void removeFromBookList(String word)
    {
        if (_head!=null)
        {
            WordNode x= _head;
            WordNode y = _head.getNext();
            
            if(x.getWord() == word)
            {
                _head=y;
            }
            
            while( y!=null)
            {
                if( y.getWord() == word )
                {
                    x.setNext( y.getNext() );
                }
                x=x.getNext();
                y=y.getNext();
            }

        }
    }

    /**
     * return all the list of books
     */
    public String toString()
    {
        WordNode cur; // moving on the list
        String s="";
        for(cur=_head;cur.getNext()!=null;cur=cur.getNext())
        {
            s+= cur+" -> ";
        }  
        s+=cur;
        return s;
    }

    /**
     * get the head of the list.
     */
    public WordNode getHead()
    {
        return _head;
    }
}
