package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User user = em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(user);
            em.persist(meal);
        } else {
            if (em.createNamedQuery(Meal.UPDATE)
                    .setParameter("dateTime", meal.getDateTime())
                    .setParameter("calories", meal.getCalories())
                    .setParameter("description", meal.getDescription())
                    .setParameter("id", meal.getId())
                    .setParameter("userid", userId)
                    .executeUpdate() == 0){
                return null;
            }
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return  em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {

//        Meal meal = em.find(Meal.class, id);
//        if (meal != null && meal.getUser().getId() != userId) {
//            return null;
//        }

        Meal meal = null;

        try {
            meal = (Meal) em.createQuery("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
                    .setParameter("id", id)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e){
            //do not process, return null
        }

        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return em.createNamedQuery(Meal.ALL_SORTED_BETWEEN).setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}