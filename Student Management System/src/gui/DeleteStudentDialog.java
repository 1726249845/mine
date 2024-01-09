package gui;

import controller.StudentController;
import model.Student;
import javax.swing.*;
import java.awt.*;
import java.util.List;
// 用于删除学生信息的对话框类
public class DeleteStudentDialog extends JDialog {
    private JTextField studentIdField;
    private JButton deleteButton, findButton;
    private JTextArea studentInfoArea;
    private StudentController controller;

    public DeleteStudentDialog(Frame parent, StudentController controller,StudentTableModel studentTableModel) {
        super(parent, "删除学生", true);
        this.controller = controller;
        // 设置布局管理器为边界布局
        setLayout(new BorderLayout());
        // 创建顶部面板，包括学号输入和查找按钮
        JPanel inputPanel = new JPanel();
        studentIdField = new JTextField(20);
        findButton = new JButton("查找");
        inputPanel.add(new JLabel("学号:"));
        inputPanel.add(studentIdField);
        inputPanel.add(findButton);
        // 将面板添加到对话框的上方
        add(inputPanel, BorderLayout.NORTH);

        // 学生信息显示区域
        studentInfoArea = new JTextArea(20, 50);
        studentInfoArea.setEditable(false);// 设置为不可编辑
        studentInfoArea.setFont(new Font("宋体", Font.PLAIN, 16));
        // 添加滚动面板到对话框的中心区域
        add(new JScrollPane(studentInfoArea), BorderLayout.CENTER);

        // 删除按钮
        deleteButton = new JButton("删除");
        add(deleteButton, BorderLayout.SOUTH);

        // 查找按钮事件处理
        findButton.addActionListener(e -> findStudent());

        // 删除按钮事件处理
        deleteButton.addActionListener(e -> deleteStudent(studentTableModel));

        pack();
        setLocationRelativeTo(parent);
    }

    private void findStudent() {
        String studentId = studentIdField.getText();
        Student student = controller.getStudentById(studentId);
        if (student != null) {
            // 显示学生信息
            studentInfoArea.setText("学号: " + student.getStudentId() + "\n" +
                    "姓名: " + student.getName() + "\n" +
                    "性别: " + student.getGender() + "\n" +
                    "出生日期: " + student.getBirthDate() + "\n" +
                    "政治面貌: " + student.getPoliticalStatus() + "\n" +
                    "家庭住址: " + student.getAddress() + "\n" +
                    "电话: " + student.getPhone() + "\n" +
                    "宿舍号: " + student.getDormitoryNumber() + "\n");
        } else {
            studentInfoArea.setText("未找到学号为 " + studentId + " 的学生。");
        }
    }

    private void deleteStudent(StudentTableModel studentTableModel) {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty() || controller.getStudentById(studentId) == null) {
            JOptionPane.showMessageDialog(this, "请先查找有效的学生信息。");
            return;
        }

        // 再次确认删除
        int confirm = JOptionPane.showConfirmDialog(this,
                "确定要删除学号为 " + studentId + " 的学生吗？", "确认删除",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteStudent(studentId);
            studentInfoArea.setText("");
            JOptionPane.showMessageDialog(this, "学生已被删除。");
            List<Student> updatedStudents = controller.getAllStudents();
            studentTableModel.setStudents(updatedStudents);
        }
    }
}