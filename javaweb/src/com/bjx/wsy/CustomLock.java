/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: CustomLock
 * Author:   Administrator
 * Date:     2019-10-12 11:14
 * Description: 针对多线程加锁的理解
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈针对多线程加锁的理解〉
 *
 * @author Administrator
 * @create 2019-10-12
 * @since 1.0.0
 */
class Phone{
    //该方法加锁
    public static synchronized void sendEmil() throws InterruptedException {
        //使线程休眠4秒jdk8新特性
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName()+"\t sendEmil");
    }
    //该方法加锁
    public  synchronized void sendSms()
    {
        System.out.println(Thread.currentThread().getName()+"\t sendSms");
    }
    //普通方法
    public void call()
    {
        System.out.println(Thread.currentThread().getName()+"\t call");
    }
}

/**
 * 1.第一种情况之间启动都是synchronized的方法
 * 结果：我们都知道因为A线程先启动具有先天的优势肯定是先打印发送邮件后答应发送短信
 * 2.在A线程调用的发邮件的方法使线程休眠4秒钟
 * 结果：先打印邮件后打印短信，因为我的使用的synchronized的方法，它会锁定对象的锁，而这个对象只有一把锁，当我们的
 * A线程先进入synchronized的方法，就把这个对象的锁拿走了，这时候B线程进入这个加油synchronized的方法没有锁只能等待
 * 直到A线程休眠4秒后把锁换回来的时候才能进入发短息的方法
 * 3.现在我们的B线程执行普通的打电话方法
 *  结果:先打印打电话后打印发邮件，因为B线程是执行的普通方法没有竞争锁索引先执行普通方法
 * 4.实例化两步手机A线程执行第一步手机的发邮件B线程执行第二部手机的发短息
 *  结果：先执行B线程的发短信后执行A线程的发邮件,因为实例化了两个手机每个手机的锁是不一样的，不发生锁的竞争关系所以不影响B线程
 * 5.同一部手机两个都是静态的synchronized的方法
 *  结果：先执行A线程的发邮件后执行发短信，因为synchronized锁的是对象的锁，但是静态同步方法是锁当当前的class对象，因为是同一个class对象
 *  所以B只能等待A线程的完结才能开始
 * 6.两部手机都是静态同步方法
 *  结果：虽然是两个手机但是因为都是静态同步方法那就是锁的都是同一个class对象所以和上面的一样
 * 7.一个静态同步方法一个普通的同步方法一部手机
 *  结果：静态同步方法锁的是class对象，同步方法锁的是当前对象的，他两不是一个对象所以和上面一样所以不竞争锁，先打印短信后打印邮件
 *  8一个静态同步方法，一个普通方法，两部手机
 *  结果：因为A线程锁的class对象，但是B线程执行的是phone2的发短息方法该方法锁的的phone2的锁，没有竞争关系所以先执行phone2的发短息
 */
public class CustomLock {

    public static void main(String[] args){
        //创建资源类
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        //创建多线程访问

        new Thread( () -> {
            try {
                phone.sendEmil();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread( () -> {
            //phone.sendSms();
            //phone.call();
            //phone2.sendSms();
            //phone.sendSms();
            //phone2.sendSms();
            //phone.sendSms();
            phone2.sendSms();
        },"B").start();
    }
}