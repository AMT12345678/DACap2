package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entidade.Jogo;
import JPAUtil.JPAUtil;

public class JogoDao {

	public static void salvar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jogo);
		em.getTransaction().commit();
		em.close();
	}

	public static void excluir(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jogo = em.find(Jogo.class, jogo.getId());
		em.remove(jogo);
		em.getTransaction().commit();
		em.close();
	}

	public static List<Jogo> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogo j");
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
	}
	
	public static List<Integer> buscarValoresPares(Jogo j){
		EntityManager em = JPAUtil.criarEntityManager();
		TypedQuery<Jogo> q = em.createQuery("from Jogo j where j.id = :id", Jogo.class).setParameter("id", j.getId());
		List<Integer> listaPares = q.getSingleResult().getNumerosPares();
		em.close();		
		return listaPares;
	}
}
