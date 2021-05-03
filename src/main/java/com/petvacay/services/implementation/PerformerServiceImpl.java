package com.petvacay.services.implementation;

import com.petvacay.dto.performer.PerformerPreviewDTO;
import com.petvacay.mappers.performer.PerformerPreviewMapper;
import com.petvacay.repositories.PerformerRepository;
import com.petvacay.services.CategoryService;
import com.petvacay.services.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;

@Service
public class PerformerServiceImpl implements PerformerService {

    private EntityManagerFactory entityManagerFactory;
    private PerformerPreviewMapper performerPreviewMapper;
    private CategoryService categoryService;
    private PerformerRepository performerRepository;

    @Autowired
    public PerformerServiceImpl(EntityManagerFactory entityManagerFactory, PerformerPreviewMapper performerPreviewMapper, CategoryService categoryService, PerformerRepository performerRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.performerPreviewMapper = performerPreviewMapper;
        this.categoryService = categoryService;
        this.performerRepository = performerRepository;
    }

    @Override
    public List<PerformerPreviewDTO> filterPerformers(List<Long> categoryIds, Date startDate, Date endDate, String city) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Performer> criteriaQuery = builder.createQuery(Performer.class);
//        Root<Performer> root = criteriaQuery.from(Performer.class);
//        List<Predicate> predicates = new ArrayList<>();
//
//        predicates.add(builder.not(builder.or(builder.between(root.join("unavailableDates").get("startDate"), startDate, endDate),
//                builder.between(root.join("unavailableDates").get("endDate"), startDate, endDate),
//                builder.and(builder.lessThanOrEqualTo(root.join("unavailableDates").get("startDate"), startDate),
//                        builder.greaterThanOrEqualTo(root.join("unavailableDates").get("endDate"), startDate)))));
//        predicates.add(builder.equal(root.get("city"), city));
//        if (categoryIds != null) {
//            categoryService.getCategoriesByIds(categoryIds).forEach(category ->
//                    predicates.add(builder.isMember(category, root.get("categories"))));
//        }
//
//        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
//
//        TypedQuery<Performer> typedQuery = entityManager.createQuery(criteriaQuery);
//
//        List<PerformerPreviewDTO> result = performerPreviewMapper.convertListToDto(typedQuery.getResultList());
//
//        transaction.commit();
//        entityManager.close();
//
//        return result;
        return performerPreviewMapper.convertListToDto(performerRepository.findAll());
    }
}
