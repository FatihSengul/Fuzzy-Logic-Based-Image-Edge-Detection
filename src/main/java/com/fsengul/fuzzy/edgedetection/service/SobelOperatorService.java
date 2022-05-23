/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsengul.fuzzy.edgedetection.service;

import com.fsengul.fuzzy.edgedetection.classic.SobelOperatorEdgeDetector;
import com.fsengul.fuzzy.edgedetection.interfaces.EdgeDetector;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;


@Service
public class SobelOperatorService extends GrayscaleImageService {
    
    private final EdgeDetector edgeDetector = new SobelOperatorEdgeDetector();
    
    @Override
    public BufferedImage processImage(BufferedImage input) {
        BufferedImage grayscaleImage = imageTransformer.toGrayscaleImage(input);
        
        BufferedImage output = edgeDetector.detectEdges(grayscaleImage);
        
        return output;
    }  
}
