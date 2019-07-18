/**
 * Copyright (C), 2015-2019, 百佳信科技有限公司
 * FileName: HELLOWORD
 * Author:   Administrator
 * Date:     2019-7-18 10:12
 * Description: 新手练习
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bjx.wsy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈新手练习〉
 *
 * @author Administrator
 * @create 2019-7-18
 * @since 1.0.0
 */
public class HelloWorld {
    private static String NAME;

   private static final String address ="CHINA";
   private Integer age = 12;
    public static void main(String[] args) {
        System.out.println("test idea");
        ArrayList list = new ArrayList();
        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "mrsWang");
        String name = map2.get("name");
        int num= 1;
        System.out.println("num = " + num);
        System.out.println("name = " + name);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        if (list != null) {
            System.out.println("is not null");
        }
        for (Object o : list) {
            System.out.println("o = " + o);
        }
        for (int i = 0; i <list.size(); i++) {
            System.out.println("list"+i+"=="+list.get(i));
        }
    }

    public  void method(String id){
        StringBuffer buffer = new StringBuffer("abc");
    }
}