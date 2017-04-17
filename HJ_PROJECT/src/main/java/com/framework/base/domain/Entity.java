package com.framework.base.domain;

import java.io.Serializable;


/**
 * 作用:描述一个实体，所有对象的实现，包含一些如：id,等基本的属性
 * <p/>
 */

public interface Entity extends Serializable {

	Long getId ();

    void setId (Long id);

    /*
    String getEntityName();

    void setEntityName(String entityName);
	*/
}
