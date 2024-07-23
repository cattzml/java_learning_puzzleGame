package com.itheima.ui;

import javax.swing.*;

/**
 * className: Test
 * package: com.itheima.ui
 *
 * @author Meiling Zeng
 * @version 1.0
 * @create 2024/7/21 13:32
 */
public class Test {
    public static void main(String[] args) {
        // 创建游戏主界面
        JFrame gameJframe = new JFrame();
        gameJframe.setSize(603,680);
        gameJframe.setVisible(true);
        // 创建登录界面
        JFrame loginJframe = new JFrame();
        loginJframe.setSize(488,430);
        loginJframe.setVisible(true);
        // 创建注册界面
        JFrame registerJframe = new JFrame();
        registerJframe.setSize(488,500);
        registerJframe.setVisible(true);
    }
}
