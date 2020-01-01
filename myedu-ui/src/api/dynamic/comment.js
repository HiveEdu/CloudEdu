import request from '@/utils/request'

// 查询动态评论列表
export function listComment(query) {
  return request({
    url: '/dynamic/comment/list',
    method: 'get',
    params: query
  })
}

// 查询动态评论详细
export function getComment(id) {
  return request({
    url: '/dynamic/comment/' + id,
    method: 'get'
  })
}

// 新增动态评论
export function addComment(data) {
  return request({
    url: '/dynamic/comment',
    method: 'post',
    data: data
  })
}

// 修改动态评论
export function updateComment(data) {
  return request({
    url: '/dynamic/comment',
    method: 'put',
    data: data
  })
}

// 删除动态评论
export function delComment(id) {
  return request({
    url: '/dynamic/comment/' + id,
    method: 'delete'
  })
}

// 导出动态评论
export function exportComment(query) {
  return request({
    url: '/dynamic/comment/export',
    method: 'get',
    params: query
  })
}