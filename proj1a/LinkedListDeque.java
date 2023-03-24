public class LinkedListDeque<T>{
    private class node{
        node p_next;
        node p_last;
        T data;
        public node(node next,node last,T data){
            p_next = next;
            p_last = last;
            this.data = data;
        }
    }
    private int size;
    private node sentinel;
    public LinkedListDeque(T data){
        this.size = 1;
        this.sentinel = new node(null,null,data);
        //This "data" above is meaningless and will not be used.
        sentinel.p_next = new node(sentinel,sentinel,data);
        sentinel.p_last = sentinel.p_next;
    }
    public LinkedListDeque(){
        this.size = 0;
        this.sentinel = new node(null,null,null);
        sentinel.p_next=sentinel;
        sentinel.p_last=sentinel;
    }
    public void addFirst(T data){
        node new_node = new node(sentinel.p_next,sentinel,data);
        sentinel.p_next = new_node;
        new_node.p_next.p_last = new_node;
        size++;
    }
    public void addLast(T data){
        node new_node = new node(sentinel,sentinel.p_last,data);
        new_node.p_last.p_next = new_node;
        sentinel.p_last = new_node;
        size++;
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        if(sentinel.p_next == sentinel){
            return true;
        }else{
            return false;
        }
    }
    public void printDeque(){
        node ptr = sentinel.p_next;
        while(ptr != sentinel){
            System.out.print(ptr.data+" ");
            ptr = ptr.p_next;
        }
        System.out.println();
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        node save = sentinel.p_next;
        save.p_next.p_last = sentinel;
        sentinel.p_next = save.p_next;
        size --;
        return save.data;
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }
        node save = sentinel.p_last;
        save.p_last.p_next =sentinel;
        sentinel.p_last = save.p_last;
        size --;
        return save.data;
    }
    public T get(int index){
       if(index >= size){
           return null;
       }
       int i = 0;
       T data = null;
       node ptr = sentinel.p_next;
       while(i <= index){
           data = ptr.data;
           ptr = ptr.p_next;
           i++;
       }
       return data;
    }
    /*public static void main(String[] args){
        int i = 0;
        LinkedListDeque a = new LinkedListDeque(9);
        while(i<30){
            a.addFirst(i);
            a.addLast(i);
            if( i%10== 0) {
                a.printDeque();
            }
            //a.test_printarray();
            a.printDeque();
            i++;
        }
        a.printDeque();
    }*/
}
