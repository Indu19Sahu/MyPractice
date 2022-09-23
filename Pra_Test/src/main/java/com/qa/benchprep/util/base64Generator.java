package com.qa.benchprep.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class base64Generator {

   public static void main(String args[])
   {
    base64Generator t1=new base64Generator();
   
    try {
t1.fileToBase64StringConversion();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
   }
   public void fileToBase64StringConversion() throws IOException {
       // load file from /src/test/resources
//        ClassLoader classLoader = getClass().getClassLoader();
       File inputFile = new File("/Users/apple/Documents/Indu/Atlas/Consolidated_Atlas_final2/src/main/resources/base64/19MB_JPG.jpg");

       byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
       String encodedString = Base64
         .getEncoder()
         .encodeToString(fileContent);
//        System.out.print(encodedString);

       FileWriter writer = new FileWriter("/Users/apple/Documents/Indu/Atlas/Consolidated_Atlas_final2/src/main/resources/base64/19MB_JPG.txt", true);
       writer.write(encodedString);
       writer.flush();
       writer.close();
          }
}

