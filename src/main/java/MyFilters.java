import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;


public class MyFilters {
    private BufferedImage imageForEditing;
    private Facebook facebook;
    public static final int SEPIA_DEPTH = 20;
    public static final int SEPIA_INTENSITY = 30;
    public static final int NEGATIVE_FILTER = 0;
    public static final int BLACK_AND_WHITE_FILTER = 1;
    public static final int SEPIA_FILTER = 2;
    public static final int MIRROR_FILTER = 3;
    public static final int COLOR_SHIFT_LEFT_FILTER = 4;
    public static final int COLOR_SHIFT_RIGHT = 5;


    public MyFilters(URL url){
        try {
            this.imageForEditing = ImageIO.read(url);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setToNegative(BufferedImage originalImage){
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                int currentRGB = originalImage.getRGB(x, y);
                Color currentColor = new Color(currentRGB);
                Color newColor = new Color(255 - currentColor.getRed(),
                        255 - currentColor.getGreen(),255 - currentColor.getBlue());
                this.imageForEditing.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToBlackAndWhite(BufferedImage originalImage){
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                int currentRGB = originalImage.getRGB(x, y);
                Color currentColor = new Color(currentRGB);
                int grey = (int) (0.299 * currentColor.getRed() + 0.587 * currentColor.getGreen()
                        + 0.114*currentColor.getBlue());
                Color newColor = new Color(grey, grey, grey);
                this.imageForEditing.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToSepia(BufferedImage originalImage){
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                int currentRGB = originalImage.getRGB(x, y);
                Color currentColor = new Color(currentRGB);
                int average = (currentColor.getRed()+ currentColor.getGreen()+ currentColor.getBlue())/3;
                int newRed= average+(SEPIA_DEPTH*2);
                int newGreen = average+SEPIA_DEPTH;
                int newBlue = average-SEPIA_INTENSITY;

                if (newRed > 255)newRed = 255;
                if (newGreen > 255)newGreen = 255;
                if (newBlue<0)newBlue=0;

                Color newColor = new Color(newRed, newGreen, newBlue);
                this.imageForEditing.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToMirror(BufferedImage originalImage){

        for(int y = 0; y < originalImage.getHeight(); y++){
            for(int x = 0; x < originalImage.getWidth(); x++){
                this.imageForEditing.setRGB((originalImage.getWidth() -1)-x, y,
                        originalImage.getRGB(x,y));
            }
        }
    }

    public void setToColorShiftLeft(BufferedImage originalImage){
        for (int y = 0; y < originalImage.getHeight(); y++){
            for (int x = 0; x < originalImage.getWidth(); x++){
                int currentRGB = originalImage.getRGB(x, y);
                Color currentColor = new Color(currentRGB);
                Color newColor = new Color(currentColor.getBlue(), currentColor.getRed(),
                        currentColor.getGreen());
                this.imageForEditing.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setToColorShiftRight(BufferedImage originalImage){
        for (int y = 0; y < originalImage.getHeight(); y++){
            for (int x = 0; x < originalImage.getWidth(); x++){
                int currentRGB = originalImage.getRGB(x, y);
                Color currentColor = new Color(currentRGB);
                Color newColor = new Color(currentColor.getGreen(), currentColor.getBlue(),
                        currentColor.getRed());
                this.imageForEditing.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setFilter(int type, BufferedImage originalImage){
        switch (type){
            case NEGATIVE_FILTER -> setToNegative(originalImage);
            case BLACK_AND_WHITE_FILTER -> setToBlackAndWhite(originalImage);
            case SEPIA_FILTER -> setToSepia(originalImage);
            case MIRROR_FILTER -> setToMirror(originalImage);
            case COLOR_SHIFT_LEFT_FILTER -> setToColorShiftLeft(originalImage);
            case COLOR_SHIFT_RIGHT -> setToColorShiftRight(originalImage);
        }
    }

    public BufferedImage getImageForEditing(){
        return this.imageForEditing;
    }

}