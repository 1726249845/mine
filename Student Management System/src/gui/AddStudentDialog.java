package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import controller.StudentController;
import model.Student;

public class AddStudentDialog extends JDialog {
    private JTextField studentIdField, nameField, genderField, birthDateField, politicalStatusField, addressField, phoneField, dormitoryNumberField;
    private JButton submitButton;
    private StudentController controller;

    // 对话框的构造函数
    public AddStudentDialog(Frame parent, StudentController controller,StudentTableModel studentTableModel) {
        super(parent, "添加学生", true);
        this.controller = controller;
        // 设置布局并添加标签和文本字段
        setLayout(new GridLayout(9, 2));
        add(new JLabel("学号:"));
        studentIdField = new JTextField(20);
        add(studentIdField);
        add(new JLabel("姓名:"));
        nameField = new JTextField(20);
        add(nameField);
        add(new JLabel("性别:"));
        genderField = new JTextField(20);
        add(genderField);
        add(new JLabel("出生日期(xxxx-xx-xx):"));
        birthDateField = new JTextField(20);
        add(birthDateField);
        add(new JLabel("政治面貌:"));
        politicalStatusField = new JTextField(20);
        add(politicalStatusField);
        add(new JLabel("家庭住址:"));
        addressField = new JTextField(20);
        add(addressField);
        add(new JLabel("电话:"));
        phoneField = new JTextField(20);
        add(phoneField);
        add(new JLabel("宿舍号:"));
        dormitoryNumberField = new JTextField(20);
        add(dormitoryNumberField);
        // 提交按钮
        submitButton = new JButton("添加");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                submitStudent(studentTableModel);// 提交学生信息
            }
        });
        add(submitButton);
        // 设置对话框的大小和位置
        setSize(400, 500);
        setLocationRelativeTo(parent);
    }
    // 提交学生信息的方法
    private void submitStudent(StudentTableModel studentTableModel) {
        // 从文本字段中获取数据
        // 数据验证和学生对象的创建逻辑
        String studentId = studentIdField.getText();
        if (controller.getStudentById(studentId) != null) {
            JOptionPane.showMessageDialog(this, "学号 " + studentId + " 已存在，请使用其他学号。");
            return; // 中断提交流程
        }
        String name = nameField.getText();
        String gender = genderField.getText();
        if (!gender.equals("男") && !gender.equals("女")) {
            JOptionPane.showMessageDialog(this, "性别必须为‘男’或‘女’。");
            return;
        }
        String birthDate = birthDateField.getText();
        if (!isValidDate(birthDate)) {
            JOptionPane.showMessageDialog(this, "生日必须为 'xxxx-xx-xx' 格式。");
            return;
        }
        String politicalStatus = politicalStatusField.getText();
        if (!politicalStatus.equals("群众") && !politicalStatus.equals("共青团员")&&
                !politicalStatus.equals("共产党员")&&!politicalStatus.equals("入党积极分子")&&
                !politicalStatus.equals("少先队员")&&!politicalStatus.equals("党员")&&
                !politicalStatus.equals("中共党员")&& !politicalStatus.equals("团员")) {
            JOptionPane.showMessageDialog(this, "请输入正确的政治面貌。");
            return;
        }
        String address = addressField.getText();
        String phone = phoneField.getText();
        if (!phone.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "电话号码必须为11位数字。");
            return;
        }
        String dormitoryNumber = dormitoryNumberField.getText();
        // 创建 Student 对象并添加到系统中
        Student student = new Student(studentId, name,gender,birthDate,politicalStatus,address,phone,dormitoryNumber);
        controller.addStudent(student);
        // 关闭对话框
        setVisible(false);
        dispose();
        List<Student> updatedStudents = controller.getAllStudents();
        studentTableModel.setStudents(updatedStudents);
    }
    // 验证日期格式是否有效的方法
    private boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}