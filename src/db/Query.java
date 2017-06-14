/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.SQLException;
import java.util.List;
import model.Eat;
import model.Food;

/**
 *
 * @author Kanan
 */
public interface Query {

    public List<Eat> getSelectedEat(String... foods) throws SQLException;

    public List<Eat> getSelectedEatWithFoodIds(Integer... foods) throws SQLException;

    public List<Eat> getInfoAboutEat(String eat) throws SQLException;

    public List<Eat> getInfoAboutEatWith(int eatId) throws SQLException;

    public List<Eat> getEatTime4EatName(String eatName) throws SQLException;

    public List<Food> getFoodListByFoodTypeId(Integer id) throws SQLException;

    public List<Integer> getFoodListByEatId(Integer id) throws SQLException;

    public List<Food> getFoodListByEatName(String name) throws SQLException;

    public List<Eat> getEatList() throws SQLException;

    public List<Food> getDefaultFoodList();

    public int insertEat(String eatName);

    public void insertIngridients(int eatId, List<Integer> foodId);

}
