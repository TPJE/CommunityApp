package ca.bytetube.communityApp.util;

import ca.bytetube.communityApp.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * Handle img and return reference path of new img
     * @param thumbnail
     * @param targetAddr
     * @return
     */

    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        // Get random and unique file name
        String realFileName = getRandomFileName();
        // Get file extension such as png., jpg.
        String extension = getFileExtension(thumbnail.getImageName());
        // If the path not exist, create one automatically
        makeDirPath(targetAddr);
        // Get relative path (with file name)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is : " + relativeAddr);
        // Get save path of file
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is : " + PathUtil.getImgBasePath() + relativeAddr);
        // Call Thumbnails generate an img with watermark
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("failed to generate thumbnail: " + e.toString());
        }

        // Return image's relative path
        return relativeAddr;

    }

    /**
     * Create random file name, use current yyyyMMddHHmmss + 5 random numbers
     *
     * @return
     */

    public static String getRandomFileName() {
        // Get 5 random numbers
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * Get file extension
     *
     * @param fileName
     * @return
     */

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * Create target path automatically, "/Users/tippi/Desktop/image/xxx.jpg"
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
    }
}
