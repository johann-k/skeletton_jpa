package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


@Entity
@NamedQuery(name="Person.findAll", query="SELECT f FROM Person f")
public class Person {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private String id;
  private String firstName;
  private String lastName;

  private Family family;

  private String nonsenseField = "";


  public String getId() {
    return id;
  }

  public void setId(String Id) {
    this.id = Id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  // Leave the standard column name of the table
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @ManyToOne
  public Family getFamily() {
    return family;
  }

  public void setFamily(Family family) {
    this.family = family;
  }

  @Transient
  public String getNonsenseField() {
    return nonsenseField;
  }

  public void setNonsenseField(String nonsenseField) {
    this.nonsenseField = nonsenseField;
  }


} 