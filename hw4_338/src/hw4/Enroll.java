package hw4;

public class Enroll {

	private String letter_grade;
	private double grade;
	private Course course;
	private Student student;
	
	public Enroll(String letter_grade, double grade, Course course, Student student) {
		this.letter_grade = letter_grade;
		this.grade = grade;
		this.course = course;
		this.student = student;
	}

	public String getLetter_grade() {
		return letter_grade;
	}

	public void setLetter_grade(String letter_grade) {
		this.letter_grade = letter_grade;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Override
	public String toString() {
		return "Enroll [letter_grade=" + letter_grade + ", grade=" + grade + ", course=" + course + ", student="
				+ student + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
