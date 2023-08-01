/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.zsoos.windowmaster.model;

import java.util.HashMap;
import java.util.Map;

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
        return getDollarCost(PRICE_GLASS_PER_SQUAREFOOT, PRICE_TRIM_PER_FOOT);
    }
    
    public float getDollarCost(float glassPrice, float trimPrice) {
        return area*glassPrice + perimeter*trimPrice;
    }
    
    public static Map<String,Float> getInfoOfMultipleWindows(float height, float width, int amount, float glassPrice, float trimPrice) {
        var info = new HashMap<String, Float>();
        Window window = new Window(height, width);
        
        info.put("area", window.area*(float)amount);
        info.put("perimeter", window.perimeter*(float)amount);
        info.put("cost", window.getDollarCost(glassPrice, trimPrice)*(float)amount);
        
        return info;
    }
    public static Map<String,Float> getInfoOfMultipleWindows(float height, float width, int amount) {
        return getInfoOfMultipleWindows(height, width, amount, PRICE_GLASS_PER_SQUAREFOOT, PRICE_TRIM_PER_FOOT);
    }

    @Override
    public String toString() {
        return String.format("%.3f feet tall, %.3f feet wide, has an area of %.3f square feet, has a %.3f long perimeter and costs $%.2f",height, width, area, perimeter, getDollarCost());
    }
    
    
    
    
}
