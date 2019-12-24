package Stack;

import java.util.Arrays;

public class ArrayStack<T> implements Stack<T> {
    private Object[] myStack = new Object[1];
    private int front = 0;

    public boolean isEmpty(){
        return front == 0;
    }

    public T peek(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        else{
            return (T)myStack[front - 1];
        }
    }

    public void push(T thing){
        if(front == myStack.length){
            myStack = Arrays.copyOf(myStack, myStack.length * 2 + 1);
            push(thing);
        }
        else{
            myStack[front] = thing;
            front ++;
        }
    }

    public T pop(){
        if(front == 0){
            throw new UnsupportedOperationException();
        }
        T myObj = peek();
        front --;
        return myObj;
    }
}
