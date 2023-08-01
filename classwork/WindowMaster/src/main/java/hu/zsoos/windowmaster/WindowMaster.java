/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hu.zsoos.windowmaster;

import hu.zsoos.windowmaster.model.Window;
import java.util.Scanner;

/**
 *
 * @author lordz
 * desc I 
 */
public class WindowMaster {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        float height = scanFloatValue("Desired height for window:");
        float width = scanFloatValue("Desired width for window:");
        float glassPrice = scanFloatValue("Price of glass per square foot:");
        float trimPrice = scanFloatValue("Price of trim per foot:");
        int numberOfWindows = scanIntegerValue("How many windows do you need?");
        
        Window createdWindow = new Window(height, width);
        //System.out.println(createdWindow);
        
        
       var windowClusterInfo = Window.getInfoOfMultipleWindows(height, width, numberOfWindows, glassPrice, trimPrice);
       
       System.out.printf("Each window will be %s\n", createdWindow);
       System.out.printf("For %d windows:\n", numberOfWindows);
       System.out.printf("The combined area will be %.3f square feet\n", windowClusterInfo.get("area"));
       System.out.printf("The combined perimeter will be %.3f feet\n", windowClusterInfo.get("perimeter"));
       System.out.printf("The combined cost will be $%.2f\n", windowClusterInfo.get("cost"));
    }
    
    private static float scanFloatValue(String prompt) {
        System.out.println(prompt);
        
        String input = scanner.nextLine();
        
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException _ignore) {
            System.out.println("That is not a valid number");
        }
        return scanFloatValue(prompt);
    }
    private static int scanIntegerValue(String prompt) {
        System.out.println(prompt);
        
        String input = scanner.nextLine();
        
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException _ignore) {
            System.out.println("That is not a valid number");
        }
        return scanIntegerValue(prompt);
    }
}
