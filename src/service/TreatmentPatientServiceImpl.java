package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import vo.Patient;

public class TreatmentPatientServiceImpl implements TreatmentPatientService {

	List<Patient> list = new ArrayList<>();
	
	public TreatmentPatientServiceImpl() {
		getFile();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getFile() {
		
		File file = null;
		ObjectInputStream ois = null;
		
		try {
			file = new File("Patient.dat");
			
			if(!file.exists()) return;
			ois = new ObjectInputStream(new FileInputStream(file));
			
			list = (List<Patient>) ois.readObject();
		}catch(IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void setFile() {
		
		File file = null;
		ObjectOutputStream oos = null;
		
		try {
			
			file = new File("Patient.dat");
			oos = new ObjectOutputStream(new FileOutputStream(file));
			
			oos.writeObject(list);
			oos.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void regist(Patient patient) {
		  list.add(patient);
		
	}

	@Override
	public Patient findByPatientId(String patientId) {
		Patient patient = null;
		
		for(Patient temp : list) {
			if(temp.getPatientId().equals(patientId)) {
				patient = temp;
				break;
			}
			
		}
		
		return patient;
	}

	@Override
	public List<Patient> findByPatientName(String name) {
		List<Patient> patientList = new ArrayList<>();
		
		for(Patient temp : list) {
			if(temp.getName().equals(name)) {
				patientList.add(temp);
			}
		}
		
		return patientList;
	}

	@Override
	public List<Patient> selectAll() {
		return list;
	}

}
