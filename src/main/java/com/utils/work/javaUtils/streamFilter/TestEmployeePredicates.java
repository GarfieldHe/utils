package com.utils.work.javaUtils.streamFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestEmployeePredicates {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
        Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
        Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
        Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
        Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
        Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
        Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
        Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
        Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
        Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");

        List<Employee> employees = new ArrayList<Employee>();
        employees.addAll(Arrays.asList(new Employee[] { e1, e2, e3, e4, e5, e6, e7, e8, e9, e10 }));

        // 函数式编程，如果是静态方法的时候必须得用类名双冒号静态方法
        employees.forEach(System.out::println);
        // 输出符合表达式的每一个对象
        employees.stream().filter(p -> p.getAge() > 21).forEach(System.out::println);

//        employees.stream().filter(predicate).collect(Collectors.<Employee>toList());

        // 返回符合表达式集合的第一个对象
        System.out.println(employees.stream().filter(p -> p.getAge() > 21).findFirst());

        // 抽取对象中所有的id的集合
        System.out.println(employees.stream().map(Employee::getId).collect(Collectors.toList()));
    }
}
