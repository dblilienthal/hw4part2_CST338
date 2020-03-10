package hw4;

import java.util.ArrayList;

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

	/*
	 * This returns the average GPA of all the students enrolled in the course.
	 */
	
	public double getAverage() {
		ArrayList<Enroll> t = School.getEnroll();
		double studentNum = 0;
		double gpaTotal = 0;
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).getCourse().getCourseID() == this.getCourseID()) { 
				gpaTotal += t.get(i).getGrade();
				studentNum++;
			}
		}
		if (studentNum == 0) {
			return 0.0;
		}
		return gpaTotal/studentNum;
	}
	
	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseTitle=" + courseTitle + ", location=" + location
				+ ", instructor_id=" + instructorId + "]" + "\n";
	}
}
