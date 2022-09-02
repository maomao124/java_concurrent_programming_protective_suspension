package mao.t3;

/**
 * Project name(项目名称)：java并发编程_保护性暂停
 * Package(包名): mao.t3
 * Class(类名): GuardedObject
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 23:12
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class GuardedObject
{
    // 标识 Guarded Object
    private int id;

    /**
     * 响应
     */
    private Object response;

    /**
     * 保护对象
     *
     * @param id id
     */
    public GuardedObject(int id)
    {
        this.id = id;
    }

    /**
     * 得到id
     *
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * 得到响应
     *
     * @param timeout 超时时间
     * @return {@link Object}
     */
    public Object getResponse(long timeout)
    {
        synchronized (this)
        {
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            while (response == null)
            {
                long waitTime = timeout - passedTime;
                if (timeout - passedTime <= 0)
                {
                    break;
                }
                try
                {
                    this.wait(waitTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
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
        synchronized (this)
        {
            // 给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }


}
