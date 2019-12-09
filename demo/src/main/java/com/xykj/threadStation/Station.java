package com.xykj.threadStation;

public class Station extends Thread {
    public Station (String name) {
        super(name);
    }
    static int ticket = 20;
    static String str = "sqq";
    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (str) {
                if (ticket > 0) {
                    System.out.println(getName() + ticket);
                    ticket --;
                } else {
                    System.out.println(getName() + "over");
                }
            }
            try {
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}