package com.myedu.project.dynamic.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.DyRedisKeyUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.file.FileUploadUtils;
import com.myedu.framework.config.MyEduConfig;
import com.myedu.framework.security.LoginUser;
import com.myedu.project.dynamic.domain.DyLikedCountDTO;
import com.myedu.project.dynamic.domain.YunDyComment;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.domain.vo.YunDynamicVo;
import com.myedu.project.dynamic.service.IYunDyCommentService;
import com.myedu.project.dynamic.service.IYunDyLikesService;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.project.dynamic.domain.YunDynamic;
import com.myedu.project.dynamic.service.IYunDynamicService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 云托管动态管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@RestController
@RequestMapping("/dynamic/dynamic")
public class YunDynamicController extends BaseController
{
    @Autowired
    private IYunDynamicService yunDynamicService;
    @Autowired
    private IYunDyLikesService yunDyLikesService;
    @Autowired
    private IYunDyCommentService yunDyCommentService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询云托管动态管理列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunDynamicVo yunDynamic)
    {
        startPage();
        List<YunDynamicVo> list = yunDynamicService.selectYunDynamicList(yunDynamic);
        List<YunDynamicVo> listNew =new ArrayList<>();
        //将redis中的数据与数据库中数据相结合
        list.stream().forEach(p ->{
            List<YunDyLikes> yunDyLikes = yunDyLikesService.getLikedDataFromRedisList();
            for (YunDyLikes like : yunDyLikes) {
                if(p.getId()==like.getDyId()){
                    p.setStatus(like.getStatus());
                }
            }
            List<DyLikedCountDTO> dyLikedCountDTOS = yunDyLikesService.getLikedCountFromRedisList();
            for (DyLikedCountDTO dto : dyLikedCountDTOS) {
                YunDynamicVo yunDynamicVo = yunDynamicService.selectYunDynamicById(dto.getId());
                //点赞数量属于无关紧要的操作，出错无需抛异常
                if (yunDynamicVo != null) {
                    if(yunDynamicVo.getLikes()==null){yunDynamicVo.setLikes((long) 0);}
                    Long likeNum = yunDynamicVo.getLikes() + dto.getCount();
                    if(p.getId()==yunDynamicVo.getId()){
                        p.setLikes(likeNum);
                    }
                }
            }
            listNew.add(p);
        });
        return getDataTable(listNew);
    }

    /**
     * 导出云托管动态管理列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:export')")
    @Log(title = "云托管动态管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunDynamicVo yunDynamic)
    {
        List<YunDynamicVo> list = yunDynamicService.selectYunDynamicList(yunDynamic);
        ExcelUtil<YunDynamicVo> util = new ExcelUtil<YunDynamicVo>(YunDynamicVo.class);
        return util.exportExcel(list, "dynamic");
    }

    /**
     * 获取云托管动态管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunDynamicService.selectYunDynamicById(id));
    }

    /**
     * 新增云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:add')")
    @Log(title = "云托管动态管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDynamic yunDynamic)
    {
        yunDynamic.setCreateById(SecurityUtils.getUserId());
        yunDynamic.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunDynamicService.insertYunDynamic(yunDynamic));
    }

    /**
     * 修改云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:edit')")
    @Log(title = "云托管动态管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDynamic yunDynamic)
    {
        yunDynamic.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunDynamicService.updateYunDynamic(yunDynamic));
    }

    /**
     * 删除云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:remove')")
    @Log(title = "云托管动态管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for (int i = 0; i < ids.length; i++) {
            yunDyLikesService.deleteYunDyLikesByDyId(ids[i]);
            yunDyCommentService.deleteYunDyCommentByDyId(ids[i]);
            //删除redis中缓存的点赞数据
            Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, ScanOptions.NONE);
            while (cursor.hasNext()){
                Map.Entry<Object, Object> entry = cursor.next();
                String key = String.valueOf(entry.getKey());
                String[] split = key.split("::");
                System.out.println("split.length"+split.length);
                Long likedDynamicId = Long.valueOf(split[0]);
                //存到 list 后从 Redis 中删除
                if(ids[i]==likedDynamicId){
                    redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, key);
                }
            }
            Cursor<Map.Entry<Object, Object>> cursor2 = redisTemplate.opsForHash().scan(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, ScanOptions.NONE);
            while (cursor2.hasNext()){
                Map.Entry<Object, Object> map = cursor2.next();
                //将点赞数量存储在 DyLikedCountDTO
                System.out.println("map.getKey()____"+map.getKey());
                String key = String.valueOf(map.getKey());
                if(ids[i]==Long.valueOf(key)){
                    //从Redis中删除这条记录
                    redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, map.getKey());
                }
            }
        }
        return toAjax(yunDynamicService.deleteYunDynamicByIds(ids));
    }




    /**
     * 动态点赞
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:like')")
    @GetMapping(value = "/like/{id}")
    public AjaxResult like(@PathVariable("id") Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        //先把数据存到Redis里,再定时存回数据库
        yunDyLikesService.saveLiked2Redis(id, SecurityUtils.getUserId());
        yunDyLikesService.incrementLikedCount(id);
        return ajax;
    }

    /**
     * 动态取消点赞
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:unlike')")
    @GetMapping(value = "/unlike/{id}")
    public AjaxResult unlike(@PathVariable("id") Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        //先把数据存到Redis里,再定时存回数据库
        yunDyLikesService.unlikeFromRedis(id, SecurityUtils.getUserId());
        yunDyLikesService.decrementLikedCount(id);
        return ajax;
    }


    /**
     * 新增云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:comment')")
    @Log(title = "云托管动态管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/comment")
    public AjaxResult comment(@RequestBody YunDyComment yunDyComment)
    {
        AjaxResult ajax = AjaxResult.success();
        yunDyComment.setCreateById(SecurityUtils.getUserId());
        yunDyComment.setCreateBy(SecurityUtils.getUsername());
        yunDyCommentService.insertYunDyComment(yunDyComment);
        YunDynamic  yunDynamic=yunDynamicService.selectYunDynamicById(yunDyComment.getDyId());
        yunDynamic.setComments(yunDynamic.getComments()+1);
        return toAjax(yunDynamicService.updateYunDynamic(yunDynamic));
    }
}
