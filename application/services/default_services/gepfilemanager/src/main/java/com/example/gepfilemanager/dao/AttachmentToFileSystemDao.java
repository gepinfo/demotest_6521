package com.example.gepfilemanager.dao;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class AttachmentToFileSystemDao {

    private final String imageFolderLocation = "public/";
    private final String imageAccessLocation = "static/";
    private HttpServletResponse response;

    public String fileSaveToSystem(MultipartFile file, String fileName) throws IOException {
        log.info( "info", "Enter into AttachmentToFileSystemService: fileSaveToSystem");
        System.out.println("File Save----------------->" + "public/" + fileName + " " + fileName.getClass());

        String saveTo = imageFolderLocation + fileName;
        File saveToFile = new File(saveTo);
        try (FileOutputStream fileOutputStream = new FileOutputStream(saveToFile)) {
            FileCopyUtils.copy(file.getInputStream(), fileOutputStream);
        }

        log.info( "Exit from AttachmentToFileSystemService: fileSaveToSystem");
        return imageAccessLocation + fileName;
    }

    public void deleteAttachment(String fileKey) {
        log.info( "Enter into AttachmentToFileSystemService: deleteAttachment");
        File file = new File(fileKey);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted!");
            } else {
                System.out.println("Failed to delete the file");
            }
        }
        log.info( "Exit from AttachmentToFileSystemService: deleteAttachment");
    }

    public void fileDownload(String fileKey) throws IOException {
        log.info( "Enter into AttachmentToFileSystemService: fileDownload");
        File file = new File(fileKey);
        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
//            FileCopyUtils.copy(file, response.getOutputStream());
        }
        log.info("Exit from AttachmentToFileSystemService: fileDownload");
    }
}

