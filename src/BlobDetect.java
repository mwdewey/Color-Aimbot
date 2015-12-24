import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlobDetect {

    private final int BLOBDIST = 2;
    private final Color[] colors = new Color[]{Color.black,Color.white,Color.blue,Color.cyan,Color.green,Color.magenta,
    Color.orange,Color.pink,Color.red,Color.yellow};

    List<Blob> blobs;

    BlobDetect(){
        blobs = new ArrayList<>();
    }

    void processImage(BufferedImage inputImage){

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        List<Integer> blobIdMap = new ArrayList<>(width*height);
        List<Integer> tempBlobIds = new ArrayList<>(100);
        for(int i = 0; i < width*height; i++) blobIdMap.add(Integer.MAX_VALUE);

        int blobId = 0;
        long time = System.currentTimeMillis();
        for(int w = 0; w < width; w++){
            for(int h = 0; h < height; h++){

                // if is blank, aka not blob, skip
                if(inputImage.getRGB(w,h) == Color.white.getRGB()) continue;

                // check surrounding area for other blobs
                tempBlobIds.clear();

                // top/bottom/left/right
                for(int i = 0; i < 3; i++) tempBlobIds.add(getIdFromMap(w-1+i,h-1,width,height,blobIdMap));
                for(int i = 0; i < 3; i++) tempBlobIds.add(getIdFromMap(w-1+i,h+1,width,height,blobIdMap));
                tempBlobIds.add(getIdFromMap(w-1,h,width,height,blobIdMap));
                tempBlobIds.add(getIdFromMap(w+1,h,width,height,blobIdMap));

                // grab lowest id
                int lowestID = Integer.MAX_VALUE;
                for(int i : tempBlobIds) if(i < lowestID) lowestID = i;

                // if id is MAX, then it needs new id
                if(lowestID == Integer.MAX_VALUE) {
                    blobIdMap.set(h*width + w,blobId);
                    blobId++;
                }
                else blobIdMap.set(h*width + w,lowestID);
            }
        }


        System.out.println(blobId);
        System.out.println(System.currentTimeMillis()-time);

    }

    static int getIdFromMap(int x, int y, int width, int height, List<Integer> blobIdMap){

        if(x < 0 || x >= width) return Integer.MAX_VALUE;
        if(y < 0 || y >= height) return Integer.MAX_VALUE;

        return blobIdMap.get(y*width + x);
    }


}
