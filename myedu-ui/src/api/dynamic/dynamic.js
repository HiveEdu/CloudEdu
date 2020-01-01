import request from '@/utils/request'

// 查询云托管动态管理列表
export function listDynamic(query) {
  return request({
    url: '/dynamic/dynamic/list',
    method: 'get',
    params: query
  })
}

// 查询云托管动态管理详细
export function getDynamic(id) {
  return request({
    url: '/dynamic/dynamic/' + id,
    method: 'get'
  })
}

// 新增云托管动态管理
export function addDynamic(data) {
  return request({
    url: '/dynamic/dynamic',
    method: 'post',
    data: data
  })
}

// 修改云托管动态管理
export function updateDynamic(data) {
  return request({
    url: '/dynamic/dynamic',
    method: 'put',
    data: data
  })
}

// 删除云托管动态管理
export function delDynamic(id) {
  return request({
    url: '/dynamic/dynamic/' + id,
    method: 'delete'
  })
}

// 导出云托管动态管理
export function exportDynamic(query) {
  return request({
    url: '/dynamic/dynamic/export',
    method: 'get',
    params: query
  })
}

//上傳照片动态
export function uploadImage(data) {
  return request({
    // url: '/dynamic/dynamic/upload',
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}
