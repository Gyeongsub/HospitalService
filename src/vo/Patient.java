package vo;

import java.io.Serializable;

public class Patient implements Serializable, Comparable<Patient> {

	private static final long serialVersionUID = 1L;
	private String patientId;
	private String name;
	private int age;
	private Department part;
	private int period;
	private int expenses;
	private int hospitalBill;
	public Patient() {
		
	}
	public Patient(String patientId, String name, int age, Department part, int period) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.part = part;
		this.period = period;
		setExpenses();
		setHospitalBill();
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Department getPart() {
		return part;
	}
	public void setPart(Department part) {
		this.part = part;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getExpenses() {
		return expenses;
	}
	public void setExpenses() {
		if(this.age < 10) 				 		this.expenses = 7000;
		else if(this.age >= 10 & this.age < 20) this.expenses = 5000;
		else if(this.age >= 20 & this.age < 30) this.expenses = 8000;
		else if(this.age >= 30 & this.age < 40) this.expenses = 7000;
		else if(this.age >= 40 & this.age < 50) this.expenses = 4500;
		else if(this.age >= 50) 			    this.expenses = 2300;
	}
	public int getHospitalBill() {
		return hospitalBill;
	}
	public void setHospitalBill() {

		if(this.period <= 3) 						   this.hospitalBill = this.period * 30000;
		else if(this.period < 10) 					   this.hospitalBill = this.period * 25000;
		else if(this.period >= 10 & this.period < 15)  this.hospitalBill = (int) (this.period * 25000 * (0.85));
		else if(this.period >= 15 & this.period < 20)  this.hospitalBill = (int) (this.period * 25000 * (0.80));
		else if(this.period >= 20 & this.period < 30)  this.hospitalBill = (int) (this.period * 25000 * (0.77));
		else if(this.period >= 30 & this.period < 100) this.hospitalBill = (int) (this.period * 25000 * (0.72));
		else if(this.period >= 100) 			       this.hospitalBill = (int) (this.period * 25000 * (0.68));

	}
	@Override
	public String toString() {
		String show
		= String.format("%s %s   %s  %swon   %dwon   %dwon   %dwon",
						patientId, name, part.getPart(), expenses, period, hospitalBill, (expenses + hospitalBill));
			return show;
	}
	public int compareTo(Patient p) {
			return this.patientId.compareTo(p.getPatientId());
	}

}
