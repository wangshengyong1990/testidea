/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: CustomThread
 * Author:   Administrator
 * Date:     2019-10-11 14:35
 * Description: java8新特性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈java8新特性〉
 *
 * @author Administrator
 * @create 2019-10-11
 * @since 1.0.0
 */
class SellSomething {
    private int count = 15;
    Lock lock = new ReentrantLock();

    public void Sell(){
        lock.lock();
        try {
            if(count>0){
                System.out.println(Thread.currentThread().getName()+"进行售卖"+(count--)+"还剩下"+count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class CustomThread {

    public static void main(String[] args) {
        SellSomething sellSomething = new SellSomething();
        //java 8新特性匿名内部类调用单一接口的方式
        /**这样的使用效果使用有前提条件，该接口方法只有一个方法
         * 拷贝（）写死右箭头 落地大括号
         */
        new Thread(()-> {
            for (int i=0;i<20;i++) sellSomething.Sell();


        },"A").start();
        new Thread(()-> {
            for (int i=0;i<20;i++) sellSomething.Sell();
        },"B").start();
        new Thread(()-> {
            for (int i=0;i<20;i++) sellSomething.Sell();
        },"C").start();

    }
}