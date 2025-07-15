package app;

import dao.Student;
import dao.StudentService;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Student by ID");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    System.out.print("Course: ");
                    sc.nextLine(); // consume newline
                    String course = sc.nextLine();
                    service.addStudent(new Student(name, age, course));
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    Student student = service.getStudent(id);
                    System.out.println(student != null ? student : "Student not found.");
                    break;

                case 3:
                    List<Student> list = service.getAllStudents();
                    for (Student s : list) {
                        System.out.println(s);
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    Student existing = service.getStudent(updateId);
                    if (existing != null) {
                        System.out.print("New Name: ");
                        sc.nextLine();
                        existing.setName(sc.nextLine());
                        System.out.print("New Age: ");
                        existing.setAge(sc.nextInt());
                        System.out.print("New Course: ");
                        sc.nextLine();
                        existing.setCourse(sc.nextLine());
                        service.updateStudent(existing);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    service.deleteStudent(deleteId);
                    break;

                case 6:
                    JPAUtil.close();
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
