package in.co.school.mgt.bean;

public class ClassBean extends BaseBean {

	
	private String name;
	private String description;
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
