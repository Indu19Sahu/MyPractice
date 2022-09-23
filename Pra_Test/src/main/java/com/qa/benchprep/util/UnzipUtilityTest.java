package com.qa.benchprep.util;

public class UnzipUtilityTest {
    public static void main(String[] args) {
        String zipFilePath = (System.getProperty("user.dir") +"/downloaded-BOL_DT-files");
        String destDirectory = (System.getProperty("user.dir") +"/downloaded-BOL_DT-files");
        UnzipUtility unzipper = new UnzipUtility();
        try {
            unzipper.unzip(zipFilePath, destDirectory);
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
    }
}