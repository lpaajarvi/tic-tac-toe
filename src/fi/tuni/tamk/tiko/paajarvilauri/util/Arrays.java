/*
 * Arrays class contains useful functions to make process of modifying arrays faster and
 * clearer.
 * 
 * Method "toIntArray (String[])" ,
 * which will be returned. The Strings need to contain numbers only for it to work (or a - in front of them as in case of negative numbers).
 * 
 * If every String in the method does not contain numbers that can be converted into
 * integer, the method will not work as intended. It will
 * print out an error message while letting the incomplete array be returned anyway.
 * 
 * 
 * Method "contains" takes first parameter integer (value), and checks if it's identical to any
 * of the indexes in the second parameter (array of integer values). The method will return boolean
 * value True in this case, and otherwise it will return False. 
 *
 * 
 * Method "containsSameValues" checks how many of the integers in the first array (first
 * parameter), have the same value as any of the indexes in another array (second parameter).
 * 
 * Important: It is expected that both of the arrays contain only unique values.
 * 
 * For example arrays [1, 2, 3, 4, 5, 6, 7] and [4, 10, 21, 22, 23, 1, 25] would return the
 * value 2, since the numbers 4 and 1 are in both of the arrays.
 * 
 * 
 * Method "addPrefix" (int[], int) takes int array as first parameter and returns a String containing value
 * of each of its indexes, while seperating them with comma "," and space " " and also adding bracket "["
 * as the first character in a string, and "]" as the last one. If there is
 * only one index in it, there will be no comma or space included. The second parameter (int) will determine
 * the amount of numbers each part(index turned into part of the String) will hold, and if it's set out to be bigger than
 * the current number, there will be "0":s added as prefixes to it to fill the set number.
 * 
 * For example int array of {2, 911, 35} with a proposed number of 4 given as second parameter will return the following String:
 * "[0002, 0911, 0035]".
 *
 * If proposed number is not 0, but negative or smaller or equal to the longest number in the int array turned into String, the amount
 * of numbers will be set auomatically according to the longest number in the array. So the same array with a proposed number of -1 or 2
 * for example, would print out the following String:
 * "[002, 911, 035]" since 911 is the longest of the indexes.
 *
 * If proposed number is 0, the same array would not contain any of the prefixes, but just the brackets:
 * "[2, 911, 35]"
 * 
 * If the int array contains negative numbers, they will be added to the final String as well, but they are not taken into account when
 * determining the length of the array. For example int array of {-2, 35, -911,  9} with a proposed number of 5 would print out as:
 * "[-00002, 00035, -00911, 00009]"
 * 
 * 
 * Method "charArrayToString" (char[]) takes char array as its parameter and returns a String containing
 * all of its indexes. Instead of char ' ' it will add text [SPACE] in the string.
 * 
 *
*/





//toString() VAATII KORJAUKSEN INDEX OUT OF BOUND JOS ARRAYSSA EI OO MITÄÄN

package src.fi.tuni.tamk.tiko.paajarvilauri.util;

import src.fi.tuni.tamk.tiko.paajarvilauri.util.Math;
import javax.lang.model.util.ElementScanner6;

/** 
* Contains useful functions to make process of modifying arrays faster.
* <P>
* 
* @author Lauri Paajarvi
* @version 2019-1210
* @since 1.8
*/
public class Arrays {
    /**
     * Takes array containing Strings and turns them into integers in an array.
     * <P>
     * Strings need to contain numbers only for it to work (or a - in front of
     * them as in case of negative numbers). If this is not the case It will
     * print out an error message while letting the incomplete array be
     * returned anyway.
     * <P>
     * @param array String array that is going to be converted
     * @return int array with Strings converted to Integers
     */
    public static int [] toIntArray(String [] array) {
        int [] intArray = new int[array.length];
        
        for (int i=0; i<intArray.length; i++) {
            try {
                intArray[i]=Integer.parseInt(array[i]);
            } catch (Exception e) {
                System.out.println("Error. method toIntArray couldn't be completed. Additional info about error: "+ e );
                }
        }
        return intArray;
    }
    /**
     * Takes first parameter and checks if it's identical to any one in array.
     * <P>
     * checks if the first param(int) is identical to any of the indexes in the
     * second parameter (array of integer values). The method will return
     * boolean value True in this case, and otherwise it will return False. 
     * <P>
     * @param value integer that method tries to find from the array
     * @param array array where the integer either is or isn't
     * @return outcome as boolean (true/false)
     */
    public static boolean contains(int value, int [] array) {
        boolean found=false;
        for (int i=0; i<array.length; i++) {
            if (array[i] == value) {
               return true;
            }
        }
        return found;  
    }
    /**
     * Checks how many mutual(equal in both) values there are in two int arrays
     * <P>
     * Works best for cases where it's known that one array do not contain same
     * value more than once in its indexes. Otherwise you have to be careful
     * what you can do with the info provided.
     * <P>
     * @param array1 First integer array
     * @param array2 Second integer array
     * @return number of equal values found in them
     */
    public static int containsSameValues(int [] array1, int [] array2) {
        int amount = 0;
        for (int i=0; i<array1.length; i++) {
            if (contains(array1[i], array2)) {
                amount++;
            }
        }
        return amount;
    }
    /**
     * Returns String containing contents of intArray, seperating them with ", "
     * <P>
     * If there is only one value in intArray, no comma will be added.
     * @param intArray Integer array used for conversion
     * @return String cotaining intArray as String (including commas)
     */
    public static String toString(int[] intArray) {
        String arrayAsString="";
        if (intArray.length > 1) {
            for (int i=0; i<intArray.length-1; i++) {
                arrayAsString += intArray[i]+", ";
            }
        arrayAsString += intArray[intArray.length-1];
        } else {
            arrayAsString += intArray[0];
        }
    return arrayAsString;
    }

