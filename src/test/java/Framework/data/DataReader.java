package Framework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getDataToMap(String filePath) throws IOException
	{ 
		

	//json file to string	
	String jsonContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	//string to Hash map the below java code is used -JsonDataBind
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});//Converts String to json and stores in a list
    return data;
	
	
	}
}
