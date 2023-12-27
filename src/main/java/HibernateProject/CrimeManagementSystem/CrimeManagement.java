package HibernateProject.CrimeManagementSystem;
	
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import java.util.Scanner;
import java.util.List;
	
public class CrimeManagement 
{
    // Static variables for Hibernate configuration, session, and session factory shared across the class.
    static Configuration config;
    static Session session;
    static SessionFactory factory;
	
    // Create a single Scanner instance for the entire class
    private static Scanner sc = new Scanner(System.in);

	
    // Entity 1.Officer - Information 
    public static void OfficerInfo()
    {
 	// Hibernate Connection specifications
	Configuration config = new Configuration();
    	config.configure();
    	SessionFactory factory = config.buildSessionFactory();
    	Session session = factory.openSession();
		
    	while(true)
		{
    		System.out.println("\n------------------------------------------------");
		System.out.println("|                Officer details               |");
		System.out.println("------------------------------------------------");
		System.out.println("|          1. Add Officer Information          |");
		System.out.println("|           2. View Officer Details            |");
		System.out.println("|          3. Update Officer Details           |");
		System.out.println("|               4. Drop Officer                |");
		System.out.println("|             5. Back to main page             |");
		System.out.println("------------------------------------------------");

		System.out.println();
		System.out.print("Enter your choice: ");
		int option = sc.nextInt();
		switch(option)
		{
		case 1:
			addOfficer();
			break;
		case 2:
			viewOfficer();
			break;
		case 3:
		        updateOfficer();
    	                break;
		case 4:
			dropOfficer();
			break;
		case 5:
			return;
			
		default:
			System.out.println("Invalid! Choose a correct option.");
		}// close switch 
	}// close while
    }// close function OfficerInfo
	
	
	// Function to Add Officer Details 
	public static void addOfficer()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
	    	// Calling class Officer to access getters-setters
    		Officer of = new Officer();
    	
    		// To Add Officer ID
    		System.out.print("Officer ID: ");
    		int officer_id = sc.nextInt();
    	    	
    		// Check if Officer ID already exists
	        Officer existingOfficer = session.get(Officer.class, officer_id);
        	if (existingOfficer != null) 
		{
            		System.out.println("Warning: Officer with ID " + officer_id + " already exists. Please enter a unique ID.");
            		return;
        	}
        	of.setOfficer_id(officer_id);
        
        	sc.nextLine();
    		// To Add Officer Name
    		System.out.print("Officer Name: ");
    		String name = sc.nextLine();
    		of.setName(name);
	    	
	    	// To Add Officer Contact-Info
	    	System.out.print("Officer Contact-Info: ");
	    	long contact_info = sc.nextLong();
	    	of.setContact_Info(contact_info);
    	
	    	System.out.println("\nOfficer Details Inserted with\n   Officer ID: "+officer_id+"\n   Officer Name: "+name+"\n   Officer Contact-Info: "+contact_info);
	    	System.out.println("------------------------------------------------\n");
    	
