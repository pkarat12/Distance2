package ua.karatnyk.service.utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public static void savePhotoUserInProject(MultipartFile file, int idUser, String folder) throws IOException {
		
		String pathFile = FileManager.pathToUserFolderInProject(folder, idUser)+File.separator+FileManager.nameFile(file);
		
		if(pathFile != null) {
			
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			File destination = new File(pathFile);
			ImageIO.write(image, "png", destination);
			
		}
	}
	
	public static void saveFileInProject(MultipartFile file, int userId, String folder) {
		String pathFile = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+
				File.separator+"webapp"+File.separator+folder+File.separator+ "user"+userId;
		File uploadDir = new File(pathFile);
		if(!uploadDir.exists()) uploadDir.mkdirs();
		if(pathFile != null) {
			try {
				byte[] bytes = file.getBytes();
	            BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(uploadDir.getAbsolutePath()+File.separator+file.getOriginalFilename())));
	            stream.write(bytes);
	            stream.flush();
	            stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static String pathToFolderInProject(String folder) {
		String rootPath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+
				File.separator+"webapp"+File.separator+folder;
		File uploadDir = new File(rootPath);
		if(!uploadDir.exists()) uploadDir.mkdirs();
		return uploadDir.getAbsolutePath();
	}
	
	public static String pathToUserFolderInProject(String folder, int idUser) {
		String rootPath = FileManager.pathToFolderInProject(folder)+File.separator+"user"+idUser;
		File uploadDir = new File(rootPath);
		if(!uploadDir.exists()) uploadDir.mkdirs();
		return uploadDir.getAbsolutePath();
	}
	
	public static String fullPathToUserImages(int idUser, String nameFile, String folder) {
		return FileManager.pathToUserFolderInProject(folder, idUser)+File.separator+nameFile;
	}
	
	public static String nameFile(MultipartFile file) {
		return file.getOriginalFilename();
	}
	
	public static String encodedFileToByteFromProject(String pathToFile) {
		try {
			File file = new File(pathToFile);
			byte[] fileToByteArray ;
			fileToByteArray = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
			String encodedByte = new String(fileToByteArray);
			return encodedByte;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deleteFileFromProject(String pathToFile) {
		if(!pathToFile.isEmpty() && pathToFile != null) {
			File file = new File(pathToFile);
			file.delete();
		}
		
	}
	
	public static String pathToDefaultImage(String nameImage, String folder) {
		return FileManager.pathToFolderInProject(folder)+File.separator+nameImage;
	}
	


}
