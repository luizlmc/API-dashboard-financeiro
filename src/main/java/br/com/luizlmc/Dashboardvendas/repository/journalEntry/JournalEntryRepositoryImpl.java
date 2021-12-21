package br.com.luizlmc.Dashboardvendas.repository.journalEntry;

import br.com.luizlmc.Dashboardvendas.model.JournalEntry;
import br.com.luizlmc.Dashboardvendas.model.JournalEntry_;
import br.com.luizlmc.Dashboardvendas.repository.filter.JournalEntryFilter;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JournalEntryRepositoryImpl implements JournalEntryRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<JournalEntry> filter(JournalEntryFilter journalEntryFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<JournalEntry> criteria = builder.createQuery(JournalEntry.class);
        Root<JournalEntry> root = criteria.from(JournalEntry.class);

        // creating the restrictions
        Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<JournalEntry> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    private Predicate[] createRestrictions(JournalEntryFilter journalEntryFilter, CriteriaBuilder builder,
                                           Root<JournalEntry> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(journalEntryFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get(JournalEntry_.DESCRIPTION)), "%" + journalEntryFilter.getDescription().toLowerCase() + "%"));
        }

        if (!ObjectUtils.isEmpty(journalEntryFilter.getDueDateFrom())) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(JournalEntry_.DUE_DATE), journalEntryFilter.getDueDateFrom()));
        }

        if (!ObjectUtils.isEmpty(journalEntryFilter.getDueDateTo())) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(JournalEntry_.DUE_DATE), journalEntryFilter.getDueDateTo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
