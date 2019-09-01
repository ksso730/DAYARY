package us.flower.dayary.common;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


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
}
