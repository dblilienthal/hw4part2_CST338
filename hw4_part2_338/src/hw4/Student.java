package hw4;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student {

	private int studentID;
	private String name;
	private boolean isGraduate;
	private Enroll enroll;

	public Student(int studentID, String name, boolean isGraduate) {
		this.studentID = studentID;
		this.name = name;
		this.isGraduate = isGraduate;
	}

	public Student() {
		studentID = 11111;
		name = "NA";
		isGraduate = false;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGraduate() {
		return isGraduate;
	}

	public void setGraduate(boolean isGraduate) {
		this.isGraduate = isGraduate;
	}

	public Enroll getEnroll() {
		return enroll;
	}

	public double getAverage() {
		ArrayList<Enroll> t = School.getEnroll();
		double studentNum = 0;
		double gpaTotal = 0;
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).getStudent().getStudentID() == this.getStudentID()) {
				if (t.get(i).getGrade() != 0) {
					gpaTotal += t.get(i).getGrade();
					studentNum++;
				}
			}
		}
		if (studentNum == 0) {
			return 0.0;
		}
		double GPA = gpaTotal / studentNum;
		DecimalFormat df = new DecimalFormat("#.##");
		double GPARound = Double.valueOf(df.format(GPA));
		return GPARound;
	}

	/*
	 * This toString method will check to see if the student is graduated. If the student has not
	 * graduated yet, then it will toString the student ID number, their name, the number of courses
	 * they are enrolled in (along with their grade in the course) and the course's average grade.
	 * If the student is already graduated, then it only prints out the student's ID along with
	 * "no student information".
	 */
	
	@Override
	public String toString() {
		if (isGraduate() == false) {
			String avg = String.valueOf(this.getAverage());
			ArrayList<Enroll> e = School.getEnroll();
			ArrayList<String> c = new ArrayList<String>();
			String studentCourses;
			for (int i = 0; i < e.size(); i++) {
				if (e.get(i).getStudent().getStudentID() == this.studentID) {
					int courseNum = e.get(i).getCourse().getCourseID();
					double grade = e.get(i).getGrade();
					String letter_grade = e.get(i).getLetter_grade();

					String temp1 = String.valueOf(courseNum);
					String temp2 = String.valueOf(grade);
					c.add(temp1 + ": " + temp2 + " " + "(" + letter_grade + ")");
				}
			}
			studentCourses = String.join("\n", c);
			return "Student Number: " + studentID + 
					"\nName: " + name + 
					"\nCourses Enrolled:\n" + studentCourses
					+ "\nCourse Average: " + avg + "\n";
		}
		else {
			return "studentID: " + studentID + "\nNo student information.";
		}
	}
}
