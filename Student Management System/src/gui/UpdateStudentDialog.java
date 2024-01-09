package gui;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import controller.StudentController;
import model.Student;

public class UpdateStudentDialog extends JDialog {
    private JTextField studentIdField, nameField, genderField, birthDateField, politicalStatusField, addressField, phoneField, dormitoryNumberField;
    private JButton findButton, updateButton;
    private StudentController controller;
    private Student currentStudent;

    public UpdateStudentDialog(Frame parent, StudentController controller,StudentTableModel studentTableModel) {
        super(parent, "修改学生信息", true);
        this.controller = controller;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));
        // 输入学号和查找按钮
        JPanel findPanel = new JPanel();
        studentIdField = new JTextField(20);
        findButton = new JButton("查找");
        findPanel.add(new JLabel("学号:"));
        findPanel.add(studentIdField);
        findPanel.add(findButton);
        add(findPanel, BorderLayout.NORTH);

        // 学生信息编辑区域
        JPanel infoPanel = new JPanel(new GridLayout(9, 2));
        // 添加姓名、性别、出生日期等其他字段
        nameField = new JTextField(20);
        genderField = new JTextField(20);
        birthDateField = new JTextField(20);
        politicalStatusField = new JTextField(20);
        addressField = new JTextField(20);
        phoneField = new JTextField(20);
        dormitoryNumberField = new JTextField(20);
        infoPanel.add(new JLabel("姓名:"));
        infoPanel.add(nameField);
        infoPanel.add(new JLabel("性别:"));
        infoPanel.add(genderField);
        infoPanel.add(new JLabel("出生日期:"));
        infoPanel.add(birthDateField);
        infoPanel.add(new JLabel("政治面貌:"));
        infoPanel.add(politicalStatusField);
        infoPanel.add(new JLabel("家庭住址:"));
        infoPanel.add(addressField);
        infoPanel.add(new JLabel("电话号码:"));
        infoPanel.add(phoneField);
        infoPanel.add(new JLabel("宿舍号:"));
        infoPanel.add(dormitoryNumberField);
        add(infoPanel, BorderLayout.CENTER);

        // 更新按钮
        updateButton = new JButton("更新");
        add(updateButton, BorderLayout.SOUTH);

        // 查找按钮事件处理
        findButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            currentStudent = controller.getStudentById(studentId);
            if (currentStudent != null) {
                // 显示学生信息以供编辑
                nameField.setText(currentStudent.getName());
                genderField.setText(currentStudent.getGender());
                birthDateField.setText(currentStudent.getBirthDate());
                politicalStatusField.setText(currentStudent.getPoliticalStatus());
                addressField.setText(currentStudent.getAddress());
                phoneField.setText(currentStudent.getPhone());
                dormitoryNumberField.setText(currentStudent.getDormitoryNumber());
            } else {
                JOptionPane.showMessageDialog(this, "未找到学号为 " + studentId + " 的学生");
            }
        });

        // 更新按钮事件处理
        updateButton.addActionListener(e -> {
            if (currentStudent != null) {
                // 更新学生信息
                String name = nameField.getText();
                String gender = genderField.getText();
                if (!gender.equals("男") && !gender.equals("女")) {
                    JOptionPane.showMessageDialog(this, "性别必须为‘男’或‘女’。");
                    return; // 中断提交流程
                }
                String birthDate = birthDateField.getText();
                if (!isValidDate(birthDate)) {
                    JOptionPane.showMessageDialog(this, "生日必须为 'xxxx-xx-xx' 格式。");
                    return; // 中断提交流程
                }
                String politicalStatus = politicalStatusField.getText();
                if (!politicalStatus.equals("群众") && !politicalStatus.equals("共青团员")&&!politicalStatus.equals("共产党员")&&!politicalStatus.equals("入党积极分子")&&!politicalStatus.equals("少先队员")&&!politicalStatus.equals("党员")&&!politicalStatus.equals("中共党员")&& !politicalStatus.equals("团员")) {
                    JOptionPane.showMessageDialog(this, "请输入正确的政治面貌。");
                    return; // 中断提交流程
                }
                String address = addressField.getText();
                String phone = phoneField.getText();
                if (!phone.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(this, "电话号码必须为11位数字。");
                    return; // 中断提交流程
                }
                String dormitoryNumber = dormitoryNumberField.getText();
                currentStudent.setName(name);
                currentStudent.setGender(gender);
                currentStudent.setBirthDate(birthDate);
                currentStudent.setPoliticalStatus(politicalStatus);
                currentStudent.setAddress(address);
                currentStudent.setPhone(phone);
                currentStudent.setDormitoryNumber(dormitoryNumber);
                controller.updateStudent(currentStudent);
                JOptionPane.showMessageDialog(this, "学生信息已更新");
                List<Student> updatedStudents = controller.getAllStudents();
                studentTableModel.setStudents(updatedStudents);
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }
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