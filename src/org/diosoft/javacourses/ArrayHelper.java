package org.diosoft.javacourses;


import java.util.Arrays;
public class ArrayHelper {

    public Person [] merge(Person [] leftArray, Person [] rightArray){
        Person[] copiedLeftArray = sortCopiedArray(leftArray);
        int countOfDifference = getCountOfDifference(rightArray, copiedLeftArray);
        Person [] temp = Arrays.copyOf(leftArray, copiedLeftArray.length + countOfDifference);
        return mergeArray(rightArray, copiedLeftArray, temp);
    }

    private Person[] sortCopiedArray(Person[] leftArray) {
        Person [] copiedLeftArray = Arrays.copyOf(leftArray, leftArray.length);
        Arrays.sort(copiedLeftArray);
        return copiedLeftArray;
    }

    private Person[] mergeArray(Person[] rightArray, Person[] leftArray, Person[] newArray) {
        int i = leftArray.length;
        for (Person element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) < 0) {
                newArray[i++] = element;
            }
        }
        return newArray;
    }

    private int getCountOfMatches(Person[] rightArray, Person[] leftArray) {
        int count = 0;
        for (Person element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) >= 0) {
                count++;
            }
        }
        return count;
    }

    private int getCountOfDifference(Person[] rightArray, Person[] leftArray) {
        return rightArray.length - getCountOfMatches(rightArray,leftArray);
    }

    public boolean compareArrays(Person [] actual, Person[] expected){
        return Arrays.equals(actual,expected);
    }
}
