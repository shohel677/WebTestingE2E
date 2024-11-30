package testComponents;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        String reportsFolderPath =System.getProperty("user.dir").replace("Test", "Utility")+ File.separator+"reports";

        String AllureReportsFolderPath = System.getProperty("user.dir") +File.separator+"allure-results";

        deleteFolderContents(new File(reportsFolderPath));
        deleteFolderContents(new File(AllureReportsFolderPath));
    }

    private void deleteFolderContents(File folder) {
        try {
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            deleteFolderContents(file);
                        } else {
                            if (!file.delete()) {
                                System.err.println("Unable to delete file: " + file.getAbsolutePath());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
