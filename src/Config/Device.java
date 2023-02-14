package Config;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

@SerializedName("Type")
@Expose
private String type;
@SerializedName("Config")
@Expose
private List<String> config = null;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public List<String> getConfig() {
return config;
}

public void setConfig(List<String> config) {
this.config = config;
}

}