package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    static HashMap<String, ArrayList<String>> dishList = new HashMap<>();
    ArrayList<String> dishesSetToCreate = new ArrayList<>();

    // Приветствую. Из опыта только текущий курс, но люблю "поломать голову" над разными решениями.

    private static class NewDish {
        boolean isExist;
        String category;

        private NewDish(boolean isExist, String category) {
            this.isExist = isExist;
            this.category = category;
        }
    }

    private NewDish isDishNameExistInAnyCategory(String dishName) {
        boolean isNewDish = false;
        String category = null;
        for (String type : dishList.keySet()) {
            ArrayList<String> dishLists = dishList.get(type);
            for (String dish : dishLists) {
                if (dish.equals(dishName)) {
                    isNewDish = true;
                    category = type;
                    break;
                }
            }
        }
        return new NewDish(isNewDish, category);
    }

    public void addNewDishToDishList(String dishType, String dishName) {

        NewDish newDish = isDishNameExistInAnyCategory(dishName);

        if (!dishList.isEmpty() && newDish.isExist) {
            System.out.println("Блюдо: " + dishName + " уже присутствует в категории: " + newDish.category + ".");
        } else {
            if (dishList.isEmpty()) {
                System.out.println("Создан новый список блюд.");
            }
            if (dishList.containsKey(dishType)) {
                ArrayList<String> dishesInCategory = dishList.get(dishType);
                dishesInCategory.add(dishName);
                dishList.put(dishType, dishesInCategory);
            } else {
                ArrayList<String> dishesNewCategory = new ArrayList<>();
                dishesNewCategory.add(dishName);
                dishList.put(dishType, dishesNewCategory);
            }
            System.out.println("Блюдо: " + dishName + " добавлено в категорию: " + dishType + ".");

        }
    }

    public void addCategoriesToMenu(String nextItem) {

        if (dishList.containsKey(nextItem)) {
            dishesSetToCreate.add(nextItem);
        }
    }

    public int getMaxNumberOfCombos() {
        int maxNumberOfCombos = 1;

        for (String dishes : dishesSetToCreate) {
            ArrayList<String> var = dishList.get(dishes);
            maxNumberOfCombos *= var.size();
        }

        return maxNumberOfCombos;
    }

    public void getRandomMenusSet(int numberOfCombos) {

        ArrayList<ArrayList<String>> randomDishesSet = new ArrayList<>();

        int i = 0;
        while (i < numberOfCombos) {
            Random random = new Random();
            ArrayList<String> randomDishesByCategories = new ArrayList<>();

            for (String dishesSet : dishesSetToCreate) {
                ArrayList<String> var = dishList.get(dishesSet);
                int dishRandomIndex = random.nextInt(var.size());
                randomDishesByCategories.add(var.get(dishRandomIndex));
            }

            if (!randomDishesSet.contains(randomDishesByCategories)) {
                randomDishesSet.add(randomDishesByCategories);
                System.out.println("Комбинация меню № " + (i + 1));
                System.out.println(randomDishesSet.get(i));
                i++;

            }
        }
        dishesSetToCreate.clear();
    }
}
