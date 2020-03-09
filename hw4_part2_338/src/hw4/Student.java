package hw4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
			//&& !t.get(i).getLetter_grade().equals("IP")
			if (t.get(i).getStudent().getStudentID() == this.getStudentID()) { 
				/*if (!t.get(i).getLetter_grade().equals("IP")) {
					gpaTotal += t.get(i).getGrade();
					studentNum++;
				}*/
				gpaTotal += t.get(i).getGrade();
				studentNum++;
			}
		}
		double GPA = gpaTotal/studentNum;
		DecimalFormat df = new DecimalFormat("#.##");      
		double GPARound = Double.valueOf(df.format(GPA));
		return GPARound;
	}

	@Override
	public String toString() {
		if (isGraduate() == false) {
			return "Student [studentID=" + studentID + ", name=" + name + ", isGraduate=" + isGraduate + "] \n";
		}
		else {
			return "studentID= " + studentID + " No student information.";
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		School school = new School("SCD");
		school.addInstructor(1,  "tom", "tom@csumb.edu", "813-111-1212");
		school.addCourse(338, "CST338 Software Design", 1, "BIT105"); 
		school.addCourse(438,  "Software Engineering", 1,  "BIT104");
		school.addStudent(1001, "Grace", 338, 68, "D"); 
	
		
		
		// Grace does not have a grade. So student average should be 0.0
		Student grace = school.getStudentInfo(1001);
		double gpa = grace.getAverage();
		System.out.println(grace);
		System.out.println(gpa);

	}

}
