package org.cibertec.controlador;

import java.io.Serializable;
import java.util.List;

import org.cibertec.model.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class ProductoJpaController implements Serializable{
private static final long serialVersionUID = 1L;
private EntityManagerFactory emf;

//Constructor por defe cto (USANDO JPAUtil)
public ProductoJpaController() {
	this.emf = JPAUtil.getEntityManagerFactory();
}

public EntityManager getEntityManager() {
	return emf.createEntityManager();
}

//METODOS JPA
public void registrar(Producto data) {
	EntityManager em = getEntityManager();
	try {
		em.getTransaction().begin();
		em.persist(data);
		em.getTransaction().commit();
	}catch(Exception e) {
		if(em.getTransaction().isActive())
			em.getTransaction().rollback();
		throw e;
	}finally {
		em.close();
	}
}

public List<Producto> findAllProducto(){
	EntityManager em = getEntityManager();
	try {
		TypedQuery<Producto> q = em.createQuery("SELECT e FROM e", Producto.class);
		return q.getResultList();
	}finally {
		em.close();
	}
}

public Producto buscarById(int codigo) {
	EntityManager em = getEntityManager();
	try {
		return em.find(Producto.class, codigo);
	}finally {
		em.close();
	}
}
	
}
