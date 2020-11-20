package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	
	

	private static final Logger l = Logger.getLogger(DepartementServiceImpl.class);

	public void affecterEmployeADepartement(int employeId, int depId) {
		
		Optional<Departement> value = this.deptRepoistory.findById(depId);
		
		if (value.isPresent()) {
		Departement depManagedEntity =value.get();
		
		
		Optional<Employe> valuee = this.employeRepository.findById(employeId);
		
		if (valuee.isPresent()) {
		Employe employeManagedEntity = valuee.get();
		
try {
	l.info(" L'Info est affecter Employer a departement ");
	l.debug("Le Debug affecter employe Id  "+employeId);
	l.debug("Le Debug affecter Dpartment  Id  "+depId);
	
	if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
			l.debug("Le Debug de employeManagedEntity  "+employeManagedEntity);
	}
		
else{

			depManagedEntity.getEmployes().add(employeManagedEntity);
			l.debug("Le Debug de depManagedEntity  "+depManagedEntity);
			
		}
}catch(Exception e) {
	l.error("Erreur affecter Employe A Departement" + e);
}
}
		}
		
	}
	
	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Optional<Departement> value =deptRepoistory.findById(depId);
		if (value.isPresent()) {
		Departement dep = value.get();
		
		
		try{
		l.info(" L'Info est desaffecter Employe Du Departement ");
		l.debug("Le Debug desaffecter employe Id  "+employeId);
		l.debug("Le Debug desaffecter Dpartment  Id  "+depId);
		
		

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
			
			
				break;//a revoir
			}
		
		}
		
		}
		 catch(Exception e) {
			l.error("Erreur desaffecter Employe A Departement" + e);
		}
		l.info(" sortir  desaffecter Employe Du Departement ");
	}
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		try {
			l.info(" info sur le salaire moyen par departement");
			l.debug("parametre Id  departement." + departementId);
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	} catch (

			Exception e) {
				l.error("Erreur dans getSalaireByEmployeIdJPQL" + e);
				return null;
			}
	}

}