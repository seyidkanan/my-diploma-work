/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Eat;
import model.Food;
import util.Util;

/**
 *
 * @author Kanan
 */
public class QueriesImpl implements Query {

    // List<Eat> a =  q.select(Eat.class);
    ConnectionDB connectionDB = new ConnectionDB();
    Util util = new Util();

    @Override
    public List<Eat> getSelectedEat(String... foods) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = util.createSql4SearchEats(foods);
        System.out.println(sql);
        List<Eat> eatList = new ArrayList<>();
        try {
            c = connectionDB.getConnection();
            if (c.isClosed() == false) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    eatList.add(new Eat(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(c, ps, rs);
        }
        return eatList;
    }

    @Override
    public List<Eat> getSelectedEatWithFoodIds(Integer... foods) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = util.createSql4SearchEatsWithIds(foods);
        System.out.println(sql);
        List<Eat> eatList = new ArrayList<>();
        c = connectionDB.getConnection();
        if (c.isClosed() == false) {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                eatList.add(new Eat(rs.getInt(1), rs.getString(2)));
            }
        }
        connectionDB.closeConnection(c, ps, rs);
        return eatList;
    }

    @Override
    public List<Eat> getInfoAboutEat(String eat) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = util.createSqlInfoEat(eat);
        System.out.println(sql);
        List<Eat> eatList = new ArrayList<>();
        c = connectionDB.getConnection();
        if (c.isClosed() == false) {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                eatList.add(new Eat(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }
        connectionDB.closeConnection(c, ps, rs);
        return eatList;
    }

    @Override
    public List<Eat> getInfoAboutEatWith(int eatId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = util.createSqlInfoEatWith(eatId);
        System.out.println(sql);
        List<Eat> eatList = new ArrayList<>();
        c = connectionDB.getConnection();
        if (!c.isClosed()) {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                eatList.add(new Eat(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }
        connectionDB.closeConnection(c, ps, rs);
        return eatList;
    }

    @Override
    public List<Eat> getEatTime4EatName(String eatName) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select eatime.name from eat ea\n"
                + "inner join eat_time eatime on eatime.id=ea.eat_time_id\n"
                + "inner join eat_name eaname on eaname.id=ea.eat_name_id\n"
                + "where eaname.name=\"" + eatName + "\"";
        System.out.println(sql);
        List<Eat> eatList = new ArrayList<>();
        c = connectionDB.getConnection();
        if (c.isClosed() == false) {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                eatList.add(new Eat(rs.getString(1)));
            }
        }
        connectionDB.closeConnection(c, ps, rs);
        return eatList;
    }

    @Override
    public List<Food> getFoodListByFoodTypeId(Integer id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id, name FROM food where food_type_id=?";
        System.out.println(sql);
        List<Food> foodList = new ArrayList<>();
        try {
            c = connectionDB.getConnection();
            if (c.isClosed() == false) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    foodList.add(new Food(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(c, ps, rs);
        }
        return foodList;
    }

    @Override
    public List<Integer> getFoodListByEatId(Integer id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT foo.id FROM ingredient ing\n"
                + "inner join food foo on foo.id=ing.food_id\n"
                + "inner join eat_name eanam on eanam.id=ing.eat_id\n"
                + "where eanam.id=?";
        System.out.println(sql);
        List<Integer> foodsIdList = new ArrayList<>();
        try {
            c = connectionDB.getConnection();
            if (c.isClosed() == false) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    foodsIdList.add(rs.getInt("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(c, ps, rs);
        }
        return foodsIdList;
    }

    @Override
    public List<Food> getFoodListByEatName(String name) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT foo.id,foo.name, ing.comment FROM qida.ingredient ing\n"
                + "inner join food foo on foo.id=ing.food_id\n"
                + "inner join eat_name eaname on eaname.id=ing.eat_id\n"
                + "where eaname.name=?";
        System.out.println(sql);
        List<Food> foodsIdList = new ArrayList<>();
        try {
            c = connectionDB.getConnection();
            if (c.isClosed() == false) {
                ps = c.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
                while (rs.next()) {
                    foodsIdList.add(new Food(rs.getInt("id"), rs.getString("name"), rs.getString("comment")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(c, ps, rs);
        }
        return foodsIdList;
    }

    @Override
    public List<Eat> getEatList() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name from eat_name";
        System.out.println(sql);
        List<Eat> eatList = new ArrayList<>();
        try {
            c = connectionDB.getConnection();
            if (c.isClosed() == false) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    eatList.add(new Eat(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(c, ps, rs);
        }
        return eatList;
    }

    @Override
    public List<Food> getDefaultFoodList() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT foo.id,foo.name, ing.comment FROM qida.ingredient ing\n"
                + "inner join food foo on foo.id=ing.food_id\n"
                + "inner join eat_name eaname on eaname.id=ing.eat_id";
        System.out.println(sql);
        List<Food> foodsIdList = new ArrayList<>();
        try {
            c = connectionDB.getConnection();
            if (c.isClosed() == false) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    foodsIdList.add(new Food(rs.getInt("id"), rs.getString("name"), rs.getString("comment")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionDB.closeConnection(c, ps, rs);
            } catch (Exception e) {

            }
        }
        return foodsIdList;
    }

    @Override
    public int insertEat(String eatName) {
        int id = 0;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into eat_name(name)values(?)";
        System.out.println(sql);
        c = connectionDB.getConnection();
        try {
            if (!c.isClosed()) {
                ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, eatName);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connectionDB.closeConnection(c, ps, null);
            } catch (Exception e) {
            }
        }
        return id;
    }

    @Override
    public void insertIngridients(int eatId, List<Integer> foodId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = Util.createMultipleInsertSql(eatId, foodId);
        System.out.println(sql);
        c = connectionDB.getConnection();
        try {
            if (!c.isClosed()) {
                ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connectionDB.closeConnection(c, ps, null);
            } catch (Exception e) {
            }
        }
    }

}
