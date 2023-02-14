package com.wm.ui;
import java.util.ArrayList;

import netP5.NetAddress;

public class Station {
	String StationName;
	NetAddress NetSettings;	
	ArrayList<SettingRule> SettingList;
	String LastOneStr;
	boolean IsSendData;
	
	public Station(String _Name,String _IP,int _Port)
	{
		StationName = _Name;
		NetSettings = new NetAddress(_IP,_Port);
		LastOneStr = "";
		IsSendData = false;

		SettingList = new ArrayList<SettingRule>();
	}
	

}
