package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_BREAKFAST_ID = START_SEQ + 2;
    public static final int USER_LUNCH_ID = START_SEQ + 3;
    public static final int USER_DINNER_ID = START_SEQ + 4;
    public static final int USER_BREAKFAST1_ID = START_SEQ + 5;
    public static final int USER_LUNCH1_ID = START_SEQ + 6;
    public static final int USER_DINNER1_ID = START_SEQ + 7;
    public static final int ADMIN_LUNCH_ID = START_SEQ + 8;
    public static final int ADMIN_DINNER_ID = START_SEQ + 9;

    public static final Meal USER_BREAKFAST = new Meal(USER_BREAKFAST_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal USER_LUNCH = new Meal(USER_LUNCH_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal USER_DINNER = new Meal(USER_DINNER_ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal USER_BREAKFAST1 = new Meal(USER_BREAKFAST1_ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal USER_LUNCH1 = new Meal(USER_LUNCH1_ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal USER_DINNER1 = new Meal(USER_DINNER1_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_LUNCH = new Meal(ADMIN_LUNCH_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_DINNER = new Meal(ADMIN_DINNER_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);
    public static final List<Meal> USER_MEALS = Arrays.asList(
            USER_DINNER1, USER_LUNCH1, USER_BREAKFAST1, USER_DINNER, USER_LUNCH, USER_BREAKFAST
    );


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
