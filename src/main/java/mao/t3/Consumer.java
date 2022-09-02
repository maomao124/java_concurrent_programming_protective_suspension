package mao.t3;

/**
 * Project name(项目名称)：java并发编程_保护性暂停
 * Package(包名): mao.t3
 * Class(类名): Consumer
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 23:18
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Consumer extends Thread
{
    @Override
    public void run()
    {
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        System.out.println("开始收信 id:" + guardedObject.getId());
        Object mail = guardedObject.getResponse(5000);
        System.out.println("收到信 id:" + guardedObject.getId() + ", 内容:" + mail);
    }
}