    //needs documentation
    public static int[] removeIndex(int [] original, int index) {
        int [] newer = new int[original.length-1];

        if (index >= 0 && index < original.length) {
            
            for (int i=0; i<index; i++) {
                newer[i]=original[i];
            }
            for (int i=index+1; i<original.length; i++) {
                newer[i-1]=original[i];
            }
        } else {
            newer = new int[original.length];
            newer=original;
        }
    return newer;
    }

    // copy-pasted from https://www.geeksforgeeks.org/selection-sort/
    public static void sort(int arr[]) {
     
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first 
            // element 
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        } 
    }

    public static int[] sortRandom(int arr[]) {

        int[] newArr = new int[arr.length];
        int counter=0;

        while (arr.length > 0) {

            int randomIndex = Math.getRandom(0, arr.length-1);
            newArr[counter]=arr[randomIndex];
            counter++;
            //method int[] removeIndex(int[], int) created earlier for this same Arrays.class is used
            arr = removeIndex(arr, randomIndex);
        }
        return newArr;
        
    

    }

    /**
     * Will add at least one 0, or number as parm, and turn int array to String
     * <P>
     * Takes int array as parameter and turns the contents of it into one
     * String, seperates the values with ", ", adds brackets "[" "]" before
     * and after the first and the last value. If proposedNumberLength is 0,
     * there will be no prefixes, but the brackets will still be there, so
     * array {1, 2, 234, 86} would be printed out as [1, 2, 234, 86] 
     * <P>
     * @param intArray Array given as parameter
     * @param proposedNumberLength numberLength (given as parameter) is less
     * than 0, or if it's smaller than the longest number in the array, the
     * size will be set automatically so there will be the same amount of
     * numbers in every "index", as there are in the longest number
     * in the array.
     * @return String with the contents explained
     */
    public static String addPrefix(int [] intArray, int proposedNumberLength) {
        /*if numberLength (given as parameter) is less than 0, or if it's smaller 
        than the longest number in the array, the size will be set automatically so
        there will be the same amount of numbers in every "index", as there are in the longest number
        in the array.
        */
        String toBeReturned="[";
        /*if proposedNumberLength is 0, there will be no prefixes, but the brackets will still be there, so array {1, 2, 234, 86}
        would be printed out as [1, 2, 234, 86] 
        */
        if (proposedNumberLength==0) {
            toBeReturned+=Arrays.toString(intArray)+"]";
        } else {
            /*Java.lang.Math is used to make sure negative values will be taken into account too, but the minus "-" character, won't be
            taken into account when the number of prefixes is determined
            */
            int numberLength;
            int maxValue=java.lang.Math.abs(intArray[0]);
            for (int i=0; i<intArray.length; i++) {
                if (java.lang.Math.abs(intArray[i])>maxValue) {
                    maxValue=java.lang.Math.abs(intArray[i]);
                }
            }
            int maxLength=Integer.toString(maxValue).length();
            if (proposedNumberLength<maxLength) {
                numberLength=maxLength;
            } else {
                numberLength=proposedNumberLength;
            }
            for (int arrayIndex=0; arrayIndex<intArray.length; arrayIndex++) {
                String valueOfIndex=Integer.toString(java.lang.Math.abs(intArray[arrayIndex]));
                int noOfZeroes=numberLength-valueOfIndex.length();
                
                if (intArray[arrayIndex]<0) {
                    toBeReturned += "-";
                }
                for (int zeroCounter=0; zeroCounter<noOfZeroes; zeroCounter++) {
                    toBeReturned += "0";
                }
                toBeReturned += valueOfIndex;
                if (arrayIndex<(intArray.length-1)) {
                    toBeReturned += ", ";
                } else {
                    toBeReturned += "]";
                }
            }
        }
        return toBeReturned;
    }
    /**
     * Checks if the given character, is found in the char array (both param)
     * 
     * @param value Character that might be in the array
     * @param charArray Array that is checked, whether it has that character.
     * @return true if it was found at least once
     */
    public static boolean containsChar(char value, char[] charArray) {
        boolean found=false;
        for (int i=0; i<charArray.length; i++) {
            if (charArray[i] == value) {
               return true;
            }
        }
        return found;  
    }
    /**
     * Turns contents of array of characters into one string. ' ' is [SPACE].
     * 
     * @param charArray Array given as parameter
     * @return String holding the same values as charArray in same order,
     * except the ' ' is replaced with [SPACE]
     */
    public static String charArrayToString(char[] charArray) {
        String toBeReturned="";
        for (int i=0; i<charArray.length; i++) {
            if(charArray[i]!=' ') {
                toBeReturned += charArray[i];
            } else toBeReturned += "[SPACE]";
        }
        return toBeReturned;
    }
    /**
     * Goes through the array and returns the index value with maximum number.
     * 
     * @param array Int array that is given as parameter, that is being checked
     * @return index of the biggest number in that array
     */
    public static int returnIndexOfMax(int [] array) {
        int indexOfMax=0;
        for (int i=0; i<array.length; i++) {
            if(array[i]>array[indexOfMax]) {
                indexOfMax=i;
            }
        }

        return indexOfMax;
    }
}


