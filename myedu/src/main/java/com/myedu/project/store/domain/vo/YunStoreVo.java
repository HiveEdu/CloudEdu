package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunStore;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/3/14
 * Time: 21:16
 * Description:
 */
@Data
public class YunStoreVo  extends YunStore {

    /*
     * @Description :门店Vip等级
     */
    private Integer level;
}
