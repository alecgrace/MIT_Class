/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.util.ArrayList;
import java.util.Random;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for (int i = 0; i < 4; i ++) {
        	turtle.turn(90);
        	turtle.forward(sideLength);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double angle = ((sides - 2.00) * 180.00) / sides;
        double rounded = Math.round(angle * 100.0)/100.0;
        return rounded;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        double sides = 360.00 / (180.00 - angle);
        int rounded = (int) Math.round(sides);
        return rounded;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle = calculateRegularPolygonAngle(sides);
        for (int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(180 - angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double sideA = Math.abs(targetX - currentX);
        double sideB = Math.abs(targetY - currentY);
        double angleB = Math.atan(sideA/sideB);
        angleB = Math.toDegrees(angleB);
        double angleC = 0.0;
        if (currentHeading == 0) {
            angleC = angleC;
        } else {
            angleC = 360 - currentHeading;
        }
        double answer = angleB + angleC;
        return answer;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        
        double currentHeading = 0.0;
        
        int length = xCoords.size();
        
        double current = 0.0;
        
        List returnList = new ArrayList();
        
        for (int i = 0; i < length - 1; i ++) {
            current = calculateHeadingToPoint(currentHeading, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1), yCoords.get(i + 1));
            returnList.add(current);
            currentHeading = currentHeading + current;
        }
        
        return returnList;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    
    public static boolean isOdd(int num) {
        if (num % 2 == 0) {
            return false;
        } else {
            return true;
        }
        
    }
    public static void drawPersonalArt(Turtle turtle) {
        
        List colors = new ArrayList<PenColor>();
        colors.add(PenColor.BLACK);
        colors.add(PenColor.BLUE);
        colors.add(PenColor.CYAN);
        colors.add(PenColor.GRAY);
        colors.add(PenColor.GREEN);
        colors.add(PenColor.MAGENTA);
        colors.add(PenColor.ORANGE);
        colors.add(PenColor.PINK);
        colors.add(PenColor.RED);
        
        for (int i = 0; i < 50; i++) {
            
            int len = 50;
            
            turtle.forward(len);
            Random rand = new Random(); //instance of random class
            int upperbound = 9;
              //generate random values from 0-8
            int int_random = rand.nextInt(upperbound); 
            
            if (isOdd(int_random)) {
                turtle.turn(30 * i);
            } else {
                turtle.turn(-(30 * i));
            }
            
            PenColor current = (PenColor) colors.get(int_random);
            turtle.color(current);
            for (int j = 0; j < 5; j++) {
                turtle.forward(5);
                turtle.turn(90);
            }
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        drawSquare(turtle, 40);
        
        calculateRegularPolygonAngle(3);
        
        calculatePolygonSidesFromAngle(50);
        
        drawRegularPolygon(turtle, 5, 50);
        
        calculateHeadingToPoint(30, 0, 1, 0, 0);

        System.out.println(calculateHeadingToPoint(1.0, 4, 5, 4, 6));
        
        List<Integer> xpoints = new ArrayList<>();
        List<Integer> ypoints = new ArrayList<>();
        xpoints.add(0);
        xpoints.add(1);
        xpoints.add(1);
        ypoints.add(0);
        ypoints.add(1);
        ypoints.add(2);
        
        calculateHeadings(xpoints, ypoints);
        
        drawPersonalArt(turtle);
        
        turtle.draw();
        
    }

}
