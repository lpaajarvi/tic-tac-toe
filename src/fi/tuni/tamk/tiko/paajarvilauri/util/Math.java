package src.fi.tuni.tamk.tiko.paajarvilauri.util;

/**
* Contains additional methods not found in java.lang.Math class
*  
* Method getRandom will generate a pseudorandom Integer which minimum value is the first parameter of method,
* and the maximum value is equal to the second parameter of the method. It will accept integers as parameters.
*
* Method weeksToYears will simply divide integer given as parameter by 52, and return it, rounded down.
*
* @author Lauri Paajarvi
* @version 2019-1210
* @since 1.8
*/

public class Math {
    /**
     * Generates a random integer in a range between (including) min and max.
     * <P>
     * Each value has equal chance of being generated, at least if we assume
     * pseudorandom algorhitm achieves that.
     * 
     * @param min Minimum integer that can be the result
     * @param max Maximum integer that can be the result
     * @return the random number in the range of min and max
     */
    public static int getRandom(int min, int max) {
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }
    /**
     * Turns weeks into years. If not full year(52 weeks), then not included.
     * 
     * @param weeks How many weeks are being divided by 52.
     * @return The result (rounded down)
     */
    public static int weeksToYears(int weeks) {
        return weeks/52;
    }
}
