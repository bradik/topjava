package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Modifying
    @Transactional
    Meal save(Meal user);

    @Override
    Meal findOne(Integer integer);

    @Modifying
    @Transactional
    @Query(name = Meal.DELETE)
    int delete(@Param("id") Integer id,@Param("userId") Integer userId);

    @Query(name = Meal.ALL_SORTED)
    List<Meal> getAll(@Param("userId") Integer userId);

    @Query(name = Meal.GET_BETWEEN)
    List<Meal> getBBetween(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("userId") int userId);
}
