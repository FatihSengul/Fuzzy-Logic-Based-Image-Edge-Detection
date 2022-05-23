/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsengul.fuzzy.edgedetection.service;

import com.fsengul.fuzzy.edgedetection.util.ImageTransformer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;


@Service
public class GrayscaleImageService {
    
    protected final ImageTransformer imageTransformer = new ImageTransformer();
    
    public BufferedImage processImage(BufferedImage input) {
        BufferedImage grayscaleImage = imageTransformer.toGrayscaleImage(input);
        
        return grayscaleImage;
    }
}
