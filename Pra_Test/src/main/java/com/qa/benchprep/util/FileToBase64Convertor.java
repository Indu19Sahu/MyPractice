package com.qa.benchprep.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class FileToBase64Convertor {
	public static String fileToBase64StringConversion(String path1) throws IOException {
		File inputFile = new File(path1);

		byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);

		// System.out.println("1st File:"+encodedString);
		return encodedString;

	}
}
