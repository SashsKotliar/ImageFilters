import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.awt.*;


public class Facebook extends BasicJPanel{

    private static final String URL_FACEBOOK="https://facebook.com/";

    public Facebook(int x, int y, int w, int h, Color color, String userInput) {
        super(x, y, w, h, color);
        System.setProperty("webdriver.chrome.driver", "C:\\1\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL_FACEBOOK);
        driver.manage().window().maximize();

        WebElement usernameTextInput = driver.findElement(By.id("email"));//by.path""
        usernameTextInput.sendKeys("aleksandra.kotliar@gmail.com");

        WebElement passwordTextInput = driver.findElement(By.id("pass"));
        passwordTextInput.sendKeys("30039991s");

        WebElement enterButton = driver.findElement(By.name("login"));
        enterButton.click();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String profile=deleteR(userInput);
            driver.get(URL_FACEBOOK+profile);
            driver.manage();
        }).start();

    }

    public static   String deleteR(String name){
        return name.replace(" ","");
    }
}


