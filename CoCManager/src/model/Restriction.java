package model;

public class Restriction {
	private Integer thereshold;
	private RestrictionType type;
	
	
	public Restriction(RestrictionType type, Integer thereshold) {
		this.setType(type);
		this.thereshold = thereshold;
	}

	public Integer getThereshold() {
		return thereshold;
	}

	public void setThereshold(Integer thereshold) {
		this.thereshold = thereshold;
	}

	public RestrictionType getType() {
		return type;
	}

	public void setType(RestrictionType type) {
		this.type = type;
	}
}
