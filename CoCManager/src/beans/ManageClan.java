package beans;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Clan;
import model.Restriction;

@ManagedBean
@SessionScoped
public class ManageClan extends BaseBean implements IClan {
	private String name;
	private String description;
	private LinkedHashMap<String, Boolean> enabledRestrictions;
	private LinkedHashMap<String, Integer> restrictionValues;
	
	public ManageClan() {
		enabledRestrictions = new LinkedHashMap<String, Boolean>();
		restrictionValues = new LinkedHashMap<String, Integer>();
		for(int i = 0 ; i < data.getRestrictionTypes().length ; i++) {
			enabledRestrictions.put(data.getRestrictionTypes()[i].getName(), false);
		}
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String create() {
		Clan c = new Clan(name);
		c.setDescription(description);
		for(String key : enabledRestrictions.keySet()) {
			if(enabledRestrictions.get(key)) {
				System.out.println("Creating restriction in clan: " + key);
				System.out.println("Restriction value is: " + restrictionValues.get(key));
				Restriction r = new Restriction(data.findRestrictionType(key), restrictionValues.get(key));
				c.addRestriction(r);				
			}
		}
		data.createClan(c);
		resetForm();
		return null;
	}
	
	private void resetForm() {
		name = null;
		description = null;
		for(String key : restrictionValues.keySet()) {
			restrictionValues.put(key, null);
		}
	}

	@Override
	public String[] getSelectedRestrictions() {
		return null;
	}

	@Override
	public void setSelectedRestrictions(String[] selected) {
		for (int i = 0; i < selected.length; i++) {
			enabledRestrictions.put(selected[i], true);
		}
	}

	@Override
	public Map<String, Integer> getRestrictionValues() {
		return restrictionValues;
	}
}
