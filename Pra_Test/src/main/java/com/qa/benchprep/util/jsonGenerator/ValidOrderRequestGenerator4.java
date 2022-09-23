package com.qa.benchprep.util.jsonGenerator;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.json.simple.JSONObject;

import com.qa.benchprep.base.Base;
import com.qa.benchprep.constant.Field;
import com.qa.benchprep.util.randomNumberGenerator;

public class ValidOrderRequestGenerator4 {
	public static Properties prop = new Properties();
	static String dtfile;
	@SuppressWarnings({ "unchecked"})
	public static void jsonGenerator() throws IOException {
		Base.initializeProperty();
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src//main//resources//Properties//jsonProperty.properties");
		prop.load(fis);

	JSONObject mainObj = new JSONObject();
	JSONObject dataObj = new JSONObject();

	dataObj.put("orderNumber","295573");
	dataObj.put("dtFileId","dt_37");
	dataObj.put("dtFileType",prop.getProperty("dtFileType"));

	
	try
	{
			dtfile = new String((Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/resources/base64/25MB_PNG_Base64.txt"))));
//			dtfile = new String((Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/resources/base64/MyFile2.txt"))));
	
	//System.out.println(dtfile);
	dataObj.put("dtFile", dtfile);
	mainObj.put("topic", "/subscriptions/1b3f4302-c578-4a10-b16f-00806d6dbdfb/resourceGroups/rg-dih-dev-001/providers/Microsoft.EventGrid/topics/eg-fnnOrder-dev");
	mainObj.put("data", dataObj);
	mainObj.put("dataVersion", "");
	mainObj.put("metadataVersion", "1");
	mainObj.put("subject", "FNN order");
	mainObj.put("eventType", "DIH.Orders.DtUpdated");
	mainObj.put("eventTime", "2020-01-15T18:41:00.9584103Z");
	String eventid =randomNumberGenerator.getAlphaNumericString(10);
	mainObj.put("id", eventid);

	FileWriter file = new FileWriter(Field.JSON_PAYLOAD_PATH);
	
//		System.out.println("["+mainObj.toJSONString()+"]");
	file.write("["+mainObj.toJSONString()+"]");
	file.flush();
	file.close();
	}
	catch (IOException e) {
	// TODO: handle exception
	}
	//System.out.println("Done");
	}



}
