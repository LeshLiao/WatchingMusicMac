package Config;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import netP5.NetAddress;

public class MyStation {

	@SerializedName("StationID")
	@Expose
	private Integer stationID;
	@SerializedName("Name")
	@Expose
	private String name;
	@SerializedName("IP")
	@Expose
	private String iP;
	@SerializedName("Port")
	@Expose
	private Integer port;
	@SerializedName("Titles")
	@Expose
	private String titles;
	@SerializedName("Devices")
	@Expose
	private List<Device> devices = null;
	@SerializedName("Rules")
	@Expose
	private List<Rule> rules = null;

	//Write by myself
	private String LastTempString;
	private NetAddress NetSettings;
	
	public Integer getStationID() {
	return stationID;
	}

	public void setStationID(Integer stationID) {
	this.stationID = stationID;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getIP() {
	return iP;
	}

	public void setIP(String iP) {
	this.iP = iP;
	}

	public Integer getPort() {
	return port;
	}

	public void setPort(Integer port) {
	this.port = port;
	}

	public String getTitles() {
	return titles;
	}

	public void setTitles(String titles) {
	this.titles = titles;
	}

	public List<Device> getDevices() {
	return devices;
	}

	public void setDevices(List<Device> devices) {
	this.devices = devices;
	}

	public List<Rule> getRules() {
	return rules;
	}

	public void setRules(List<Rule> rules) {
	this.rules = rules;
	}
	
	//Write by myself
	public String getLastTempString() {
	return LastTempString;
	}
	
	public void setLastTempString(String _LastTempString) {
	this.LastTempString = _LastTempString;
	}
	
	public NetAddress getNetSettings() {
	return NetSettings;
	}
	
	public void setNetSettings(String _IP,int _Port) {
	this.NetSettings = new NetAddress(_IP,_Port);
	}

}
