package us.flower.dayary.common;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageManager {

	public void makeThumbnail(BufferedImage originalImage, String fileExtension, String thumbnailFilePath, String fileName, int resizePixel) throws Throwable {
		BufferedImage finalImg;//최종 이미지
		BufferedImage squeareImage = square(originalImage);//정사각형
		if(squeareImage.getWidth()>resizePixel){//썸네일
			finalImg=resize(squeareImage, resizePixel, resizePixel);
		}else {
			finalImg=squeareImage;
		}
		save(finalImg, fileExtension, thumbnailFilePath, fileName);
	}

	public BufferedImage square(BufferedImage image){
		int ow = image.getWidth();  // 원본 이미지의 너비
		int oh = image.getHeight(); // 원본 이미지의 높이
		int nw;//crop 될 이미지의 너비
		int nh;//crop 될 이미지의 높이

		if(ow > oh) {//너비와 높이 중, 더 작은 것 기준
			nw = oh;
			nh = oh;
		}else {
			nw = ow;
			nh = ow;
		}
		return Scalr.crop(image, (ow-nw) / 2, (oh-nh) / 2, nw, nh);
	}

	public BufferedImage resize(BufferedImage image, int widthPixel, int heightPixel){
		return Scalr.resize(image, widthPixel, heightPixel);
	}

	public void save(BufferedImage image, String fileExtension, String filePath, String fileName) throws IOException {
		ImageIO.write(image, fileExtension, new File(filePath + "/" + fileName+"."+fileExtension));
	}

}
