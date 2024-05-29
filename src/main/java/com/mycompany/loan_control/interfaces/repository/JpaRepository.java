package com.mycompany.loan_control.interfaces.repository;

import java.util.List;

public interface JpaRepository<Entity,PrimaryKey> {
  Entity getById(PrimaryKey id);

  List<Entity> getAll();

  boolean save(Entity book);

  boolean update(Entity book);

  boolean delete(PrimaryKey id);
}
