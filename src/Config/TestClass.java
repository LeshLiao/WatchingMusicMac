package Config;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class TestClass {

	public static void main(String[] args) throws IOException {
            
			JsonReader reader = new JsonReader(new FileReader("test01.json"));
			Gson gson = new GsonBuilder().create();
			MyJsonConfig p = gson.fromJson(reader, MyJsonConfig.class);
            System.out.println(p);  

            for (int i = 0; i < p.getMyStations().size(); i++)
    			//System.out.println( p.getMyStations().get(i).getRules());
            	for (int j = 0; j < p.getMyStations().get(i).getRules().size(); j++)
            		System.out.println( p.getMyStations().get(i).getRules().get(j).getOutputType());
        
	}

}
