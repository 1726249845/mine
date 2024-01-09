package gui;

import javax.swing.*;
import java.awt.*;
import controller.StudentController;
import model.Student;

public class FindStudentDialog extends JDialog {
    private JTextField studentIdField;
    private JTextArea studentInfoArea;
    private JButton findButton;
    private StudentController controller;
    public FindStudentDialog(Frame parent, StudentController controller) {
        super(parent, "查找学生", true);
        this.controller = controller;
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("学号:"));
        studentIdField = new JTextField(20);
        inputPanel.add(studentIdField);
        add(inputPanel, BorderLayout.NORTH);
        studentInfoArea = new JTextArea(15, 30);
        studentInfoArea.setEditable(false);
        add(new JScrollPane(studentInfoArea), BorderLayout.CENTER);

        findButton = new JButton("查找");
        findButton.addActionListener(e -> findStudent());
        add(findButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void findStudent() {
        String studentId = studentIdField.getText();
        Student student = controller.getStudentById(studentId);
        if (student != null) {
            // 显示学生信息
            studentInfoArea.setFont(new Font("宋体", Font.PLAIN, 16)); // 设置字体大小为16
            studentInfoArea.setText("    学号: " + student.getStudentId() + "\n" +
                            "    姓名: " + student.getName() + "\n" +
                            "    性别: " + student.getGender() + "\n" +
                            "出生日期: " + student.getBirthDate() + "\n" +
                            "政治面貌: " + student.getPoliticalStatus() + "\n" +
                            "家庭住址: " + student.getAddress() + "\n" +
                            "    电话: " + student.getPhone() + "\n" +
                            "  宿舍号: " + student.getDormitoryNumber() + "\n");
        } else {
            studentInfoArea.setText("未找到学号为 " + studentId + " 的学生。");
        }
    }
}
