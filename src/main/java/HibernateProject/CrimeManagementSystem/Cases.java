package HibernateProject.CrimeManagementSystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.Column;

@Entity
@Table(name = "Cases")
public class Cases 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "case_id")
	private int case_id;

	@ManyToOne
	@JoinColumn(name = "officer_id")
	private Officer officer_id;
	
	@ManyToOne
	@JoinColumn(name = "suspect_id")
	private Suspect suspect_id;
	
	@ManyToOne
	@JoinColumn(name = "victim_id")
	private Victim victim_id;
	
	@Column(name = "case_title")
	private String case_title;
	
	@Column(name = "reported_date")
	private String reported_date;

	@Column(name = "status")
	private String status;

	public int getCase_id() {
		return case_id;
	}

	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}
	
	public void setOfficer(Officer officer_id) {
	    this.officer_id = officer_id;
	}
	
	public Officer getOfficer() {
        return officer_id;
    }
	
	public int getOfficer_id() {
	    return officer_id != null ? officer_id.getOfficer_id() : 0;
	}
	
	public void setSuspect(Suspect suspect_id) {
	    this.suspect_id = suspect_id;
	}
	
	public Suspect getSuspect() {
        return suspect_id;
    }

    public int getSuspect_id() {
	    return suspect_id != null ? suspect_id.getSuspect_id() : 0;
	}
    
	public void setVictim(Victim victim_id) {
	    this.victim_id = victim_id;
	}
    
    public Victim getVictim() {
        return victim_id;
    }
	
    public int getVictim_id() {
	    return victim_id != null ? victim_id.getVictim_id() : 0;
	}
   
    public String getCase_title() {
		return case_title;
	}

	public void setCase_title(String case_title) {
		this.case_title = case_title;
	}

	public String getReported_date() {
		return reported_date;
	}

	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
