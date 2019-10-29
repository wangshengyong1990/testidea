/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: BlockingQueueDemo
 * Author:   Administrator
 * Date:     2019-10-29 16:04
 * Description: blokingqueue
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈blokingqueue〉
 *
 * @author Administrator
 * @create 2019-10-29
 * @since 1.0.0
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建一个队列对象，该队列是实现了collection接口的
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //队列添加元素并成功返回true失败会报错
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));
        //移除队列的第一个元素并返回移除的对象,当队列没有元素时会报错
        System.out.println("移除队列的第一个元素并返回移除的对象==="+blockingQueue.remove());
        //检查队列的元素排列第一个元素
        System.out.println("检查队列的元素排列第一个元素====="+blockingQueue.element());
        //添加队列元素，成功返回true，失败返回false
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        //依次获取队列的元素，没有的元素为null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //当队列中的元素满了将会一直阻塞队列的添加，直到队列有元素取出为止
        blockingQueue.put("z");
        blockingQueue.put("x");
        blockingQueue.put("c");

        System.out.println(blockingQueue.take());
        blockingQueue.put("v");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //取出队列的元素，当没有元素的时候没有返回值，但是如果队列中没有元素，他会一直等待队列加入元素
        //System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //和上面的offer的用法一样都是添加元素，但是这个会等待一定的时间，如果时间过期则不等待返回false
        System.out.println(blockingQueue.offer("d",3L, TimeUnit.SECONDS));


    }
}