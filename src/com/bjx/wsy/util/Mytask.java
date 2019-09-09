/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: Mytask
 * Author:   Administrator
 * Date:     2019-7-31 9:53
 * Description: task
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy.util;

/**
 * 〈一句话功能简述〉<br> 
 * 〈task〉
 *
 * @author Administrator
 * @create 2019-7-31
 * @since 1.0.0
 */
public class Mytask implements Runnable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("task正在执行"+name+"的任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+name+"执行完毕");
    }

    public Mytask(String name) {
        this.name = name;
    }
}