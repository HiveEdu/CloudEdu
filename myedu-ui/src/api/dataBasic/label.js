import request from '@/utils/request'

// 查询标签管理列表
export function listLabel(query) {
  return request({
    url: '/dataBasic/label/list',
    method: 'get',
    params: query
  })
}

// 查询标签管理详细
export function getLabel(id) {
  return request({
    url: '/dataBasic/label/' + id,
    method: 'get'
  })
}

// 新增标签管理
export function addLabel(data) {
  return request({
    url: '/dataBasic/label',
    method: 'post',
    data: data
  })
}

// 修改标签管理
export function updateLabel(data) {
  return request({
    url: '/dataBasic/label',
    method: 'put',
    data: data
  })
}

// 删除标签管理
export function delLabel(id) {
  return request({
    url: '/dataBasic/label/' + id,
    method: 'delete'
  })
}

// 导出标签管理
export function exportLabel(query) {
  return request({
    url: '/dataBasic/label/export',
    method: 'get',
    params: query
  })
}