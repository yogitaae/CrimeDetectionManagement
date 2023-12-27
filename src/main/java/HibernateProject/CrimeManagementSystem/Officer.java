package HibernateProject.CrimeManagementSystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Officer 
{
	@Id
	@Column(name = "officer_id")
	private int officer_id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "contact_info")
	long contact_info;
	
	public int getOfficer_id() {
		return officer_id;
	}
	public void setOfficer_id(int officer_id) {
		this.officer_id = officer_id;
	}
	
	public long getContact_Info() {
		return contact_info;
	}
	public void setContact_Info(long contact_info) {
		this.contact_info = contact_info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
