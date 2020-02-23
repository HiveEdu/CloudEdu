import request from '@/utils/request'

// 查询云托管动态管理列表
export function listDynamic(query) {
  return request({
    url: '/dynamic/dynamic/list',
    method: 'get',
    params: query
  })
}

// 查询云托管动态管理详细
export function getDynamic(id) {
  return request({
    url: '/dynamic/dynamic/' + id,
    method: 'get'
  })
}

//点赞
export function like(id) {
  return request({
    url: '/dynamic/dynamic/like/' + id,
    method: 'get'
  })
}

//取消点赞
export function unlike(id) {
  return request({
    url: '/dynamic/dynamic/unlike/' + id,
    method: 'get'
  })
}

//评论
export function comment(data) {
  return request({
    url: '/dynamic/dynamic/comment',
    method: 'post',
    data: data
  })
}
// 新增云托管动态管理
export function addDynamic(data) {
  return request({
    url: '/dynamic/dynamic',
    method: 'post',
    data: data
  })
}

// 修改云托管动态管理
export function updateDynamic(data) {
  return request({
    url: '/dynamic/dynamic',
    method: 'put',
    data: data
  })
}

// 删除云托管动态管理
export function delDynamic(id) {
  return request({
    url: '/dynamic/dynamic/' + id,
    method: 'delete'
  })
}

// 导出云托管动态管理
export function exportDynamic(query) {
  return request({
    url: '/dynamic/dynamic/export',
    method: 'get',
    params: query
  })
}

//上傳照片动态
export function uploads(data) {
  return request({
    url: data.url,
    method: 'post',
    data: data
  })
}
