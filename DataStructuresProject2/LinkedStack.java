package Stack;

public class LinkedStack<T> implements Stack<T> {
  public class Node<T> {
    private T thing;
    private Node nextNode = null;

    private Node getNext(){
      return nextNode;
    }

    private void setNext (Node n){
      nextNode = n;
    }

    private void setVal (T myIn){
      thing = myIn;
    }

    private T getVal(){ return thing; }
  }

  Node head = null;

  // First, let's push data onto the top
  public void push(T thing){
    Node n = new Node<T>();
    n.setVal(thing);
    n.setNext(head);
    head = n;
  }

  public T pop(){
    if(head == null){
      throw new UnsupportedOperationException();
    }
    else{
      Object myVal = head.getVal();
      head = head.getNext();
      return (T)myVal;
    }
  }

  // I really don't like doing this much casting. It's weirding me out. But I know best. Shhhh, you creepy compiler.
  //          Just hush. I know best.
  public T peek() {
    if(head == null){
      return null;
    }
    else {
      return (T)head.getVal();
    }
  }

  public boolean isEmpty(){
    return head == null;
  }
}