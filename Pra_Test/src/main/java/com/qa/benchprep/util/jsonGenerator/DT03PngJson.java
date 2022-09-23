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

public class DT03PngJson {

	Base base = new Base();
	public static Properties jsonconfig = new Properties();
	static String dtfile3;

	@SuppressWarnings({ "unchecked" })
	public void jsonGenerator() throws IOException {
		Base.initializeProperty();
		jsonconfig = new Properties();
		FileInputStream config_path = new FileInputStream(Base.prop.getProperty(Field.JSON_CONFIG));
		jsonconfig.load(config_path);
		JSONObject mainObj = new JSONObject();
		JSONObject dataObj = new JSONObject();

		dataObj.put("orderNumber", jsonconfig.getProperty(Field.ORDER_NUMBER));
		dataObj.put("dtFileId", jsonconfig.getProperty(Field.DT_FILE_ID_THREE));
		dataObj.put("dtFileType", jsonconfig.getProperty(Field.DT_FILE_TYPE_THREE));

		try {
			dtfile3 = new String((Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + "/src/main/resources/demo/DT_03_png.txt"))));

			dataObj.put("dtFile", dtfile3);
			mainObj.put("topic", jsonconfig.getProperty(Field.TOPIC));
			mainObj.put("data", dataObj);
			mainObj.put("dataVersion", "");
			mainObj.put("metadataVersion", Field.METADATA_VERSION);
			mainObj.put("subject", Field.SUBJECT);
			mainObj.put("eventType", Field.DT_EVENT_TYPE);
			mainObj.put("eventTime", "2020-01-15T18:41:00.9584103Z");
			String eventid = randomNumberGenerator.getAlphaNumericString(10);
			mainObj.put("id", eventid);
			FileWriter file = new FileWriter(Field.JSON_PAYLOAD_PATH);
			file.write("[" + mainObj.toJSONString() + "]");
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
