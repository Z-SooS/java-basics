/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.zsoos.windowmaster.model;

/**
 *
 * @author lordz
 */
public class Window {
    public static final float PRICE_TRIM_PER_FOOT = 2.25f;
    public static final float PRICE_GLASS_PER_SQUAREFOOT = 3.50f;
    
    private final float height;
    private final float width;
    private final float perimeter;
    private final float area;

    public Window(float height, float width) {
        this.height = height;
        this.width = width;
        this.perimeter = height*2 + width*2;
        this.area = height*width;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getPerimeter() {
        return perimeter;
    }

    public float getArea() {
        return area;
    }
    
    public float getDollarCost() {
        return perimeter*PRICE_TRIM_PER_FOOT + area*PRICE_GLASS_PER_SQUAREFOOT;
    }

    @Override
    public String toString() {
        return String.format("This window is %.3f feet tall, %.3f feet wide, has an area of %.3f square feet, has a %.3f long perimeter and costs $%.2f",height, width, area, perimeter, getDollarCost());
    }
    
    
    
    
}
