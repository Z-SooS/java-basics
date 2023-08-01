/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hu.zsoos.windowmaster;

import hu.zsoos.windowmaster.model.Window;
import java.util.Scanner;

/**
 *
 * @author lordz
 */
public class WindowMaster {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        float height = scanFloatValue("Desired height for window:");
        float width = scanFloatValue("Desired width for window:");
        
        Window createdWindow = new Window(height, width);
        
        System.out.println(createdWindow);
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
}
