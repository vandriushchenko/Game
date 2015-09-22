package org.diosoft.javacourses;

import java.util.Arrays;

public class Game {
    public int [] leftUnion(int [] leftArray, int [] rightArray){
        int [] copiedLeftArray = Arrays.copyOf(leftArray, leftArray.length);
        Arrays.sort(copiedLeftArray);
        int countOfMatches = getCountOfMatches(rightArray, copiedLeftArray);
        int [] temp = Arrays.copyOf(leftArray, copiedLeftArray.length+ countOfMatches);
        return appendArray(rightArray, copiedLeftArray, temp);
    }

    private int[] appendArray(int[] rightArray, int[] leftArray, int[] newArray) {
        int i = leftArray.length;
        for (int element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) >= 0) {
                newArray[i++] = element;
            }
        }
        return newArray;
    }

    private int getCountOfMatches(int[] rightArray, int[] leftArray) {
        int count = 0;
        for (int element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) >= 0) {
                count++;
            }
        }
        return count;
    }

    public boolean compareArrays(int [] actual, int[] expected){
        if(actual.length!=expected.length)
            return false;
        for (int i = 0; i < actual.length; i++) {
            if(actual[i]!=expected[i]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int [] left = {1,5,4,23,65,32,78};
        int [] right = {3,5,24,4,1,2,34,45,32,5};
        int[] expectedArray = {1, 5, 4, 23, 65, 32, 78, 5, 4, 1, 32, 5};
        Game game = new Game();
        int[] actual = game.leftUnion(left, right);
        System.out.println(game.compareArrays(actual, expectedArray));
    }

}
