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
    properties.setProperty("hibernate.hbm2ddl.auto", dotenv.get("HIBERNATE_HBM2DDL_AUTO"));
    properties.setProperty("hibernate.show_sql", dotenv.get("HIBERNATE_SHOW_SQL"));
    properties.setProperty("hibernate.format_sql", dotenv.get("HIBERNATE_FORMAT_SQL"));
    properties.setProperty("hibernate.connection.pool_size", dotenv.get("HIBERNATE_CONNECTION_POOL_SIZE"));
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
