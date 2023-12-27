package HibernateProject.CrimeManagementSystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Suspect 
{
	@Id
	@Column(name = "suspect_id")
	private int suspect_id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "contact_info")
	long contact_info;

	public int getSuspect_id() {
		return suspect_id;
	}

	public void setSuspect_id(int suspect_id) {
		this.suspect_id = suspect_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContact_info() {
		return contact_info;
	}

	public void setContact_info(long contact_info) {
		this.contact_info = contact_info;
	}
	
	
}
