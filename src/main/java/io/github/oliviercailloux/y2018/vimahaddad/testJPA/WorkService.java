package io.github.oliviercailloux.y2018.vimahaddad.testJPA;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class WorkService {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<Work> getAll() {

		return em.createQuery(helper.selectAll(Work.class)).getResultList();

	}

	@Transactional
	public void persist(Work work) {

		em.persist(work);

	}
}
