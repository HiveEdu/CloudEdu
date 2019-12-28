package com.myedu.project.thumbsup.repository;

import com.myedu.project.thumbsup.domain.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface UserLikeRepository extends JpaRepository<UserLike, Integer> {

    Page<UserLike> findByLikedUserIdAndStatus(String likedUserId, Integer status, Pageable pageable);

    Page<UserLike> findByLikedPostIdAndStatus(String likedPostId, Integer status, Pageable pageable);

    UserLike findByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);
}