package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {

	@Autowired
	IEntrepriseService entrepriseService;

	Integer a, b;

	@Test
	public void testAjouterDepartement() {

		Departement dep = new Departement("ooredoo");
		b = entrepriseService.ajouterDepartement(dep);
		assertNotNull(b);
	}

	@Test
	public void testGetAllDepartementsNamesByEntreprise() {

		List<String> depNames = entrepriseService.getAllDepartementsNamesByEntreprise(15);
		assertNotNull(depNames);
	}

	@Test
    public void testAffecterDepartementAEntreprise(){

        entrepriseService.affecterDepartementAEntreprise(26,15);

    }

}