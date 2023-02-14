package Config;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyJsonConfig {
	@SerializedName("ProjectName")
	@Expose
	private String projectName;
	@SerializedName("FileVersion")
	@Expose
	private String fileVersion;
	@SerializedName("Timestamp")
	@Expose
	private String timestamp;
	@SerializedName("MyStations")
	@Expose
	private List<MyStation> myStations = null;

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

	public String getTimestamp() {
	return timestamp;
	}

	public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
	}

	public List<MyStation> getMyStations() {
	return myStations;
	}

	public void setMyStations(List<MyStation> myStations) {
	this.myStations = myStations;
	}
	
    @Override
    public String toString() {
        return "projectName:" + projectName;
    }

}
