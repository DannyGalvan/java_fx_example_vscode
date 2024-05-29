package com.mycompany.loan_control.dbContext;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mycompany.loan_control.App;

import io.github.cdimascio.dotenv.Dotenv;

public class PersistManager {
  private String PERSISTENCE_UNIT_NAME = "unidad-de-persistencia";
  private EntityManagerFactory emf;

  public PersistManager() {
    Dotenv dotenv = Dotenv.configure().filename(App.getNameConfiguration()).load();
    Properties properties = new Properties();
    properties.setProperty("javax.persistence.jdbc.driver", dotenv.get("JDBC_DRIVER"));
    properties.setProperty("javax.persistence.jdbc.url", dotenv.get("DB_URL"));
    properties.setProperty("javax.persistence.jdbc.user", dotenv.get("DB_USER"));
    properties.setProperty("javax.persistence.jdbc.password", dotenv.get("BD_PASSWORD"));
    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void close() {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }
}
