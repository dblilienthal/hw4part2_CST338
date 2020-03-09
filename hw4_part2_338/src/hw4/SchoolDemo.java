package hw4;

public class SchoolDemo {
	public static void main(String[] args) {
		School SCD = new School("SCD");

		System.out.println("===== Read Data 1 =====");
		SCD.readData("test1.txt");
		System.out.println("\n===== School Info 1 =====");
		SCD.schoolInfo();
		
		System.out.println("===== Read Data 2 =====");
		SCD.readData("test2.txt");
		System.out.println("\n===== School Info 2 =====");
		SCD.schoolInfo();
		
		System.out.println(SCD.addInstructor(700, "E. Tao", "tao@csumb.edu", "777-777-1234"));
		System.out.println(SCD.addCourse(300, "CST300 – ProSem", 700, "BIT110"));
		System.out.println(SCD.addCourse(231, "CST231 – Intro C++", 100, "BIT104"));
		System.out.println("\n===== Failed Course Addition Duplicate Course=====");
		System.out.println(SCD.addCourse(306, "CST306 – GUI Dev", 250, "BIT120"));
		System.out.println("\n===== Failed Course Addition Instructor does not exist=====");
		System.out.println(SCD.addCourse(499, "CST499 – iOS Dev", 150, "BIT104"));

		System.out.println("\n===== Good Bye! =====");
	}
}
