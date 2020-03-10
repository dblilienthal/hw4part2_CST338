package hw4;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
	
	/*
	 * This method will read data from a text file and insert it into the appropriate list
	 */

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
	
	/*
	 * This will add an instructor to the instructor ArrayList
	 */

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
	
	/*
	 * This will add a course to the course ArrayList
	 */

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
	
	/*
	 * Adding a student if the id doesn't exists, if the id exists but then the name
	 * is different, then that would be an error if it already exists then create 
	 * an enrollment, but if it doesn't exist then create a student and an enrollment.
	 */

	public boolean addStudent(int id, String name, int course_id, double grade, String letter_grade) {
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
		System.out.println("Error instructor Id not found! Instructor Id: " + id);
		return null;
	}

	public Course getCourseID(int id) {
		for (Course c : courses) {
			if (c.getCourseID() == id) {
				return c;
			}
		}
		System.out.println("Error course Id not found! course Id: " + id);
		return null;
	}

	public Student getStudentID(int id) {
		for (Student s : students) {
			if (s.getStudentID() == id) {
				return s;
			}
		}
		System.out.println("Error student Id not found! Student Id: " + id);
		return null;
	}

	/*
	 * Returns the ArrayList of Students
	 */
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	/*
	 * Returns the ArrayList of courses
	 */
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/*
	 * Returns the students name
	 */
	
	public Student getStudentName(String name) {
		for (Student s : getStudents()) {
			if (s.getName() == name) {
				return s;
			}
		}
		System.out.println("Error student name not found! Student name: " + name);
		return null;
	}

	/*
	 * This returns the Enroll ArrayList
	 */
	
	public static ArrayList<Enroll> getEnroll() {
		return enroll;
	}

	/*
	 * Method prints a short summary of all courses
	 */
	
	public void courseInfo() {
		System.out.println("Number of Courses:" + courses.size());
		for (Course c: courses) {
			int temp = 0;
			for (Enroll e2: enroll) {
				if (c.getCourseID() == e2.getCourse().getCourseID()) {
					temp++;
				}
			}
			System.out.println(c.getCourseID() + ": " + temp + " enrolled" );
		}
	}

	public void courseInfo(int id) {
		Course c = getCourseID(id);
		Instructor i = getInstructorId(c.getInstructorId());
		System.out.println("Course Number: " + c.getCourseID());
		System.out.println("Instructor: " + i.getInstructorName());
		System.out.println("Course title: " + c.getCourseTitle());
		System.out.println("Room: " + c.getLocation());
		int temp = 0;
		for (Enroll e2: enroll) {
			if (c.getCourseID() == e2.getCourse().getCourseID()) {
				temp++;
			}
		}
		System.out.println("Total enrolled: " + temp);
		System.out.println("Course average " + c.getAverage());
	}

	/*
	 * Returns the courses
	 */
	
	public Course getCourse(int courseId) {
		Course c = getCourseID(courseId);
		if (c == null) {
			System.out.println("Course Does Not Exist");
		}
		return c;
	}

	/*
	 * This will delete a course from the courses ArrayList
	 */
	
	public boolean deleteCourse(int id) {
		Course c = getCourseID(id);
		List<Course> course = new ArrayList<Course>(courses);
		if (course.isEmpty() || enroll.isEmpty()) {
			courses.remove(course.indexOf(c));
			System.out.println("Course delete successful! Course Id: " + id);
			return true;
		}
		System.out.println("Course delete failed! Course not empty");
		return false;
	}

	/*
	 * Returns the student object
	 */
	
	public Student getStudentInfo(int id) {
		Student s = getStudentID(id);
		if (getStudents().contains(s)) {
			return s;
		}
		System.out.println("Error student not found! Student Id: " + id);
		return null;
	}

	/*
	 * This will unregister the student from all courses and mark the student as graduated. 
	 * If the student id is invalid or if the student has already graduated, print a failure 
	 * message and return false. 
	 */
	
	public boolean graduateStudent(int id) {
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
			System.out.println("Student graduation sucessful! Student Id: " + id);
			return true;
		}		
		return false;
	}

	/*
	 * This will assign a student to a course and add it to the enrollment ArrayList
	 */
	
	public boolean register(int studentId, int courseId) {
		Student s = getStudentID(studentId);
		Course c = getCourseID(courseId);
		if (s == null || c == null) {
			return false;
		}
		//This will replace a students letter grade and grade if the is re-taking the course
		for (int i = 0; i < enroll.size(); i++) {
			if (enroll.get(i).getStudent().getStudentID() == studentId && enroll.get(i).getCourse().getCourseID() == courseId) {
				enroll.get(i).setLetter_grade("IP");
				enroll.get(i).setGrade(0.0);
				return true;
			}	
		}
		Enroll e = new Enroll("IP", 0, c, s);
		enroll.add(e);
		return true;
	}

	/*
	 * This method will update the grade of a student enrolled in a course. 
	 */
	
	public boolean grade(int studentId, int courseId, double grade, String letter_grade) {
		Student s = getStudentID(studentId);
		Course c = getCourseID(courseId);
		if (s == null || c == null){
			System.out.println("Grade Failed! Student or Course does not exist!");
			return false;
		}
		//If the student is already enrolled in the class, this will replace the grade
		for (int i = 0; i<enroll.size(); i++) {
			if(enroll.get(i).getStudent().getStudentID() == studentId && enroll.get(i).getCourse().getCourseID() == courseId) {
				enroll.remove(i);
			}
		}
		Enroll e = new Enroll(letter_grade, grade, c, s);
		enroll.add(e);
		System.out.println("Grade entered succefully! Student Id: " + studentId + " Course Id: " + courseId + " Grade: " + grade + " Letter Grade: " + letter_grade);
		return true;
	}

	/*
	 * This method will remove a student from the enrollment ArrayList
	 */

	public boolean drop(int studentId, int courseId) {
		Student s = getStudentID(studentId);
		Course c = getCourseID(courseId);
		if (s == null || c == null) {
			System.out.println("Error! Drop Failed. Student Id or Course Id does not exist");
			return false;
		}
		for (int i = 0; i<enroll.size(); i++) {
			if(enroll.get(i).getStudent().getStudentID() == studentId && enroll.get(i).getCourse().getCourseID() == courseId) {
				enroll.remove(i);
				System.out.println("Drop Sucessfull! Student Id: " + studentId + " Course Id: " + courseId);
				return true;
			}
		}
		return false;
	}

	/*
	 * This will assign an instructor to a course using the instructor Id and the courseId
	 */
	
	public boolean assign(int instructorId, int courseId) {
		Instructor i = getInstructorId(instructorId);
		Course c = getCourseID(courseId);
		if (i == null || c == null ) {
			System.out.println("Assign failed! Instructor or Course does not exist.");
			return false;
		}
		c.setInstructorId(courseId);//set the course to a new courseId
		c.setInstructorId(instructorId);//Set the course to a new Instructor 
		System.out.println("Assign Sucessful! Instructor Id: " + instructorId + " Course Id: " + courseId);
		return true;

	}

	/*
	 * This method will unassign an instructor from a course. The instructor ID will be 0 
	 * when there is no instructor assigned.  
	 */
	
	public boolean unassign(int instructorId, int courseId) {
		Instructor i = getInstructorId(instructorId);
		Course c = getCourseID(courseId);
		if (i == null || c == null || c.getInstructorId() != instructorId) {
			System.out.println("Unassign failed! Instructor, Instructor Id, or Course does not exist!");
			return false;
		}
		instructorId = 0;
	    c.setInstructorId(instructorId); // set the new instructor id to 0
	    System.out.println("Unassign Sucessful! Instructor Id: " + instructorId + " Course Id:" + courseId);
		return true;
	}

	@Override
	public String toString() {
		return "School [name=" + name + ", students=" + students + ", courses=" + courses + ", faculty=" + faculty
				+ ", enroll=" + enroll + "]";
	}
}
