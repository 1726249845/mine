package controller;

import model.Student;
import storage.StudentDataStorage;
import java.util.List;

public class StudentController {
    private final StudentDataStorage studentDataStorage;
    // 构造函数：初始化学生数据存储
    public StudentController(StudentDataStorage studentDataStorage) {
        this.studentDataStorage = studentDataStorage;
    }
    // 添加学生：将新学生添加到存储中
    public void addStudent(Student student) {
        List<Student> students = studentDataStorage.loadStudents(); // 加载当前所有学生
        students.add(student); // 添加新学生
        studentDataStorage.saveStudents(students); // 保存更新后的学生列表
    }

    // 删除学生：根据学生ID从存储中删除学生
    public void deleteStudent(String studentId) {
        List<Student> students = studentDataStorage.loadStudents(); // 加载当前所有学生
        students.removeIf(student -> student.getStudentId().equals(studentId)); // 删除匹配的学生
        studentDataStorage.saveStudents(students); // 保存更新后的学生列表
    }

    // 更新学生：更新现有学生的信息
    public void updateStudent(Student updatedStudent) {
        List<Student> students = studentDataStorage.loadStudents(); // 加载当前所有学生
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(updatedStudent.getStudentId())) {
                students.set(i, updatedStudent); // 替换为更新后的学生信息
                break;
            }
        }
        studentDataStorage.saveStudents(students); // 保存更新后的学生列表
    }

    // 获取所有学生：返回存储中的所有学生列表
    public List<Student> getAllStudents() {
        return studentDataStorage.loadStudents();
    }

    // 根据学生ID获取单个学生：返回匹配的学生或null
    public Student getStudentById(String studentId) {
        List<Student> students = studentDataStorage.loadStudents(); // 加载当前所有学生
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student; // 找到匹配的学生并返回
            }
        }
        return null; // 如果没有找到学生，返回null
    }
}


