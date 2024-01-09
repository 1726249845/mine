package model;
import java.util.Date;
public class Student {
    private String studentId;
    private String name;
    private String gender;
    private String birthDate;
    private String politicalStatus;
    private String address;
    private String phone;
    private String dormitoryNumber;

    public Student(String studentId, String name, String gender, String birthDate, String politicalStatus, String address, String phone, String dormitoryNumber) {
        this.studentId = studentId;//学号
        this.name = name;//姓名
        this.gender = gender;//性别
        this.birthDate = birthDate;//出生日期
        this.politicalStatus = politicalStatus;//政治面貌
        this.address = address;//家庭地址
        this.phone = phone;//电话号码
        this.dormitoryNumber = dormitoryNumber;//宿舍号
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDormitoryNumber() {
        return dormitoryNumber;
    }

    public void setDormitoryNumber(String dormitoryNumber) {
        this.dormitoryNumber = dormitoryNumber;
    }
}

