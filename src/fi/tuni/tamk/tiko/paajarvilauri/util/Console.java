
package src.fi.tuni.tamk.tiko.paajarvilauri.util;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;


/**
 * Contains methods where user is prompted to give certain value in console.
 * 
 * Uses Scanner-class to do so. Methods often contain either preset or custom
 * error messages given as String parameters, if the user typed something else
 * than what was asked.
 * 
 * @author Lauri Paajarvi -lauri.paajarvi@tuni.fi-
 * @version 2019-1210
 * @since 1.8
 */
public class Console {
    /**
     * Reads integer from the user and tries it again with text if error occure
     * <P>
     * readIntPrintErrorIfNot int(string) will read an integer from the
     * end-user using java.util.Scanner, and print out an error message (given
     * as parameter) if the user tries to insert a non-integer value. 
     * <P>
     * @param errorMessage What user sees in case of error
     * @return integer that the user typed in console
     */
    public static int readIntPrintErrorIfNot(String errorMessage) {

        Scanner s = new Scanner(System.in);
        
        boolean success=false;
        int input = 0;

        while (!success) {
            try {
                input = Integer.parseInt(s.nextLine());
                success=true;

            } catch(NumberFormatException e) {
                System.out.println(errorMessage);
                success = false;
            }
        }
        return input;
    }
    /**
     * Reads Integer from the user with allowed min and max. Errors printed.
     * <P>
     * Will use the readIntPrintErrorIfNot -method, but in addition gives out
     * an error message and excepts another answer, if the number is not
     * between min and max, given as parameters. In that case will except
     * another answer from user before continuing.
     * <P>
     * @param min Minimum number allowed from the user
     * @param max Maximum number allowed from the user
     * @param errorMessageNonNumeric Prints this error if user didnt enter int.
     * @param errorMessageNonMinAndMax Prints if int was not between min and max.
     * @return The user given integer that is between min and max.
     */
    public static int readInt(int min, int max, String errorMessageNonNumeric, String errorMessageNonMinAndMax) {
        boolean validValue=false;
        int input = 0;
        while (!validValue) {
            input=readIntPrintErrorIfNot(errorMessageNonNumeric);
            if (input >= min && input <=max) {
                validValue=true;
            } else {
                System.out.println(errorMessageNonMinAndMax);
            }
        }
        return input;
    }
    /**
     * Reads (y/n)-(true/false) boolean value from the user.
     * <P>
     * will print out the String given as parameter, while adding " (y/n)" at
     * the end of it, and will continue to do so until the end user manages to
     * provide either y, Y, n or N as first letter in their answer. a boolean
     * value of False. Excepetion will be printed out and the question will be
     * prompted again, in case something unexpected happens. If user hits just
     * enter without providing any information, or tries to write something
     * other than a String containing Y, y, N or n as the first letter, there
     * will be error message shown "Please type Y, y, N or n and press enter
     * to continue."
     * 
     * @param question Question that is promtpted to the user
     * @return True or false (y or n), based on users answer
     */
    public static boolean readBooleanYorN(String question) {
        boolean answer=false;
        boolean success=false;
        String temp = "";
        String information="Please type Y, y, N or n and press enter to continue."; 
        Scanner s = new Scanner(System.in);
        while (!success) {
            try {
                System.out.println(question+" (y/n)");
                temp=s.nextLine();
                //we don't want to let user see String
                if (temp.length()==0) {
                    System.out.println(information);
                }
                else if (temp.charAt(0)=='n' || temp.charAt(0)=='N') {
                    return false;
                } else if (temp.charAt(0)=='y' || temp.charAt(0)=='Y') {
                    return true;
                } else {
                    System.out.println(information);
                }
            } catch (Exception e){
                System.out.println(e);
                }
        }
        return answer;
    }
}