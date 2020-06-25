package com.myedu.project.system.service.impl;

import cn.hutool.http.HttpRequest;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.project.system.Repository.YunUserCollectRepository;
import com.myedu.project.system.domain.SysUser;
import com.myedu.project.system.domain.YunUserCollect;
import com.myedu.project.system.mapper.YunUserCollectMapper;
import com.myedu.project.system.service.IYunUserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户收藏Service业务层处理
 * 
 * @author myedu
 * @date 2020-06-25
 */
@Service
public class YunUserCollectServiceImpl implements IYunUserCollectService 
{
    @Autowired
    private YunUserCollectMapper yunUserCollectMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private YunUserCollectRepository yunUserCollectRepository;

    /**
     * 查询用户收藏
     * 
     * @param id 用户收藏ID
     * @return 用户收藏
     */
    @Override
    public YunUserCollect selectYunUserCollectById(Long id)
    {
        return yunUserCollectMapper.selectYunUserCollectById(id);
    }


    /**
     * 查询用户收藏列表
     * 
     * @param yunUserCollect 用户收藏
     * @return 用户收藏
     */
    @Override
    public List<YunUserCollect> selectYunUserCollectList(YunUserCollect yunUserCollect)
    {
        return yunUserCollectMapper.selectYunUserCollectList(yunUserCollect);
    }

    /**
     * 新增用户收藏
     * 
     * @param yunUserCollect 用户收藏
     * @return 结果
     */
    @Override
    public int insertYunUserCollect(YunUserCollect yunUserCollect)
    {

        int count = 0;
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //用户id
        yunUserCollect.setUserId(loginUser.getUser().getUserId());
        //用户头像
        yunUserCollect.setUserImage(loginUser.getUser().getAvatar());
        //用户昵称
        yunUserCollect.setUserNickname(loginUser.getUser().getNickName());
        yunUserCollect.setCreateBy(loginUser.getUser().getNickName());
        yunUserCollect.setCreateTime(DateUtils.getNowDate());

        YunUserCollect findCollection = yunUserCollectRepository.findByUserIdAndStoreId(yunUserCollect.getUserId(),
                yunUserCollect.getStoreId());
        if (findCollection == null) {
            yunUserCollectMapper.insertYunUserCollect(yunUserCollect);
            count = 1;
        }
        return count;
    }

    /**
     * 修改用户收藏
     * 
     * @param yunUserCollect 用户收藏
     * @return 结果
     */
    @Override
    public int updateYunUserCollect(YunUserCollect yunUserCollect)
    {
        yunUserCollect.setUpdateTime(DateUtils.getNowDate());
        return yunUserCollectMapper.updateYunUserCollect(yunUserCollect);
    }

    /**
     * 批量删除用户收藏
     * 
     * @param ids 需要删除的用户收藏ID
     * @return 结果
     */
    @Override
    public int deleteYunUserCollectByIds(Long[] ids)
    {
        return yunUserCollectMapper.deleteYunUserCollectByIds(ids);
    }

    /**
     * 删除用户收藏信息
     * 
     * @param id 用户收藏ID
     * @return 结果
     */
//    @Override
//    public int deleteYunUserCollectById(Long id)
//    {
//        return yunUserCollectMapper.deleteYunUserCollectById(id);
//    }
    /**
     * 用户删除收藏信息
     *
     * @param storeId 用户收藏ID
     * @return 结果
     */
    @Override
    public int delete(Long storeId) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return yunUserCollectRepository.deleteByUserIdAndStoreId(loginUser.getUser().getUserId(), storeId);
    }
}
