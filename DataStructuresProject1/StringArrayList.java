package TestingArea;

public class StringArrayList {

    //My list, behind the scenes
    private String[] myList = new String[11];
    //Points at the end of the list as it exists to the outside world (NOT the end of the array)
    private int myPointer = 0;

    // Simply adds a string to the end of the list
    public int add(String stringy){
        //If the new item would be outta bounds, expand the list
        if(myPointer == myList.length){
            String[] myNewList = new String[myList.length * 2 + 1];
            for(int n = 0; n < myList.length; n ++){
                myNewList[n] = myList[n];
            }
            myList = myNewList;
            //And then just actually add the thing. Recursion!
            return add(stringy);
        }
        else{
            myList[myPointer] = stringy;
            myPointer ++;
            return myPointer;
        }
    }

    // Gets the string at list element i
    public String get(int i){
        if(i >= myPointer || i < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            return myList[i];
        }
    }

    // Gets the index of the given string. If it's not in the list, return -1
    public int indexOf(String s){
        for(int n = 0; n < myPointer; n++){
            if(myList[n] == s){
                return n;
            }
        }
        return -1;
    }

    // Credits to... ah, what's his name, Michael? Possibly Michael for coming up with using the indexOf method so I
    // didn't have to write another for loop for this function.
    // checks to see if the list contains input s
    public Boolean contains(String s){
        if(indexOf(s) != -1){
            return true;
        }
        else{
            return false;
        }
    }

    // Returns the size of the list, or rather the size as the outside world sees it (it could have empty vals past
    //      myPointer that we don't want them to know about or work with)
    public int size(){
        return myPointer;
    }

    // Adds something at index 'index', and shifts everything to the right from that position on
    public int add(int index, String s){
        if(index >= myPointer || index < 0){
            throw new IndexOutOfBoundsException();
        }
        else {
            // This just ensures we have enough room in the list
            // Without having to deal with that "resizing" nonsense again.
            add(myList[myPointer - 1]);
            String nextUp = myList[index];
            myList[index] = s;
            for (int n = index; n < myPointer - 1; n++) {
                String temp = myList[n + 1];
                myList[n + 1] = nextUp;
                nextUp = temp;
            }
            myPointer++;
            return myPointer;
        }
    }

    // Dumps the whole list
    public void clear(){
        myList = new String[1];
        myPointer = 0;
    }

    // Checks if it's empty. Honestly just as easy as using myArray.length() == 0, but whatever.
    public Boolean isEmpty(){
        if(myPointer == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // Removes the element at index i from the list
    public String remove(int i){
        if(i >= myPointer || i < 0){
            throw new IndexOutOfBoundsException();
        }
        else {
            String returner = myList[i];

            for (int n = i; n < myPointer - 1; n++) {
                myList[n] = myList[n + 1];
            }
            myPointer--;
            return returner;
        }
    }

    // Sets the element at index 'index' to 's'
    public void set(int index, String s){
        myList[index] = s;
    }

    // returns an array. Obviously a trick question, cause you can't return your list with all that garbage data past
    //      myPointer.
    public String[] toArray(){
        String[] returner = new String[myPointer];
        for(int n = 0; n < myPointer; n++){
            returner[n] = myList[n];
        }
        return returner;
    }
}
