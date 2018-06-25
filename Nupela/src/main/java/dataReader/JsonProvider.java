package dataReader;

import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class JsonProvider extends AbstractDataProvider{

		public static Object[][] getDataFromJson(String fileName){

		Object[][] returnValue = null;
		try {

			JsonElement jsonData = new JsonParser().parse(new FileReader("./data/"+fileName+".json"));
			JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
			List<?> testData = new Gson().fromJson(dataSet, new TypeToken<List<?>>() {
			}.getType());
			int columnCount = testData.get(0).toString().split(",").length;
			returnValue = new Object[testData.size()][columnCount];
			for (int i = 0; i < returnValue.length; i++) {
				String[] dataEle = testData.get(i).toString().replaceAll("[{}]", "").split(",");
				for (int j = 0; j < dataEle.length; j++) {
					String[] data = dataEle[j].split("=");
					try {
						returnValue[i][j] = data[1].trim();
					} catch (Exception e1) {
						returnValue[i][j] = "";
					}
				}
			}
			




		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;

	}



}