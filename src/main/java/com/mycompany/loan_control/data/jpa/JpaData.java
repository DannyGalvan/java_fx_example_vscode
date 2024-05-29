package com.mycompany.loan_control.data.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.interfaces.repository.JpaRepository;

public class JpaData<Entity, PrimaryKey> implements JpaRepository<Entity, PrimaryKey> {

  protected final EntityManager em;
  protected final Class<Entity> entityClass;

  public JpaData(PersistManager persistManager, Class<Entity> entityClass) {
    this.em = persistManager.getEntityManager();
    this.entityClass = entityClass;
  }

  @Override
  public Entity getById(PrimaryKey id) {
    try {
      return em.find(entityClass, id);
    } catch (Exception e) {
      System.out.println("Erro al obtener de: " + entityClass.getSimpleName() + " " + e.getMessage());
      return null;
    }
  }

  @Override
  public List<Entity> getAll() {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Entity> cq = cb.createQuery(entityClass);
      Root<Entity> rootEntry = cq.from(entityClass);
      CriteriaQuery<Entity> all = cq.select(rootEntry);

      List<Entity> list = em.createQuery(all).getResultList();

      return list;
    } catch (Exception e) {
      System.out.println("Error al obtener todos de : " + entityClass.getSimpleName() + " " + e.getMessage());
      return null;
    }
  }

  @Override
  public boolean save(Entity book) {
    try {
      em.getTransaction().begin();
      em.persist(book);
      em.getTransaction().commit();

      return true;

    } catch (Exception e) {
      System.out.println("Error al agregar: " + entityClass.getSimpleName() + " " + e.getMessage());
      return false;
    }
  }

  @Override
  public boolean update(Entity book) {
    try {
      em.getTransaction().begin();
      em.merge(book);
      em.getTransaction().commit();

      return true;

    } catch (Exception e) {
      System.out.println("Error al actualizar: " + entityClass.getSimpleName() + " " + e.getMessage());

      return false;
    }
  }

  @Override
  public boolean delete(PrimaryKey id) {
    try {
      Entity book = em.find(entityClass, id);
      em.getTransaction().begin();
      em.remove(book);
      em.getTransaction().commit();

      return true;

    } catch (Exception e) {
      System.out.println("Error al eliminar : " + entityClass.getSimpleName() + " " + e.getMessage());

      return false;
    }
  }
}
