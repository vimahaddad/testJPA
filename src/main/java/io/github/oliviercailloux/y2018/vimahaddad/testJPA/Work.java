package io.github.oliviercailloux.y2018.vimahaddad.testJPA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Work")
public class Work {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int idWork;

	@Column(name="contextForTheWork")
	private String contextForTheWork;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<Integer> idExpressions;

	public Work() {
		this.contextForTheWork = "";
		this.idExpressions = new ArrayList<>();
	}
	
	public Work(int idWork, String contextForTheWork) {
		this.idWork = idWork;
		this.contextForTheWork = contextForTheWork;
	}

}