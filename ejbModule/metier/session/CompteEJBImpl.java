package metier.session;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import metier.entities.Compte;

	@Stateless(name="CP")
	public class CompteEJBImpl implements ICompteLocal,ICompteRemote{
		@PersistenceContext(unitName = "UP_COMPTE")
		private EntityManager em;
		@Override
		public void addCompte(Compte c) {
			// TODO Auto-generated method stub
			em.persist(c);
			
			
		}
		@Override
		public List<Compte> getAllComptes() {
			// TODO Auto-generated method stub
			//Query req=em.createQuery("select c from Compte c where c.active=true");
			Query req=em.createQuery("select c from Compte c");
			return req.getResultList();
		}
		@Override
		public Compte getCompte(Long code) {
			// TODO Auto-generated method stub
			Compte cp=em.find(Compte.class, code);
			if(cp==null)throw new RuntimeException("Compte introuvable");
			return cp;
		}
		@Override
		public void verser(double mt, Long code) {
			// TODO Auto-generated method stub
			Compte cp=getCompte(code);
			cp.setSolde(cp.getSolde()+mt);//implicite
			em.persist(cp);//explicite
			
		}
		@Override
		public void retirer(double mt, Long code) {
			// TODO Auto-generated method stub
			Compte cp=getCompte(code);
			cp.setSolde(cp.getSolde()-mt);//implicite
			em.persist(cp);//explicite
			
			
		}
		@Override
		public void virement(double mt, Long cpte1, Long cpt2) {
			// TODO Auto-generated method stub
			retirer(mt,cpte1);
			verser(mt, cpt2);
		}
		@Override
		public void updateCompte(Compte c) {
			// TODO Auto-generated method stub
			em.merge(c);
		}
		@Override
		public void supprimerCompte(Long code) {
			// TODO Auto-generated method stub
			Compte cp=getCompte(code);
			em.remove(cp);
		}
}
