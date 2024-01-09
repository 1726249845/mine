package storage;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDataStorage {
    private final String fileName; // 文件名，用于存储和读取学生数据

    // 构造函数，初始化数据存储时要使用的文件名
    public StudentDataStorage(String fileName) {
        this.fileName = fileName;
    }

    // 从文件中加载学生列表
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>(); // 创建学生列表
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) { // 逐行读取
                Student student = parseStudent(line); // 将每行文本解析为学生对象
                if (student != null) {
                    students.add(student); // 添加到学生列表
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 打印异常堆栈信息
        }
        return students; // 返回学生列表
    }

    // 将学生列表保存到文件
    public void saveStudents(List<Student> students) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : students) { // 遍历学生列表
                out.println(studentToFileString(student)); // 将每个学生对象转换为文本行并写入文件
            }
        } catch (IOException e) {
            e.printStackTrace(); // 打印异常堆栈信息
        }
    }

    // 将一行文本解析为学生对象
    private Student parseStudent(String line) {
        try {
            String[] parts = line.split("\t"); // 假设使用制表符作为分隔符
            // 解析学生信息的各个部分
            String studentId = parts[0];
            String name = parts[1];
            String gender = parts[2];
            String birthDate = parts[3];
            String politicalStatus = parts[4];
            String address = parts[5];
            String phone = parts[6];
            String dormitoryNumber = parts[7];

            // 创建并返回学生对象
            return new Student(studentId, name, gender, birthDate, politicalStatus, address, phone, dormitoryNumber);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常堆栈信息
            return null; // 在解析出错时返回null
        }
    }

    // 将学生对象转换为一行文本
    private String studentToFileString(Student student) {
        // 使用制表符分隔学生信息，并返回合成的字符串
        return student.getStudentId() + "\t" +
                student.getName() + "\t" +
                student.getGender() + "\t" +
                student.getBirthDate() + "\t" +
                student.getPoliticalStatus() + "\t" +
                student.getAddress() + "\t" +
                student.getPhone() + "\t" +
                student.getDormitoryNumber();
    }
}

