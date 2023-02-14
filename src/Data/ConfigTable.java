package Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import Config.MyJsonConfig;
import Config.MyStation;


public class ConfigTable 
{
	//ArrayList<Station> StationList;
	private String TimeStamp;
	public MyJsonConfig _myJsonConfig;
	
	
	public ConfigTable() 
	{
		//StationList = new ArrayList<Station>();
		
		//System.out.println("11223");
	}
	
	public boolean LoadJsonFile(String FileName) throws FileNotFoundException
	{
		JsonReader reader = new JsonReader(new FileReader(FileName));
		Gson gson = new GsonBuilder().create();
		this._myJsonConfig = gson.fromJson(reader, MyJsonConfig.class);
		TimeStamp = this._myJsonConfig.getTimestamp();
		return true;
	}
	
	public boolean initNetSettings()
	{
		for (int i = 0; i < this._myJsonConfig.getMyStations().size(); i++)
		{
			MyStation myStation = _myJsonConfig.getMyStations().get(i);
			myStation.setNetSettings(myStation.getIP(),myStation.getPort());
		}
		return true;
	}
	
	public String getTimeStamp()
	{
		return TimeStamp;
	}
	
	public boolean initialize(String FileName)
	{
		/*
		Station TempStation;
		
		StationList.add(new Station("Led 54 Par Light","10.1.1.12",2346));
		TempStation = StationList.get(StationList.size() - 1);
//		TempStation.SettingList.add(new SettingRule(true,31,0,0));
//		TempStation.SettingList.add(new SettingRule(true,30,0,1));
//		TempStation.SettingList.add(new SettingRule(true,29,0,2));
//		TempStation.SettingList.add(new SettingRule(true,28,0,3));
		TempStation.SettingList.add(new SettingRule(true,112,0,0));
		TempStation.SettingList.add(new SettingRule(true,113,0,1));
		TempStation.SettingList.add(new SettingRule(true,114,0,2));
		TempStation.SettingList.add(new SettingRule(true,115,0,3));

		StationList.add(new Station("Led Matrix pad","10.1.1.6",2346));
		TempStation = StationList.get(StationList.size() - 1);
		
		//#1
		TempStation.SettingList.add(new SettingRule(true,36,0,0));
		TempStation.SettingList.add(new SettingRule(true,37,0,1));
		TempStation.SettingList.add(new SettingRule(true,38,0,2));
		TempStation.SettingList.add(new SettingRule(true,39,0,3));
		TempStation.SettingList.add(new SettingRule(true,68,0,4));
		TempStation.SettingList.add(new SettingRule(true,69,0,5));
		TempStation.SettingList.add(new SettingRule(true,70,0,6));
		TempStation.SettingList.add(new SettingRule(true,71,0,7));
		
		//#2
		TempStation.SettingList.add(new SettingRule(true,75,0,8));
		TempStation.SettingList.add(new SettingRule(true,74,0,9));
		TempStation.SettingList.add(new SettingRule(true,73,0,10));
		TempStation.SettingList.add(new SettingRule(true,72,0,11));
		TempStation.SettingList.add(new SettingRule(true,43,0,12));
		TempStation.SettingList.add(new SettingRule(true,42,0,13));
		TempStation.SettingList.add(new SettingRule(true,41,0,14));
		TempStation.SettingList.add(new SettingRule(true,40,0,15));
		
		//#3
		TempStation.SettingList.add(new SettingRule(true,44,0,16));
		TempStation.SettingList.add(new SettingRule(true,45,0,17));
		TempStation.SettingList.add(new SettingRule(true,46,0,18));
		TempStation.SettingList.add(new SettingRule(true,47,0,19));
		TempStation.SettingList.add(new SettingRule(true,76,0,20));
		TempStation.SettingList.add(new SettingRule(true,77,0,21));
		TempStation.SettingList.add(new SettingRule(true,78,0,22));
		TempStation.SettingList.add(new SettingRule(true,79,0,23));
		
		//#4
		TempStation.SettingList.add(new SettingRule(true,83,0,24));
		TempStation.SettingList.add(new SettingRule(true,82,0,25));
		TempStation.SettingList.add(new SettingRule(true,81,0,26));
		TempStation.SettingList.add(new SettingRule(true,80,0,27));
		TempStation.SettingList.add(new SettingRule(true,51,0,28));
		TempStation.SettingList.add(new SettingRule(true,50,0,29));
		TempStation.SettingList.add(new SettingRule(true,49,0,30));
		TempStation.SettingList.add(new SettingRule(true,48,0,31));
		
		
		//#5
		TempStation.SettingList.add(new SettingRule(true,52,0,32));
		TempStation.SettingList.add(new SettingRule(true,53,0,33));
		TempStation.SettingList.add(new SettingRule(true,54,0,34));
		TempStation.SettingList.add(new SettingRule(true,55,0,35));
		TempStation.SettingList.add(new SettingRule(true,84,0,36));
		TempStation.SettingList.add(new SettingRule(true,85,0,37));
		TempStation.SettingList.add(new SettingRule(true,86,0,38));
		TempStation.SettingList.add(new SettingRule(true,87,0,39));
		
		//#6
		TempStation.SettingList.add(new SettingRule(true,91,0,40));
		TempStation.SettingList.add(new SettingRule(true,90,0,41));
		TempStation.SettingList.add(new SettingRule(true,89,0,42));
		TempStation.SettingList.add(new SettingRule(true,88,0,43));
		TempStation.SettingList.add(new SettingRule(true,59,0,44));
		TempStation.SettingList.add(new SettingRule(true,58,0,45));
		TempStation.SettingList.add(new SettingRule(true,57,0,46));
		TempStation.SettingList.add(new SettingRule(true,56,0,47));
		
		//#7
		TempStation.SettingList.add(new SettingRule(true,60,0,48));
		TempStation.SettingList.add(new SettingRule(true,61,0,49));
		TempStation.SettingList.add(new SettingRule(true,62,0,50));
		TempStation.SettingList.add(new SettingRule(true,63,0,51));
		TempStation.SettingList.add(new SettingRule(true,92,0,52));	
		TempStation.SettingList.add(new SettingRule(true,93,0,53));
		TempStation.SettingList.add(new SettingRule(true,94,0,54));
		TempStation.SettingList.add(new SettingRule(true,95,0,55));
		
		//#8
		TempStation.SettingList.add(new SettingRule(true,99,0,56));
		TempStation.SettingList.add(new SettingRule(true,98,0,57));
		TempStation.SettingList.add(new SettingRule(true,97,0,58));
		TempStation.SettingList.add(new SettingRule(true,96,0,59));
		TempStation.SettingList.add(new SettingRule(true,67,0,60));
		TempStation.SettingList.add(new SettingRule(true,66,0,61));
		TempStation.SettingList.add(new SettingRule(true,65,0,62));
		TempStation.SettingList.add(new SettingRule(true,64,0,63));
		*/
		return true;
		
	}

}
