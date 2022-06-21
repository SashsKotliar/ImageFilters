import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.util.List;
import java.awt.*;


public class Facebook extends BasicJPanel {

    private static final String URL_FACEBOOK = "https://facebook.com/";
    private static final String EMAIL_OR_PASSWORD = "Naamabbitan@gmail.com";
    private static final String PASSWORD = "Abcd12";


    private ImageIcon backGround;
    private JLabel pic1;
    private BufferedImage originalImage;
    private BufferedImage imageForEdit;
    private MyFilters images;
    private URL imageURL;

    private Dimension dimension;

    public Facebook(int x, int y, int w, int h, Color color, String userInput, JLabel pic1) {
        super(x, y, w, h, color);
        this.pic1 = pic1;
        System.setProperty("webdriver.chrome.driver", "C:\\1\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL_FACEBOOK);
        driver.manage().window().maximize();

        WebElement usernameTextInput = driver.findElement(By.id("email"));//by.path""
        usernameTextInput.sendKeys(EMAIL_OR_PASSWORD);

        WebElement passwordTextInput = driver.findElement(By.id("pass"));
        passwordTextInput.sendKeys(PASSWORD);

        WebElement enterButton = driver.findElement(By.name("login"));
        enterButton.click();

        int currentY = Constants.FIRST_BUTTON_Y;
        for (int i = 0; i < Constants.AMOUNT_OF_BUTTONS; i++) {
            addButton(i, Constants.filterOptions[i], Constants.TEXT_FIELD_X, currentY,
                    Constants.TEXT_FIELD_W, Constants.TEXT_FIELD_H, Color.white, Color.black);
            currentY += Constants.TEXT_FIELD_H;
        }

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String profile = fixSpace(userInput);
            driver.get(URL_FACEBOOK + profile);
            driver.manage();
            List<WebElement> images = driver.findElements(By.cssSelector("div > a > div > svg > g > image"));
            try {
                this.imageURL=new URL(images.get(0).getAttribute("xlink:href"));
                this.originalImage = ImageIO.read(imageURL);
                this.images = new MyFilters(imageURL);
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    public void getImage(URL url,int x,int y,int w,int h ) throws IOException {
        this.pic1 = new JLabel();
        pic1.setBounds(x, (y+320)/2, 320, 320);
        this.add(pic1);
        BufferedImage bufferedImage = ImageIO.read(url);
        ImageIcon image = new ImageIcon(bufferedImage);
        pic1.setIcon(image);
        this.add(pic1);
    }

    public String fixSpace(String name) {
        return name.replace(" ", "");
    }

    public void setImageURL(){

    }

    public JButton addButton(int type, String text, int x, int y, int w, int h, Color foregroundColor,
                             Color color) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.addActionListener(e -> {
            BufferedImage image = this.originalImage;
            images.setFilter(type, image);
            repaint();
        });
        button.setBackground(color);
        button.setForeground(foregroundColor);
        this.add(button);
        repaint();
        return button;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backGround != null) {
            this.backGround.paintIcon(this, g, 0, 0);
        }
        if (this.originalImage!=null){
            g.drawImage(this.originalImage, Constants.IMAGE_X, Constants.IMAGE_Y, this);
            if (this.images!=null) {
                g.drawImage(this.images.getImageForEditing(), Constants.WINDOW_W -
                        Constants.IMAGE_X - Constants.IMAGE_W, Constants.IMAGE_Y, this);
            } else {
                g.drawImage(this.originalImage, Constants.WINDOW_W -
                        Constants.IMAGE_X - Constants.IMAGE_W, Constants.IMAGE_Y, this);
            }
        }
    }
}