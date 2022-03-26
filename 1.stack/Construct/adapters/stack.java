public class stack {
    private class ListNode{
        int data = 0;
        ListNode next = null;
        
        public ListNode(int data){
             this.data = data;
        }
    }


    private ListNode head = null;
    private ListNode tail = null;
    private int NoOfElements = 0;


    private void addfirst(ListNode node){
        // ListNode node = new ListNode(data);
       if(this.head == null)
       {
           this.head = node;
           this.tail = node;
       }
       else{
           node.next = this.head;
           this.head = node;
       }
    }

    private ListNode removefirst(){
        ListNode rn = this.head;
       if(this.head == this.tail)
       {
           this.head = null;
           this.tail = null;
       }
       else{
           this.head = rn.next;
       }
       return rn.next = null;
    }

    public int size() {
        return this.NoOfElements;
    }

    public boolean isEmpty() {
        return this.NoOfElements == 0;
    }

    protected void StackEmptyException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("StackISEmpty");
    }

    public void push(int data){
        ListNode node = new ListNode(data);
        addfirst(node);
        this.NoOfElements++;
    }

    protected int top_(){
        return this.head.data;
    }

    public int top() throws Exception{
        StackEmptyException();
        return top_();
    }

    public int pop_(){
         ListNode node = removefirst();
         this.NoOfElements--;
         return node.data; 
    }

    public int pop() throws Exception{
        StackEmptyException();
        return pop_();
    }

}