	    	// Saving updates
	    	session.save(of);
	    	Transaction transaction = session.beginTransaction();
	    	transaction.commit();
		
	}
	
	
	// Function to View Officer Table
	public static void viewOfficer()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
    	
    		System.out.println("\n------------------------------------------------");
		System.out.println("|                Officer Details               |");
		System.out.println("------------------------------------------------");
		System.out.println("| Officer_ID  | Officer_Name | Officer_Contact |");
		System.out.println("------------------------------------------------");

		// hql query to view Officer Table
		String hqlQuery = "from Officer";
		List<Officer> data = session.createQuery(hqlQuery, Officer.class).list();
		
		for(Officer o : data)
		{
			System.out.printf("| %-10s | %-13s | %-15d |\n", o.getOfficer_id(), o.getName(), o.getContact_Info());
		}
		
		System.out.println("------------------------------------------------\n");
	}
	
	
	// Function to Update Officer Details 
	public static void updateOfficer()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
    	
		System.out.println("\n------------------------------------------------");
	    	System.out.println("|           Update Officer details             |");
	    	System.out.println("------------------------------------------------");
    		System.out.println("|          1. Update Officer Name              |");
    		System.out.println("|      2. Update Officer Contact-Info          |");
    		System.out.println("------------------------------------------------");

        	System.out.println();
        	System.out.print("Enter your choice ");

       		int option1 = sc.nextInt();
       		switch (option1) 
        	{
        	case 1:
	          	updateOfficerName();
            		break;
        	case 2:
            		updateOfficerContact();
            		break;
        
	        default:
            		System.out.println("Invalid! Choose a correct option.");
		}// close switch
	}
	
	
	// Function to Update Officer Name
	public static void updateOfficerName()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
    		// Enter ID for which name is to be updated
    		System.out.print("Officer ID: ");
    		int officer_id = sc.nextInt();
    	
	    	Officer of = session.get(Officer.class,officer_id);
		Transaction transaction = session.beginTransaction();
		
		sc.nextLine();
		System.out.print("Enter Officer's updated name "+officer_id+" : ");
		String name = sc.nextLine();
		
		of.setName(name);
		session.update(of);		
		System.out.println("\nName Updated Successfully.");
		transaction.commit();
		
	}
	
		
	// Function to Update Officer Contact
	public static void updateOfficerContact()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
	    	// Enter ID for which Contact info is to be updated
	    	System.out.print("Officer ID: ");
    		int officer_id = sc.nextInt();
    	
    		Officer of = session.get(Officer.class,officer_id);
		Transaction transaction = session.beginTransaction();
		
		System.out.print("Enter Officer's updated contact-info "+officer_id+" : ");
		long contact_info = sc.nextLong();
		
		of.setContact_Info(contact_info);
		session.update(of);		
		System.out.println("\nContact-Info Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Drop Officer Detail
	public static void dropOfficer() 
	{
		// Hibernate Connection Specification
	    	Configuration config = new Configuration();
	    	config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();

	    	// Prompt the user to enter the Officer ID to drop
	    	System.out.print("Enter Officer ID to drop: ");
	    	int officerIdToDelete = sc.nextInt();

	    	try 
	    	{
		    	// Retrieve the Officer object based on the entered ID
		    	Officer of = session.get(Officer.class, officerIdToDelete);

	        	if (of != null) 
	        	{
	            		// Begin transaction
	            		Transaction transaction = session.beginTransaction();

	            		// Delete the Officer
	            		session.delete(of);

	            		// Commit the transaction
	            		transaction.commit();

	            		System.out.println("Officer with ID " + officerIdToDelete + " deleted successfully.");
	        	} 
	        	else 
	        	{
	            		System.out.println("No Officer found with ID " + officerIdToDelete);
	        	}
	    } 
	    catch (ConstraintViolationException ex) 
	    {
	        // Handle foreign key constraint violation
	        System.out.println("Warning: Cannot delete Officer with ID " + officerIdToDelete + " due to existing references in other tables.");
	    }
	    catch (Exception e) 
	    {
	        System.out.println("Cannot delete Officer with ID " + officerIdToDelete + " due to foreign key constraint.");
	    }
	}

	
	// Entity 2.Suspect - Information
	public static void SuspectInfo()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
	    	config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
    		int option;
		
    		while(true)
		{
			System.out.println("\n------------------------------------------------");
			System.out.println("|              Suspect details                 |");
			System.out.println("------------------------------------------------");
			System.out.println("|           1. Add Suspect Info                |");
			System.out.println("|         2. View Suspect Details              |");
			System.out.println("|        3. Update Suspect Details             |");
			System.out.println("|              4. Drop Suspect                 |");
			System.out.println("|           5. Back to main page               |");
			System.out.println("------------------------------------------------");

			System.out.println();
			System.out.print("Enter your choice: ");
			option = sc.nextInt();
			switch(option)
			{
			case 1:
				addSuspect();
				break;
			case 2:
				viewSuspect();
				break;
			case 3:
				updateSuspect();			
				break;
			case 4:
				dropSuspect();	
				break;
			case 5:
				return;
			
			default:
				System.out.println("Invalid! Choose a correct option.");
			}// close switch
		}// close while
	}// close function
	
	
	// Function to Add Suspect Details 
	public static void addSuspect()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
	    	config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
	    	Suspect sus = new Suspect();
    	
    		// Add Suspect ID
    		System.out.print("Suspect ID: ");
    		int suspect_id = sc.nextInt();
    	
	    	// Check if Suspect ID already exists
        	Suspect existingSuspect = session.get(Suspect.class, suspect_id);
        	if (existingSuspect != null) 
		{
            		System.out.println("Warning: Suspect with ID " + suspect_id + " already exists. Please enter a unique ID.");
            		return;
        	}
        	sus.setSuspect_id(suspect_id);
    	        
     		   sc.nextLine();
	    	// Add Suspect Name
	    	System.out.print("Suspect Name: ");
	    	String name = sc.nextLine();
	    	sus.setName(name);
    	
	    	// Add Suspect Gender
	    	System.out.print("Suspect Gender: ");
	    	String gender = sc.next();
	    	sus.setGender(gender);
    	
	    	sc.nextLine();
	    	// Add Suspect Address
	    	System.out.print("Suspect Address: ");
	    	String address = sc.nextLine();
	    	sus.setAddress(address);
    	
	    	// Add Suspect Contact info
	    	System.out.print("Suspect Contact-Info: ");
	    	long contact_info = sc.nextLong();
	    	sus.setContact_info(contact_info);
    	
	    	System.out.println("\nSuspect Details Inserted with\n   Suspect ID: "+suspect_id+"\n   Suspect Name: "+name+"\n   Suspect Gender: "+gender+"\n   Suspect Address: "+address+"\n  Suspect Contact-Info: "+contact_info);
	    	System.out.println("------------------------------------------------\n");
    	
	    	session.save(sus);
    		Transaction transaction = session.beginTransaction();
    		transaction.commit();
		
      }
	
	
	// Function to View Suspect Table
	public static void viewSuspect()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();

    		System.out.println("-----------------------------------------------------------------------------------");
    		System.out.println("|                             Suspect Details:                                    |");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("| Suspect_ID | Suspect_Name  | Suspect_Gender | Suspect_Address | Suspect_Contact |");
		System.out.println("-----------------------------------------------------------------------------------");
		
		// hql query to view Suspect table
		String hqlQuery = "from Suspect";
		List<Suspect> data = session.createQuery(hqlQuery, Suspect.class).list();
		
		for(Suspect s : data)
		{
			System.out.printf("| %-10d | %-13s | %-14s | %-15s | %-15d |\n", s.getSuspect_id(), s.getName(), s.getGender(), s.getAddress(), s.getContact_info());
		}
		
		System.out.println("-----------------------------------------------------------------------------------\n");
		
	}
	
	
	// Function to Update Suspect table
	public static void updateSuspect()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
    			
		System.out.println("------------------------------------------------");
		System.out.println("|          Update Suspect details              |");
		System.out.println("------------------------------------------------");
		System.out.println("|         1. Update Suspect Name               |");
		System.out.println("|        2. Update Suspect Address             |");
		System.out.println("|      3. Update Suspect Contact-Info          |");
		System.out.println("------------------------------------------------");

		System.out.println();
		System.out.print("Enter your choice: ");
		
		int option = sc.nextInt();
		switch(option)
		{
		case 1:
			updateSuspectName();
			break;
		case 2:
			updateSuspectAddress();
			break;
		case 3:
			updateSuspectContact();
			break;
						
		default:
			System.out.println("Invalid! Choose a correct option.");
		}// close switch
	}
		
	
	// Function to Update Suspect Name
	public static void updateSuspectName()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		// Enter ID for which name is to be updated
    		System.out.print("Suspect ID: ");
    		int suspect_id = sc.nextInt();
    	
    		Suspect sus = session.get(Suspect.class,suspect_id);
		Transaction transaction = session.beginTransaction();
		
		sc.nextLine();
		System.out.print("Enter Suspect's updated name for ID "+suspect_id+" : ");
		String name = sc.nextLine();
		
		sus.setName(name);
		session.update(sus);		
		System.out.println("\nName Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Update Suspect Address
	public static void updateSuspectAddress()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
	    	// Enter ID for which address is to be updated
	    	System.out.print("Suspect ID: ");
	    	int suspect_id = sc.nextInt();
    	
    		Suspect sus = session.get(Suspect.class,suspect_id);
		Transaction transaction = session.beginTransaction();
		
		sc.nextLine();
		System.out.print("Enter Suspect's updated address for ID "+suspect_id+" : ");
		String address = sc.nextLine();
		
		sus.setAddress(address);
		session.update(sus);		
		System.out.println("\nAddress Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Update Suspect Contact
	public static void updateSuspectContact()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		// Enter ID for which contact-info is to be updated
    		System.out.print("Suspect ID: ");
    		int suspect_id = sc.nextInt();
    	
    		Suspect sus = session.get(Suspect.class,suspect_id);
		Transaction transaction = session.beginTransaction();
		
		System.out.print("Enter Suspect's updated contact-info for ID "+suspect_id+" : ");
		long contact_info = sc.nextLong();
		
		sus.setContact_info(contact_info);
		session.update(sus);		
		System.out.println("\nContact-Info Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Drop Suspect Details
	public static void dropSuspect() 
	{
		// Hibernate Connection Specification
	    	Configuration config = new Configuration();
	    	config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();

	    	// Prompt the user to enter the Officer ID to drop
	    	System.out.print("Enter Suspect ID to drop: ");
	    	int suspectIdToDelete = sc.nextInt();
	    
	    	try
	    	{
	    		// Retrieve the Officer object based on the entered ID
	    		Suspect sus = session.get(Suspect.class, suspectIdToDelete);

	    	if (sus != null)
	    	{
	    		// Begin transaction
	    		Transaction transaction = session.beginTransaction();
	    		
	    		// Delete the Officer
	    		session.delete(sus);
	    		
	    		// Commit the transaction
	    		transaction.commit();
	    		
	    		System.out.println("Suspect with ID " + suspectIdToDelete + " deleted successfully.");
	    	}
	    	else
	    	{
	    		System.out.println("No Suspect found with ID " + suspectIdToDelete);
	    	}
	    }
	    catch (Exception e) 
	    {
	        System.out.println("Cannot delete Suspect with ID " + suspectIdToDelete + " due to foreign key constraint.");
	    }
	}
	
	
	// Entity 3.Victim - Information
	public static void VictimInfo()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
    		int option;
		
    		while(true)
		{
			System.out.println("\n------------------------------------------------");
			System.out.println("|               Victim details                 |");
			System.out.println("------------------------------------------------");
			System.out.println("|             1. Add Victim Info               |");
			System.out.println("|           2. View Victim Details             |");
			System.out.println("|          3. Update Victim Details            |");
			System.out.println("|               4. Drop Victim                 |");
			System.out.println("|            5. Back to main page              |");
			System.out.println("------------------------------------------------");

			System.out.println();
			System.out.print("Enter your choice: ");
			option = sc.nextInt();
			switch(option)
			{
			case 1:
				addVictim();
				break;
			case 2:
				viewVictim();
				break;
			case 3:
				updateVictim();
				break;
			case 4:
				dropVictim();
				break;
			case 5:
				return;
			
			default:
				System.out.println("Invalid! Choose a correct option.");
			}// close switch
		}// close while
	}// close function
	
	
	//Function to Add Victim Details
	public static void addVictim()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		Victim v = new Victim();
    	
    		// Add Victim ID
    		System.out.print("Victim ID: ");
    		int victim_id = sc.nextInt();
    	
	    	// Check if Victim ID already exists
	        Victim existingVictim = session.get(Victim.class, victim_id);
	        if (existingVictim != null) 
	        {
            		System.out.println("Warning: Victim with ID " + victim_id + " already exists. Please enter a unique ID.");
            		return;
        	}
    		v.setVictim_id(victim_id);
    	
    		sc.nextLine();
    		// Add Victim Name
    		System.out.print("Victim Name: ");
    		String name = sc.nextLine();
    		v.setName(name);
    	
    		// Add Victim Gender
    		System.out.print("Victim Gender: ");
    		String gender = sc.next();
    		v.setGender(gender);
	    	
	    	sc.nextLine();
	    	// Add Victim Address
	    	System.out.print("Victim Address: ");
	    	String address = sc.nextLine();
	    	v.setAddress(address);
    	
	    	// Add Victim Contact-info
	    	System.out.print("Victim Contact-Info: ");
	    	long contact_info = sc.nextLong();
	    	v.setContact_info(contact_info);
    	
	    	System.out.println("\nVictim Details Inserted with\n   Victim ID: "+victim_id+"\n   Victim Name: "+name+"\n   Victim Gender: "+gender+"\n   Victim Address: "+address+"\n  Victim Contact-Info: "+contact_info);
	    	System.out.println("------------------------------------------------\n");
	    	
	    	session.save(v);
	    	Transaction transaction = session.beginTransaction();
    		transaction.commit();
		
	}
	
	
	// Function to view Victim table 
	public static void viewVictim()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
	    	config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
    		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("|                               Victim Details                                    |");		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("| Victim_ID |  Victim_Name  | Victim_Gender |  Victim_Address  |  Victim_Contact  |");
		System.out.println("-----------------------------------------------------------------------------------");

		// hql query to view table
		String hqlQuery = "from Victim";
		List<Victim> data = session.createQuery(hqlQuery, Victim.class).list();
		
		for(Victim v : data)
		{
			System.out.printf("| %-9d | %-13s | %-13s | %-16s | %-16d |\n", v.getVictim_id(), v.getName(), v.getGender(), v.getAddress(), v.getContact_info());
		}
		
		System.out.println("-----------------------------------------------------------------------------------");
		
	}
	
	
	// Function to Update Victim Details
	public static void updateVictim()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
	    	
    		System.out.println("\n------------------------------------------------");
    		System.out.println("|            Update Victim details             |");
		System.out.println("------------------------------------------------");
		System.out.println("|            1. Update Victim Name             |");
		System.out.println("|           2. Update Victim Address           |");
		System.out.println("|         3. Update Victim Contact-Info        |");
		System.out.println("------------------------------------------------");
		
		System.out.println();
		System.out.print("Enter your choice: ");
		
		int option = sc.nextInt();
		switch(option)
		{
		case 1:
			updateVictimName();
			break;
		case 2:
			updateVictimAddress();
			break;
		case 3:
			updateVictimContact();
			break;
				
		default:
			System.out.println("Invalid! Choose a correct option.");		
		}// close switch
	}
	
	
	// Function to Update Victim Name
	public static void updateVictimName()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		// Enter ID for which name is to be updated 
    		System.out.print("Victim ID: ");
    		int victim_id = sc.nextInt();
    	
	    	Victim v = session.get(Victim.class,victim_id);
		Transaction transaction = session.beginTransaction();
		
		sc.nextLine();
		System.out.print("Enter Victim's updated name for ID "+victim_id+" : ");
		String name = sc.nextLine();
		
		v.setName(name);
		session.update(v);		
		System.out.println("\nName Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Update Victim Address
	public static void updateVictimAddress()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		// Enter ID for which address is to be updated
    		System.out.print("Victim ID: ");
    		int victim_id = sc.nextInt();
    	
    		Victim v = session.get(Victim.class,victim_id);
		Transaction transaction = session.beginTransaction();
		
		sc.nextLine();
		System.out.print("Enter Victim's updated address for ID "+victim_id+" : ");
		String address = sc.nextLine();
		
		v.setAddress(address);
		session.update(v);		
		System.out.println("\nAddress Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Update Victim Contact
	public static void updateVictimContact()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
	    	config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
    		// Enter ID for which contact-info is to be updated
    		System.out.print("Victim ID: ");
    		int victim_id = sc.nextInt();
    	
    		Victim v = session.get(Victim.class,victim_id);
		Transaction transaction = session.beginTransaction();
		
		System.out.print("Enter Victim's updated contact-info for ID "+victim_id+" : ");
		long contact_info = sc.nextLong();
		
		v.setContact_info(contact_info);
		session.update(v);		
		System.out.println("\nContact-Info Updated Successfully.");
		
		transaction.commit();
		
	}
	
	
	// Function to Drop Victim Details
	public static void dropVictim() 
	{
		// Hibernate Connection Specification
	    	Configuration config = new Configuration();
	    	config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();

	    	// Prompt the user to enter the Vitcim ID to drop
	    	System.out.print("Enter Victim ID to drop: ");
	    	int victimIdToDelete = sc.nextInt();

	    	// Retrieve the Victim object based on the entered ID
	    	Victim v = session.get(Victim.class, victimIdToDelete);

	    	try
	    	{
		    	if (v != null) 
	    		{
		    		// Begin transaction
	    			Transaction transaction = session.beginTransaction();
	    		
	    			// Delete the Victim
	    			session.delete(v);
	    		
	    			// Commit the transaction
	    			transaction.commit();
	    		
	    			System.out.println("Victim with ID " + victimIdToDelete + " deleted successfully.");
	    		}
	    		else
	    		{
		    		System.out.println("No victim found with ID " + victimIdToDelete);
	    		}
	    	}
	    catch (Exception e) 
	    {
	        System.out.println("Cannot delete Victim with ID " + victimIdToDelete + " due to foreign key constraint.");
	    }
	}
	
	
	// Entity 4.Cases - Information
	public static void CasesInfo()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		while(true)
		{
    			System.out.println("\n------------------------------------------------");
			System.out.println("|                 Case details                 |");
			System.out.println("------------------------------------------------");
			System.out.println("|               1. Add Case Info               |");
			System.out.println("|             2. View Case Details             |");
			System.out.println("|            3. Update Case Details            |");
			System.out.println("|             4. Back to main page             |");
			System.out.println("------------------------------------------------");

			System.out.println();
			System.out.print("Enter your choice: ");
			int option = sc.nextInt();
			switch(option)
			{
			case 1:
				addCase();
				break;
			case 2:
				viewCase();
				break;
			case 3:
				updateCase();
				break;
			case 4:
				return;
			
			default:
				System.out.println("Invalid! Choose a correct option.");
			}// close switch
		}// close while 
	}// close function
	
	
	// Function to Add Cases Details
	public static void addCase() {
	    // Hibernate Connection Specification
	    Configuration config = new Configuration();
	    config.configure();
	    SessionFactory factory = config.buildSessionFactory();
	    Session session = factory.openSession();

	    Cases c = new Cases();

	    // To catch exceptions, non-existing foreign keys, use try-catch block
	    try 
	    {
	    	Transaction transaction = session.beginTransaction();
	        // Add Case ID
	        System.out.print("Case ID: ");
	        int case_id = sc.nextInt();

	        // Check if Cases ID already exists
	        Cases existingCase = session.get(Cases.class, case_id);
	        if (existingCase != null) 
	        {
	            System.out.println("Warning: Case with ID " + case_id + " already exists. Please enter a unique ID.");
	            return;
	        }
	        c.setCase_id(case_id);

	        // Add Officer ID
	        System.out.print("Officer ID: ");
	        int officer_id = sc.nextInt();
	        Officer officer = session.get(Officer.class, officer_id);

	        // To check if entered ID exists or not
	        if (officer == null) 
	        {
	            throw new ObjectNotFoundException(officer_id, "Officer");
	        }
	        c.setOfficer(officer);

	        // Add Suspect ID
	        System.out.print("Suspect ID: ");
	        int suspect_id = sc.nextInt();
	        Suspect suspect = session.get(Suspect.class, suspect_id);

	        // To check if entered ID exists or not
	        if (suspect == null) 
	        {
	            throw new ObjectNotFoundException(suspect_id, "Suspect");
	        }
	        c.setSuspect(suspect);

	        // Add Victim ID
	        System.out.print("Victim ID: ");
	        int victim_id = sc.nextInt();
	        Victim victim = session.get(Victim.class, victim_id);

	        // To check if entered ID exists or not
	        if (victim == null) 
	        {
	            throw new ObjectNotFoundException(victim_id, "Victim");
	        }
	        c.setVictim(victim);

	        sc.nextLine();
	        // Add Case title
	        System.out.print("Case Title: ");
	        String case_title = sc.nextLine();
	        c.setCase_title(case_title);

	        // Add Case Status
	        System.out.print("Case Status: ");
	        String status = sc.next();
	        c.setStatus(status);

	        sc.nextLine();
	        System.out.print("Case Date (YYYY-MM-DD): ");
	        String reported_date = sc.nextLine();
	        c.setReported_date(reported_date);

	        System.out.println("Case Details Inserted with\n   Case ID: " + case_id + "\n   Officer ID: " + officer_id
	                + "\n   Suspect ID: " + suspect_id + "\n   Victim ID: " + victim_id + "\n   Case Title: " + case_title
	                + "\n   Case Status: " + status + "\n   Reported Date: " + reported_date);
	        System.out.println("------------------------------------------------\n");

	        session.save(c);
	        transaction.commit();
	    } 
	    catch (ObjectNotFoundException e) 
		    {
	        	System.out.println("Error: " + e.getMessage() + " not found.");
	    } 
	    catch (Exception e) 
		    {
	        System.out.println("Error: " + e.getMessage());
	    }
	}

	
	
	// Function to view Case table
	public static void viewCase()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
	    	config.configure();
		SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
	    	System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|                                                   Case Details:                                                     |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|  Case_ID | Officer_ID | Suspect_ID |  Victim_ID  |       Case_Title       |     Reported_Date      |  Case_Status   |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		
		// hql query to fetch Cases table
		String hqlQuery = "from Cases";
		List<Cases> data = session.createQuery(hqlQuery, Cases.class).list();
		
		for(Cases c : data)
		{
			System.out.printf("| %-8d | %-10d | %-10d | %-11d | %-22s | %-22s | %-14s |\n", c.getCase_id(), c.getOfficer().getOfficer_id(), c.getSuspect().getSuspect_id(), c.getVictim().getVictim_id(), c.getCase_title(), c.getReported_date(), c.getStatus());
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		
	}
	
	
	// Function to Update Cases Details
	public static void updateCase()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
    	
    		System.out.println("\n------------------------------------------------");
    		System.out.println("|            Update Case details               |");
		System.out.println("------------------------------------------------");
		System.out.println("|           1. Update Cases Title              |");
		System.out.println("|          2. Update Reported Date             |");
		System.out.println("|          3. Update Cases Status              |");		
		System.out.println("------------------------------------------------");
		
		System.out.println();
		System.out.print("Enter your choice: ");
		int option = sc.nextInt();
		switch(option)
		{
		case 1:
			updateCaseTitle();
			break;
		case 2:
			updateReportedDate();
			break;
		case 3:
			updateCaseStatus();
			break;
		case 4:
			return;
			
		default:
			System.out.println("Invalid! Choose a correct option.");		
		}// close switch
	}
	
	
	// Function to Update Case Title
	public static void updateCaseTitle()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
	    	// Enter ID for which Title is to be updated
	    	System.out.print("Case ID: ");
	    	int case_id = sc.nextInt();
    	
	    	Cases c = session.get(Cases.class,case_id);
		Transaction transaction = session.beginTransaction();
		
		sc.nextLine();
		System.out.print("Enter Case's updated title for ID "+case_id+" : ");
		String case_title = sc.nextLine();
		
		c.setCase_title(case_title);
		session.update(c);		
		System.out.println("\nTitle Updated Successfully.");
		
		transaction.commit();
		
	}
	
	// Function to Update Case Date
	public static void updateReportedDate()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
	  	config.configure();
	   	SessionFactory factory = config.buildSessionFactory();
	   	Session session = factory.openSession();
		
	   	// Enter ID for which Status is to be updated
	   	System.out.print("Case ID: ");
	   	int case_id = sc.nextInt();
	   	
	   	Cases c = session.get(Cases.class,case_id);
		Transaction transaction = session.beginTransaction();
		
		System.out.print("Enter Case's updated date for ID "+case_id+" : ");
		String reported_date = sc.next();
		
		c.setStatus(reported_date);
		session.update(c);		
		System.out.println("\nDate Updated Successfully.");
		
		transaction.commit();
			
	}
	
	// Function to Update Case Status
	public static void updateCaseStatus()
	{
		// Hibernate Connection Specification
		Configuration config = new Configuration();
    		config.configure();
    		SessionFactory factory = config.buildSessionFactory();
    		Session session = factory.openSession();
		
    		// Enter ID for which Status is to be updated
    		System.out.print("Case ID: ");
    		int case_id = sc.nextInt();
    	
    		Cases c = session.get(Cases.class,case_id);
		Transaction transaction = session.beginTransaction();
		
		System.out.print("Enter Case's updated status for ID "+case_id+" : ");
		String status = sc.next();
		
		c.setStatus(status);
		session.update(c);		
		System.out.println("\nStatus Updated Successfully.");
		
		transaction.commit();
		
	}

	
	public static void main(String[] args) 
	{
		Configuration config = new Configuration();
	    	config.configure();
	    	SessionFactory factory = config.buildSessionFactory();
	    	Session session = factory.openSession();
		
		while(true)
		{
			System.out.println("=====================================================================================");
			System.out.println("||                           CRIME MANAGEMENT SYSTEM                               ||");
			System.out.println("=====================================================================================");
			System.out.println("||                         1. Officer table details                                ||");
			System.out.println("||                         2. Suspect table details                                ||");
			System.out.println("||                         3. Victim table details                                 ||");
			System.out.println("||                          4. Case table details                                  ||");
			System.out.println("||                                 5. Exit                                         ||");
			System.out.println("=====================================================================================");
			
			System.out.println();
			System.out.print("Enter Your Choice: ");
			
			int option = sc.nextInt();			
			switch(option)
			{
			case 1:
				OfficerInfo();
				break;
			case 2:
				SuspectInfo(); 
				break;
			case 3:
				VictimInfo();
				break;
			case 4: 
				CasesInfo();
				break;
			case 5:
				return;
			
			default:
				System.out.println("Invalid! Enter a correct option.");
				break;
			}// close switch			
		}// close while
	}// close main
}

