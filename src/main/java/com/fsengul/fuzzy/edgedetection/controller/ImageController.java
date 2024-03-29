package com.fsengul.fuzzy.edgedetection.controller;

import com.fsengul.fuzzy.edgedetection.service.FuzzyLogicService;
import com.fsengul.fuzzy.edgedetection.service.GrayscaleImageService;
import com.fsengul.fuzzy.edgedetection.service.SobelOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
public class ImageController {

    private BufferedImage input;

    @Autowired
    private FuzzyLogicService fuzzyLogicService;

    @Autowired
    private SobelOperatorService sobelOperatorService;

    @Autowired
    private GrayscaleImageService grayscaleImageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("file") MultipartFile file)
            throws IOException, InterruptedException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        input = ImageIO.read(in);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/fuzzy_output", method = RequestMethod.GET)
    public @ResponseBody void getOutputImage(HttpServletResponse response)
            throws IOException {
        BufferedImage output = null;

        if (input != null) {
            output = fuzzyLogicService.processImage(input);
        }

        writeImageToResponse(output, response);
    }

    @RequestMapping(value = "/sobel_output", method = RequestMethod.GET)
    public @ResponseBody void getSobelImage(HttpServletResponse response) throws IOException {
        BufferedImage output = null;

        if (input != null) {
            output = sobelOperatorService.processImage(input);
        }

        writeImageToResponse(output, response);
    }

    @RequestMapping(value = "/grayscale_output", method = RequestMethod.GET)
    public @ResponseBody void getGrayscaleImage(HttpServletResponse response) throws IOException {
        BufferedImage output = null;

        if (input != null) {
            output = grayscaleImageService.processImage(input);
        }

        writeImageToResponse(output, response);
    }

    @RequestMapping(value = "/input_image", method = RequestMethod.GET)
    public @ResponseBody void getInputImage(HttpServletResponse response)
            throws IOException {
        writeImageToResponse(input, response);
    }

    private void writeImageToResponse(BufferedImage image,
                                      HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        OutputStream out = response.getOutputStream();
        if (image != null) {
            ImageIO.write(image, "jpg", out);
        }
        out.close();
    }

}
