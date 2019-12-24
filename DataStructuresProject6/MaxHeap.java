package TheFinalCode;

import java.util.Arrays;
import java.lang.Math;

public class MaxHeap {

  private static final int DEFAULT_CAPACITY = 20;
  private int nextPosition = 0;
  private long[] values;
  
  public MaxHeap() {
    values = new long[DEFAULT_CAPACITY];
  }
  
  public MaxHeap(int initialCapacity) {
    values = new long[initialCapacity];
  }
  
  public MaxHeap(long[] array) {
    this.values = array;
    for (int i = 1; i < values.length; i++) {
      reheapUp(i);
    }
    nextPosition = values.length;
  }

  public void show(){
    System.out.println(Arrays.toString(values));
  }
  
  public void heapsort(long[] array) {
    int end = array.length - 1;

    while(end > 1){
      //Swap the top and the bottom (and stick the top at the end)
      array = heapSortSwapper(array, 0, end);

      end -= 1;

      //Then let the top roll on down
      int lvl = 0;
      int loc = 0;
      while(!nextRowSmaller(array, loc, lvl, end)){
        //swap the node with its largest child and repeat
        int aPos = getChildPos(lvl, loc, 0);
        int bPos = getChildPos(lvl, loc, 1);

        long a = array[aPos];
        long b = array[bPos];

        int pos;

        if(a > b){
          array = heapSortSwapper(array, loc, aPos);
          pos = aPos;
        }
        else{
          array = heapSortSwapper(array, loc, bPos);
          pos = bPos;
        }

        loc = pos;

        lvl += 1;
      }
    }

    //Swap the last two
    array = heapSortSwapper(array, 0, 1);

    //And print for posterity
    System.out.println(Arrays.toString(array));
  }

  //Note: offset should be 0 or 1, depending on if you want the first or second child
  private int getChildPos(int parentLevel, int parent, int offset){
    int space = 2 * (parent - ((int)Math.pow(2, parentLevel) - 1));

    return (space + (int)(Math.pow(2, parentLevel + 1)) - 1 + offset);
  }

  private boolean nextRowSmaller(long[] listy, int itemPos, int level, int end){
    if(getChildPos(level, itemPos, 0) >= end){
      return true;
    }

    long myItem = listy[itemPos];

    //Gives the distance between the beginning of the levelth row and the number
    //Double it for the distance to the first child (added to 2^n+1 - 1,  of course)
    int space = 2 * (itemPos - (int)Math.pow(2, level) + 1);

    long childA = listy[getChildPos(level, itemPos, 0)];
    long childB = listy[getChildPos(level, itemPos, 1)];

    return (myItem > childA) && (myItem > childB);
  }

  private long[] heapSortSwapper(long[] listy, int a, int b){
    //Just going to have to hope the end user knows what they're doing, eh?
    long tmp = listy[a];
    listy[a] = listy[b];
    listy[b] = tmp;
    return listy;
  }
  
  public boolean isEmpty() {
    return nextPosition == 0;
  }
  
  public void add(long value) {
    ensureCapacity();
    values[nextPosition] = value;
    reheapUp(nextPosition);
    nextPosition++;
  }
  
  public long getMax() {
    if (this.isEmpty()) {
      throw new UnsupportedOperationException("Heap is empty.");
    }
    return values[0];
  }
  
  public void remove(long badLong) {
    int a = -1;
    for(int i = 0; i == nextPosition; i ++){
      if(values[i] == badLong){
        a = i;
      }
    }

    if(a == -1){
      throw new UnsupportedOperationException("The item was not in the heap.");
    }

    int b = nextPosition - 1;

    swap(a, b);

    values[b] = 0;

    reheapDown(a);
  }
  
  private void reheapDown(int i) {
    int maxChild = getMaxChildIndex(i);
    if (maxChild > -1) {
      if (values[i] < values[maxChild]) {
        swap(i, maxChild);
        reheapDown(maxChild);
      }
    }
  }
  
  private void reheapUp(int i) {
    int parent = (i - 1) / 2;
    if (parent >= 0) {
      if (values[parent] < values[i]) {
        swap(i, parent);
        reheapUp(parent);
      }
    }
  }
    
  private void ensureCapacity() {
    if (nextPosition >= values.length) {
      long[] temp = new long[values.length * 2 + 1];
      System.arraycopy(values, 0, temp, 0, values.length);
      values = temp;
    }
  }
  
  private void swap(int a, int b) {
    long temp = values[a];
    values[a] = values[b];
    values[b] = temp;
  }

  private int getMaxChildIndex(int i) {
    int left = 2 * i + 1;
    if (left >= nextPosition) {
      return -1;
    } else {
      int right = 2 * i + 2;
      if (right >= nextPosition || values[left] > values[right]) {
        return left;
      } else {
        return right;
      }
    }
  }
}