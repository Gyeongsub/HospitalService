package vo;

public enum Department {
	SU("Surgery"), IN("Internal"), SK("Dermatology");
	
	private String part;
	Department(String part) {
		this.part = part;
	}
	public String getPart() {
		return this.part;
	}
}
