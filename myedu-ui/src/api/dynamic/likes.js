import request from '@/utils/request'

// 查询动态点赞列表
export function listLikes(query) {
  return request({
    url: '/dynamic/likes/list',
    method: 'get',
    params: query
  })
}

// 查询动态点赞详细
export function getLikes(id) {
  return request({
    url: '/dynamic/likes/' + id,
    method: 'get'
  })
}

// 新增动态点赞
export function addLikes(data) {
  return request({
    url: '/dynamic/likes',
    method: 'post',
    data: data
  })
}

// 修改动态点赞
export function updateLikes(data) {
  return request({
    url: '/dynamic/likes',
    method: 'put',
    data: data
  })
}

// 删除动态点赞
export function delLikes(id) {
  return request({
    url: '/dynamic/likes/' + id,
    method: 'delete'
  })
}

// 导出动态点赞
export function exportLikes(query) {
  return request({
    url: '/dynamic/likes/export',
    method: 'get',
    params: query
  })
}