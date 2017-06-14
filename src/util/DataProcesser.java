/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import db.QueriesImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Eat;
import model.Food;

/**
 *
 * @author Kanan
 */
public class DataProcesser {

    public static List<Eat> getEatBase() {
        QueriesImpl queriesDB = new QueriesImpl();
        List<Eat> eatList = null;
        try {
            eatList = queriesDB.getEatList();
            for (int i = 0; i < eatList.size(); i++) {
                eatList.get(i).setFoods(getFoodList(eatList.get(i).getName(), queriesDB));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eatList;
    }

    private static List<Food> getFoodList(String eatName, QueriesImpl queriesDB) throws SQLException {
        List<Food> foods = queriesDB.getFoodListByEatName(eatName);
        return foods;
    }

    public static int insertEat(String eatName) {
        QueriesImpl queriesDB = new QueriesImpl();
        return queriesDB.insertEat(eatName);
    }

    public static List<Food> getDefaultFoodList() {
        QueriesImpl queriesDB = new QueriesImpl();
        return queriesDB.getDefaultFoodList();
    }

    public static void insertIngredients(int eatId, List<Integer> food) {
        QueriesImpl queriesDB = new QueriesImpl();
        queriesDB.insertIngridients(eatId, food);
    }

}
