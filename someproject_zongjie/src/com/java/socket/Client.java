package com.java.socket;

public class Client extends Thread{
    
    @Override
    public void run() {
        try{
            while(true){
                ClientSender.getInstance().send();
                synchronized(Client.class){
                    Thread.sleep(2000);
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * 程序的入口main方法
     * @param args
     */
    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }
}