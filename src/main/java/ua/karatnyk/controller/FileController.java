package ua.karatnyk.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.karatnyk.service.utilities.Constants;

@Controller
public class FileController {
	
	@Autowired
    ServletContext context;
	
	
	 @RequestMapping(value = "/user{userId}/file{fileName:.+}", method = RequestMethod.GET)
	 protected String preivewSection(      
	     HttpServletRequest request,
	         HttpSession httpSession,
	     HttpServletResponse response, @PathVariable("fileName") String fileName, @PathVariable("userId") String userId) {
	     try {
	    	 String downloadFolder = context.getRealPath(Constants.FOLDER_FOR_USERS_FILES+File.separator+"user"+userId);
	    	 Path path = Paths.get(downloadFolder + File.separator + fileName);
	         byte[] documentInBytes = Files.readAllBytes(path);         
	         //response.setHeader("Content-Disposition", "inline; filename=\"report.pdf\"");
	         response.setDateHeader("Expires", -1);
	         response.setContentType("application/pdf");
	         response.setContentLength(documentInBytes.length);
	         response.getOutputStream().write(documentInBytes);
	     } catch (Exception ioe) {
	    	 ioe.printStackTrace();
	     } finally {
	     }
	     return null;
	 }

	
	/*@RequestMapping("/download/{fileName:.+}")
    public void downloader(HttpServletRequest request, HttpServletResponse response,
        @PathVariable("fileName") String fileName) {
        try {
            String downloadFolder = context.getRealPath("/downloads");
            File file = new File(downloadFolder + File.separator + fileName);
            if (file.exists()) {
                String mimeType = context.getMimeType(file.getPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                response.setContentLength((int) file.length());
                OutputStream os = response.getOutputStream();
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int b = -1;
                while ((b = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, b);
                }
                fis.close();
                os.close();
            } else {
                System.out.println("Requested " + fileName + " file not found!!");
            }
        } catch (IOException e) {
            System.out.println("Error:- " + e.getMessage());
        }
    }
	*/
	

}
