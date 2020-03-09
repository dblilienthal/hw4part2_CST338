package hw4;

public class Instructor {

	private int instructorId;
	private String instructorName;
	private String instructorEmail;
	private String instructorPhone;
	
	public Instructor(int instructorId, String instructorName, String instuctorEmail, String instructorPhone) {
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.instructorEmail = instuctorEmail;
		this.instructorPhone = instructorPhone;
	}
	
	public Instructor() {
		instructorId = 0000;
		instructorName = "NA";
		instructorEmail = "NA";
		instructorPhone = "NA";
		
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getInstuctorEmail() {
		return instructorEmail;
	}

	public void setInstuctorEmail(String instuctorEmail) {
		this.instructorEmail = instuctorEmail;
	}

	public String getInstructorPhone() {
		return instructorPhone;
	}

	public void setInstructorPhone(String instructorPhone) {
		this.instructorPhone = instructorPhone;
	}

	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", instructorName=" + instructorName + ", instructorEmail="
				+ instructorEmail + ", instructorPhone=" + instructorPhone + "] \n";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
