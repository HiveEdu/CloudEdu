import request from '@/utils/request'

// 查询门店类型关联列表
export function listStoreType(query) {
  return request({
    url: '/store/storeType/list',
    method: 'get',
    params: query
  })
}

// 查询门店类型关联详细
export function getStoreType(storeId) {
  return request({
    url: '/store/storeType/' + storeId,
    method: 'get'
  })
}

// 新增门店类型关联
export function addStoreType(data) {
  return request({
    url: '/store/storeType',
    method: 'post',
    data: data
  })
}

// 修改门店类型关联
export function updateStoreType(data) {
  return request({
    url: '/store/storeType',
    method: 'put',
    data: data
  })
}

// 删除门店类型关联
export function delStoreType(storeId) {
  return request({
    url: '/store/storeType/' + storeId,
    method: 'delete'
  })
}

// 导出门店类型关联
export function exportStoreType(query) {
  return request({
    url: '/store/storeType/export',
    method: 'get',
    params: query
  })
}