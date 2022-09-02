package mao.t3;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Project name(项目名称)：java并发编程_保护性暂停
 * Package(包名): mao.t3
 * Class(类名): Mailboxes
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 23:15
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Mailboxes
{
    /**
     * 盒子
     */
    private static final Map<Integer, GuardedObject> boxes = new Hashtable<>();

    private static int id = 1;

    /**
     * 生成id
     *
     * @return int
     */
    private static synchronized int generateId()
    {
        return id++;
    }

    /**
     * 获得保护对象
     *
     * @param id id
     * @return {@link GuardedObject}
     */
    public static GuardedObject getGuardedObject(int id)
    {
        return boxes.remove(id);
    }

    /**
     * 创建对象
     *
     * @return {@link GuardedObject}
     */
    public static GuardedObject createGuardedObject()
    {
        GuardedObject guardedObject = new GuardedObject(generateId());
        boxes.put(guardedObject.getId(), guardedObject);
        return guardedObject;
    }

    /**
     * 得到id
     *
     * @return {@link Set}<{@link Integer}>
     */
    public static Set<Integer> getIds()
    {
        return boxes.keySet();
    }
}
