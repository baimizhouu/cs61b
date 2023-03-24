public class ArrayDeque<T> {
    private T[] array;
    private double usage_limit = 0.25;
    private int size;
    private int nextLast;
    private int nextFirst;
    public ArrayDeque(T item){
        this.array = (T[]) new Object[8];

        nextFirst = 3;
        nextLast = 4;
        this.addFirst(item);
    }
    public ArrayDeque(){
        this.array = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }
    private int index_increment(int index,int increment){
        if(increment == +1){
            index ++;
            if(index == array.length){
                index = 0;
            }
        } else if (increment == -1) {
            index --;
            if (index == -1){
                index = array.length-1;
            }
        }
        return index;
    }
    public void addFirst(T item){
        if(this.capacity_check()){
            this.size_revise((int) (size/usage_limit-1));
        }
        array[nextFirst] = item;
        this.nextFirst = index_increment(nextFirst,-1);
        size ++;
    }
    public void addLast(T item){
        if(this.capacity_check()){
            this.size_revise((int) (size/usage_limit-1));
        }
        array[nextLast] = item;
        this.nextLast = index_increment(nextLast,+1);
        size ++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int index = index_increment(nextFirst,+1);
        while (index != nextLast){
            System.out.print(array[index] + " ");
            index = index_increment(index,+1);
        }
        System.out.println();
    }
    public T removeFirst(){
        if(size != 0) {
            this.nextFirst = index_increment(nextFirst,+1);
        }
        size -- ;
        if(array.length >= 16) {
            this.usage_check();
        }
        return array[nextFirst];
    }
    public T removeLast(){
        if(size != 0){
            this.nextLast = index_increment(nextLast,-1);
        }
        size -- ;
        if(array.length >= 16) {
            this.usage_check();
        }
        return array[nextLast];
    }
    public T get(int index){
        T item;
        int index_now = nextFirst;
        int i = 0 ;
        while( i <= index){
            index_now = index_increment(index_now,+1);
            i++;
        }
        item = array[index_now];
        return item;
    }
    private  void size_revise(int new_length){
        //Hardest and most important method in this class.
        //1.expand
        if(new_length > array.length){
            T[] new_array = (T[]) new Object[new_length];
            int incre = new_length - array.length;
            System.arraycopy(array,0,new_array,0,nextLast);
            System.arraycopy(array,nextFirst+1,new_array,nextLast+incre,array.length-nextFirst-1);
            nextFirst = nextLast+incre-1;
            array = new_array;
        }
        //2.contract
        if(new_length< array.length){
            T[] new_array = (T[]) new Object[new_length];
            int index_now = index_increment(nextFirst,+1);
            int i = 0;
            while(index_now != nextLast){
                new_array[i] = array[index_now];
                index_now = index_increment(index_now,+1);
            }
            array = new_array;
            nextFirst = new_length-1;
            nextLast = size - 1;
        }
    }
    private void usage_check(){
        if(((double)size/array.length) < usage_limit){
            if(usage_limit < 0.5) {
                this.size_revise(size * 2);
            }else{
                this.size_revise(Math.max(size,(int) ((double)size/usage_limit)));
            }
        }
    }
    private boolean capacity_check(){
        return size == array.length;
    }
}
