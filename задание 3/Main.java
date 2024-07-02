/*Создать классы, спецификации которых приведены ниже. Определить конструкторы и методы setТип(), getТип(), toString(). Определить дополнительно
методы в классе, создающем массив объектов. Задать критерий выбора данных
и вывести эти данные на консоль. В каждом классе, обладающем информацией,
должно быть объявлено несколько конструкторов.
1. Student: id, Фамилия, Имя, Отчество, Дата рождения, Адрес, Телефон,
Факультет, Курс, Группа.
Создать массив объектов. Вывести:
a) список студентов заданного факультета;
b) списки студентов для каждого факультета и курса;
c) список студентов, родившихся после заданного года;
d) список учебной группы*/

import java.time.LocalDate;
import java.util.Arrays;

class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;

    public Student(int id, String lastName, String firstName, String middleName, LocalDate birthDate, String address, String phone, String faculty, int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public int getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getFaculty() { return faculty; }
    public int getCourse() { return course; }
    public String getGroup() { return group; }

    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course=" + course +
                ", group='" + group + '\'' +
                '}';
    }
}

class StudentManager {
    private Student[] students;
    private int count;

    public StudentManager() {
        students = new Student[10];
        count = 0;
    }

    public void addStudent(Student student) {
        if (count == students.length) {
            students = Arrays.copyOf(students, students.length * 2);
        }
        students[count++] = student;
    }

    public void printStudentsByFaculty(String faculty) {
        for (int i = 0; i < count; i++) {
            if (students[i].getFaculty().equalsIgnoreCase(faculty)) {
                System.out.println(students[i]);
            }
        }
    }

    public void printStudentsByFacultyAndCourse() {
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    public void printStudentsBornAfterYear(int year) {
        for (int i = 0; i < count; i++) {
            if (students[i].getBirthDate().getYear() > year) {
                System.out.println(students[i]);
            }
        }
    }

    public void printStudentsByGroup(String group) {
        for (int i = 0; i < count; i++) {
            if (students[i].getGroup().equalsIgnoreCase(group)) {
                System.out.println(students[i]);
            }
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        manager.addStudent(new Student(1, "Ульзутуев", "Жамсо", "Цырендоржиевич", LocalDate.of(2005, 10, 30), "ул. Жердева, 27", "1234567890", "ПИиИИ", 1, "Б7632"));
        manager.addStudent(new Student(2, "Щербаков", "Петр", "Петрович", LocalDate.of(1999, 5, 15), "ул. Ленина, 2", "0987654321", "Математика", 2, "Б102"));
        manager.addStudent(new Student(3, "Сидоров", "Сидор", "Сидорович", LocalDate.of(2001, 3, 20), "ул. Ленина, 3", "1111111111", "Физика", 1, "Б7632"));

        System.out.println("Список студентов заданного факультета (ПИиИИ):");
        manager.printStudentsByFaculty("ПИиИИ");

        System.out.println("\nСписки студентов для каждого факультета и курса:");
        manager.printStudentsByFacultyAndCourse();

        System.out.println("\nСписок студентов, родившихся после 2000 года:");
        manager.printStudentsBornAfterYear(2000);

        System.out.println("\nСписок учебной группы (Б7632):");
        manager.printStudentsByGroup("Б7632");
    }
}