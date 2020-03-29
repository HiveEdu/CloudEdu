package com.myedu.app.dynamic.controller;

import com.myedu.common.utils.DyRedisKeyUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dynamic.domain.DyLikedCountDTO;
import com.myedu.project.dynamic.domain.YunDyComment;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.domain.YunDynamic;
import com.myedu.project.dynamic.domain.vo.YunDynamicVo;
import com.myedu.project.dynamic.service.IYunDyCommentService;
import com.myedu.project.dynamic.service.IYunDyLikesService;
import com.myedu.project.dynamic.service.IYunDynamicService;
import com.myedu.project.parents.domain.YunComplaint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 云托管动态管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Api("云托管动态管理")
@RestController
@RequestMapping("/app/dynamic/dynamic")
public class AppDynamicController extends BaseController
{
    @Autowired
    private IYunDynamicService yunDynamicService;
    @Autowired
    private IYunDyLikesService yunDyLikesService;
    @Autowired
    private IYunDyCommentService yunDyCommentService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询云托管动态管理列表
     */
    @ApiOperation("查询云托管动态管理列表")
    @ApiImplicitParam(name = "yunDynamic", value = "查询云托管动态管理列表",
            dataType = "YunDynamicVo")
    @GetMapping("/list")
    public TableDataInfo list(YunDynamicVo yunDynamic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
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
        }else {
            return getDataTableLose(null);
        }

    }

    /**
     * 导出云托管动态管理列表
     */
    @ApiOperation("导出云托管动态管理列表")
    @ApiImplicitParam(name = "yunDynamic", value = "导出云托管动态管理列表",
            dataType = "YunDynamicVo")
    @Log(title = "云托管动态管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunDynamicVo yunDynamic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            List<YunDynamicVo> list = yunDynamicService.selectYunDynamicList(yunDynamic);
            ExcelUtil<YunDynamicVo> util = new ExcelUtil<YunDynamicVo>(YunDynamicVo.class);
            return util.exportExcel(list, "dynamic");
        }else {
            return AjaxResult.error("token无效");
        }

    }



    /**
     * 获取云托管动态管理详细信息
     */
    @ApiOperation("获取云托管动态管理详细信息")
    @ApiImplicitParam(name = "id", value = "获取云托管动态管理详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        if (loginUser!=null) {
            YunDyComment yunDyComment=new YunDyComment();
            yunDyComment.setDyId(id);
            List<YunDyComment> yunDyComments=yunDyCommentService.selectYunDyCommentList(yunDyComment);
            ajax.put("yunDyComments", yunDyComments);
            ajax.put(AjaxResult.DATA_TAG, yunDynamicService.selectYunDynamicById(id));
            return ajax;
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 新增云托管动态管理
     */
    @ApiOperation("新增云托管动态管理")
    @ApiImplicitParam(name = "yunDynamic", value = "新增云托管动态管理",
            dataType = "YunDynamic")
    @Log(title = "云托管动态管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDynamic yunDynamic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunDynamic.setCreateById(loginUser.getUser().getUserId());
            yunDynamic.setCreateBy(loginUser.getUser().getNickName());
            return toAjax(yunDynamicService.insertYunDynamic(yunDynamic));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 修改云托管动态管理
     */
    @ApiOperation("修改云托管动态管理")
    @ApiImplicitParam(name = "yunDynamic", value = "修改云托管动态管理",
            dataType = "YunDynamic")
    @Log(title = "云托管动态管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDynamic yunDynamic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunDynamic.setUpdateBy(loginUser.getUser().getNickName());
            return toAjax(yunDynamicService.updateYunDynamic(yunDynamic));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 删除云托管动态管理
     */
    @ApiOperation("删除云托管动态管理")
    @ApiImplicitParam(name = "ids", value = "删除云托管动态管理",
            dataType = "Long[]")
    @Log(title = "云托管动态管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
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
        }else {
            return AjaxResult.error("token无效");
        }

    }



    /**
     * 动态点赞
     */
    @ApiOperation("动态点赞")
    @ApiImplicitParam(name = "ids", value = "动态点赞",
            dataType = "Long")
    @GetMapping(value = "/like/{id}")
    public AjaxResult like(@PathVariable("id") Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            AjaxResult ajax = AjaxResult.success();
            //先把数据存到Redis里,再定时存回数据库
            yunDyLikesService.saveLiked2Redis(id, loginUser.getUser().getUserId());
            yunDyLikesService.incrementLikedCount(id);
            return ajax;
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 动态取消点赞
     */
    @ApiOperation("动态取消点赞")
    @ApiImplicitParam(name = "id", value = "动态取消点赞",
            dataType = "Long")
    @GetMapping(value = "/unlike/{id}")
    public AjaxResult unlike(@PathVariable("id") Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            AjaxResult ajax = AjaxResult.success();
            //先把数据存到Redis里,再定时存回数据库
            yunDyLikesService.unlikeFromRedis(id, loginUser.getUser().getUserId());
            yunDyLikesService.decrementLikedCount(id);
            return ajax;
        }else {
            return AjaxResult.error("token无效");
        }

    }


    /**
     * 新增云托管动态管理
     */
    @ApiOperation("新增云托管动态管理")
    @ApiImplicitParam(name = "yunDyComment", value = "新增云托管动态管理",
            dataType = "YunDyComment")
    @Log(title = "云托管动态管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/comment")
    public AjaxResult comment(@RequestBody YunDyComment yunDyComment)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            AjaxResult ajax = AjaxResult.success();
            yunDyComment.setCreateById(loginUser.getUser().getUserId());
            yunDyComment.setCreateBy(loginUser.getUser().getNickName());
            yunDyCommentService.insertYunDyComment(yunDyComment);
            YunDynamic  yunDynamic=yunDynamicService.selectYunDynamicById(yunDyComment.getDyId());
            yunDynamic.setComments(yunDynamic.getComments()+1);
            return toAjax(yunDynamicService.updateYunDynamic(yunDynamic));
        }else {
            return AjaxResult.error("token无效");
        }

    }



}
