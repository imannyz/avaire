package com.avairebot.orion.commands;

import com.avairebot.orion.contracts.commands.Command;
import com.avairebot.orion.utilities.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryHandler {

    private static final List<Category> VALUES = new ArrayList<>();

    public static boolean addCategory(String name, String defaultPrefix) {
        for (Category category : VALUES) {
            if (category.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        VALUES.add(new Category(name, defaultPrefix));
        return true;
    }

    public static Category fromLazyName(String name) {
        name = name.toLowerCase();

        for (Category category : getValues()) {
            if (category.getName().toLowerCase().startsWith(name)) {
                return category;
            }
        }
        return null;
    }

    public static Category fromCommand(Command command) {
        Category category = command.getCategory();
        if (category != null) {
            return category;
        }

        String[] path = command.getClass().getName().split("\\.");
        String commandPackage = path[path.length - 2];

        for (Category cat : getValues()) {
            if (cat.getName().equalsIgnoreCase(commandPackage)) {
                return cat;
            }
        }
        return null;
    }

    public static Category random() {
        return VALUES.get(RandomUtil.getInteger(VALUES.size()));
    }

    public static List<Category> getValues() {
        return VALUES;
    }
}