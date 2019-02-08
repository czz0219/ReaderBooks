package com.annatation;
@SxtTable("tb_student")
public class SxtStudent {
	@SxtField(columnName = "id", length = 10, type = "int")
	private int id;
	@SxtField(columnName = "sname", length = 10, type = "varchar")
	private String studentName;
	@SxtField(columnName = "age", length = 10, type = "int")
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
