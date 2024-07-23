package com.itheima.test;

import java.util.Random;

/**
 * className: Test01
 * package: com.itheima.test
 *
 * @author Meiling Zeng
 * @version 1.0
 * @create 2024/7/21 19:17
 */
public class Test01 {
    public static void main(String[] args) {
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;

        }

        for (int i = 0; i < tempArr.length; i++) {
            System.out.print(tempArr[i] + " ");
        }
        System.out.println();
        int[][] date = new int[4][4];
        for (int i = 0; i < tempArr.length; i++) {
            date[i/4][i % 4] = tempArr[i];

        }
        for (int i = 0; i < date.length; i++) {
            for (int j = 0; j < date[i].length; j++) {
                System.out.print(date[i][j]+" ");
            }
            System.out.println();
        }

    }

}
