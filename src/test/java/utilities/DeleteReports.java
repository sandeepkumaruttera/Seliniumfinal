package utilities;

import java.io.File;

public class DeleteReports {

	public static void deleteTestReportsFolder() {
		String projectPath = System.getProperty("user.dir");
		String propertyFile = projectPath + "/test-output";
        File testReportsFolder = new File(propertyFile);

        if (testReportsFolder.exists()) {
            deleteFolder(testReportsFolder);
            System.out.println("Existing test-reports folder deleted.");
        }else {
        	System.out.println("Not Present");
        }
        
       
		String propertyFileZip = projectPath + "/test-output.zip";
        File testReportsFolderZip = new File(propertyFileZip);
        
        if (testReportsFolderZip.exists()) {
            deleteFolder(testReportsFolderZip);
            System.out.println("Existing test-reports zip folder deleted.");
        }else {
        	System.out.println("Not Present");
        }
    }
	
	private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }
}
