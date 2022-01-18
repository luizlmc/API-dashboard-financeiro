package br.com.luizlmc.DashboardFinanceiro.repository.journalEntry;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.DashboardFinanceiro.model.Category_;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry_;
import br.com.luizlmc.DashboardFinanceiro.model.Person_;
import br.com.luizlmc.DashboardFinanceiro.repository.filter.JournalEntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<JournalEntry> filter(JournalEntryFilter journalEntryFilter,Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<JournalEntry> criteria = builder.createQuery(JournalEntry.class);
        Root<JournalEntry> root = criteria.from(JournalEntry.class);

        // creating the restrictions
        Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<JournalEntry> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(journalEntryFilter));
    }

    @Override
    public Page<JournalEntrySummaryDTO> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<JournalEntrySummaryDTO> criteria = builder.createQuery(JournalEntrySummaryDTO.class);
        Root<JournalEntry> root = criteria.from(JournalEntry.class);

        criteria.select(builder.construct(JournalEntrySummaryDTO.class,
                root.get(JournalEntry_.ID), root.get(JournalEntry_.DESCRIPTION),
                root.get(JournalEntry_.DUE_DATE), root.get(JournalEntry_.PAYMENT_DATE),
                root.get(JournalEntry_.AMOUNT), root.get(JournalEntry_.ENTRY_TYPE),
                root.get(JournalEntry_.CATEGORY).get(Category_.NAME),
                root.get(JournalEntry_.PERSON).get(Person_.NAME)));

        Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<JournalEntrySummaryDTO> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(journalEntryFilter));
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

    private void addPaginationRestrictions(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int recordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * recordsPerPage;

        query.setFirstResult(firstPageRecord);
        query.setMaxResults(recordsPerPage);
    }

    private Long total(JournalEntryFilter journalEntryFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<JournalEntry> root = criteria.from(JournalEntry.class);

        Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
