package gui;

import controller.StudentController;
import model.Student;
import storage.StudentDataStorage;
import javax.swing.*;
import java.awt.*;
import java.util.List;
// 主窗口类，用于展示学生管理系统的用户界面
public class MainWindow extends JFrame {
    private JTable studentTable;// 表格，用于显示学生数据
    private JButton addButton, deleteButton, updateButton, findButton;
    private StudentTableModel model;// 表格模型
    StudentController controller = new StudentController(new StudentDataStorage("students.txt"));
    public MainWindow() {
        setTitle("学生管理系统");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // 创建按钮并添加到顶部面板
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("添加");
        deleteButton = new JButton("删除");
        updateButton = new JButton("修改");
        findButton = new JButton("查询");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(findButton);
        add(buttonPanel, BorderLayout.NORTH);
        // 创建表格并添加到主窗口
        studentTable = new JTable(); // 需要实现表格模型来显示数据
        CustomCellRenderer renderer = new CustomCellRenderer();// 设置自定义单元格渲染器
        studentTable.setDefaultRenderer(Object.class, renderer);
        studentTable.setRowHeight(30);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        // 为按钮添加事件监听器
        addButton.addActionListener(e -> openAddDialog());
        deleteButton.addActionListener(e -> openDeleteDialog());
        updateButton.addActionListener(e -> openUpdateDialog());
        findButton.addActionListener(e -> openFindDialog());
        // 设置窗口居中
        setLocationRelativeTo(null);
        // 初始化和显示窗口
        setVisible(true);
        List<Student> students = controller.getAllStudents(); // 这个方法返回所有学生数据
        model = new StudentTableModel(students);
        studentTable.setModel(model);
    }


    private void openAddDialog() {
        AddStudentDialog dialog =
                new AddStudentDialog(this, controller,model);
        dialog.setVisible(true);
    }
    private void openDeleteDialog() {
        DeleteStudentDialog dialog =
                new DeleteStudentDialog(this, controller, model);
        dialog.setVisible(true);
    }
    private void openUpdateDialog() {
        UpdateStudentDialog dialog =
                new UpdateStudentDialog(this, controller,model);
        dialog.setVisible(true);
    }
    private void openFindDialog() {
        FindStudentDialog dialog =
                new FindStudentDialog(this, controller);
        dialog.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}