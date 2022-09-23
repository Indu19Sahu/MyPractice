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

public class EmptydtFileTypeJson {
	Base base = new Base();
	public static Properties jsonconfig = new Properties();
	static String dtfile;

	@SuppressWarnings({ "unchecked"})
	public void jsonGenerator() throws IOException {
		try {

			Base.initializeProperty();
			jsonconfig = new Properties();
			FileInputStream config_path = new FileInputStream(Base.prop.getProperty(Field.JSON_CONFIG));
			jsonconfig.load(config_path);

			JSONObject mainObj = new JSONObject();
			JSONObject dataObj = new JSONObject();

			mainObj.put("topic", jsonconfig.getProperty(Field.TOPIC));
			mainObj.put("subject", Field.SUBJECT);
			mainObj.put("eventType", Field.DT_EVENT_TYPE);
			mainObj.put("eventTime", "2020-01-15T18:41:00.9584103Z");
			mainObj.put("data", dataObj);

			dataObj.put("orderNumber", jsonconfig.getProperty(Field.ERROR_OREDERNUMBER_EMPTY_DTFILETYPE));
			dataObj.put("dtFileId", jsonconfig.getProperty(Field.DT_FILE_ID_THREE));

			dtfile = new String((Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + "/src/main/resources/demo/DT_01_PNG.txt"))));

			dataObj.put("dtFile", dtfile);
			dataObj.put("dtFileType", "");

			mainObj.put("dataVersion", "");
			mainObj.put("metadataVersion", Field.METADATA_VERSION);

			String eventid = randomNumberGenerator.getAlphaNumericString(10);
			mainObj.put("id", eventid);

			FileWriter file = new FileWriter(Field.JSON_ErrorPayload_PATH);

			System.out.println("[" + mainObj.toJSONString() + "]");
			file.write("[" + mainObj.toJSONString() + "]");
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		// System.out.println("Done");
	}

}
