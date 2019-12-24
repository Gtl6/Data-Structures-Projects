package Sorting;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayTest {

  public static int[] createArray(int size) {
    int[] result = new int[size];
    for (int i = 0; i < result.length; i++) {
      result[i] = (int)(Math.random() * 1000);
    }
    return result;
  }
  
  public static boolean isSorted(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] > array[i]) {
        return false;
      }
    }
    return true;
  }

    @Test
    public void testInsertSort() {
        //Insert Sort Test
        InsertionSorter is = new InsertionSorter();
        System.out.println("Merge Sort");
        is.init(createArray(1000));
        is.sort();
        System.out.println(is.getSortTime());
        System.out.println(is.getMoves());
        assertTrue(isSorted(is.getArray()));
    }

    @Test
    public void testMergeSort(){
        //Merge Sort Test
        MergeSorter ms = new MergeSorter();
        System.out.println("Merge Sort");
        ms.init(createArray(1000));
        ms.sort();
        System.out.println(ms.getSortTime());
        System.out.println(ms.getMoves());
        assertTrue(isSorted(ms.getArray()));
    }


  @Test
  public void testSelectionSort() {
      //Selection Sort Test
      SelectionSorter ss = new SelectionSorter();
      System.out.println("Selection Sort");
      ss.init(createArray(1000));
      ss.sort();
      System.out.println(ss.getSortTime());
      System.out.println(ss.getMoves());
      assertTrue(isSorted(ss.getArray()));
  }
}
