package com.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.model.Entrenador;
import com.hibernate.util.HibernateUtil;

public class EntrenadorDAO {
	
	public void insertEntrenador(Entrenador e) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(e);
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void updateEntrenador(Entrenador e) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(e);
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void deleteEntrenador(int idEntrenador) {
		Transaction transaction = null;
		Entrenador e = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			e = session.get(Entrenador.class, idEntrenador);
			session.remove(e);
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public Entrenador selectEntrenadorById(int idEntrenador) {
		Transaction transaction = null;
		Entrenador e = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			e = session.get(Entrenador.class, idEntrenador);
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return e;
	}
	
	

	public List<Entrenador> selectAllEntrenador() {
		Transaction transaction = null;
		List<Entrenador> entrenadores = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			entrenadores = session.createQuery("FROM Entrenador", Entrenador.class).getResultList();
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return entrenadores;
	}
}
