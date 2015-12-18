import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ColorAimbot {

    public static void main(String[] args) throws Exception{

        int x = 500;
        int y = 500;
        int width = 500;
        int height = 500;

        Robot robot = new Robot();
        Rectangle captureSize = new Rectangle(x, y, width, height);
        ImageIcon icon = new ImageIcon();
        BufferedImage screenShotImage;
        JFrame captureFrame = new JFrame();
        JFrame previewFrame = new JFrame();
        List<BufferedImage> imageBuffer = new ArrayList<>();
        int bufferLimit = 10;

        captureFrame.setUndecorated(true);
        captureFrame.setBackground(new Color(0, 0, 0, 0));
        captureFrame.setLocation(x, y);
        captureFrame.setSize(width, height);
        captureFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        captureFrame.setVisible(true);

        previewFrame.setLocation(x+600, y);
        previewFrame.setSize(width, height);
        previewFrame.add(new JLabel(icon), BorderLayout.CENTER);
        previewFrame.setVisible(true);

        while (true) {
            imageBuffer.add(robot.createScreenCapture(captureSize));
            if(imageBuffer.size() > bufferLimit) imageBuffer.remove(0);

            for(BufferedImage img : imageBuffer){

            }

            for(int w = 0; w < width; w++){
                for(int h = 0; h < height; h++){
                    //if(image.getRGB(w,h) == Color.white.getRGB()) image.setRGB(w,h,Color.black.getRGB());
                    //else image.setRGB(w,h,Color.white.getRGB());
                }
            }

            //icon.setImage(image);
            previewFrame.repaint();
            previewFrame.validate();
        }



    }
}
