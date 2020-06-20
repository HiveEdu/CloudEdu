import request from '@/utils/request'

// 查询组合数据详情列表
export function listYunSystemGroupData(query) {
  return request({
    url: '/system/yunSystemGroupData/list',
    method: 'get',
    params: query
  })
}

// 查询组合数据详情详细
export function getYunSystemGroupData(id) {
  return request({
    url: '/system/yunSystemGroupData/' + id,
    method: 'get'
  })
}

// 新增组合数据详情
export function addYunSystemGroupData(data) {
  return request({
    url: '/system/yunSystemGroupData',
    method: 'post',
    data: data
  })
}

// 修改组合数据详情
export function updateYunSystemGroupData(data) {
  return request({
    url: '/system/yunSystemGroupData',
    method: 'put',
    data: data
  })
}

// 删除组合数据详情
export function delYunSystemGroupData(id) {
  return request({
    url: '/system/yunSystemGroupData/' + id,
    method: 'delete'
  })
}

// 导出组合数据详情
export function exportYunSystemGroupData(query) {
  return request({
    url: '/system/yunSystemGroupData/export',
    method: 'get',
    params: query
  })
}