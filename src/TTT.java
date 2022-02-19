
import com.sun.java_cup.internal.runtime.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class TTT extends javax.swing.JFrame {

    /**
     * Creates new form TTTA
     */
    String Name, ID, pass, id1, id2;
    int Quyen, admin = 0;
    int DN1 = 0, DN2 = 0, diemMax=2, turn = 2, ls1 = 0, ls2 = 0, tttk = 0, diem1=0,diem2=0;
    int buttonused[] = {0,0,0,0,0,0,0,0,0}, p1w[] = {0,0,0,0,0,0,0,0,0}, p2w[] = {0,0,0,0,0,0,0,0,0};     
    public String userName(String name){
        return Name = name;
    }public String userID(String id){
        return ID = id;
    }public String userPass(String pa){
        return pass = pa;
    }public int userQuyen(int q){
        return Quyen = q;
    }public String getName1(){
        return lblNP1.getText();
    }public String getName2(){
        return lblNP2.getText();
    }public String getid1(String id){
        return id1 = id;
    }public String getid2(String id){
        return id2= id;
    }public void setName1(String name1){
        lblNP1.setText(name1);
    }public void setName2(String name2){
        lblNP2.setText(name2);
    }public void setDP1(){
        this.txtD1.setText(Integer.toString(0));
        this.diem1 = 0;
    }public void setDP2(){
        this.txtD2.setText(Integer.toString(0));
        this.diem2 = 0;
    }
    public void disablebuttonDN1(){
        this.btnDN1.setVisible(false);
    }
    public void disablebuttonDN2(){
        this.btnDN2.setVisible(false);
    }
    public void showbuttonLS1(){
        this.btnLS1.setVisible(true);
    }
    public void showbuttonDX1(){
        this.btnDX1.setVisible(true);
    }
    public void showbuttonDX2(){
        this.btnDX2.setVisible(true);
    }
    public void showbuttonLS2(){
        this.btnLS2.setVisible(true);
    }
    
       
    
    int p1w(){
        if(p1w[0]==1 && p1w[1] ==1 && p1w[2]==1)
            return 1;
        else if(p1w[3]==1 && p1w[4] ==1 && p1w[5]==1)
            return 1;
        else if(p1w[6]==1 && p1w[7] ==1 && p1w[8]==1)
            return 1;
        else if(p1w[0]==1 && p1w[3] ==1 && p1w[6]==1)
            return 1;
        else if(p1w[1]==1 && p1w[4] ==1 && p1w[7]==1)
            return 1;
        else if(p1w[2]==1 && p1w[5] ==1 && p1w[8]==1)
            return 1;
        else if(p1w[0]==1 && p1w[4] ==1 && p1w[8]==1)
            return 1;
        else if(p1w[2]==1 && p1w[4] ==1 && p1w[6]==1)
            return 1;
        return 0;
    }
    int p2w(){
        if(p2w[0]==1 && p2w[1] ==1 && p2w[2]==1)
            return 1;
        else if(p2w[3]==1 && p2w[4] ==1 && p2w[5]==1)
            return 1;
        else if(p2w[6]==1 && p2w[7] ==1 && p2w[8]==1)
            return 1;
        else if(p2w[0]==1 && p2w[3] ==1 && p2w[6]==1)
            return 1;
        else if(p2w[1]==1 && p2w[4] ==1 && p2w[7]==1)
            return 1;
        else if(p2w[2]==1 && p2w[5] ==1 && p2w[8]==1)
            return 1;
        else if(p2w[0]==1 && p2w[4] ==1 && p2w[8]==1)
            return 1;
        else if(p2w[2]==1 && p2w[4] ==1 && p2w[6]==1)
            return 1;
        return 0;
    } 
    
    public TTT() {
        initComponents();
        btnDX1.setVisible(false);
        btnDX2.setVisible(false);
        btnLS1.setVisible(false);
        btnLS2.setVisible(false);
    }
    public String ketqua;
    public void LuuKQ(){
        if(DN1 == 0 && DN2 == 0){
            ketqua = Integer.parseInt(txtD1.getText()) > Integer.parseInt(txtD2.getText()) ? lblNP1.getText()+" thắng": lblNP2.getText() + "thắng";
            String err = Connect.getConnection();
            if(!err.equals("")){
                JOptionPane.showMessageDialog(this, err, "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String sql = "{call ThemResults(?,?,?,?,?,?,?,?)}";
            CallableStatement cstm;
            String kq;
            try {
                cstm = Connect.conn.prepareCall(sql);
                cstm.setInt(1, Integer.parseInt(txtD1.getText()));
                cstm.setInt(2, Integer.parseInt(txtD2.getText()));
                cstm.setString(3, lblNP1.getText());
                cstm.setString(4, lblNP2.getText());
                cstm.setString(5, ketqua);
                cstm.setString(6, id1);
                cstm.setString(7, id2);
                cstm.registerOutParameter(8, java.sql.Types.NVARCHAR);
                cstm.execute();
                kq = cstm.getString(8);
                cstm.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Lỗi: "+ e,"Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            return;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnChoi = new javax.swing.JButton();
        txtD2 = new javax.swing.JTextField();
        txtD1 = new javax.swing.JTextField();
        btnDN1 = new javax.swing.JButton();
        btnDN2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDX1 = new javax.swing.JButton();
        btnDX2 = new javax.swing.JButton();
        lblNP2 = new javax.swing.JLabel();
        lblNP1 = new javax.swing.JLabel();
        btnLS1 = new javax.swing.JButton();
        btnLS2 = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mTT = new javax.swing.JMenu();
        mHD = new javax.swing.JMenu();
        mTTTK = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                setting(evt);
            }
        });

        btn1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btnChoi.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnChoi.setText("Chơi lại");
        btnChoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoiActionPerformed(evt);
            }
        });

        txtD2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtD2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtD2.setText("0");
        txtD2.setEnabled(false);

        txtD1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtD1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtD1.setText("0");
        txtD1.setEnabled(false);

        btnDN1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDN1.setForeground(new java.awt.Color(0, 102, 102));
        btnDN1.setText("Đăng nhập");
        btnDN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDN1ActionPerformed(evt);
            }
        });

        btnDN2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDN2.setForeground(new java.awt.Color(0, 102, 102));
        btnDN2.setText("Đăng nhập");
        btnDN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDN2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(51, 255, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setBackground(new java.awt.Color(204, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("O");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnDX1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDX1.setForeground(new java.awt.Color(0, 102, 102));
        btnDX1.setText("Đăng xuất");
        btnDX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDX1ActionPerformed(evt);
            }
        });

        btnDX2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDX2.setForeground(new java.awt.Color(0, 102, 102));
        btnDX2.setText("Đăng xuất");
        btnDX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDX2ActionPerformed(evt);
            }
        });

        lblNP2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNP2.setForeground(new java.awt.Color(51, 51, 255));
        lblNP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblNP1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNP1.setForeground(new java.awt.Color(51, 51, 255));
        lblNP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnLS1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLS1.setForeground(new java.awt.Color(0, 102, 102));
        btnLS1.setText("Lịch sử");
        btnLS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLS1ActionPerformed(evt);
            }
        });

        btnLS2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLS2.setForeground(new java.awt.Color(0, 102, 102));
        btnLS2.setText("Lịch sử");
        btnLS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLS2ActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jMenuBar1.setToolTipText("");
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mTT.setText("Thông tin");
        mTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mTTMouseClicked(evt);
            }
        });
        jMenuBar1.add(mTT);

        mHD.setText("Hướng dẫn");
        mHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mHDMouseClicked(evt);
            }
        });
        jMenuBar1.add(mHD);

        mTTTK.setText("Thông tin tài khoản");
        mTTTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mTTTKMouseClicked(evt);
            }
        });
        jMenuBar1.add(mTTTK);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnDX1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtD1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDN1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnLS1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(lblNP1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDN2)
                            .addComponent(txtD2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLS2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNP2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnDX2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(btnChoi)
                .addGap(204, 204, 204)
                .addComponent(btnThoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDN1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLS1)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDN2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLS2)
                                .addGap(18, 18, 18))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNP2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNP1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDX1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnDX2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChoi)
                    .addComponent(btnThoat))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void KQTran(){
        if(diem1 == diemMax || diem2 == diemMax){
            LuuKQ();
            int chon = JOptionPane.YES_OPTION;
            String ten1 = "";
            String ten2 = "";
            if(!lblNP1.getText().equals(""))
                ten1 = lblNP1.getText();
            else
                ten1 = "Người chơi X";
            if(!lblNP2.getText().equals(""))
                ten2 = lblNP2.getText();
            else
                ten2 = "Người chơi O";
            if (diem1 == diemMax) {
                chon = JOptionPane.showConfirmDialog(this, "<html><h4><H3 style='color:red;'>"+ten1
                +"</H3><h4>đã thắng trận đấu này</h4></html>"+"\n\nCó muốn chơi nữa không?", "Thông Báo" + JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
            if (diem2 == diemMax) {
                chon = JOptionPane.showConfirmDialog(this, "<html><H3 style='color:red;'>"+ten2
                +"</H3><h4> đã thắng trận đấu này</h4></html>"+"\n\nCó muốn chơi nữa không?", "Thông Báo" + JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
            if (chon == JOptionPane.NO_OPTION) {
                Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP1.getText()+"'");
                Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP2.getText()+"'");
                this.dispose();
            } else {
                btn1.setText("");btn2.setText("");btn3.setText("");btn4.setText("");btn5.setText("");btn6.setText("");btn7.setText("");btn8.setText("");btn9.setText("");
                turn = 2;diem1=0;diem2=0;txtD1.setText("0");txtD2.setText("0");for(int i=0 ; i<9 ; i++)
                buttonused[i]=0;
                for(int i=0 ; i<9 ; i++)
                p1w[i]=0;
                for(int i=0 ; i<9 ; i++)
                p2w[i]=0;
            }
        }
}
    public void KQVan(){
        String ten1, ten2;
        if(lblNP1.getText().equals("")) ten1 = "Người chơi X";
        else ten1 = lblNP1.getText();
        if(lblNP2.getText().equals("")) ten2 = "Người chơi O";
        else ten2 = lblNP2.getText();
        if(p1w()==1){
            JOptionPane.showMessageDialog(this,"<html><h4><H3 style='color:red;'>"+ten1+"</H3> đã thắng ván đấu này</h4></html>","Kết thúc",JOptionPane.ERROR_MESSAGE);
            diem1+=1;
            for(int i=0 ; i<9 ; i++) buttonused[i]=0;
            for(int i=0 ; i<9 ; i++) p1w[i]=0;
            for(int i=0 ; i<9 ; i++) p2w[i]=0;
            txtD1.setText(Integer.toString(diem1));
            btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText(""); btn5.setText(""); btn6.setText(""); btn7.setText(""); btn8.setText(""); btn9.setText("");
        }
        else if(p2w()==1){
            JOptionPane.showMessageDialog(this,"<html><h4><H3 style='color:red;'>"+ten2+"</H3> đã thắng ván đấu này</h4></html>","Kết thúc",JOptionPane.ERROR_MESSAGE);
            diem2+=1;
            for(int i=0 ; i<9 ; i++) buttonused[i]=0;
            for(int i=0 ; i<9 ; i++) p1w[i]=0;
            for(int i=0 ; i<9 ; i++) p2w[i]=0;
            txtD2.setText(Integer.toString(diem2));
            btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText(""); btn5.setText(""); btn6.setText(""); btn7.setText(""); btn8.setText(""); btn9.setText(""); 
        }
        else if(!(btn1.getText().equals("") || btn2.getText().equals("") || btn3.getText().equals("") || btn4.getText().equals("") || btn5.getText().equals("") 
                || btn6.getText().equals("") || btn7.getText().equals("") || btn8.getText().equals("") || btn9.getText().equals(""))){
            JOptionPane.showMessageDialog(this,"Ván đầu hòa","Kết thúc",JOptionPane.ERROR_MESSAGE);
            for(int i=0 ; i<9 ; i++) buttonused[i]=0;
            for(int i=0 ; i<9 ; i++) p1w[i]=0;
            for(int i=0 ; i<9 ; i++) p2w[i]=0;
            btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText(""); btn5.setText(""); btn6.setText(""); btn7.setText(""); btn8.setText(""); btn9.setText("");
        }
    }
    
    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        if(buttonused[8]==0){
            if(turn % 2 == 0){
                turn++; btn9.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[8]=1; p1w[8]=1; KQVan();
            }else{
                turn++; btn9.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[8]=1; p2w[8]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        if(buttonused[7]==0){
            if(turn % 2 == 0){
                turn++; btn8.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[7]=1; p1w[7]=1; KQVan();
            }else{
                turn++; btn8.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[7]=1; p2w[7]=1; KQVan();
            }
        } else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        if(buttonused[6]==0){
            if(turn % 2 == 0){
                turn++; btn7.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[6]=1; p1w[6]=1; KQVan();
            }
            else{
                turn++; btn7.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[6]=1; p2w[6]=1; KQVan();
            }
        }
        else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        if(buttonused[5]==0){
            if(turn % 2 == 0){
                turn++; btn6.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[5]=1; p1w[5]=1; KQVan();
            }else{
                turn++; btn6.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[5]=1; p2w[5]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        if(buttonused[4]==0){
            if(turn % 2 == 0){
                turn++; btn5.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[4]=1; p1w[4]=1; KQVan();
            }else{
                turn++; btn5.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[4]=1; p2w[4]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if(buttonused[3]==0){
            if(turn % 2 == 0){
                turn++; btn4.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[3]=1; p1w[3]=1; KQVan();
            }else{
                turn++; btn4.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[3]=1; p2w[3]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if(buttonused[2]==0){
            if(turn % 2 == 0){
                turn++; btn3.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[2]=1; p1w[2]=1; KQVan();
            }else{
                turn++; btn3.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[2]=1; p2w[2]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if(buttonused[1]==0){
            if(turn % 2 == 0){
                turn++; btn2.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[1]=1; p1w[1]=1; KQVan();
            }else{
                turn++; btn2.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[1]=1; p2w[1]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if(buttonused[0]==0){
            if(turn % 2 == 0){
                turn++;  btn1.setText("<HTML><div style='color:Red;'>X</div></HTML>"); buttonused[0]=1; p1w[0]=1; KQVan();
            }else{
                turn++; btn1.setText("<HTML><div style='color:Blue;'>O</div></HTML>"); buttonused[0]=1; p2w[0]=1; KQVan();
            }
        }else
            JOptionPane.showMessageDialog(this,"Đã được đánh dấu","Thông báo",JOptionPane.ERROR_MESSAGE);
        KQTran();
    }//GEN-LAST:event_btn1ActionPerformed

    private void btnChoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoiActionPerformed
        btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText(""); btn5.setText(""); btn6.setText(""); btn7.setText(""); btn8.setText(""); btn9.setText(""); turn = 2;
        for(int i=0 ; i<9 ; i++) buttonused[i]=0;
        for(int i=0 ; i<9 ; i++) p1w[i]=0;
        for(int i=0 ; i<9 ; i++) p2w[i]=0;
    }//GEN-LAST:event_btnChoiActionPerformed

    private void mHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mHDMouseClicked
        Huongdan frmHuongdan= new Huongdan();
        frmHuongdan.setVisible(true);
        frmHuongdan.setLocationRelativeTo(this);
    }//GEN-LAST:event_mHDMouseClicked

    private void btnDN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDN1ActionPerformed
        DN1 = 1;
        DangNhap frmDangNhap = new DangNhap(this);
        frmDangNhap.setVisible(true);
        frmDangNhap.setLocationRelativeTo(this); 
        
    }//GEN-LAST:event_btnDN1ActionPerformed

    private void btnDN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDN2ActionPerformed
        DN2 = 1;
        DangNhap frmDangNhap = new DangNhap(this);
        frmDangNhap.setVisible(true);
        frmDangNhap.setLocationRelativeTo(this); 
    }//GEN-LAST:event_btnDN2ActionPerformed

    private void btnLS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLS1ActionPerformed
        userName(lblNP1.getText());
        LichSu frmLichSu = new LichSu(this);
        frmLichSu.DocKQ();
        frmLichSu.setVisible(true);
        frmLichSu.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnLS1ActionPerformed

    private void btnLS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLS2ActionPerformed
        userName(lblNP2.getText());
        LichSu frmLichSu = new LichSu(this);
        frmLichSu.DocKQ();
        frmLichSu.setVisible(true);
        frmLichSu.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnLS2ActionPerformed

    private void btnDX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDX1ActionPerformed
        btnDN1.setVisible(true);
        btnLS1.setVisible(false);
        btnDX1.setVisible(false);
        Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP1.getText()+"'");
        lblNP1.setText("");
    }//GEN-LAST:event_btnDX1ActionPerformed

    private void btnDX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDX2ActionPerformed
        btnDN2.setVisible(true);
        btnLS2.setVisible(false);
        btnDX2.setVisible(false);
        Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP2.getText()+"'");
        lblNP2.setText("");
        
    }//GEN-LAST:event_btnDX2ActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP1.getText()+"'");
        Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP2.getText()+"'");
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void setting(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_setting
        Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP1.getText()+"'");
        Connect.setQuery("UPDATE users set USERONL=0 where NAME=N'"+lblNP2.getText()+"'");
    }//GEN-LAST:event_setting

    private void mTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mTTMouseClicked
        ThongTin frmThongTin = new ThongTin();
        frmThongTin.setVisible(true);
        frmThongTin.setLocationRelativeTo(this);
    }//GEN-LAST:event_mTTMouseClicked

    private void mTTTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mTTTKMouseClicked
        tttk=1;
        DangNhap frmDangNhap = new DangNhap(this);
        frmDangNhap.setVisible(true);
        frmDangNhap.setLocationRelativeTo(this); 
    }//GEN-LAST:event_mTTTKMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TTT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnChoi;
    private javax.swing.JButton btnDN1;
    private javax.swing.JButton btnDN2;
    private javax.swing.JButton btnDX1;
    private javax.swing.JButton btnDX2;
    private javax.swing.JButton btnLS1;
    private javax.swing.JButton btnLS2;
    private javax.swing.JButton btnThoat;
    private java.awt.Canvas canvas1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblNP1;
    private javax.swing.JLabel lblNP2;
    private javax.swing.JMenu mHD;
    private javax.swing.JMenu mTT;
    private javax.swing.JMenu mTTTK;
    private javax.swing.JTextField txtD1;
    private javax.swing.JTextField txtD2;
    // End of variables declaration//GEN-END:variables
}
