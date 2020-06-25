package com.myedu.project.store.storeSearch.service.Impl;

import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import com.myedu.project.store.storeSearch.reponsitory.StoreSearchVoRepository;
import com.myedu.project.store.storeSearch.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreSearchImpl implements StoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSearchImpl.class);
//    @Autowired
//    private EsProductDao productDao;
    @Autowired
    private StoreSearchVoRepository storeRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public Page<StoreSearchVo> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return storeRepository.findByNameOrOrKeywords(keyword, keyword, pageable);
    }


}
