/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: Singleton
 * Author:   Administrator
 * Date:     2019-9-12 9:45
 * Description: 单例模式的五中实现方式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy;

/**
 * 〈一句话功能简述〉<br> 
 * 〈单例模式的五中实现方式〉
 *
 * @author Administrator
 * @create 2019-9-12
 * @since 1.0.0
 */
public class Singleton {
    /**
     *  优点：饿汉模式天生是线程安全的，使用时没有延迟。
     *  缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
     */
   private static Singleton singleton = new Singleton ();
   //私有化构造方法，外部无法新建
   private Singleton(){};
   //外部通过静态方法拿去对象
   public static Singleton getSingleton(){
       return singleton;
   }

}

/**
 * 懒汉式单例模式
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 * 缺点：非线程安全。
 * 所谓非线程安全的情况就是，在两个线程都走到 if(singleton01==null)的时候，第一个线程
 * 失去时间片第二个线程去创建singleton对象完成，这时对一个线程在执行的话还是null就会在创建
 * singleton对象，出现线程不安全的现象
 */
class Singleton01{
    //私有化Singleton01
    private static Singleton01 singleton01 = null;
    //私有化构造方法外部无法创建
    private Singleton01(){};
    //给外部提供一个可拿取的方法，但是做一下判断，如果没有创建为null就创建，有的话就返回
    public static Singleton01 getSingleton01(){
        if(singleton01==null){
            singleton01 = new Singleton01();
        }
        return singleton01;
    }
}

/**
 * 懒汉式线程加锁
 * 我们在外部拿取singleton对象的时候加锁synchronized方法，给方法加锁控制线程
 * 排队有锁的进入方法没有锁的排队。保证线程安全只创建一个singleton对象
 * 优点：同上，但加锁了。
 *
 * 缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。
 *
 */
class Singleton02{
    //私有化Singleton01
    private static Singleton02 singleton02 = null;
    //私有化构造方法外部无法创建
    private Singleton02(){};
    //给外部提供一个可拿取的方法，但是做一下判断，如果没有创建为null就创建，有的话就返回
    public static synchronized Singleton02 getSingleton01(){
        if(singleton02==null){
            singleton02 = new Singleton02();
        }
        return singleton02;
    }
}

/**
 * 双重锁机制单例模式
 * 很明显双重锁机制就是加两把锁
 *
 * volatile让变量每次在使用的时候，都从主存中取。而不是从各个线程的“工作内存”。
 *
 * volatile具有synchronized关键字的“可见性”，但是没有synchronized关键字的“并发正确性”，也就是说不保证线程执行的有序性。
 *
 * 也就是说，volatile变量对于每次使用，线程都能得到当前volatile变量的最新值。但是volatile变量并不保证并发的正确性。
 *
 * =========================分割线1=================================
 *
 * 在Java内存模型中，有main memory，每个线程也有自己的memory (例如寄存器)。为了性能，一个线程会在自己的memory中保持要访问的变量的副本。
 * 这样就会出现同一个变量在某个瞬间，在一个线程的memory中的值可能与另外一个线程memory中的值，或者main memory中的值不一致的情况。
 */
class Singleton03{
    //关键参数 volatile
    private volatile static Singleton03 singleton03 = null;
    //私有化构造方法外部无法创建对象
    private Singleton03(){};
    //外部拿取对象的方法
    public static Singleton03 getSingleton03(){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if(singleton03==null){
            //再次检查实例是否存在，如果不存在才真的创建实例
           synchronized (Singleton03.class){
               if(singleton03==null){
                   singleton03 = new Singleton03();
               }
           }
        }
        return singleton03;
    }
}