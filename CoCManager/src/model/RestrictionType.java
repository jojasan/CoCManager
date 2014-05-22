package model;


public class RestrictionType {
	private String appliesTo;
	private Condition condition = Condition.GET;
	private String name;
	
	public enum Condition {
		GET, LET
	}

	public RestrictionType(String name, String appliesTo) {
		this.name = name;
		this.appliesTo = appliesTo;
	}
	
	public String getAppliesTo() {
		return appliesTo;
	}

	public void setAppliesTo(String appliesTo) {
		this.appliesTo = appliesTo;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
