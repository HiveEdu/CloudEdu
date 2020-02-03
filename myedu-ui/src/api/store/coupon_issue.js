import request from '@/utils/request'

// 查询优惠券制作列表
export function listCoupon_issue(query) {
  return request({
    url: '/store/coupon_issue/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券制作详细
export function getCoupon_issue(id) {
  return request({
    url: '/store/coupon_issue/' + id,
    method: 'get'
  })
}

// 新增优惠券制作
export function addCoupon_issue(data) {
  return request({
    url: '/store/coupon_issue',
    method: 'post',
    data: data
  })
}

// 修改优惠券制作
export function updateCoupon_issue(data) {
  return request({
    url: '/store/coupon_issue',
    method: 'put',
    data: data
  })
}

// 删除优惠券制作
export function delCoupon_issue(id) {
  return request({
    url: '/store/coupon_issue/' + id,
    method: 'delete'
  })
}

// 导出优惠券制作
export function exportCoupon_issue(query) {
  return request({
    url: '/store/coupon_issue/export',
    method: 'get',
    params: query
  })
}