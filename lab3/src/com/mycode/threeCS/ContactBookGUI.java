package com.mycode.threeCS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactBookGUI extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextArea contactList;

    public ContactBookGUI() {
        // 设置窗口标题和大小
        setTitle("个人通讯录系统");
        setSize(500, 400);

        // 添加面板组件
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        JPanel listPanel = new JPanel(new BorderLayout());

        // 添加输入框组件
        JLabel nameLabel = new JLabel("姓名：");
        JLabel phoneLabel = new JLabel("手机号：");

        nameField = new JTextField(10);
        phoneField = new JTextField(10);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);

        // 添加按钮组件
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton addButton = new JButton("添加");
        JButton updateButton = new JButton("修改");
        JButton deleteButton = new JButton("删除");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // 添加联系人列表组件
        JLabel listLabel = new JLabel("联系人列表：");
        contactList = new JTextArea(15, 30);

        listPanel.add(listLabel, BorderLayout.NORTH);
        listPanel.add(contactList, BorderLayout.CENTER);

        // 将组件添加到主面板上
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(listPanel, BorderLayout.SOUTH);

        // 添加主面板到窗口上
        getContentPane().add(mainPanel);

        // 注册按钮事件监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                // 向服务器发送添加联系人请求
                // 并在客户端显示添加结果
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                // 向服务器发送修改联系人请求
                // 并在客户端显示修改结果
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                // 向服务器发送删除联系人请求
                // 并在客户端显示删除结果
            }
        });

        // 设置显示窗口
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ContactBookGUI contactBookGUI = new ContactBookGUI();
    }
}