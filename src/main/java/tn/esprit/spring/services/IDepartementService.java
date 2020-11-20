package tn.esprit.spring.services;

public interface IDepartementService {

	public void affecterEmployeADepartement(int employeId, int depId);
	public void desaffecterEmployeDuDepartement(int employeId, int depId);
	public Double getSalaireMoyenByDepartementId(int departementId);


	
	
	

	
}