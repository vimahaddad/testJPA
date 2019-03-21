package io.github.oliviercailloux.y2018.vimahaddad.testJPA;

import static java.util.Objects.requireNonNull;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class QueryHelper {

	@PersistenceUnit
	private EntityManagerFactory emFactory;

	public QueryHelper() {
		emFactory = null;
	}

	public EntityManagerFactory getEmFactory() {
		return requireNonNull(emFactory);
	}

	public <T> CriteriaQuery<T> selectAll(Class<T> type) {
		final CriteriaQuery<T> query = emFactory.getCriteriaBuilder().createQuery(type);
		final Root<T> from = query.from(type);
		query.select(from);
		return query;
	}

	public void setEmFactory(EntityManagerFactory emFactory) {
		this.emFactory = requireNonNull(emFactory);
	}

}