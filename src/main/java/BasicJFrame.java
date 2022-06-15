import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BasicJFrame extends JFrame {

    private BasicJPanel mainPanel;


    public BasicJFrame(int w, int h) {
        this.setSize(w,h);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.mainPanel = new BasicJPanel(0, 0, Constants.WINDOW_W, Constants.WINDOW_H, Color.GRAY);
        this.add(mainPanel);
    }
}
