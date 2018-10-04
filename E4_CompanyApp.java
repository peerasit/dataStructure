import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Arrays;

import javax.sound.midi.Soundbank;

class Person {

	private String name = null;
	private char sex = ' ';
	private int age = 0;
	private double height = 0;
	private double weight = 0;
	private boolean single = true;

	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	public Person(String name, char sex) {
		this.name = name;
		this.sex = sex;
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person(String name, char sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public Person(String name, char sex, int age, double height, double weight, boolean single) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.single = single;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setHeightWeight(double height, double weight) {
		this.height = height;
		this.weight = weight;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public void setInfo(String name, char sex, int age, double height, double weight, boolean single) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.single = single;
	}

	public String getName() {
		return name;
	}

	public char getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public boolean isSingle() {
		return single;
	}

	public double getBMI() {
		if (height <= 0 | weight <= 0) {
			return -1;
		}

		double bmi = weight / Math.pow((height / 100), 2);
		String str = String.format("%.1f", bmi);
		return Double.parseDouble(str);
	}

	public int getBodyState() {
		double bmi = getBMI();
		if (bmi == -1) {
			return -1;
		}
		if (bmi < 18.5) {
			return 0;
		}
		if (bmi <= 24.9) {
			return 1;
		}
		if (bmi <= 29.9) {
			return 2;
		} else {
			return 3;
		}
	}

	public String toString() {
		if (name == null | name.length() == 0 || age == 0 || sex == ' ') {
			return "Error";
		}
		
		String result = String.format("%s%s is %d years old and %s.", (sex=='M')? "Mr.":"Ms.",name,age,(isSingle())? "single":"married");
		
//		String result = "";
//		if (sex == 'M') {
//			result += "Mr. ";
//		} else if (sex == 'F') {
//			result += "Ms. ";
//		}
//		result += name + " is " + String.valueOf(age) + "years old and ";
//		if (single) {
//			result += "single. ";
//		} else {
//			result += "married. ";
//		}
		if (getBMI() != -1) {
			result+=String.format(" %s has ", (sex=='M')? "He":"She");

			if (getBodyState() == 0) {
				result += "underweight";
			} else if (getBodyState() == 1) {
				result += "normal";
			} else if (getBodyState() == 2) {
				result += "overweight";
			} else if (getBodyState() == 3) {
				result += "obesity";
			}
			result += " body (BMI=" + getBMI() + ").";
		}
		return result;
	}

}

class Employee extends Person {
	private double salary = 0;
	private int work_length = 0;

	public Employee() {
		super();
	}

	public Employee(String name) {
		super(name);
	}

	public Employee(String name, char sex) {
		super(name, sex);
	}

	public Employee(String name, int age) {
		super(name, age);
	}

	public Employee(String name, char sex, int age) {
		super(name, sex, age);
	}

	public Employee(String name, char sex, int age, double height, double weight, boolean single) {
		super(name, sex, age, height, weight, single);
	}

	public Employee(String name, char sex, int age, double height, double weight, boolean single, double salary,
			int work_length) {
		super(name, sex, age, height, weight, single);
		this.salary = salary;
		this.work_length = work_length;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setWorkLength(int work_length) {
		this.work_length = work_length;
	}

	public double getSalary() {
		return salary;
	}

	public int getWorkLength() {
		return work_length;
	}
}

class Company {
	int MaxEmployee = 1000;
	int TotalEmployee = 0;
	Employee[] EmployeeList;

	public Company() {
		EmployeeList = new Employee[MaxEmployee];
	}

	public Company(int max) {
		MaxEmployee = max;
		EmployeeList = new Employee[MaxEmployee];
	}

	public void addEmployee(Employee emp) {
		if(TotalEmployee<MaxEmployee)
			EmployeeList[TotalEmployee++] = emp;
	}

	public Employee getEmployee(int no) {
		Employee result = EmployeeList[no - 1];
		return result;
	}

	public int getTotalEmployee() {
		return TotalEmployee;
	}
}

public class E4_CompanyApp {
	public static void main(String[] args) {
		Company c = new Company(500);
		//System.out.println(c.getTotalEmployee());
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("D:\\test.txt");
			br = new BufferedReader(fr);
			while (true) {
				String readResult = br.readLine();
				if (readResult.equals("$$$")) {
					// System.out.println("end");
					break;
				}
				if (readResult.charAt(0) != '!') {
					//System.out.println(readResult);
					String[] info = readResult.split(":");
					//System.out.println(Arrays.toString(info));
					c.addEmployee(new Employee(info[0],
							info[1].charAt(0),
							Integer.parseInt(info[2]),
							Double.parseDouble(info[3]),
							Double.parseDouble(info[4]),
							(info[5].equals("S")),
							Double.parseDouble(info[6]),
							Integer.parseInt(info[7])));
					
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		int totalEmployee = c.getTotalEmployee();
		for(int i = 1;i<=totalEmployee;i++) {
			Employee emp = c.getEmployee(i);
			if(emp.getSex() == 'M' | emp.getSex() == 'm' ) {
				System.out.println(emp.getName()+" ("+emp.getAge()+") "+String.format("%.2f",emp.getSalary()));
			}
		}
		for(int i = 1;i<=totalEmployee;i++) {
			Employee emp = c.getEmployee(i);
			if(emp.getSex() == 'F' | emp.getSex() == 'f' ) {
				System.out.println(emp.getName()+" ("+emp.getAge()+") "+String.format("%.2f",emp.getSalary()));
			}
		}
		for(int i = 1;i<=totalEmployee;i++) {
			Employee emp = c.getEmployee(i);
			if(emp.getSex() == 'F' | emp.getSex() == 'f' ) {
				if(emp.isSingle() && emp.getAge()<= 30 && emp.getBodyState() == 1) {
					double preSalary = (emp.getSalary()*105)/100;
					emp.setSalary(preSalary);
					//System.out.println(emp.getSalary());
				}
			}
		}
		for(int i = 1;i<=totalEmployee;i++) {
			Employee emp = c.getEmployee(i);
			int length = emp.getWorkLength()+1;
			emp.setWorkLength(length);
			//System.out.println(emp.toString());
		}
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("D:\\result.txt");
			bw = new BufferedWriter(fw);
			for(int i = 1;i<=totalEmployee;i++) {
				Employee emp = c.getEmployee(i);
				String result = String.format("%s:%c:%d:%.0f:%.0f:%c:%.0f:%d:", 
						emp.getName(),
						emp.getSex(),
						emp.getAge(),
						emp.getHeight(),
						emp.getWeight(),
						(emp.isSingle()? 'S':'M'),
						emp.getSalary(),
						emp.getWorkLength()
						);
						
				bw.write(result);
				bw.newLine();
			}
			bw.write("$$$");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
			
			if(bw != null) {
				bw.close();
			}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}