package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTestResults {

	public static void zipTestReportsFolder() throws IOException {
        String projectPath = System.getProperty("user.dir");
        String sourceFolderPath = projectPath + "/test-output";
        String zipFilePath = "test-output.zip";
        
        // Create a FileOutputStream to write the ZIP file
        FileOutputStream fos = new FileOutputStream(zipFilePath);
        
        // Create a ZipOutputStream
        ZipOutputStream zos = new ZipOutputStream(fos);
        
        // Create a File object for the source folder
        File sourceFolder = new File(sourceFolderPath);
        
        // Add files from the source folder to the ZIP file
        addFilesToZip(sourceFolder, sourceFolder.getName(), zos);
        
        // Close the ZipOutputStream
        zos.close();
    }
    
    private static void addFilesToZip(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                // Recursively add files from subdirectories
                addFilesToZip(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }
            
            // Create a new ZipEntry corresponding to the file
            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            
            // Write the contents of the file to the ZipOutputStream
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }
            fis.close();
            
            // Close the current ZipEntry
            zos.closeEntry();
        }
    }
}
