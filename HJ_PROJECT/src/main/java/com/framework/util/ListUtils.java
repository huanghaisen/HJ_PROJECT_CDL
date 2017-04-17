package com.framework.util;

import com.framework.base.domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * List工具类
 * <p/>
 */
public class ListUtils {
    /**
     * Iterator转List
     *
     * @param it Iterator
     * @return List
     */
    public static List itTo(Iterator it) {
        List list = new ArrayList();
        while (it.hasNext()) {
            Object o = it.next();
            list.add(o);
        }
        return list;
    }

    /**
     * 查看List里是否包含对象
     *
     * @param list   list
     * @param entity 实体对象
     * @return 有true 无false
     */
    public static boolean contains(List list, Entity entity) {
        boolean exist = false;
        if (list != null && list.size() > 0)
            for (int j = 0; j < list.size(); j++) {
                Entity info_li = (Entity) list.get(j);
                if (info_li.getId() == entity.getId()) {
                    list.remove(info_li);
                    exist = true;
                    break;
                }
            }
        return exist;
    }
}
