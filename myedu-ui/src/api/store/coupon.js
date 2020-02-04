import request from '@/utils/request'

// 查询店铺优惠券列表
export function listCoupon(query) {
  return request({
    url: '/store/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询店铺优惠券详细
export function getCoupon(id) {
  return request({
    url: '/store/coupon/' + id,
    method: 'get'
  })
}

// 新增店铺优惠券
export function addCoupon(data) {
  return request({
    url: '/store/coupon',
    method: 'post',
    data: data
  })
}

// 修改店铺优惠券
export function updateCoupon(data) {
  return request({
    url: '/store/coupon',
    method: 'put',
    data: data
  })
}

// 删除店铺优惠券
export function delCoupon(id) {
  return request({
    url: '/store/coupon/' + id,
    method: 'delete'
  })
}

// 导出店铺优惠券
export function exportCoupon(query) {
  return request({
    url: '/store/coupon/export',
    method: 'get',
    params: query
  })
}

//优惠券发布
export function publishCou(data) {
  return request({
    url: '/store/coupon/publish',
    method: 'post',
    data: data
  })
}
