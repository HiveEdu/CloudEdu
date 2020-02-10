import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询支付宝配置类列表
export function listAlipay(query) {
  return request({
    url: '/account/alipay/list',
    method: 'get',
    params: query
  })
}

// 查询支付宝配置类详细
export function getAlipay(id) {
  return request({
    url: '/account/alipay/' +  praseStrEmpty(id),
    method: 'get'
  })
}

// 新增支付宝配置类
export function addAlipay(data) {
  return request({
    url: '/account/alipay',
    method: 'post',
    data: data
  })
}

// 修改支付宝配置类
export function updateAlipay(data) {
  return request({
    url: '/account/alipay',
    method: 'put',
    data: data
  })
}

// 删除支付宝配置类
export function delAlipay(id) {
  return request({
    url: '/account/alipay/' + id,
    method: 'delete'
  })
}

// 导出支付宝配置类
export function exportAlipay(query) {
  return request({
    url: '/account/alipay/export',
    method: 'get',
    params: query
  })
}

// 支付测试
export function toAliPay(url, data) {
  return request({
    url:url,
    data: data,
    method: 'post'
  })
}
