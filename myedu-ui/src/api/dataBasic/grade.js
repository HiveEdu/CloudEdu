import request from '@/utils/request'

// 查询年级基础列表
export function listGrade(query) {
  return request({
    url: '/dataBasic/grade/list',
    method: 'get',
    params: query
  })
}

// 查询年级基础详细
export function getGrade(id) {
  return request({
    url: '/dataBasic/grade/' + id,
    method: 'get'
  })
}

// 新增年级基础
export function addGrade(data) {
  return request({
    url: '/dataBasic/grade',
    method: 'post',
    data: data
  })
}

// 修改年级基础
export function updateGrade(data) {
  return request({
    url: '/dataBasic/grade',
    method: 'put',
    data: data
  })
}

// 删除年级基础
export function delGrade(id) {
  return request({
    url: '/dataBasic/grade/' + id,
    method: 'delete'
  })
}

// 导出年级基础
export function exportGrade(query) {
  return request({
    url: '/dataBasic/grade/export',
    method: 'get',
    params: query
  })
}
