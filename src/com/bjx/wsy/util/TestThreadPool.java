/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: TestThreadPool
 * Author:   Administrator
 * Date:     2019-7-31 9:23
 * Description: test ThreadPool
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy.util;

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈test ThreadPool〉
 *
 * @author Administrator
 * @create 2019-7-31
 * @since 1.0.0
 */
public class TestThreadPool{

   private static final int COREPOLLSIZE = 5;//核心线程数
   private static final int MAXMUMPOLLSIZE = 10;//最大线程数
   private static final BlockingQueue linkBlockingQueue = new LinkedBlockingQueue<>(5);
   /*ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；

    LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；

    synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
    */
    private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//拒绝机制
    /*ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
    ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
    ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
    ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务*/
    private static final long KEEPALIVETIME = 2000;//过期时间
    private static final TimeUnit unit = TimeUnit.MILLISECONDS;//过期时间单位毫秒


    public static void main(String[] args) {
        ThreadPoolExecutor testExecutor = new ThreadPoolExecutor(COREPOLLSIZE, MAXMUMPOLLSIZE, KEEPALIVETIME, unit,linkBlockingQueue,handler );
        for (int i = 0; i < 20; i++) {
            Mytask mytask = new Mytask(i+"");
            testExecutor.execute(mytask);
            System.out.println("线程池中线程数目："+testExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    testExecutor.getQueue().size()+"，已执行完的任务数目："+testExecutor.getCompletedTaskCount());
        }
        testExecutor.shutdown();
    }

}