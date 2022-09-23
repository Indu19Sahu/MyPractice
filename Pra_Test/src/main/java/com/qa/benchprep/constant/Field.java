/**
 * This class contains all the field name used in the property files
 */
package com.qa.benchprep.constant;

/**
 * @author Indu Sahu
 *
 */
public final class Field {
	
	private Field () {
		
	}

	//Executable Files
	private static final String CHROMEDRIVER = System.getProperty("user+dir")+"/src/test/resources/config/chromedriver.exe";
	
	
	public static final String CHROME_DOWNLOAD_DEFAULT_DIRECTORY = "download.default_directory";
	public static final String EXPECTED_FILE_PATH = "expected.bol_dt.path";
	public static final String TESTDATA = "project_testdata";
	public static final String ACTUAL_FILES = "actual.bol_dt.path";
	public static final String WEB_CONFIG = "webapp_config";
	public static final String PAYLOAD_CONFIG ="payload_config";
	public static final String JSON_CONFIG ="json_config";
	
	//For URL of Atlas site
	public static final String ATLAS_URL = "atlasurl";
	//For URL of ErrorReporting Dev site
	public static final String URL = "url";
	//For URL of ErrorReporting QA site
	public static final String URL_QA = "urlqa";
	
	//For payload(both SPA and Atlas)
	public static final String ENDPOINT_DEV = "endpointdev";
	public static final String RESOURCE_DEV = "resourcedev";
	public static final String SUBSCRIPTION_KEY_DEV = "Ocp-Apim-Subscription-Key(dev)";
	public static final Object CONTENT_TYPE = "application/json";
	public static final String ORDER_NUMBER = "orderNumber";
	public static final String TOPIC = "topicdev";
	public static final Object SUBJECT = "FNN order";
	public static final Object DT_EVENT_TYPE = "DIH.Orders.DtUpdated";
	public static final Object METADATA_VERSION = "1";
	public static final Object BOL_EVENT_TYPE = "DIH.Orders.BolUpdated";
	
	//For atlas payload
	public static final String BOL_FILE_ID_ONE = "bolFileId1";
	public static final String BOL_FILE_TYPE_ONE = "bolFileType1";
	public static final String BOL_FILE_ID_TWO = "bolFileId2";
	public static final String BOL_FILE_TYPE_TWO = "bolFileType2";
	public static final String DT_FILE_ID_ONE = "dtFileId1";
	public static final String Dt_FILE_TYPE_ONE = "dtFileType1";
	public static final String DT_FILE_ID_TWO = "dtFileId2";
	public static final String Dt_FILE_TYPE_TWO = "dtFileType2";
	public static final String DT_FILE_ID_THREE = "dtFileId3";
	public static final String DT_FILE_TYPE_THREE = "dtFileType3";
	
	//For SPA payload
	public static final String ERROR_OREDERNUMBER_EMPTY_DTFILE = "errorrder_emptydtFile";
	public static final String ERROR_OREDERNUMBER_EMPTY_DTFILETYPE="errorrder_emptydtFileType";
	
	//For Credentials of Atlas Site
	public static final String EMAIL_ATLAS = "emailForAtlas";
	public static final String PASSWORD_ATLAS = "passwordForAtlas";
	
	//For Credentials of Atlas Site
	public static final String EMAIL_SPA = "emailforSPA";
	public static final String PASSWORD_SPA = "passwordforSPA";
	public static final String JSON_PAYLOAD_PATH = "json_payload_path";
	public static final String JSON_ErrorPayload_PATH = "json_payload_path_error";
	
	
	public static String getChromedriver() {
		return CHROMEDRIVER;
	}
	

	
	}
