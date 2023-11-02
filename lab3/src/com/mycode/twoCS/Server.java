package com.mycode.twoCS;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000); // 设置服务器端口号为5000
            System.out.println("服务器已启动，等待客户端连接...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 接受客户端连接请求
                System.out.println("客户端已连接：" + clientSocket);

                // 处理客户端请求的线程
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // 处理客户端发送的请求
                    String response = processRequest(inputLine);
                    out.println(response); // 发送响应给客户端
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        class Contact {
            private String name;
            private String phone;
            private String email;

            public Contact(String name, String phone, String email) {
                this.name = name;
                this.phone = phone;
                this.email = email;
            }

            public Contact(String name, String phoneNumber) {
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            // getters and setters
        }
        
        private String processRequest(String request) {
            String[] parts = request.split(";"); // 将请求按照分号进行分割

            String action = parts[0]; // 第一部分是操作类型，如添加、修改或删除
            String name = parts[1]; // 第二部分是联系人姓名
            String phoneNumber = parts[2]; // 第三部分是联系人手机号

            // 假设通讯录系统的数据结构是一个简单的集合来存储联系人对象
            List<Contact> contacts = new ArrayList<>();

            switch (action) {
                case "ADD":
                    contacts.add(new Contact(name, phoneNumber)); // 添加联系人
                    return "联系人已添加成功";
                case "UPDATE":
                    for (Contact contact : contacts) {
                        if (contact.getName().equals(name)) { // 根据姓名查找联系人
                            contact.setPhone(phoneNumber); // 更新联系人手机号
                            return "联系人已更新成功";
                        }
                    }
                    return "未找到该联系人，无法更新";
                case "DELETE":
                    for (Contact contact : contacts) {
                        if (contact.getName().equals(name)) { // 根据姓名查找联系人
                            contacts.remove(contact); // 删除联系人
                            return "联系人已删除成功";
                        }
                    }
                    return "未找到该联系人，无法删除";
                default:
                    return "无效操作";
            }
        }

    }
}