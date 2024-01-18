package ru.practicum.dinner;

import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            switch (scanner.nextLine()) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("Работа программы завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Введена неправильная команда. Попробуйте снова.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addNewDishToDishList(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem;
        do {
            nextItem = scanner.nextLine();
            dc.addCategoriesToMenu(nextItem);

        } while (!nextItem.isEmpty());

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();

        if (numberOfCombos > dc.getMaxNumberOfCombos()) {
            System.out.println("Введенное значение превышает максимально допустимое для списка блюд ресторана");
            numberOfCombos = dc.getMaxNumberOfCombos();
            System.out.println("Установлено максимально допустимое кол-во наборов");
        }

        dc.getRandomMenusSet(numberOfCombos);
        System.out.println();
        scanner.nextLine();
    }
}
