/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Kanan
 */
public class Eat {

    private String name;
    private Integer id;
    private String desc;
    private String eatTime;
    private Integer foodId;

    private List<Food> foods;

    public Eat(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Eat(String name, String desc, String eatTime) {
        this.name = name;
        this.desc = desc;
        this.eatTime = eatTime;
    }

    public Eat(Integer id, String name, List<Food> foods) {
        this.name = name;
        this.id = id;
        this.foods = foods;
    }

    public Eat() {
    }

    public Eat(String eatTime) {
        this.eatTime = eatTime;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEatTime() {
        return eatTime;
    }

    public void setEatTime(String eatTime) {
        this.eatTime = eatTime;
    }

    @Override
    public String toString() {
        return "Eat{" + "name=" + name + ", id=" + id + ", desc=" + desc + ", eatTime=" + eatTime + ", foodId=" + foodId + '}';
    }

}
