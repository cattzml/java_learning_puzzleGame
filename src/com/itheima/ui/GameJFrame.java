package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.util.Random;

/**
 * className: GameJFrame
 * package: com.itheima.ui
 *
 * @author Meiling Zeng
 * @version 1.0
 * @create 2024/7/21 13:41
 */
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // java中路径分为两种：绝对路径和相对路径，前者以盘符开始，后者相对当前项目而言
    int[][] data = new int[4][4];// 成员变量
    // 记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    // 计数
    int step = 0;
    int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    // 定义变量随机选择图片
    Random r = new Random();
    int rand1 = r.nextInt(1,9);

    // 定义变量用于展示图片的路径
    String path = "puzzlegame\\image\\animal\\animal"+rand1+"\\";
    //    String path = "puzzlegame\\image\\animal\\animal1\\";
    // 创建菜单上面了选项的对象
    JMenu changePic = new JMenu("Change Pic");
    // 创建选项下面的条目
    JMenuItem replayItem = new JMenuItem("Reply");
    JMenuItem reLoginItem = new JMenuItem("ReLogin");// ctrl+D 向下复制
    JMenuItem closeItem = new JMenuItem("Close");// ctrl+D 向下复制

    JMenuItem accountItem = new JMenuItem("Our Account");// ctrl+D 向下复制
    JMenuItem girlItem = new JMenuItem("Girl");
    JMenuItem animalItem = new JMenuItem("Animal");
    JMenuItem sportItem = new JMenuItem("Sport");


    public GameJFrame(){
        // 初始化界面 ctrl+alt+M
        initJFrame();
        // 初始化菜单
        initJMenuBar();
        // 打乱图片顺序
        initData();
        // 初始化图片
        initImage();

        // 显示界面 放最后
        this.setVisible(true);
    }


    private void initData() {
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i/4][i % 4] = tempArr[i];

        }
    }

    // 初始化图片
    private void initImage(){
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        if(victory()){
            // 显示胜利的图片
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
//            System.out.println(victory());
        }

        // 先加载的图片在上方，后加载的图片在下方，因此背景图片最后添加
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                // 创建一个图片的ImageIcon的对象
                //ImageIcon icon1 = new ImageIcon(".\\puzzlegame\\image\\animal\\animal1\\1.jpg");
                // 创建一个JLable对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path+num+".jpg"));
                // 指定图片位置
                jLabel.setBounds(j*105+83,105*i+134,105,105);
                // 给图片添加边框 0 突起 1 让图片凹下去
                jLabel.setBorder(new BevelBorder(1));
                // 把管理容器添加到界面中
                //this.add(jLabel);
                this.getContentPane().add(jLabel);

            }
        }
        // 添加背景图片
        ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40,40,508,560);
        // 添加背景图片到界面
        this.getContentPane().add(background);

        // 刷新界面
        this.getContentPane().revalidate();
        this.getContentPane().repaint();

    }
    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        // 创建菜单上面了选项的对象
        JMenu functionJMenu = new JMenu("Function");
        JMenu aboutJMenu = new JMenu("About US");
        changePic.add(girlItem);
        changePic.add(animalItem);
        changePic.add(sportItem);

        // 将每一个选项下的条目添加到选项中
        functionJMenu.add(changePic);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        // 给条目绑定事件
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        // 将菜单里两个选项添加到菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // 设置界面的宽高
        this.setSize(603,680);
        this.setTitle("拼图游戏单机版 v1.0");
        // 设置界面始终pin
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(3);

        // 取消默认的居中放置，只有取消了才会按照XY轴形式添加图片
        this.setLayout(null);
        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 按下不松的情况
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            // delete all the pics
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            // 添加背景图片
            ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40,40,508,560);
            // 添加背景图片到界面
            this.getContentPane().add(background);

            // 刷新界面
            this.getContentPane().repaint();
        }

    }
    // 按下松开就调用此方法
    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否的胜利，胜利直接结束
        if(victory()){
            return;//结束方法
        }
        // 对上下左右进行判断 左37，上38，右39，下40
        int code = e.getKeyCode();
        if(code == 37){
            // 向左移动
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            // 每移动一次，步数自增
            step++;
            // 调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 38){
            // 向上移动
            if(x == 3){
                return;
            }
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            // 每移动一次，步数自增
            step++;
            // 调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 39){
            // 向右移动
            if(y == 0){
                return;
            }
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            // 每移动一次，步数自增
            step++;
            // 调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 40){
            // 向下移动
            if(x == 0){
                return;
            }
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            // 每移动一次，步数自增
            step++;
            // 调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code==87){
            /*data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };*/
            data = win;
            initImage();
        }

    }
    // 判断data数组中的数据是否和win相同
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        /*if(data == win){
            return true;
        }*/
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();
        if(obj == replayItem){
            step = 0;
            initData();
            initImage();

        }else if(obj == reLoginItem){
            // close current page
            this.setVisible(false);
            // open login page
            new LoginJFrame();
        }else if(obj == closeItem){
            // 关闭游戏
            System.exit(0);
        }else if(obj == accountItem){
            // personal page
            // 创建弹框
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            // 把图片添加到弹框
            jDialog.getContentPane().add(jLabel);
            // 设置弹框大小
            jDialog.setSize(344,344);
            // 色湖之始终处于界面最上层
            jDialog.setAlwaysOnTop(true);
            // 居中
            jDialog.setLocationRelativeTo(null);
            // 弹框不关闭则无法点击下面的界面
            jDialog.setModal(true);
            // 显示
            jDialog.setVisible(true);
        }else if(obj == girlItem){
            int rand = r.nextInt(1,14);
            path = "puzzlegame\\image\\girl\\girl"+rand+"\\";
            step = 0;
            initData();
            initImage();
        }else if(obj == sportItem){
            int rand = r.nextInt(1,11);
            path = "puzzlegame\\image\\sport\\sport"+rand+"\\";
            step = 0;
            initData();
            initImage();
        }else if(obj == animalItem){
            int rand = r.nextInt(1,9);
            path = "puzzlegame\\image\\animal\\animal"+rand+"\\";
            step = 0;
            initData();
            initImage();
        }
    }
}
