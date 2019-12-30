import request from '@/utils/request'

// 查询score列表
export function listScore(query) {
  return request({
    url: '/score/score/list',
    method: 'get',
    params: query
  })
}

// 查询score详细
export function getScore(scoreId) {
  return request({
    url: '/score/score/' + scoreId,
    method: 'get'
  })
}

// 新增score
export function addScore(data) {
  return request({
    url: '/score/score',
    method: 'post',
    data: data
  })
}

// 修改score
export function updateScore(data) {
  return request({
    url: '/score/score',
    method: 'put',
    data: data
  })
}

// 删除score
export function delScore(scoreId) {
  return request({
    url: '/score/score/' + scoreId,
    method: 'delete'
  })
}

// 导出score
export function exportScore(query) {
  return request({
    url: '/score/score/export',
    method: 'get',
    params: query
  })
}