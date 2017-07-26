package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    //      Map<meal id,user id>
    private Map<Integer, Integer> usersMeals = new ConcurrentHashMap<>();
    private UserRepository userRepository = new InMemoryUserRepositoryImpl();

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        usersMeals.put(meal.getId(), AuthorizedUser.id());
        return meal;
    }

    @Override
    public boolean delete(int id) {

        return checkMealAccess(id) ? repository.remove(id) != null : false;
    }

    @Override
    public Meal get(int id) {
        return checkMealAccess(id) ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream().
                filter(meal -> checkMealAccess(meal.getId()))
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }

    private boolean isCurentUserAdmin() {

        User user = userRepository.get(AuthorizedUser.id());

        return user != null && user.getRoles().contains(Role.ROLE_ADMIN);
    }

    private boolean checkMealAccess(int mealId) {

        return (isCurentUserAdmin() || usersMeals.getOrDefault(repository.get(mealId),-1) == AuthorizedUser.id());
    }
}

