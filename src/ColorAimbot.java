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
        BufferedImage keyImage;
        BufferedImage prevImage;
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

        previewFrame.setLocation(x + 600, y);
        previewFrame.setSize(width, height);
        previewFrame.add(new JLabel(icon), BorderLayout.CENTER);
        previewFrame.setVisible(true);

        int superCount = -1;
        keyImage = null;
        prevImage = null;
        BufferedImage newimg;
        boolean imgDiff;
        while (true) {
            screenShotImage = robot.createScreenCapture(captureSize);

            newimg = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
            imgDiff = false;
            for(int w = 0; w < width; w++){
                for(int h = 0; h < height && keyImage != null; h++){
                    if(screenShotImage.getRGB(w,h) != keyImage.getRGB(w, h)) {
                        newimg.setRGB(w,h,screenShotImage.getRGB(w,h));
                    }

                    if(!imgDiff && screenShotImage.getRGB(w,h) != prevImage.getRGB(w, h)) imgDiff = true;
                }
            }

            if(!imgDiff) superCount++;

            if(superCount > 30 || superCount == -1){
                superCount = 0;
                keyImage = screenShotImage;
            }

            icon.setImage(newimg);
            previewFrame.repaint();
            previewFrame.validate();
            prevImage = screenShotImage;
        }



    }
}
