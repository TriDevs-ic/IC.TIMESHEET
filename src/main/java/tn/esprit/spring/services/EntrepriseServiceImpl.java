package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	private static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);

	public int ajouterEntreprise(Entreprise entreprise) {
		try {

			logger.debug("je viens de lacer l'ajout d'une entreprise. ");

			entrepriseRepoistory.save(entreprise);

			logger.info("ajout terminé avec succé !!!");

		} catch (Exception e) {
			logger.error("Erreur dans ajouterEntreprise(): " + e);
		} finally {
			logger.info("Méthode ajouterEntreprise() finie...!!!!");
		}

		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try {
			logger.debug("je viens de lacer l'ajout d'un deparetemnt. ");

			deptRepoistory.save(dep);

			logger.info("ajout terminé avec succé !!!");

		} catch (Exception e) {
			logger.error("Erreur dans ajoutDepartement(): " + e);
		} finally {
			logger.info("Méthode ajouterDepartement() finie...!!!!");
		}

		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {


		try {

			logger.debug("je viens de lacer l'affectation d'un deparetemnt à une entreprise. ");

			Optional<Entreprise> valueEnt = entrepriseRepoistory.findById(entrepriseId);
			Optional<Departement> valueDep = deptRepoistory.findById(depId);

			if (valueEnt.isPresent() && valueDep.isPresent()) {

				Entreprise entrepriseManagedEntity = valueEnt.get();
				Departement depManagedEntity = valueDep.get();

				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);

				logger.info("le departement a été bien affécté à l'entreprise !!");

			}

		} catch (Exception e) {
			logger.error("Erreur dans affecterDepartementAEntreprise(): " + e);
		} finally {
			logger.info("Méthode affecterDepartementAEntreprise() finie...!!!!");
		}

	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {

		Optional<Entreprise> valueEnt = entrepriseRepoistory.findById(entrepriseId);
		List<String> depNames = new ArrayList<>();

		try {

			if (valueEnt.isPresent()) {

				Entreprise entrepriseManagedEntity = valueEnt.get();

				for (Departement dep : entrepriseManagedEntity.getDepartements()) {
					depNames.add(dep.getName());
				}

				logger.info("les departement sont récupérés de la liste des entreprises");
			}

		} catch (Exception e) {
			logger.error("Erreur dans getAllDepartementsNamesByEntreprise(): " + e);
		} finally {
			logger.info("Méthode getAllDepartementsNamesByEntreprise() finie...!!!!");
		}

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		try {

			Optional<Departement> valueDep = deptRepoistory.findById(depId);

			if (valueDep.isPresent()) {

				Departement dep = valueDep.get();

				deptRepoistory.delete(dep);

				logger.info("Departement est supprimé de la liste des departements");
			}
		} catch (Exception e) {
			logger.error("Erreur dans deleteDepartementById(): " + e);
		} finally {
			logger.info("Méthode deleteDepartementById() finie...!!!!");
		}

	}

	public Entreprise getEntrepriseById(int entrepriseId) {

		return entrepriseRepoistory.findById(entrepriseId).get();

	}
}