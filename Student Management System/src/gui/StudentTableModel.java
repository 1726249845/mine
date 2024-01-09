package gui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Student;
// 表格模型，用于定义如何在 JTable 中显示学生数据
public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;
    private final String[] columnNames = {"学号", "姓名", "性别", "出生日期",
            "政治面貌", "家庭住址", "电话", "宿舍号"};
    public StudentTableModel(List<Student> students) {
        this.students = students;
    }
    @Override
    public int getRowCount() {
        return students.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0: return student.getStudentId();
            case 1: return student.getName();
            case 2: return student.getGender();
            case 3: return student.getBirthDate(); // 格式化日期
            case 4: return student.getPoliticalStatus();
            case 5: return student.getAddress();
            case 6: return student.getPhone();
            case 7: return student.getDormitoryNumber();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public void setStudents(List<Student> students) {
        this.students = students;
        fireTableDataChanged(); // 通知模型数据已更改
    }
}