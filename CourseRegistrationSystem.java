import java.util.ArrayList;
import java.util.List;

// Course class to store course information
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }

    public boolean registerStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        } else {
            return false; // Course is full
        }
    }

    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}

// Student class to store student information
class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (course.registerStudent(this)) {
            registeredCourses.add(course);
            System.out.println(name + " has registered for " + course.getTitle());
        } else {
            System.out.println("Sorry, " + course.getTitle() + " is full. Cannot register.");
        }
    }

    public void removeCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent(this);
            System.out.println(name + " has dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered in " + course.getTitle());
        }
    }
}

// Main course registration system
public class CourseRegistrationSystem {
    public static void main(String[] args) {
        // Creating courses
        Course c1 = new Course("CSC101", "Introduction to Programming", "Basic programming concepts", 30);
        Course c2 = new Course("MAT202", "Linear Algebra", "Fundamental concepts of linear algebra", 25);
        Course c3 = new Course("ENG301", "English Literature", "Literary analysis and critical thinking", 20);

        // Creating students
        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");

        // Registering students for courses
        s1.registerForCourse(c1);
        s1.registerForCourse(c2);
        s2.registerForCourse(c1);
        s2.registerForCourse(c3);

        // Displaying course listings
        System.out.println("Course Listing:");
        displayCourseDetails(c1);
        displayCourseDetails(c2);
        displayCourseDetails(c3);

        // Dropping a course
        s1.removeCourse(c2);

        // Displaying updated course listings
        System.out.println("\nUpdated Course Listing:");
        displayCourseDetails(c1);
        displayCourseDetails(c2);
        displayCourseDetails(c3);
    }

    public static void displayCourseDetails(Course course) {
        System.out.println("Course Code: " + course.getCourseCode());
        System.out.println("Title: " + course.getTitle());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Capacity: " + course.getCapacity());
        System.out.println("Available Slots: " + course.getAvailableSlots());
        System.out.print("Enrolled Students: ");
        List<Student> enrolledStudents = course.getEnrolledStudents();
        if (enrolledStudents.isEmpty()) {
            System.out.println("None");
        } else {
            for (Student student : enrolledStudents) {
                System.out.print(student.getName() + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
