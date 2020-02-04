import request from '@/utils/request'

// 查询店铺优惠券发布列表
export function listIssue(query) {
  return request({
    url: '/store/publishCoupon/list',
    method: 'get',
    params: query
  })
}

// 查询店铺优惠券发布详细
export function getIssue(id) {
  return request({
    url: '/store/publishCoupon/' + id,
    method: 'get'
  })
}

// 新增店铺优惠券发布
export function addIssue(data) {
  return request({
    url: '/store/publishCoupon',
    method: 'post',
    data: data
  })
}

// 修改店铺优惠券发布
export function updateIssue(data) {
  return request({
    url: '/store/publishCoupon',
    method: 'put',
    data: data
  })
}

// 删除店铺优惠券发布
export function delIssue(id) {
  return request({
    url: '/store/publishCoupon/' + id,
    method: 'delete'
  })
}

// 导出店铺优惠券发布
export function exportIssue(query) {
  return request({
    url: '/store/publishCoupon/export',
    method: 'get',
    params: query
  })
}
