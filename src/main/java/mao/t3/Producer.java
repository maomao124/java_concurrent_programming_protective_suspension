package mao.t3;

/**
 * Project name(项目名称)：java并发编程_保护性暂停
 * Package(包名): mao.t3
 * Class(类名): Producer
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 23:21
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Producer extends Thread
{
    /**
     * id
     */
    private final int id;

    /**
     * 邮件
     */
    private final String mail;

    /**
     * 生产商
     *
     * @param id   id
     * @param mail 邮件
     */
    public Producer(int id, String mail)
    {
        this.id = id;
        this.mail = mail;
    }

    /**
     * 运行
     */
    @Override
    public void run()
    {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        System.out.println("送信 id:" + id + ", 内容:" + mail);
        guardedObject.complete(mail);
    }
}
