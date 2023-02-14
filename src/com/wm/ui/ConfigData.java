package com.wm.ui;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigData {

@SerializedName("ProjectName")
@Expose
private String projectName;
@SerializedName("FileVersion")
@Expose
private String fileVersion;
@SerializedName("Timestamp")
@Expose
private Integer timestamp;
@SerializedName("Stations")
@Expose
private List<Station> stations = null;

public String getProjectName() {
return projectName;
}

public void setProjectName(String projectName) {
this.projectName = projectName;
}

public String getFileVersion() {
return fileVersion;
}

public void setFileVersion(String fileVersion) {
this.fileVersion = fileVersion;
}

public Integer getTimestamp() {
return timestamp;
}

public void setTimestamp(Integer timestamp) {
this.timestamp = timestamp;
}

public List<Station> getStations() {
return stations;
}

public void setStations(List<Station> stations) {
this.stations = stations;
}

}