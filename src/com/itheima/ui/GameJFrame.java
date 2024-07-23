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
    // java��·����Ϊ���֣�����·�������·����ǰ�����̷���ʼ��������Ե�ǰ��Ŀ����
    int[][] data = new int[4][4];// ��Ա����
    // ��¼�հ׷����ڶ�ά�����е�λ��
    int x = 0;
    int y = 0;

    // ����
    int step = 0;
    int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    // ����������ѡ��ͼƬ
    Random r = new Random();
    int rand1 = r.nextInt(1,9);

    // �����������չʾͼƬ��·��
    String path = "puzzlegame\\image\\animal\\animal"+rand1+"\\";
    //    String path = "puzzlegame\\image\\animal\\animal1\\";
    // �����˵�������ѡ��Ķ���
    JMenu changePic = new JMenu("Change Pic");
    // ����ѡ���������Ŀ
    JMenuItem replayItem = new JMenuItem("Reply");
    JMenuItem reLoginItem = new JMenuItem("ReLogin");// ctrl+D ���¸���
    JMenuItem closeItem = new JMenuItem("Close");// ctrl+D ���¸���

    JMenuItem accountItem = new JMenuItem("Our Account");// ctrl+D ���¸���
    JMenuItem girlItem = new JMenuItem("Girl");
    JMenuItem animalItem = new JMenuItem("Animal");
    JMenuItem sportItem = new JMenuItem("Sport");


    public GameJFrame(){
        // ��ʼ������ ctrl+alt+M
        initJFrame();
        // ��ʼ���˵�
        initJMenuBar();
        // ����ͼƬ˳��
        initData();
        // ��ʼ��ͼƬ
        initImage();

        // ��ʾ���� �����
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

    // ��ʼ��ͼƬ
    private void initImage(){
        // ���ԭ���Ѿ����ֵ�����ͼƬ
        this.getContentPane().removeAll();

        JLabel stepCount = new JLabel("������"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        if(victory()){
            // ��ʾʤ����ͼƬ
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
//            System.out.println(victory());
        }

        // �ȼ��ص�ͼƬ���Ϸ�������ص�ͼƬ���·�����˱���ͼƬ������
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                // ����һ��ͼƬ��ImageIcon�Ķ���
                //ImageIcon icon1 = new ImageIcon(".\\puzzlegame\\image\\animal\\animal1\\1.jpg");
                // ����һ��JLable���󣨹���������
                JLabel jLabel = new JLabel(new ImageIcon(path+num+".jpg"));
                // ָ��ͼƬλ��
                jLabel.setBounds(j*105+83,105*i+134,105,105);
                // ��ͼƬ��ӱ߿� 0 ͻ�� 1 ��ͼƬ����ȥ
                jLabel.setBorder(new BevelBorder(1));
                // �ѹ���������ӵ�������
                //this.add(jLabel);
                this.getContentPane().add(jLabel);

            }
        }
        // ��ӱ���ͼƬ
        ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40,40,508,560);
        // ��ӱ���ͼƬ������
        this.getContentPane().add(background);

        // ˢ�½���
        this.getContentPane().revalidate();
        this.getContentPane().repaint();

    }
    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        // �����˵�������ѡ��Ķ���
        JMenu functionJMenu = new JMenu("Function");
        JMenu aboutJMenu = new JMenu("About US");
        changePic.add(girlItem);
        changePic.add(animalItem);
        changePic.add(sportItem);

        // ��ÿһ��ѡ���µ���Ŀ��ӵ�ѡ����
        functionJMenu.add(changePic);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        // ����Ŀ���¼�
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        // ���˵�������ѡ����ӵ��˵�
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // �������������ò˵�
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // ���ý���Ŀ��
        this.setSize(603,680);
        this.setTitle("ƴͼ��Ϸ������ v1.0");
        // ���ý���ʼ��pin
        this.setAlwaysOnTop(true);
        // ���ý������
        this.setLocationRelativeTo(null);
        //������Ϸ�ر�ģʽ
        this.setDefaultCloseOperation(3);

        // ȡ��Ĭ�ϵľ��з��ã�ֻ��ȡ���˲Żᰴ��XY����ʽ���ͼƬ
        this.setLayout(null);
        // ������������Ӽ��̼����¼�
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // ���²��ɵ����
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            // delete all the pics
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            // ��ӱ���ͼƬ
            ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40,40,508,560);
            // ��ӱ���ͼƬ������
            this.getContentPane().add(background);

            // ˢ�½���
            this.getContentPane().repaint();
        }

    }
    // �����ɿ��͵��ô˷���
    @Override
    public void keyReleased(KeyEvent e) {
        // �ж���Ϸ�Ƿ��ʤ����ʤ��ֱ�ӽ���
        if(victory()){
            return;//��������
        }
        // ���������ҽ����ж� ��37����38����39����40
        int code = e.getKeyCode();
        if(code == 37){
            // �����ƶ�
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            // ÿ�ƶ�һ�Σ���������
            step++;
            // ���÷����������µķ�������ͼƬ
            initImage();
        }else if(code == 38){
            // �����ƶ�
            if(x == 3){
                return;
            }
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            // ÿ�ƶ�һ�Σ���������
            step++;
            // ���÷����������µķ�������ͼƬ
            initImage();
        }else if(code == 39){
            // �����ƶ�
            if(y == 0){
                return;
            }
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            // ÿ�ƶ�һ�Σ���������
            step++;
            // ���÷����������µķ�������ͼƬ
            initImage();
        }else if(code == 40){
            // �����ƶ�
            if(x == 0){
                return;
            }
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            // ÿ�ƶ�һ�Σ���������
            step++;
            // ���÷����������µķ�������ͼƬ
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
    // �ж�data�����е������Ƿ��win��ͬ
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
        // ��ȡ��ǰ���������Ŀ����
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
            // �ر���Ϸ
            System.exit(0);
        }else if(obj == accountItem){
            // personal page
            // ��������
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            // ��ͼƬ��ӵ�����
            jDialog.getContentPane().add(jLabel);
            // ���õ����С
            jDialog.setSize(344,344);
            // ɫ��֮ʼ�մ��ڽ������ϲ�
            jDialog.setAlwaysOnTop(true);
            // ����
            jDialog.setLocationRelativeTo(null);
            // ���򲻹ر����޷��������Ľ���
            jDialog.setModal(true);
            // ��ʾ
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
