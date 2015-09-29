package org.diosoft.javacourses;

import java.util.Arrays;

public class Game {
    public int [] leftUnion(int [] leftArray, int [] rightArray){
        int[] copiedLeftArray = sortCopiedArray(leftArray);
        int countOfMatches = getCountOfMatches(rightArray, copiedLeftArray);
        int [] temp = Arrays.copyOf(leftArray, copiedLeftArray.length + countOfMatches);
        return appendArray(rightArray, copiedLeftArray, temp);
    }

    public int [] merge(int [] leftArray, int [] rightArray){
        int[] copiedLeftArray = sortCopiedArray(leftArray);
        int countOfDifference = getCountOfDifference(rightArray, copiedLeftArray);
        int [] temp = Arrays.copyOf(leftArray, copiedLeftArray.length + countOfDifference);
        return mergeArray(rightArray, copiedLeftArray, temp);
    }

    public int [] innerUnion(int [] leftArray, int [] rightArray){
        int[] copiedLeftArray = sortCopiedArray(leftArray);
        int countOfMatches = getCountOfMatches(rightArray, copiedLeftArray);
        int [] temp = new int[countOfMatches];
        return innerUnionArray(rightArray, copiedLeftArray, temp);
    }

    public int [] outerUnion(int [] leftArray, int [] rightArray){
        int[] copiedLeftArray = sortCopiedArray(leftArray);
        int[] copiedRightArray = sortCopiedArray(rightArray);
        int countOfDifference = getCountOfDifference(rightArray, copiedLeftArray) + getCountOfDifference(leftArray, copiedRightArray);
        int [] temp = new int[countOfDifference];
        return outerUnionArray(copiedRightArray, copiedLeftArray, temp);
    }

    private int[] sortCopiedArray(int[] leftArray) {
        int [] copiedLeftArray = Arrays.copyOf(leftArray, leftArray.length);
        Arrays.sort(copiedLeftArray);
        return copiedLeftArray;
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

    private int[] mergeArray(int[] rightArray, int[] leftArray, int[] newArray) {
        int i = leftArray.length;
        for (int element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) < 0) {
                newArray[i++] = element;
            }
        }
        return newArray;
    }


    private int[] innerUnionArray(int[] rightArray, int[] leftArray, int[] newArray) {
        int i = 0;
        for (int element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) >= 0) {
                newArray[i++] = element;
            }
        }
        return newArray;
    }

    private int[] outerUnionArray(int[] rightArray, int[] leftArray, int[] newArray) {
        int i = 0;
        for (int element : leftArray) {
            if (Arrays.binarySearch(rightArray, element) < 0) {
                newArray[i++] = element;
            }
        }
        for (int element : rightArray) {
            if (Arrays.binarySearch(leftArray, element) < 0) {
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

    private int getCountOfDifference(int[] rightArray, int[] leftArray) {
        return rightArray.length - getCountOfMatches(rightArray,leftArray);
    }

    public boolean compareArrays(int [] actual, int[] expected){
        return Arrays.equals(actual,expected);
    }

    public static void main(String[] args) {
        int [] left = {1,5,4,23,65,32,78};
        int [] right = {3,5,24,54,1,2,34,45,32};
        int [] expectedLeftUnionArray = {1, 5, 4, 23, 65, 32, 78, 5, 1, 32};
        int [] expectedMergeArray = {1,5,4,23,65,32,78,3,24,54,2,34,45};
        int [] expectedInnerUnionArray = {5,1,32};
        int [] expectedOuterUnionArray = {4,23,65,78,2,3,24,34,45,54};
        Game game = new Game();
        int [] actual = game.leftUnion(left, right);
        System.out.println(game.compareArrays(actual, expectedLeftUnionArray));
        System.out.println(game.compareArrays(game.merge(left,right), expectedMergeArray));
        System.out.println(game.compareArrays(game.innerUnion(left, right), expectedInnerUnionArray));
        System.out.println(game.compareArrays(game.outerUnion(left, right), expectedOuterUnionArray));
    }

}
