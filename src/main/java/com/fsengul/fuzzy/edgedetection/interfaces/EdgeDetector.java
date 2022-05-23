package com.fsengul.fuzzy.edgedetection.interfaces;

import java.awt.image.BufferedImage;

public interface EdgeDetector {

    public BufferedImage detectEdges(BufferedImage grayscaleImage);

}
