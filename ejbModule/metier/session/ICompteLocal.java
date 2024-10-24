package metier.session;

import java.util.List;

import jakarta.ejb.Local;
import metier.entities.Compte;

@Local
public interface ICompteLocal {
	public void addCompte(Compte c);
	public List<Compte>getAllComptes();
	public Compte getCompte(Long code);
	public void verser(double mt,Long code);
	public void retirer(double mt,Long code);
	public void virement(double mt,Long cpte1, Long cpt2);
	public void updateCompte(Compte c);
	public void supprimerCompte(Long code);
}