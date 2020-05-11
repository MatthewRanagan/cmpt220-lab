import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class ImageSplitter {
    public static void main(String[] args) throws IOException {

        File file = new File("sample_image.jpg");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);

        int rows = 2;
        int cols = 2;
        int chunks = rows * cols;

        int chunkWidth = image.getWidth() / cols; 
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        for (int i = 0; i < imgs.length; i++) {
            ImageIO.write(imgs[i], "jpg", new File("sample_image_" + (i+1) + ".jpg"));
        }
        System.out.println(chunks + " split images created");
    }
}
