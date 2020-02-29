package hw4;

public class Course {

	private int courseID;
	private String courseTitle;
	private int instructorId;
	private String location;

	
	public Course(int courseID, String courseTitle, int instructor_id, String location) {
		this.courseID = courseID;
		this.courseTitle = courseTitle;
		this.instructorId = instructor_id;
		this.location = location;
	}
	
	//default constructor
	public Course() {
		courseID = 0000;
		courseTitle = "NA";
		instructorId = 0000;
		location = "NA";
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void updateLocation(String location) {
		this.location = location;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseTitle=" + courseTitle + ", location=" + location
				+ ", instructor_id=" + instructorId + "]" + "\n";
	}

	public static void main(String[] args) {
		Course c = new Course();
		Course c2 = new Course(338, "CST338 Software Design", 1, "BIT105");
		System.out.println(c);
		System.out.println(c2);

	}

}
