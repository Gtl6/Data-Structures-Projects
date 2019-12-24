package TheFinalCode;

public class testDoc {
    public static void main(String[] args){
        MaxHeap myHeap = new MaxHeap();

        // So the heap has a function that takes in a list of longs and 'Heapsort's it
        long[] myList = new long[]{64, 63, 30, 52, 50, 29, 28, 36, 34, 22, 35, 27, 25, 15, 14, 13, 12, 11, 10, 9, 8,
                                    7, 6, 5, 4, 3, 2};
        myHeap.heapsort(myList);


        StringTreeSet myST = new StringTreeSet();
        myST.add("M");
        myST.add("E");
        myST.add("B");
        myST.add("A");
        myST.add("C");
        myST.add("G");
        myST.add("F");
        myST.add("S");
        myST.add("P");
        myST.add("V");
        myST.add("R");
        myST.add("T");
        myST.add("Z");

        StringIterator mSI = myST.iterator();

        while(mSI.hasNext()){
            System.out.println(mSI.next());
        }
    }
}