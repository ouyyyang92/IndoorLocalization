package com.example.cilent;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Client {

    public static String send(String string){
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
            public boolean verify(String string, SSLSession ssls) {
                return true;
            }
        });
        try {
            Socket s = new Socket("192.168.0.106",8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write(string+"\n");
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();

            return mess;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
//public class Client {
//    private static Socket socket;
//    public static boolean connection_state = false;
//
//    public static void main(String[] args){
//        while (!connection_state) {
//            connect();
//            try {
//                Thread.sleep(3000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static void connect(){
//        try {
//            socket = new Socket("192.168.0.106", 9982);
//            connection_state = true;
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            new Thread(new Client_listen(socket,ois)).start();
//            new Thread(new Client_send(socket,oos)).start();
//            new Thread(new Client_heart(socket,oos)).start();
//        }catch (Exception e){
//            e.printStackTrace();
//            connection_state = false;
//        }
//    }
//
//    public static void reconnect(){
//        while (!connection_state){
//            System.out.println("正在尝试重新链接.....");
//            connect();
//            try {
//                Thread.sleep(3000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//    public  boolean listen() throws IOException, ClassNotFoundException {
//        Socket socket = new Socket("192.168.0.106", 9982);
//        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//        String string =ois.readObject().toString();
//        socket.close();
//        string=string.substring(1,string.length()-1);
//        String[] ss=string.split(":");
//        if(ss[1].substring(1,ss[1].length()-1)=="100") {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    public void send(String string,int i){
//        try {
//            socket=new Socket("192.168.0.106",9982);
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            JSONObject object = new JSONObject();
//            switch (i){
//                case 1:
//                case 2:
//                    object.put("type","2");
//                    object.put("msg",string);
//                    break;
//                default:break;
//            }
//            oos.writeObject(object);
//            oos.flush();
//            socket.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
//
//class Client_listen implements Runnable{
//    private Socket socket;
//    private ObjectInputStream ois;
//
//    Client_listen(Socket socket,ObjectInputStream ois){
//        this.socket = socket;
//        this.ois = ois;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true){
//                System.out.println(ois.readObject());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
//
//class Client_send implements Runnable{
//    private Socket socket;
//    private ObjectOutputStream oos;
//
//    Client_send(Socket socket, ObjectOutputStream oos){
//        this.socket = socket;
//        this.oos = oos;
//    }
//
//    @Override
//    public void run() {
//        try {
//            Scanner scanner = new Scanner(System.in);
//            while (true){
//                System.out.print("请输入你要发送的信息：");
//                String string = scanner.nextLine();
//                JSONObject object = new JSONObject();
//                object.put("type","chat");
//                object.put("msg",string);
//                oos.writeObject(object);
//                oos.flush();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            try {
//                socket.close();
//                Client.connection_state = false;
//                Client.reconnect();
//            }catch (Exception ee){
//                ee.printStackTrace();
//            }
//        }
//    }
//}
//
//class Client_heart implements Runnable{
//    private Socket socket;
//    private ObjectOutputStream oos;
//
//    Client_heart(Socket socket, ObjectOutputStream oos){
//        this.socket = socket;
//        this.oos = oos;
//    }
//
//    @Override
//    public void run() {
//        try {
//            System.out.println("心跳包线程已启动...");
//            while (true){
//                Thread.sleep(5000);
//                JSONObject object = new JSONObject();
//                object.put("type","heart");
//                object.put("msg","心跳包");
//                oos.writeObject(object);
//                oos.flush();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            try {
//                socket.close();
//                Client.connection_state = false;
//                Client.reconnect();
//            }catch (Exception ee){
//                ee.printStackTrace();
//            }
//        }
//    }
//}
