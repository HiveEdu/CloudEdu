package com.myedu.project.store.storeSearch.reponsitory;

import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface StoreSearchVoRepository extends ElasticsearchRepository<StoreSearchVo, Long>
{

    public List<StoreSearchVo> findByNameContaining(String name);
}
