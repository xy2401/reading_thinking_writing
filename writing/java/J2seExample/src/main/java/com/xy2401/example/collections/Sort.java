package com.xy2401.example.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
	public static void main(String[] args) {

		List<Student> Students = new ArrayList<Student>();
		Students.add(new Student("路人甲", 17));
		Students.add(new Student("传说中的少女A", 15));
		Students.add(new Student("约翰·史密斯", 16));

		Collections.sort(Students, new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				return s1.age.compareTo(s2.age);
			}
		});

		System.out.println(Students);

	}
}

class Student {
	String name;
	Integer age;

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return name + " " + age;
	}

}
