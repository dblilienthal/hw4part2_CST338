package hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Course {

	private int courseID;
	private String courseTitle;
	private int instructorId;
	private String location;

	
	public Course(int courseID, String courseTitle, int instructorId, String location) {
		this.courseID = courseID;
		this.courseTitle = courseTitle;
		this.instructorId = instructorId;
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
	
	/*public School removeCourse(int courseID) {
		for (Course c: getCourses()) {
			
		}
	}*/

	public double getAverage() {
		ArrayList<Enroll> t = School.getEnroll();
		double studentNum = 0;
		double gpaTotal = 0;
		for (int i = 0; i < t.size(); i++) {
			//System.out.println(t.get(i).getCourse().getCourseID());
			//System.out.println("This course ID" + this.getCourseID());
			if (t.get(i).getCourse().getCourseID() == this.getCourseID()) { 
				gpaTotal += t.get(i).getGrade();
				studentNum++;
			}
		}
		return gpaTotal/studentNum;
	}
	
	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseTitle=" + courseTitle + ", location=" + location
				+ ", instructor_id=" + instructorId + "]" + "\n";
	}

	public static void main(String[] args) {
		
		School SCD = new School("SCD");
		//Student student1;
		SCD.readData("test1.txt");

		SCD.addStudent(5555, "Chris Watson", 205, 50, "F");
		SCD.addStudent(9999, "Mike Watson", 205, 100.0, "A");
		SCD.addStudent(8888, "Bob Otter", 205, 50, "F");
		SCD.addStudent(7777, "Alice Otter", 205, 100, "A");
		SCD.addStudent(7777, "Alice Otter", 306, 100, "A");
		
		System.out.println(SCD.getStudents());
		
		//System.out.println(SCD.getEnroll());
		
		List<Enroll> e = SCD.getEnroll();
		System.out.println("Size: "+ e.size());
		//System.out.println(e.listIterator(1));
		//e.listIterator();
		double studentNum = 0;
		double gpaTotal = 0;
		for (Enroll e2 : SCD.getEnroll()) {
			gpaTotal += e2.getGrade();
			studentNum++;
		}
		System.out.println(gpaTotal/studentNum);
		
		Course course = SCD.getCourse(205);
		System.out.println(course);
		System.out.println(course.getAverage());


		

	}
}
