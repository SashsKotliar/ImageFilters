import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ShowImageFromURL {

    public static void show(String urlLocation) {
        Image image = null;
        try {

            URL url = new URL(urlLocation);
            URLConnection conn = url.openConnection();
            ((URLConnection) conn).setRequestProperty("User-Agent", "Mozilla/5.0");

            conn.connect();
            InputStream urlStream = conn.getInputStream();
            image = ImageIO.read(urlStream);

            JFrame frame = new JFrame();
            JLabel labelImage = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(labelImage, BorderLayout.CENTER);
            frame.setSize(image.getWidth(null) + 50, image.getHeight(null) + 50);
            frame.setVisible(true);

        } catch (IOException e) {
            System.out.println("Something went wrong, sorry:" + e.toString());
            e.printStackTrace();
        }
    }

}
