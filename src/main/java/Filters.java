import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Filters {

    public static final int SEPIA_DEPTH = 20;
    public static final int SEPIA_INTENSITY = 30;

    public void setToNegative(BufferedImage originalImage, Color currentColor){
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                Color newColor = new Color(255 - currentColor.getRed(),
                        255 - currentColor.getGreen(),255 - currentColor.getBlue());
                originalImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToBlackAndWhite(BufferedImage originalImage, Color currentColor){
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                int grey = (int) (0.299 * currentColor.getRed() + 0.587 * currentColor.getGreen()
                        + 0.114*currentColor.getBlue());
                Color newColor = new Color(grey, grey, grey);
                originalImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToSepia(BufferedImage originalImage, Color currentColor){
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                int average = (currentColor.getRed()+ currentColor.getGreen()+ currentColor.getBlue())/3;
                int newRed= average+(SEPIA_DEPTH*2);
                int newGreen = average+SEPIA_DEPTH;
                int newBlue = average-SEPIA_INTENSITY;

                if (newRed > 255)newRed = 255;
                if (newGreen > 255)newGreen = 255;
                if (newBlue<0)newBlue=0;

                Color newColor = new Color(newRed, newGreen, newBlue);
                originalImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToMirror(BufferedImage originalImage, int width, int height){
        BufferedImage mirror = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                mirror.setRGB((width -1)-x, y, originalImage.getRGB(x,y));
            }
        }
    }

    public void setToColorShiftLeft(BufferedImage originalImage, Color currentColor){
        for (int y = 0; y < originalImage.getHeight(); y++){
            for (int x = 0; x < originalImage.getWidth(); x++){
                Color newColor = new Color(currentColor.getBlue(), currentColor.getRed(),
                        currentColor.getGreen());
                originalImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToColorShiftRight(BufferedImage originalImage, Color currentColor){
        for (int y = 0; y < originalImage.getHeight(); y++){
            for (int x = 0; x < originalImage.getWidth(); x++){
                Color newColor = new Color(currentColor.getGreen(), currentColor.getBlue(),
                        currentColor.getRed());
                originalImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }









}
