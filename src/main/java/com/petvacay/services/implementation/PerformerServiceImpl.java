package com.petvacay.services.implementation;

import com.petvacay.dto.performer.PerformerPreviewDTO;
import com.petvacay.entities.Performer;
import com.petvacay.mappers.performer.PerformerPreviewMapper;
import com.petvacay.services.AvailabilityService;
import com.petvacay.services.CategoryService;
import com.petvacay.services.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PerformerServiceImpl implements PerformerService {

    private EntityManagerFactory entityManagerFactory;
    private PerformerPreviewMapper performerPreviewMapper;
    private CategoryService categoryService;
    private AvailabilityService availabilityService;

    @Autowired
    public PerformerServiceImpl(EntityManagerFactory entityManagerFactory,
                                PerformerPreviewMapper performerPreviewMapper,
                                CategoryService categoryService,
                                AvailabilityService availabilityService) {
        this.entityManagerFactory = entityManagerFactory;
        this.performerPreviewMapper = performerPreviewMapper;
        this.categoryService = categoryService;
        this.availabilityService = availabilityService;
    }

    @Override
    public List<PerformerPreviewDTO> filterPerformers(List<Long> categoryIds, Date startDate, Date endDate, String city) {
        List<PerformerPreviewDTO> result = new ArrayList<>();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Performer> performerCriteriaQuery = builder.createQuery(Performer.class);
        Root<Performer> performerRoot = performerCriteriaQuery.from(Performer.class);

        List<Predicate> predicates = new ArrayList<>();

        List<Performer> availablePerformers = availabilityService.getAvailablePerformers(startDate, endDate);

        if (availablePerformers.isEmpty()) {
            return result;
        }

        availablePerformers.forEach(performer ->
                predicates.add(builder.equal(performerRoot.get("userId"), performer.getUserId())));
        if (city != null) {
            predicates.add(builder.equal(performerRoot.get("city"), city));
        }
        if (categoryIds != null) {
            categoryService.getCategoriesByIds(categoryIds).forEach(category ->
                    predicates.add(builder.isMember(category, performerRoot.get("categories"))));
        }

        performerCriteriaQuery.select(performerRoot).where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Performer> typedQuery = entityManager.createQuery(performerCriteriaQuery);

        result = performerPreviewMapper.convertListToDto(typedQuery.getResultList());

        transaction.commit();
        entityManager.close();

        return result;
    }
}
