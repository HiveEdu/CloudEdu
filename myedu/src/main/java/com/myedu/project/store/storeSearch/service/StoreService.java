package com.myedu.project.store.storeSearch.service;

import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import org.springframework.data.domain.Page;
public interface StoreService {

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<StoreSearchVo> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */

}
