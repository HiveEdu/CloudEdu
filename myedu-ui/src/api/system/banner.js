import request from '@/utils/request'

// 查询轮廓图设置列表
export function listBanner(query) {
  return request({
    url: '/system/banner/list',
    method: 'get',
    params: query
  })
}

// 查询轮廓图设置详细
export function getBanner(id) {
  return request({
    url: '/system/banner/' + id,
    method: 'get'
  })
}

// 新增轮廓图设置
export function addBanner(data) {
  return request({
    url: '/system/banner',
    method: 'post',
    data: data
  })
}

// 修改轮廓图设置
export function updateBanner(data) {
  return request({
    url: '/system/banner',
    method: 'put',
    data: data
  })
}

// 删除轮廓图设置
export function delBanner(id) {
  return request({
    url: '/system/banner/' + id,
    method: 'delete'
  })
}

// 导出轮廓图设置
export function exportBanner(query) {
  return request({
    url: '/system/banner/export',
    method: 'get',
    params: query
  })
}