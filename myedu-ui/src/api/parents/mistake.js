import request from '@/utils/request'

// 查询学生错题记录列表
export function listMistake(query) {
  return request({
    url: '/parents/mistake/list',
    method: 'get',
    params: query
  })
}

// 查询学生错题记录详细
export function getMistake(id) {
  return request({
    url: '/parents/mistake/' + id,
    method: 'get'
  })
}

// 新增学生错题记录
export function addMistake(data) {
  return request({
    url: '/parents/mistake',
    method: 'post',
    data: data
  })
}

// 修改学生错题记录
export function updateMistake(data) {
  return request({
    url: '/parents/mistake',
    method: 'put',
    data: data
  })
}

// 删除学生错题记录
export function delMistake(id) {
  return request({
    url: '/parents/mistake/' + id,
    method: 'delete'
  })
}

// 导出学生错题记录
export function exportMistake(query) {
  return request({
    url: '/parents/mistake/export',
    method: 'get',
    params: query
  })
}