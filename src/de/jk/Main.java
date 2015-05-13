package de.jk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Family;
import model.Firsttable;
import model.Person;

public class Main {
  private static final String PERSISTENCE_UNIT_NAME = "derby-eclipselink";
  private static final String PERSISTENCE_UNIT_NAME2 = "jndiDb";
  private static EntityManagerFactory factory;

  public static void main(String[] args) {
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    // read the existing entries and write to console
    Query q = em.createQuery("SELECT t FROM Firsttable t");
    List<Firsttable> todoList = q.getResultList();
    for (Firsttable todo : todoList) {
      System.out.println(todo);
    }
    System.out.println("Size: " + todoList.size());
    em.getTransaction().begin();
for (int i = 0; i <327; i++) {
	
    // create new todo
Firsttable todo = new Firsttable();
//    todo.setId(2);
    todo.setName("This is a test");
    em.persist(todo);

}

Family family = new Family();
family.setDescription("Family for the Knopfs");
em.persist(family);
for (int i = 0; i < 11; i++) {
  Person person = new Person();
  person.setFirstName("Jim_" + i);
  person.setLastName("Knopf_" + i);
  em.persist(person);
  // now persists the family person relationship
  family.getMembers().add(person);
  em.persist(person);
  em.persist(family);
}

em.getTransaction().commit();
    em.close();
  }
} 