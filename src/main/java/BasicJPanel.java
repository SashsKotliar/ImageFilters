import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicJPanel extends JPanel {
    private ImageIcon backGround;
    private JLabel title;
    private JLabel askToInput;
    private JTextField inputField;
    private BufferedImage originalImage;
    private MyFilters images;


    public BasicJPanel(int x, int y, int w, int h, Color color) {
        this.setBounds(x, y, w, h);
        this.setBackground(color);
        this.backGround = null;
        this.title = addJLabel("WELCOME TO IMAGE PROCESSING APP!", Constants.TITLE_X,
                Constants.TITLE_Y, Constants.TITLE_W, Constants.TITLE_H, Constants.TITLE_SIZE, Color.BLACK);
        this.askToInput = addJLabel("Enter a facebook profile for image editing: ",
                Constants.TEXT_X, Constants.TEXT_Y, Constants.TEXT_W, Constants.TEXT_H,
                Constants.TEXT_SIZE, Color.black);
        this.inputField = addTextField(Constants.TEXT_FIELD_X,Constants.TEXT_FIELD_Y,
                Constants.TEXT_FIELD_W, Constants.TEXT_FIELD_H);
        try {
            File file = new File("C:\\Users\\Sasha\\Downloads\\DoraPhoto.jpg");
            this.originalImage = ImageIO.read(file);
        } catch (IOException e){
            System.out.println("Invalid data");
        }
        this.images = new MyFilters(this.originalImage);

        init();
    }

    public BasicJPanel(int x, int y, int w, int h, String fieldName, String title) {
        this.setBounds(x, y, w, h);
        this.title = addJLabel(title, 0, 0, this.getWidth(), 800, 400, Color.blue.brighter());
        this.title.setOpaque(true);
        this.backGround = new ImageIcon(fieldName);
        init();
    }

    public BasicJPanel(int x, int y, int w, int h, String fieldName) {
        this.setBounds(x, y, w, h);
        this.backGround = new ImageIcon(fieldName);
        init();
    }

    public void init() {
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backGround != null) {
            this.backGround.paintIcon(this, g, 0, 0);
        }
        g.drawImage(this.originalImage, Constants.IMAGE_X, Constants.IMAGE_Y, this);
    }



    public JLabel addJLabel(String title, int x, int y, int w, int h, int size, Color color) {
        JLabel jLabel = new JLabel(title, SwingConstants.CENTER);
        jLabel.setFont(new Font("ariel", Font.BOLD, size));
        jLabel.setForeground(color);
        jLabel.setBounds(x, y, w, h);
        this.add(jLabel);
        return jLabel;
    }

    public JTextField addTextField(int x, int y, int w, int h){
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, h);
        textField.addActionListener(e -> {
            String input = textField.getText();
            textField.setText("");
            int currentY = Constants.FIRST_BUTTON_Y;
            for (int i = 0; i < Constants.AMOUNT_OF_BUTTONS; i++) {
                addButton(i, Constants.filterOptions[i], Constants.TEXT_FIELD_X, currentY,
                        Constants.TEXT_FIELD_W, Constants.TEXT_FIELD_H, Color.white, Color.black);
                currentY += Constants.TEXT_FIELD_H;
                }

        });
        this.add(textField);
        return textField;
    }

    public JButton addButton(int type, String text, int x, int y, int w, int h, Color foregroundColor,
                             Color color) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.addActionListener(e -> {
            images.setFilter(type, this.originalImage);
        });
        button.setBackground(color);
        button.setForeground(foregroundColor);
        this.add(button);
        return button;
    }


}
