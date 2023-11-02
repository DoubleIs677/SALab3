package com.mycode.twoCS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame {
    private JTextField nameField, addressField, phoneField;
    private JTextArea outputArea;
    private JButton addButton, updateButton, deleteButton, searchButton;

    private String[] contacts = new String[100]; // 用数组模拟存储联系人信息
    private int contactCount = 0; // 当前联系人数量

    public Client() {
        setTitle("个人通讯录系统");
        setLayout(new FlowLayout());

        // 输入框
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("姓名:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("住址:"));
        addressField = new JTextField(20);
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("电话:"));
        phoneField = new JTextField(20);
        inputPanel.add(phoneField);

        // 操作按钮
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("添加");
        addButton.addActionListener(new ButtonListener());
        buttonPanel.add(addButton);
        updateButton = new JButton("修改");
        updateButton.addActionListener(new ButtonListener());
        buttonPanel.add(updateButton);
        deleteButton = new JButton("删除");
        deleteButton.addActionListener(new ButtonListener());
        buttonPanel.add(deleteButton);
        searchButton = new JButton("查询");
        searchButton.addActionListener(new ButtonListener());
        buttonPanel.add(searchButton);

        // 输出框
        outputArea = new JTextArea(10, 40);

        add(inputPanel);
        add(buttonPanel);
        add(new JScrollPane(outputArea));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 按钮监听器内部类
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) { // 添加联系人
                String contact = nameField.getText() + " - " + addressField.getText() + " - " + phoneField.getText();
                contacts[contactCount] = contact;
                contactCount++;
                outputArea.append("添加成功: " + contact + "\n");
                clearInputFields();
            } else if (e.getSource() == updateButton) { // 修改联系人
                int index = findContactIndexByName(nameField.getText());
                if (index != -1) {
                    String newContact = nameField.getText() + " - " + addressField.getText() + " - " + phoneField.getText();
                    contacts[index] = newContact;
                    outputArea.append("修改成功: " + newContact + "\n");
                    clearInputFields();
                } else {
                    outputArea.append("联系人不存在\n");
                }
            } else if (e.getSource() == deleteButton) { // 删除联系人
                int index = findContactIndexByName(nameField.getText());
                if (index != -1) {
                    String deletedContact = contacts[index];
                    for (int i = index; i < contactCount - 1; i++) {
                        contacts[i] = contacts[i + 1];
                    }
                    contactCount--;
                    outputArea.append("删除成功: " + deletedContact + "\n");
                    clearInputFields();
                } else {
                    outputArea.append("联系人不存在\n");
                }
            } else if (e.getSource() == searchButton) { // 查询联系人
                String searchName = nameField.getText();
                boolean found = false;
                for (String contact : contacts) {
                    if (contact != null && contact.startsWith(searchName)) {
                        outputArea.append("查询结果: " + contact + "\n");
                        found = true;
                    }
                }
                if (!found) {
                    outputArea.append("联系人不存在\n");
                }
                clearInputFields();
            }
        }
    }

    // 根据姓名查找联系人在数组中的索引
    private int findContactIndexByName(String name) {
        for (int i = 0; i < contactCount; i++) {
            if (contacts[i].startsWith(name)) {
                return i;
            }
        }
        return -1;
    }

    // 清空输入框
    private void clearInputFields() {
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        new Client();
    }
}