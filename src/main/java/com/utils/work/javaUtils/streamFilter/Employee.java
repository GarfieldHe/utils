package com.utils.work.javaUtils.streamFilter;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;

    public Employee(Integer id, Integer age, String gender, String fName, String lName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.firstName = fName;
        this.lastName = lName;
    }

    // 打印的时候会自动toString
    @Override
    public String toString() {
        return this.id.toString() + " - " + this.age.toString()+"\n";
    }

}
