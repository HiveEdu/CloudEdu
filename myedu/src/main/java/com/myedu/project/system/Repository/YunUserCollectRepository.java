package com.myedu.project.system.Repository;
import com.myedu.project.system.domain.YunUserCollect;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface YunUserCollectRepository extends MongoRepository<YunUserCollect,String> {

    YunUserCollect findByUserIdAndStoreId(Long userId, Long storeId);
    int deleteByUserIdAndStoreId(Long userId,Long storeId);
}
