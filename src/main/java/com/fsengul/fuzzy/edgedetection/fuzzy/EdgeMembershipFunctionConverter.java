/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsengul.fuzzy.edgedetection.fuzzy;


import static com.fsengul.fuzzy.edgedetection.util.GeneralConstants.PIXEL_SIZE;

public class EdgeMembershipFunctionConverter {
    
    public static int EDGE_END = 10;
    
    private static double MAX_EDGE_AREA;
    
    public void refresh() {
        EDGE_END = 10;
        calculateMaxEdgeArea();
    }
    
    static {
        calculateMaxEdgeArea();
    }
    
    public double convertFuzzyNumberToEdgeGrayLevel(double alphaMax) {
        double trapeziumArea = getTrapeziumArea(alphaMax);
        //return -PIXEL_SIZE * trapeziumArea / MAX_EDGE_AREA + PIXEL_SIZE;
        return PIXEL_SIZE * trapeziumArea / MAX_EDGE_AREA;
    }
    
    public void modify(Integer edgeEnd) {
        EDGE_END = edgeEnd;
        calculateMaxEdgeArea();
    }
    
    private double getTrapeziumArea(double alphaMax) {
        return EDGE_END * (2 - alphaMax) * alphaMax / 2;
    } 
    
    private static void calculateMaxEdgeArea() {
        MAX_EDGE_AREA = (double) EDGE_END / 2;
    }
}
