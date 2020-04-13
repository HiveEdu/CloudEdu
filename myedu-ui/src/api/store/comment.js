import request from '@/utils/request'

// 查询课程评论列表
export function listComment(query) {
  return request({
    url: '/store/comment/list',
    method: 'get',
    params: query
  })
}

// 查询课程评论详细
export function getComment(id) {
  return request({
    url: '/store/comment/' + id,
    method: 'get'
  })
}

// 新增课程评论
export function addComment(data) {
  return request({
    url: '/store/comment',
    method: 'post',
    data: data
  })
}

// 修改课程评论
export function updateComment(data) {
  return request({
    url: '/store/comment',
    method: 'put',
    data: data
  })
}

// 删除课程评论
export function delComment(id) {
  return request({
    url: '/store/comment/' + id,
    method: 'delete'
  })
}

// 导出课程评论
export function exportComment(query) {
  return request({
    url: '/store/comment/export',
    method: 'get',
    params: query
  })
}