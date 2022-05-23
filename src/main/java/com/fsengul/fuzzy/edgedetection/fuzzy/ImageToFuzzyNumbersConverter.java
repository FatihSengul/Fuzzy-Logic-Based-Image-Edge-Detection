package com.fsengul.fuzzy.edgedetection.fuzzy;


import com.fsengul.fuzzy.edgedetection.util.ImageTransformer;

import java.awt.image.BufferedImage;

public class ImageToFuzzyNumbersConverter {     
    
    private final MembershipFunction blackMembershipFunction = new BlackMembershipFunction();
    private final MembershipFunction whiteMembershipFunction = new WhiteMembershipFunction();
    
    private final EdgeMembershipFunctionConverter edgeMembershipFunctionConverter = new EdgeMembershipFunctionConverter();
    
    public ImageFuzzyNumber[][] convertGrayLevelImageToFuzzyNumbers(BufferedImage grayscaleImage) {
        ImageFuzzyNumber[][] fuzzyNumbers = new ImageFuzzyNumber[grayscaleImage.getWidth()][grayscaleImage.getHeight()];
        for (int i = 0; i < grayscaleImage.getWidth(); i++) {
            for (int j = 0; j < grayscaleImage.getHeight(); j++) {
                int rgb = grayscaleImage.getRGB(i, j);
                int grayLevel = ImageTransformer.convertRGBToGrayLevelAndCheckIt(rgb);

                double white = whiteMembershipFunction.getValue(grayLevel);
                double black = blackMembershipFunction.getValue(grayLevel);
                fuzzyNumbers[i][j] = new ImageFuzzyNumber(white, black);
            }
        }
        return fuzzyNumbers;
    }

    public double convertFuzzyNumberToEdgeGrayLevel(double alphaMax) {
        return edgeMembershipFunctionConverter.convertFuzzyNumberToEdgeGrayLevel(alphaMax);
    }   
    
    public void modifyBlackMembershipFunction(Integer blackStartGray, Integer blackEndGray) {
        blackMembershipFunction.modify(blackStartGray, blackEndGray);
    }
    
    public void modifyWhiteMembershipFunction(Integer whiteStartGray, Integer whiteEndGray) {
        whiteMembershipFunction.modify(whiteStartGray, whiteEndGray);
    }
    
    public void modifyEdgeMembershipFunction(Integer edgeEnd) {
        edgeMembershipFunctionConverter.modify(edgeEnd);
    }
    
    public void refreshMembershipFunction() {
        blackMembershipFunction.refresh();
        whiteMembershipFunction.refresh();
        edgeMembershipFunctionConverter.refresh();
    }
}
