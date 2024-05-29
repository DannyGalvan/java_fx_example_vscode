package com.mycompany.loan_control.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;
import com.mycompany.loan_control.data.jpa.JpaData;
import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.entities.Book;
import com.mycompany.loan_control.interfaces.repository.BookRepository;

public class BookData extends JpaData<Book, Long> implements BookRepository {

    private EntityManager em;

    @Inject
    public BookData(PersistManager persistManager) {
        super(persistManager, Book.class);
        this.em = persistManager.getEntityManager();
    }

    public List<Book> getSearchBooksByName(String search) {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Book> cq = cb.createQuery(Book.class);
      Root<Book> rootEntry = cq.from(Book.class);
      CriteriaQuery<Book> all = cq.select(rootEntry).where(cb.like(rootEntry.get("name"), "%" + search + "%"));

      List<Book> books = em.createQuery(all).getResultList();

      return books;
    } catch (Exception e) {
      System.out.println("Error al obtener de: " + this.getClass().getSimpleName() + " " + e.getMessage());
      return null;
    }
  }
}
