package ui;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import service.TreatmentPatientService;
import service.TreatmentPatientServiceImpl;
import vo.Department;
import vo.Patient;

public class TreatmentPatientUI {
	Scanner keyin = new Scanner(System.in);
	TreatmentPatientService service = new TreatmentPatientServiceImpl();

	public TreatmentPatientUI() {
		String choice;

		while(true) {
			menu();
			choice = keyin.nextLine();

			switch(choice) {
			case "1" : regist();     		break;
			case "2" : printAll();    		break;
			case "3" : searchPatientById(); break;
			case "4" : searchPatientByName(); break;
			case "0" : 
				System.out.println("** The program is terminated.");
				service.setFile();
				return;
			default : System.out.println("Choose the right number of the department of service.");
			}
		}
	}

	// Main Menu
	private void menu() {
		System.out.println("=== [Hospital Service] ===");
		System.out.println("        1. regist the patient");
		System.out.println("        2. show all the records");
		System.out.println("        3. check the record by patient number");
		System.out.println("        4. check the record by patient name");
		System.out.println("        0. terminate the program");
		System.out.println("---------------------------");
		System.out.print  ("       Your Choice: ");
	}	

	// 환자 번호로 검색
	private void searchPatientById() {
		Patient pt = null;
		
		System.out.println("### check the record by patient number ###");
		System.out.println(">patient number : ");
		String sc = keyin.nextLine();
		if(service.findByPatientId(sc) == null) {
			System.out.println("** The patient does not exist.");
			return;}
		
		pt = service.findByPatientId(sc);
		System.out.println("\n** result of check");
		System.out.println(">patient number : " + pt.getPatientId() + "\n>name  : " + pt.getName() + "\n>age  : " 
				 + pt.getAge());
		
	}
	
	// 환자 이름으로 검색
	private void searchPatientByName() {
		List<Patient> temp = null;
		
		System.out.println("### check the record by patient name ###");
		System.out.println(">patient name : ");
		String sc = keyin.nextLine();
		temp = service.findByPatientName(sc);
		
		if(temp.isEmpty()) {System.out.println("** The patient does not exist.");System.out.println(); return;}
		
		else if(temp != null) { 
		System.out.println("\n** result of check");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("   Number     Name    Department     Expenses     Period      H.Bill       Total Bill");
		System.out.println("--------------------------------------------------------------------");
		
		Collections.sort(temp);
		temp.forEach(x -> System.out.println(x));
		}
		System.out.println();
	}
	
	// 입원 환자 전체 정보 출력
	private void printAll() {
		List<Patient> list = service.selectAll();
		if(list.isEmpty()) {System.out.println("The patient does not exist."); System.out.println();return;}
		
		System.out.println("                      << Hospital Service Program >> ");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("   Number     Name    Department     Expenses     Period      H.Bill       Total Bill");
		System.out.println("--------------------------------------------------------------------");
		Collections.sort(list);
		list.forEach(x->System.out.println(x));
		
		
		System.out.println();
	}

	
	private void regist() {
		
		int sc = 0;
		String patientId = null; 		 
		String name=null; 				
		int age=0; 						
		Department part=null; 			
		int period = 0; 				
 
		System.out.println("\n### Information of the Patient ###");
		
		System.out.print("> patient number : "); patientId = keyin.nextLine();
		if(service.findByPatientId(patientId) == null) {
		
		System.out.print("> name : "); name = keyin.nextLine();
		System.out.print("> age : "); age = keyin.nextInt();
		keyin.nextLine();
		part = inputPart();
		System.out.println("> period : " );
		period = keyin.nextInt();
		keyin.nextLine();
		
		Patient pt = new Patient(patientId, name, age, part, period);
		service.regist(pt);
		
		
		}
		else if(service.findByPatientId(patientId) != null) {
			Patient temp = null;
			System.out.println("> name : " + service.findByPatientId(patientId).getName()
					+"\n> age " + service.findByPatientId(patientId).getAge());
			part = inputPart();
			System.out.println("> period : " );
			period = keyin.nextInt();
			keyin.nextLine();
			temp = service.findByPatientId(patientId);
			
			Patient pt = new Patient(temp.getPatientId(), temp.getName(), temp.getAge(), part, period);
			service.regist(pt);
			
		}
		
			
		System.out.println("** Successfully registed.");
		System.out.println();
	}
	
	
	private Department inputPart() {
		String choice;
		Department part = null;
				
		
		while(true) {
			
			System.out.println("> Department of the service");
			System.out.println("1) Surgery   2) Internal   3) Dermatology ");
			choice = keyin.nextLine();
			switch(choice) {
				case "1" : part = Department.SU; break;
				case "2" : part = Department.IN; break;
				case "3" : part = Department.SK; break;
				default : System.out.println("Please keyboard the right number. ");
			}
			
		return part;}
	}
}
