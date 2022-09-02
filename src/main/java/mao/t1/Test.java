package mao.t1;

import java.util.Date;

/**
 * Project name(项目名称)：java并发编程_保护性暂停
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/1
 * Time(创建时间)： 21:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    public static void main(String[] args)
    {
        GuardedObject guardedObject = new GuardedObject();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                //休眠2秒
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                //返回结果
                guardedObject.complete(Math.random());

            }
        }, "t1").start();

        //主线程等待
        System.out.println("主线程开始获取t1线程的运行结果");
        System.out.println(new Date());
        Object response = guardedObject.getResponse();
        System.out.println("结果为" + response);
        System.out.println(new Date());
    }
}
