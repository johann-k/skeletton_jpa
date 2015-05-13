package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FIRSTTABLE database table.
 * 
 */
@Entity
@NamedQuery(name="Firsttable.findAll", query="SELECT f FROM Firsttable f")
public class Firsttable implements Serializable {
	@Override
	public String toString() {
		return "Firsttable [id=" + id + ", name=" + name + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	public Firsttable() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}