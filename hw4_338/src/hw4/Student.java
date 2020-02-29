package hw4;

public class Student {

	private int studentID;
	private String name;
	private boolean isGraduate;
	
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

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", name=" + name + ", isGraduate=" + isGraduate + "] \n";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
