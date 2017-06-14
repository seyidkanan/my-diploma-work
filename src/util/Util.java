/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;

/**
 *
 * @author Kanan
 */
public class Util {

    public int getCountOfEats(String... foods) {
        return foods.length;
    }

    public int getCountOfEatsWithIds(Integer... foods) {
        return foods.length;
    }

    public String createSql4SearchEats(String... foods) {
        int eatsCount = getCountOfEats(foods);
        String sqlMain = null;
        String sqlHeader = "select ea_na.id, ea_na.name from ingredient ing "
                + "inner join eat ea on ea.eat_name_id=ing.eat_id "
                + "inner join food foo on foo.id=ing.food_id "
                + "inner join eat_name ea_na on ea_na.id=ea.eat_name_id "
                + "where ";
        String sqlOfEnd = " group by ea_na.name";
        if (eatsCount == 1) {
            String sqlSecondary = " foo.name like(\"%" + foods[0] + "%\") or ea_na.name like(\"%" + foods[0] + "%\") ";
            sqlMain = sqlHeader + sqlSecondary + sqlOfEnd;
        } else if (eatsCount > 1) {
            sqlHeader = addEatsSqlWhereRow(sqlHeader, eatsCount, foods);
            sqlMain = sqlHeader + sqlOfEnd;
        } else if (eatsCount == 0) {
            sqlMain = null;
        }
        return sqlMain;
    }

    private String addEatsSqlWhereRow(String mainSql, Integer eatsCount, String... foods) {
        for (int i = 0; i < eatsCount; i++) {
            if (eatsCount - 1 != i) {
                mainSql += " foo.name like(\"%" + foods[i] + "%\") or ea_na.name like(\"%" + foods[i] + "%\") or ";
            } else {
                mainSql += " foo.name like(\"%" + foods[i] + "%\") or ea_na.name like(\"%" + foods[i] + "%\") ";
            }
        }
        return mainSql;
    }

    public String createSql4SearchEatsWithIds(Integer... foods) {
        int eatsCount = getCountOfEatsWithIds(foods);
        String sqlMain = null;
        String sqlHeader = "select ea_na.id, ea_na.name from ingredient ing "
                + "inner join eat ea on ea.eat_name_id=ing.eat_id "
                + "inner join food foo on foo.id=ing.food_id "
                + "inner join eat_name ea_na on ea_na.id=ea.eat_name_id "
                + "where ";
        String sqlOfEnd = " group by ea_na.name";
        if (eatsCount == 1) {
            String sqlSecondary = " foo.id=\"" + foods[0] + "\" ";
            sqlMain = sqlHeader + sqlSecondary + sqlOfEnd;
        } else if (eatsCount > 1) {
            sqlHeader = addEatsSqlWhereRowWithIds(sqlHeader, eatsCount, foods);
            sqlMain = sqlHeader + sqlOfEnd;
        } else if (eatsCount == 0) {
            sqlMain = null;
        }
        return sqlMain;
    }

    private String addEatsSqlWhereRowWithIds(String mainSql, Integer eatsCount, Integer... foods) {
        for (int i = 0; i < eatsCount; i++) {
            if (eatsCount - 1 != i) {
                mainSql += " foo.id=\"" + foods[i] + "\" or ";
            } else {
                mainSql += " foo.id=\"" + foods[i] + "\" ";
            }
        }
        return mainSql;
    }

    public String createSqlInfoEat(String eat) {
        return "select en.name, en.desc,et.name from eat ea\n"
                + "inner join eat_name en on en.id=ea.eat_name_id\n"
                + "inner join eat_time et on et.id=ea.eat_time_id\n"
                + "where en.name=\"" + eat + "\"";
    }

    public String createSqlInfoEatWith(int eatId) {
        return "select en.name, en.desc,et.name from eat ea\n"
                + "inner join eat_name en on en.id=ea.eat_name_id\n"
                + "inner join eat_time et on et.id=ea.eat_time_id\n"
                + "where en.id=\"" + eatId + "\"";
    }

    public static String createMultipleInsertSql(int eatId, List<Integer> foodIds) {
        String head = "INSERT INTO ingredient(eat_id, food_id) ";

        String main = head + " values";
        //String values = null;
        for (int id : foodIds) {
            main += String.format("(%d,%d) ", eatId, id);
            if (foodIds.get(foodIds.size() - 1) != id) {
                main += ",";
            }
        }

        System.out.println(main);

        return main;
    }

}
