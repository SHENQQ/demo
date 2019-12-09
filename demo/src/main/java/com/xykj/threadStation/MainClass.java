package com.xykj.threadStation;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MainClass {
    /**
     * java多线程同步锁的使用
     * 示例：三个售票窗口同时出售10张票
     * */
//    public static void main(String[] args) {
//        Station station1 = new Station("a");
//        Station station2 = new Station("b");
//        Station station3 = new Station("c");
//        station1.start();
//        station2.start();
//        station3.start();
//    }
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "," + threadInfo.getThreadName());
        }
    }
}