import org.jsoup.Jsoup;

import java.awt.*;

public class Facebook extends BasicJPanel{

    private static final String URL_FACEBOOK="https://he-il.facebook.com/";

    public Facebook(int x, int y, int w, int h, Color color, String userInput) {
        super(x, y, w, h, color);
        String profile = userInput;
        //Jsoup.connect(URL_FACEBOOK + profile).get();

    }
    public Facebook(int x, int y, int w, int h, String fieldName, String title) {
        super(x, y, w, h, fieldName, title);
    }
    public Facebook(int x, int y, int w, int h, String fieldName) {
        super(x, y, w, h, fieldName);
    }

      /*  List<WebElement> p = driver.findElements(By.linkText("מידע אישי"));
        p.get(0).click();
        WebElement u = driver.findElement(By.id("Ecom_User_ID"));
        u.sendKeys("Naamabitan567");
        WebElement password = driver.findElement(By.id("Ecom_Password"));
        password.sendKeys("Libila567");
        WebElement go = driver.findElement(By.id("wp-submit"));
        go.click();*/

}
