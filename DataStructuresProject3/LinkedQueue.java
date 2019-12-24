package Queue;

public class LinkedQueue<T> implements Queue<T>{
    private class Node<T>{
        public T value;
        public Node<T> next;
        public Node<T> prev;
    }

    Node head = null;
    Node tail = null;

    public boolean isEmpty(){
        return head == null && tail == null;
    }

    public void add(T thing){
        Node<T> myNewNode = new Node<T>();
        myNewNode.value = thing;
        myNewNode.next = tail;
        if(tail != null) {
            tail.prev = myNewNode;
        }
        else{
            head = myNewNode;
        }
        tail = myNewNode;
    }

    public T peek(){
        if(isEmpty()){
            throw new UnsupportedOperationException();
        }
        return (T)head.value;
    }

    public T remove(){
        if(isEmpty()){
            throw new UnsupportedOperationException();
        }
        T myReturner = (T)head.value;
        head = head.prev;
        if(head == null){
            tail = null;
        }
        else {
            head.next = null;
        }
        return myReturner;
    }
}
