package com.qa.benchprep.util;

import java.io.IOException;

import com.qa.benchprep.base.Base;
import com.qa.benchprep.constant.Field;

import java.io.File;

public class FileCompareFromFolders_directory_and_files {

	public static String fileName;
	public static String[] filelist;
	public static String filename;
	Base base=new Base();
	public static void listFiles(String path) throws IOException, InterruptedException {

		File ActualFolder = new File(path);
		File ExpectedFile = new File(Base.prop.getProperty(Field.EXPECTED_FILE_PATH));
		String[] fileList = ExpectedFile.list();

		File[] files = ActualFolder.listFiles();

		for (File file : files) {
			System.out.println(file);
			if (file.isFile()) {
				for (String name : fileList) {

					String[] name1 = name.split("\\.", 2);
					filename = name1[0];

					if ((file.getName()).contains(filename)) {
						System.out.println("Actual File Name:" + file.getName());
						System.out.println("Expected File Name:" + filename);
						System.out.println("File Name matched: " + file.getName());

						String file1 = path + File.separator + file.getName();
						String file2 = Base.prop.getProperty(Field.EXPECTED_FILE_PATH)+ File.separator + name;

						String base64OfFActual = FileToBase64Convertor.fileToBase64StringConversion(file1);
						Thread.sleep(3000);
						String base64OfExpected = FileToBase64Convertor.fileToBase64StringConversion(file2);

						if (base64OfFActual.equalsIgnoreCase(base64OfExpected)) {
							System.out.println("Both the files have same content.");
						} else {
							System.out.println("Both the files have different content");
						}

					} else {
						System.out.println("New File Found :" + file.getName());
					}

				}
			} else if (file.isDirectory()) {
				listFiles(file.getAbsolutePath());
			}
		}

	}
}
