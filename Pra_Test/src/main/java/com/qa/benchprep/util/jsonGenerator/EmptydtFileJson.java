package com.qa.benchprep.util.jsonGenerator;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.json.simple.JSONObject;

import com.qa.benchprep.base.Base;
import com.qa.benchprep.constant.Field;
import com.qa.benchprep.util.randomNumberGenerator;

public class EmptydtFileJson {
	Base base = new Base();
	public static Properties jsonconfig = new Properties();

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
			dataObj.put("orderNumber", jsonconfig.getProperty(Field.ERROR_OREDERNUMBER_EMPTY_DTFILE));

			dataObj.put("dtFile", "");
			dataObj.put("dtFileId", jsonconfig.getProperty(Field.DT_FILE_ID_ONE));
			dataObj.put("dtFileType", jsonconfig.getProperty(Field.Dt_FILE_TYPE_ONE));

			mainObj.put("dataVersion", "");
			mainObj.put("metadataVersion", Field.METADATA_VERSION);

			String eventid = randomNumberGenerator.getAlphaNumericString(10);
			mainObj.put("id", eventid);
			FileWriter file = new FileWriter(Field.JSON_ErrorPayload_PATH);

			
			file.write("[" + mainObj.toJSONString() + "]");
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
