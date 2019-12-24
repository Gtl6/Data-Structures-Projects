package Sorting;

import java.util.*;

public class MergeSorter {
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

        myArray = sort(myArray);

        endTime = System.nanoTime();
    }

    private int[] sort(int[] mL) {
        if(mL.length > 2){
            int[] a = new int[mL.length / 2 + mL.length % 2];
            int[] b = new int[mL.length / 2];

            for(int i = 0; i < mL.length; i ++){
                if(i >= a.length){
                    b[i - a.length] = mL[i];
                }
                else{
                    a[i] = mL[i];
                }
                moves++;
            }

            a = sort(a);
            b = sort(b);

            return recombine(a, b);
        }

        else if(mL.length == 1){
            return mL;
        }
        else{
            if(mL[1] < mL[0]){
                moves ++;
                return new int[]{mL[1], mL[0]};
            }
            else{
                return mL;
            }
        }
    }

    // Combines two sorted arrays into one big sorted array
    public int[] recombine(int[] a, int[] b){
        int[] c = new int[a.length + b.length];

        int aVal = 0;
        int bVal = 0;
        int cVal = 0;

        while(aVal < a.length && bVal < b.length){
            if (a[aVal] < b[bVal]) {
                c[cVal] = a[aVal];
                aVal ++;
            }
            else{
                c[cVal] = b[bVal];
                bVal ++;
            }
            cVal ++;
            moves ++;
        }

        // Now one of the lists is done, you have to copy in the rest, but you have to figure out which one
        if(aVal < a.length){
            c = fillIn(aVal, cVal, a, c);
        }
        else{
            c = fillIn(bVal, cVal, b, c);
        }

        return c;
    }

    // For a partially completed copied list c who needs the rest of the values copied from ab, copy those vals in
    public int[] fillIn(int abPartial, int cPartial, int[] ab, int[] c){
        while(abPartial < ab.length){
            c[cPartial] = ab[abPartial];
            cPartial ++;
            abPartial ++;
            moves ++;
        }
        return c;
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
