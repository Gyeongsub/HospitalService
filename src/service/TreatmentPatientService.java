package service;

import java.util.List;

import vo.Patient;

public interface TreatmentPatientService {
	public void getFile();
	public void setFile();
	public void regist(Patient patient);
	public Patient findByPatientId(String patientId);
	public List<Patient> findByPatientName(String name);
	public List<Patient> selectAll();
}
