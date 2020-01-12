import request from '@/utils/request'

// 查询课程设置列表
export function listClass(query) {
  return request({
    url: '/yun_class/class/list',
    method: 'get',
    params: query
  })
}

// 查询课程设置详细
export function getClass(id) {
  return request({
    url: '/yun_class/class/' + id,
    method: 'get'
  })
}

// 新增课程设置
export function addClass(data) {
  return request({
    url: '/yun_class/class',
    method: 'post',
    data: data
  })
}

// 修改课程设置
export function updateClass(data) {
  return request({
    url: '/yun_class/class',
    method: 'put',
    data: data
  })
}

// 删除课程设置
export function delClass(id) {
  return request({
    url: '/yun_class/class/' + id,
    method: 'delete'
  })
}

// 导出课程设置
export function exportClass(query) {
  return request({
    url: '/yun_class/class/export',
    method: 'get',
    params: query
  })
}