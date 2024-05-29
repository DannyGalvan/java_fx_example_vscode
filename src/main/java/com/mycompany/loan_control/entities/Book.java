package com.mycompany.loan_control.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long isbn;
  @ColumnDefault("' '")
  @Column(length = 50)
  private String name;
  @ColumnDefault("' '")
  @Column(length = 500)
  private String description;
  @ColumnDefault("' '")
  private String image;
  @Column(name = "created_at")
  private LocalDate createdAt;
  @Column(name = "updated_at")
  private LocalDate updatedAt;

  @OneToMany(mappedBy = "book")
  @ToString.Exclude
  private List<LoanDetail> loanDetails;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDate.now();
  }
}
