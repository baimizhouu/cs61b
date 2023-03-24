public class ArrayDeque<T> {
    private T[] array;
    private double usage_limit = 0.25;
    private int size;
    private int nextLast;
    private int nextFirst;
    public ArrayDeque(){
        this.array=(T[]) new Object[8];
        nextFirst=3;
        nextLast=4;
        size=0;
    }
    private int index_increment(int index,int increment){
        if(increment==1){
            index++;
            if(index==array.length){
                index=0;
            }
        } else if (increment==-1) {
            index--;
            if (index==-1){
                index=array.length-1;
            }
        }
        return index;
    }
    public void addFirst(T item){
        array[nextFirst]=item;
        this.nextFirst=index_increment(nextFirst,-1);
        size++;
        this.capacity_check();

    }
    public void addLast(T item){
        array[nextLast]=item;
        this.nextLast=index_increment(nextLast,+1);
        size++;
        this.capacity_check();
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int index=index_increment(nextFirst,+1);
        while (index!=nextLast){
            System.out.print(array[index]+" ");
            index=index_increment(index,+1);
        }
        System.out.println();
    }
    public T removeFirst(){
        //An error took place here: Remove action imposed on empty list decreased the size.
        if(size==0){
            return null;
        }

        this.nextFirst=index_increment(nextFirst,+1);
        size--;
        T value=array[nextFirst];
        array[nextFirst] = null;
        if(array.length >= 16) {
            this.usage_check();
        }
        return value;
    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        this.nextLast=index_increment(nextLast,-1);
        size--;
        T value=array[nextLast];
        array[nextFirst] = null;
        if(array.length >= 16) {
            this.usage_check();
        }
        return value;
    }
    public T get(int index){

        T item;
        int index_now=index_increment(nextFirst,+1);
        int i=0;
        while(i<index){
            index_now=index_increment(index_now,+1);
            i++;
        }
        if(index_now<array.length&&index_now>=0){
            item=array[index_now];
            return item;
        }
        return null;
    }
    private  void size_revise(int new_length){
        T[] new_array=(T[]) new Object[new_length];
        nextFirst=index_increment(nextFirst,1);
        nextLast=index_increment(nextFirst,-1);
        int i=0;
        int index=nextFirst;
        while(true){
            new_array[i]=array[index];
            i++;
            index=index_increment(index,1);
            if(i==size){
                break;
            }
        }
        array = new_array;
        nextFirst=new_length-1;
        nextLast=size;
    }
    private void usage_check(){
        if(((double)size)/array.length<0.25){
                this.size_revise(size * 2);
        }
    }
    private void capacity_check(){
        if(size>=array.length){
            this.size_revise(size*4);
        }
    }
    private void test_printarray(){
        System.out.print("array: ");
        for(int i = 0;i<array.length;i++){
            System.out.print(array[i]+" ");

        }
        System.out.println();
        System.out.println("first:"+nextFirst+"Last:"+nextLast);
    }
    public static void main(String[] args){
        int i = 0;
        ArrayDeque a = new ArrayDeque();
        while(i<30){
            a.addFirst(i);
            a.addLast(i);
            /*if( i%10== 0) {
                a.printDeque();
            }*/
            a.test_printarray();
            a.printDeque();
            i++;
        }
        a.printDeque();
    }
}
