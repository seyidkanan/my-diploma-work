/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import model.Food;

/**
 *
 * @author Kanan
 */
public class CountOfEqualIng {

    public static int equality(List<Integer> foodListFromEat, List<Integer> foodListSelected) {
        int countOfEq = 0;
        for (int selectedId : foodListSelected) {
            for (int gettingId : foodListFromEat) {
                if (selectedId == gettingId) {
                    countOfEq++;
                }
            }
        }
        return countOfEq;
    }

}
