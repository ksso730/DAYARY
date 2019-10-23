package us.flower.dayary.common;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getExtension;


@Component
public class FileManager {

    public byte[] getByteArray(String filePathAndName) throws Exception {
        InputStream in = new FileInputStream(filePathAndName);
        BufferedInputStream bis = new BufferedInputStream(in);
        byte[] byteArray = IOUtils.toByteArray(bis);
        bis.close();
        in.close();
        return byteArray;
    }

    public void fileUpload(MultipartFile file, String filePathAndName) throws IOException {
        file.transferTo(new File(filePathAndName));
    }

    /**
     * 2019-10-23 임시 (minholee)
     * @param file
     * @param uploadPath
     * @return
     */
    public static String fileSave(String uploadPath, MultipartFile file) throws IllegalStateException, IOException {
        File uploadPathDir = new File(uploadPath);

        if(!uploadPathDir.exists()){
            uploadPathDir.mkdirs();
        }

        String genId = UUID.randomUUID().toString();
        genId = genId.replace("-","");

        String originalFileName = file.getOriginalFilename();
        String fileExtension = getExtension(originalFileName);
        String saveFileName = genId + "." + fileExtension;

        String savePath = calcPath(uploadPath);

        File target = new File(uploadPath + savePath, saveFileName);
        FileCopyUtils.copy(file.getBytes(), target);

        return makeFilePath(uploadPath, savePath, saveFileName);
    }


    /**
     * 2019-10-23 임시 (minholee)
     * @param uploadPath
     * @return
     */
    private static String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();

        String yearPath = File.separator + cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH));
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    /**
     * 2019-10-23 임시 (minholee)
     * @param uploadPath
     * @param paths
     */
    private static void makeDir(String uploadPath, String... paths) {

        System.out.println(paths[paths.length - 1] + " : " + new File(paths[paths.length-1]).exists());

        for(String path : paths){
            File dirPath = new File(uploadPath + path);

            if(!dirPath.exists()){
                dirPath.mkdir();
            }
        }
    }

    /**
     * 2019-10-23 임시 (minholee)
     * @param uploadPath
     * @param savePath
     * @param saveFileName
     * @return
     */
    private static String makeFilePath(String uploadPath, String savePath, String saveFileName) {

        String filePath = uploadPath + savePath + File.separator + saveFileName;
        return filePath.substring(uploadPath.length()).replace(File.separatorChar,'/');
    }

    /**
     * 2019-10-23 임시 (minholee)
     * @param uploadPath
     * @param savePath
     * @param saveFileName
     * @return
     */
    private static String makeThumnail(String uploadPath, String savePath, String saveFileName) throws  Exception {

        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + savePath, saveFileName));

        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

        String thumbnailName = uploadPath + savePath + File.separator + "s_" + saveFileName;

        File newFile = new File(thumbnailName);
        String formatName = saveFileName.substring(saveFileName.lastIndexOf(".") + 1);

        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }
}
