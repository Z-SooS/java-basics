/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hu.zsoos.hello;

import java.util.Scanner;

/**
 *
 * @author Z-SooS
 */
public class Hello {
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {        
        int num1 = scanInteger("Please enter the first number to be added:");
        int num2 = scanInteger("Please enter the second number to be added:");
        
        int sum = num1 + num2;
        
        System.out.println("The sum is: "+sum);
    }
    
    private static int scanInteger(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
                
        try {
            return Integer.parseInt(input);
        } 
        catch(NumberFormatException ex) {
            System.out.println("Input could not be parsed into an integer");
        }
        return scanInteger(prompt);
    }
}
