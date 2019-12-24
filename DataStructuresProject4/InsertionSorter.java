package Sorting;

public class InsertionSorter implements IntSorter{

    private int[] array = null;
    private int moves = 0;
    private long startTime = 0;
    private long endTime = 0;

    public void init(int[] a){
        this.array = a;
        moves = 0;
    }

    public void sort() {
        startTime = System.nanoTime();
        for (int i = 1; i < array.length; i ++){
            if(array[i - 1] > array[i]){
                int valToMove = array[i];

                // Runs back through the list, searching for the True Home of my valToMove
                for (int j = i - 1; j > -1; j --){
                    if(j == 0){
                        if(valToMove < array[0]) {
                            array[1] = array[0];
                            array[0] = valToMove;
                            break;
                        }
                        else{
                            array[1] = valToMove;
                            break;
                        }
                    }
                    else if(array[j] > valToMove){
                        array[j + 1] = array[j];
                    }
                    else{
                        array[j + 1] = valToMove;
                        break;
                    }
                    moves += 1;
                }
            }
            moves += 1;
        }
        endTime = System.nanoTime();
    }

    public void showList() {
        System.out.print('{');
        for(int i = 0; i < array.length; i ++){
            System.out.print(Integer.toString(array[i]) + ", ");
        }
        System.out.println('}');
    }

    public long getSortTime() { return endTime - startTime; }

    public int getMoves() { return moves; }

    public int[] getArray() {return array;}
}
