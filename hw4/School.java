package hw4;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//import Lab5.account;

public class School {

	private String name;
	private ArrayList<Student> students;
	private ArrayList<Course> courses;
	private ArrayList<Instructor> faculty;
	private static ArrayList<Enroll> enroll;

	public School(String name) {
		this.name = name;
		students = new ArrayList<>();
		courses = new ArrayList<>();
		faculty = new ArrayList<>();
		enroll = new ArrayList<>();
	}

	public void readData(String filename) {
		Scanner input;
		String[] arrInput;
		try {
			input = new Scanner(new File(filename));
			while (input.hasNext()) {
				int instructorRow = input.nextInt(); // Adding the instructor
				input.nextLine();
				for (int i = 0; i < instructorRow; i++) {
					String row = input.nextLine();
					arrInput = row.split(",");
					int id = Integer.parseInt(arrInput[0]);
					String name = arrInput[1];
					String email = arrInput[2];
					String phone = arrInput[3];
					addInstructor(id, name, email, phone);
				}
				int courseRow = input.nextInt(); //adding courses
				input.nextLine();
				for (int i = 0; i< courseRow; i++) {
					String row = input.nextLine();
					arrInput = row.split(",");
					int id = Integer.parseInt(arrInput[0]);
					String title = arrInput[1];
					int instructor_id = Integer.parseInt(arrInput[2]);
					String location = arrInput[3];
					addCourse(id, title, instructor_id, location);
				}
				int studentRow = input.nextInt(); //adding students
				input.nextLine();
				for (int i = 0; i< studentRow; i++) {
					String row = input.nextLine();
					arrInput = row.split(",");
					int id = Integer.parseInt(arrInput[0]);
					String name = arrInput[1];
					int course_id = Integer.parseInt(arrInput[2]);
					double grade = Double.parseDouble(arrInput[3]);
					String letter_grade = arrInput[4];
					addStudent(id, name, course_id, grade, letter_grade);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("File read complete.");
	}

	public void schoolInfo() {
		System.out.println("School Name: " + name);
		System.out.println("Instructor Information");
		System.out.println(Arrays.toString(faculty.toArray()));

		System.out.println("Course Information");
		System.out.println(Arrays.toString(courses.toArray()));

		System.out.println("Student Information");
		System.out.println(Arrays.toString(getStudents().toArray()));
	}

	public boolean addInstructor(int id, String name, String email, String phone) {
		Instructor i = getInstructorId(id);
		if (i != null) {
			System.out.println("Instructor addition failed – Employee number already used.");
			return false;
		}
		i = new Instructor(id, name, email, phone);
		faculty.add(i);
		System.out.printf("Instructor added: %d, %s, %s, %s\n", i.getInstructorId(), i.getInstructorName(),
				i.getInstuctorEmail(), i.getInstructorPhone());
		return true;
	}

	public boolean addCourse(int id, String title, int instructor_id, String location) {
		Course c = getCourseID(id);
		Instructor i = getInstructorId(instructor_id);
		if (c != null) {
			System.out.println("Course addition failed - Course number already used.");
			return false;
		}
		// checks to make sure that the instructor ID is a real instructor
		if (i == null) {
			System.out.println("Instructor addition failed – Employee number not found.");
			return false;
		}
		c = new Course(id, title, instructor_id, location);
		courses.add(c);
		System.out.printf("Course added: %d, %s, %d, %s \n", c.getCourseID(), c.getCourseTitle(), c.getInstructorId(),
				c.getLocation());

		return true;
	}

	public boolean addStudent(int id, String name, int course_id, double grade, String letter_grade) {
		// addind a student if the id doesnt exists, if the id exists but then the name
		// is different, then that would be an error
		// if it already exists then create an enrollment, but if it doesnt exist then
		// create a student and an enrollment.
		Student s = getStudentID(id);
		Course c = getCourseID(course_id);
		if (c == null) {
			System.out.println("There is no course with this ID");
			return false;
		}
		if (s == null) {
			s = new Student(id, name, false);
			getStudents().add(s);
			Enroll e = new Enroll(letter_grade, grade, c, s);
			enroll.add(e);
			System.out.printf("Student %d, %s Successfully Added \n", id, name);
		} else {
			if (!s.getName().equals(name)) {
				System.out.println("Add student failed - Student name does not equal Student ID");
				return false;
			}
			Enroll e = new Enroll(letter_grade, grade, c, s);
			enroll.add(e);
		}
		return true;
	}

	public Instructor getInstructorId(int id) {
		for (Instructor i : faculty) {
			if (i.getInstructorId() == id) {
				return i;
			}
		}
		return null;
	}

	public Course getCourseID(int id) {
		for (Course c : courses) {
			if (c.getCourseID() == id) {
				return c;
			}
		}
		return null;
	}

	public Student getStudentID(int id) {
		for (Student s : students) {
			if (s.getStudentID() == id) {
				return s;
			}
		}
		return null;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public Student getStudentName(String name) {
		for (Student s : getStudents()) {
			if (s.getName() == name) {
				return s;
			}
		}
		return null;
	}

	public static ArrayList<Enroll> getEnroll() {
		return enroll;
	}

	public void courseInfo() {
		// this method will be complete in part 3
	}

	public void courseInfo(int id) {
		// this method will be complete in part 3
	}

	public Course getCourse(int courseId) {
		// this method will be complete in part 3
		Course c = getCourseID(courseId);
		if (c == null) {
			System.out.println("Course Does Not Exist");
		}
		return c;
	}

	public boolean deleteCourse(int id) {
		// this method will be complete in part 3
		Course c = getCourseID(id);
		
		List<Course> course = new ArrayList<Course>(courses);
		List<Enroll> enrollment = new ArrayList<Enroll>(enroll);
		if (course.isEmpty() || enroll.isEmpty()) {
			//course.remove(course.indexOf(c));
			courses.remove(course.indexOf(c));
			return true;
		}
		return false;
	}

	public Student getStudentInfo(int id) {
		Student s = getStudentID(id);
		if (getStudents().contains(s)) {
			return s;
		}
		return null;
	}

	public boolean graduateStudent(int id) {
		// this method will be complete in part 3
		int j = enroll.size();
		Student s = getStudentID(id);
		if (s == null) {
			System.out.println("Student graduation failed – Non-existing student.");
			return false;
		}
		if (!s.isGraduate() == true) {
			s.setGraduate(true);
			//need to do a double loop. The first loop ensures that the second loop will loop through enough times
			for (int k = 0; k < j; k++) {
				for (int i = 0; i < enroll.size(); i++) {
					//remove student from the enrollment list
					if (enroll.get(i).getStudent().getStudentID() == id) {
						enroll.remove(i);
					}
				}
			}
			return true;
		}		
		return false;
	}

	public boolean register(int studentId, int courseId) {
		Student s = getStudentID(studentId);
		Course c = getCourseID(courseId);
		if (s == null || c == null) {
			return false;
		}
		Enroll e = new Enroll("IP", 0, c, s);
		enroll.add(e);
		return true;
	}

	public boolean grade(int studentId, int courseId, double grade, String letter_grade) {
		// this method will add a students grade to the enrollment List
		Student s = getStudentID(studentId);
		Course c = getCourseID(courseId);
		if (s == null || c == null){
			return false;
		}
		for (int i = 0; i<enroll.size(); i++) {
			if(enroll.get(i).getStudent().getStudentID() == studentId && enroll.get(i).getCourse().getCourseID() == courseId) {
				enroll.remove(i);
			}
		}
		Enroll e = new Enroll(letter_grade, grade, c, s);
		enroll.add(e);
		return true;
	}


	public boolean drop(int studentId, int courseId) {
		// this method will be complete in part 3
		Student s = getStudentID(studentId);
		Course c = getCourseID(courseId);
		if (s == null || c == null) {
			return false;
		}
		ArrayList<Enroll> enrolled = new ArrayList<Enroll>(enroll);
		ArrayList<Course> course = new ArrayList<Course>(courses);
		for (int i = 0; i<enroll.size(); i++) {
			if(enroll.get(i).getStudent().getStudentID() == studentId && enroll.get(i).getCourse().getCourseID() == courseId) {
				//enroll.remove(s.getStudentID());
				enroll.remove(i);
				return true;
			}
		}
		
		return false;
	}

	public boolean assign(int instructorId, int courseId) {
		Instructor i = getInstructorId(instructorId);
		Course c = getCourseID(courseId);
		if (i == null || c == null ) {
			return false;
		}
		c.setInstructorId(courseId);//set the course to a new courseId
		c.setInstructorId(instructorId);//Set the course to a new Instructor 
		return true;

	}

	public boolean unassign(int instructorId, int courseId) {
		Instructor i = getInstructorId(instructorId);
		Course c = getCourseID(courseId);
		if (i == null || c == null || c.getInstructorId() != instructorId) {
			return false;
		}
		instructorId = 0;
	    c.setInstructorId(instructorId); // set the new instructor id to 0
		return true;
	}

	@Override
	public String toString() {
		return "School [name=" + name + ", students=" + students + ", courses=" + courses + ", faculty=" + faculty
				+ ", enroll=" + enroll + "]";
	}
	
	public static void main(String[] args) {
		Course c = new Course();
		Course c2 = new Course(338, "CST338 Software Design", 1, "BIT105");
		System.out.println(c);
		System.out.println(c2);

		School school = new School("SCD");
		school.addCourse(338, "CST338 Software Design", 1, "BIT105");
	}

}
