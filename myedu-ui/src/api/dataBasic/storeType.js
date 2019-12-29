import request from '@/utils/request'

// 查询门店类型列表
export function listStoreType(query) {
  return request({
    url: '/dataBasic/storeType/list',
    method: 'get',
    params: query
  })
}

// 查询门店类型详细
export function getStoreType(id) {
  return request({
    url: '/dataBasic/storeType/' + id,
    method: 'get'
  })
}

// 新增门店类型
export function addStoreType(data) {
  return request({
    url: '/dataBasic/storeType',
    method: 'post',
    data: data
  })
}

// 修改门店类型
export function updateStoreType(data) {
  return request({
    url: '/dataBasic/storeType',
    method: 'put',
    data: data
  })
}

// 删除门店类型
export function delStoreType(id) {
  return request({
    url: '/dataBasic/storeType/' + id,
    method: 'delete'
  })
}

// 导出门店类型
export function exportStoreType(query) {
  return request({
    url: '/dataBasic/storeType/export',
    method: 'get',
    params: query
  })
}
