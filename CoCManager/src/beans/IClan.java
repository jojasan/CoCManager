package beans;

import java.util.Map;

public interface IClan {
	public String getName();
	public void setName(String namne);
	public String getDescription();
	public void setDescription(String description);
	public String[] getSelectedRestrictions();
	public void setSelectedRestrictions(String[] selected);
	public Map<String, Integer> getRestrictionValues();
}
