package metier.services;

import java.util.Date;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import metier.entities.Compte;
import metier.session.ICompteLocal;

@Stateless
@WebService
public class CompteService {
	@EJB(beanName = "CP")
	private ICompteLocal metier;
	
	@WebMethod
	public void addCompte(@WebParam(name="solde")double soldeInitial) {
		Compte cp=new Compte(soldeInitial,new Date(),true);
		metier.addCompte(cp);
	}
	
	@WebMethod
	public List<Compte>listComptes() {
		return metier.getAllComptes();
	}
	
	@WebMethod
	public Compte getCompte(@WebParam(name="code")Long code) {
		return metier.getCompte(code);
	}
	
	@WebMethod
	public void verser(@WebParam(name="montant")double mt,
			@WebParam(name="code")Long code) {
		metier.verser(mt, code);
	}
	
	@WebMethod
	public void retirer(@WebParam(name="montant")double mt,
			@WebParam(name="code")Long code) {
		metier.retirer(mt, code);
	}
	
	@WebMethod
	public void virement(@WebParam(name="montant")double mt,
			@WebParam(name="cpte1")Long c1,
			@WebParam(name="cpte2")Long c2) {
		metier.virement(mt, c1, c2);
		
	}
}
