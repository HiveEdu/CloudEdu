import request from '@/utils/request'

// 查询优惠券制作列表
export function listCoupon(query) {
  return request({
    url: '/store/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券制作详细
export function getCoupon(id) {
  return request({
    url: '/store/coupon/' + id,
    method: 'get'
  })
}

// 新增优惠券制作
export function addCoupon(data) {
  return request({
    url: '/store/coupon',
    method: 'post',
    data: data
  })
}

// 修改优惠券制作
export function updateCoupon(data) {
  return request({
    url: '/store/coupon',
    method: 'put',
    data: data
  })
}

// 删除优惠券制作
export function delCoupon(id) {
  return request({
    url: '/store/coupon/' + id,
    method: 'delete'
  })
}

// 导出优惠券制作
export function exportCoupon(query) {
  return request({
    url: '/store/coupon/export',
    method: 'get',
    params: query
  })
}