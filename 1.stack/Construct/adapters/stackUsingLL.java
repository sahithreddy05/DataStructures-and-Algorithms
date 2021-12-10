import java.util.LinkedList;
public class stackUsingLL {

    private LinkedList<Integer> ll = new LinkedList<>();

    public void push(int data)
    {
        ll.addFirst(data); //push_front(data);
    }

    public int top(int data){ // front();
        return ll.getFirst();
    }

    public int pop(){
        return ll.removeFirst(); //pop_front();
    }
    
}
