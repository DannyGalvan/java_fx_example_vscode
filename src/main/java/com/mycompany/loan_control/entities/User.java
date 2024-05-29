package com.mycompany.loan_control.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String password;
  @Column(name = "role_id")
  private Long roleId; 
  private boolean active;
  @Column(name = "created_at")
  private LocalDate createdAt;
  @Column(name = "updated_at")
  private LocalDate updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private Role role;

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  private List<Loan> loans;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDate.now();
  }
}
