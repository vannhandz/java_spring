package nhanvien;

import java.time.LocalDate;

public class NhanVien {
    private int id;
    private String name;
    private LocalDate bỉth;
    private String gender;
    private Double salary;
    private String phone;

    public NhanVien() {}

    public NhanVien(int id, String name, LocalDate bỉth, String gender, Double salary, String phone) {
        this.id = id;
        this.name = name;
        this.bỉth = bỉth;
        this.gender = gender;
        this.salary = salary;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBỉth() {
        return bỉth;
    }

    public void setBỉth(LocalDate bỉth) {
        this.bỉth = bỉth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
