package Queue;

public class ArrayQueue<T> implements Queue<T> {
    private Object[] myArray = new Object[10];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(T thing){
        if(size == myArray.length){
            // Have to copy over all the old data into a bigger, new list
            // Course it's a circular array, so it's going to be weird
            Object[] newArray = new Object[myArray.length *2 + 1];
            int addIt = 0;
            for(int i = head; i < myArray.length; i ++){
                newArray[addIt] = myArray[i];
                addIt ++;
            }
            for(int i = 0; i < head; i++){
                newArray[addIt] = myArray[i];
                addIt ++;
            }

            myArray = newArray;
            head = 0;
            tail = size;
            add(thing);
        }
        else{
            if(tail == myArray.length){
                tail = 0;
            }
            myArray[tail] = thing;
            tail ++;
            size ++;
        }
    }

    public T peek(){
        if(isEmpty()){
            throw new UnsupportedOperationException();
        }
        return (T)myArray[head];
    }

    public T remove(){
        if(isEmpty()){
            throw new UnsupportedOperationException();
        }
        T myVal = (T)myArray[head];
        if(head == myArray.length - 1){
            head = 0;
        }
        else{
            head += 1;
        }
        size -= 1;
        return myVal;
    }
}