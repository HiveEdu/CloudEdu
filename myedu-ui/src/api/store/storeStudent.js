import request from '@/utils/request'

// 查询门店学生管理列表
export function listStoreStudent(query) {
  return request({
    url: '/store/storeStudent/list',
    method: 'get',
    params: query
  })
}

// 查询门店学生管理详细
export function getStoreStudent(id) {
  return request({
    url: '/store/storeStudent/' + id,
    method: 'get'
  })
}

// 新增门店学生管理
export function addStoreStudent(data) {
  return request({
    url: '/store/storeStudent',
    method: 'post',
    data: data
  })
}

// 修改门店学生管理
export function updateStoreStudent(data) {
  return request({
    url: '/store/storeStudent',
    method: 'put',
    data: data
  })
}

// 删除门店学生管理
export function delStoreStudent(id) {
  return request({
    url: '/store/storeStudent/' + id,
    method: 'delete'
  })
}

// 导出门店学生管理
export function exportStoreStudent(query) {
  return request({
    url: '/store/storeStudent/export',
    method: 'get',
    params: query
  })
}