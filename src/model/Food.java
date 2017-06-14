/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Kanan
 */
public class Food {

    private Integer id;
    private String food;
    private String foodType;
    private Integer foodTypeId;

    private String comment;

    public Food() {
    }

    public Food(Integer id, String food) {
        this.id = id;
        this.food = food;
    }

    public Food(Integer id, String food, String comment) {
        this.id = id;
        this.food = food;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    @Override
    public String toString() {
        return "Food{" + "id=" + id + ", food=" + food + ", foodType=" + foodType + ", foodTypeId=" + foodTypeId + '}';
    }

}
