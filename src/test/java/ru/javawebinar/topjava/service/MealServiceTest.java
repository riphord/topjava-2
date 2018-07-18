package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(USER_BREAKFAST_ID, USER_ID);
        assertMatch(actual, USER_BREAKFAST);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(USER_LUNCH_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(ADMIN_DINNER_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_LUNCH);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception{
        service.delete(ADMIN_LUNCH_ID, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> actual = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 31), LocalDate.of(2015, Month.JUNE, 1), USER_ID);
        assertMatch(actual, USER_DINNER1, USER_LUNCH1, USER_BREAKFAST1);

    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> actual = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 14, 0), LocalDateTime.of(2015, Month.JUNE, 15, 9, 11), USER_ID);
        assertMatch(actual, USER_DINNER1, USER_LUNCH1, USER_BREAKFAST1, USER_DINNER);
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, USER_MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(ADMIN_LUNCH);
        updated.setDescription("updated meal");
        updated.setCalories(777);
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(ADMIN_LUNCH_ID, ADMIN_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound(){
        Meal updated = new Meal(USER_BREAKFAST);
        updated.setDescription("updated");
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "adminNewMeal", 2000);
        Meal created = service.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(ADMIN_ID), newMeal, ADMIN_DINNER, ADMIN_LUNCH);
    }
}