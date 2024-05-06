package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Ejercicio;
import com.hibernate.util.HibernateUtil;

public class EjercicioDAO {
	public void insertEjercicio(Ejercicio e) {
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

	public void updateEjercicio(Ejercicio e) {
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

	public void deleteEjercicio(int idEjercicio) {
		Transaction transaction = null;
		Ejercicio e = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			e = session.get(Ejercicio.class, idEjercicio);
			session.remove(e);
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public Ejercicio selectEjercicioById(int idEjercicio) {
		Transaction transaction = null;
		Ejercicio e = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			e = session.get(Ejercicio.class, idEjercicio);
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return e;
	}
	
	

	public List<Ejercicio> selectAllEjercicio() {
		Transaction transaction = null;
		List<Ejercicio> ejercicios = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ejercicios = session.createQuery("FROM Ejercicio", Ejercicio.class).getResultList();
			transaction.commit();
		} catch (Exception e1) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ejercicios;
	}
	
}
