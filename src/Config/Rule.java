package Config;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rule {
	@SerializedName("Tag")
	@Expose
	private Integer tag;
	@SerializedName("PadNo")
	@Expose
	private Integer padNo;
	@SerializedName("Input")
	@Expose
	private Integer input;
	@SerializedName("OutputType")
	@Expose
	private String outputType;
	@SerializedName("OutputParam")
	@Expose
	private List<Integer> outputParam = null;

	public Integer getTag() {
	return tag;
	}

	public void setTag(Integer tag) {
	this.tag = tag;
	}

	public Integer getPadNo() {
		return padNo;
	}

	public void setPadNo(Integer padNo) {
		this.padNo = padNo;
	}
		
	public Integer getInput() {
	return input;
	}

	public void setInput(Integer input) {
	this.input = input;
	}

	public String getOutputType() {
	return outputType;
	}

	public void setOutputType(String outputType) {
	this.outputType = outputType;
	}

	public List<Integer> getOutputParam() {
	return outputParam;
	}

	public void setOutputParam(List<Integer> outputParam) {
	this.outputParam = outputParam;
	}
}
