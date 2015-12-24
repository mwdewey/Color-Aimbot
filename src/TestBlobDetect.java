import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

public class TestBlobDetect {
    public static void main(String[] args) throws Exception{

        BlobDetect blobDetect = new BlobDetect();
        BufferedImage image = ImageIO.read(new File("C:\\Users\\Mike\\Desktop\\GIT\\Color Aimbot\\src\\test.png"));


        blobDetect.processImage(image);


    }

}
