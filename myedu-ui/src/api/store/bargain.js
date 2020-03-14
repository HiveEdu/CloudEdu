import request from '@/utils/request'

// 查询门店砍价活动列表
export function listBargain(query) {
  return request({
    url: '/store/bargain/list',
    method: 'get',
    params: query
  })
}

// 查询门店砍价活动详细
export function getBargain(id) {
  return request({
    url: '/store/bargain/' + id,
    method: 'get'
  })
}

// 新增门店砍价活动
export function addBargain(data) {
  return request({
    url: '/store/bargain',
    method: 'post',
    data: data
  })
}

// 修改门店砍价活动
export function updateBargain(data) {
  return request({
    url: '/store/bargain',
    method: 'put',
    data: data
  })
}

// 删除门店砍价活动
export function delBargain(id) {
  return request({
    url: '/store/bargain/' + id,
    method: 'delete'
  })
}

// 导出门店砍价活动
export function exportBargain(query) {
  return request({
    url: '/store/bargain/export',
    method: 'get',
    params: query
  })
}