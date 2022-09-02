package mao.t2;

/**
 * Project name(项目名称)：java并发编程_保护性暂停
 * Package(包名): mao.t2
 * Class(类名): GuardedObject
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 22:48
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class GuardedObject
{
    /**
     * 响应
     */
    private Object response;

    /**
     * 锁
     */
    private final Object lock = new Object();


    /**
     * 得到响应
     *
     * @param millis 毫秒值
     * @return {@link Object}
     */
    public Object getResponse(long millis)
    {
        synchronized (lock)
        {
            //记录最初时间
            long begin = System.currentTimeMillis();
            //已经经历的时间
            long timePassed = 0;
            while (response == null)
            {
                long waitTime = millis - timePassed;
                if (waitTime <= 0)
                {
                    break;
                }
                try
                {
                    lock.wait(waitTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                timePassed = System.currentTimeMillis() - begin;
            }
            return response;
        }

    }

    /**
     * 完成
     *
     * @param response 响应
     */
    public void complete(Object response)
    {
        synchronized (lock)
        {
            this.response = response;
            System.out.println("notify...");
            lock.notifyAll();
        }
    }

}
