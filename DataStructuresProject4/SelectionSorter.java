package Sorting;

public class SelectionSorter {
    private int[] myArray;
    private int moves = 0;
    private long startTime = 0;
    private long endTime = 0;

    public void init(int[] a){
        myArray = a;
        moves = 0;
    }

    public void sort(){
        startTime = System.nanoTime();

        int dropoff = 0;
        while(dropoff < myArray.length){
            int mIndex = minFromN(dropoff, myArray);
            int temp = myArray[dropoff];
            myArray[dropoff] = myArray[mIndex];
            myArray[mIndex] = temp;
            dropoff ++;
            moves ++;
        }

        endTime = System.nanoTime();
    }

    private int minFromN(int lower, int[] myArray){
        int lowestIndex = lower;
        while(lower < myArray.length){
            if(myArray[lowestIndex] > myArray[lower]){
                lowestIndex = lower;
            }
            lower ++;
            moves ++;
        }
        return lowestIndex;
    }

    public long getSortTime() { return endTime - startTime; }

    public int getMoves() { return moves; }

    public int[] getArray() {return myArray;}

    public void showList() {
        System.out.print('{');
        for(int i = 0; i < myArray.length; i ++){
            System.out.print(Integer.toString(myArray[i]) + ", ");
        }
        System.out.println('}');
    }
}

