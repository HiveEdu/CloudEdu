import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi"
// 查询门店账目记录列表
export function listAccount(query) {
  return request({
    url: '/store/account/list',
    method: 'get',
    params: query
  })
}

// 查询门店账目记录详细
export function getAccount(id) {
  return request({
    url: '/store/account/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增门店账目记录
export function addAccount(data) {
  return request({
    url: '/store/account',
    method: 'post',
    data: data
  })
}

// 修改门店账目记录
export function updateAccount(data) {
  return request({
    url: '/store/account',
    method: 'put',
    data: data
  })
}

// 删除门店账目记录
export function delAccount(id) {
  return request({
    url: '/store/account/' + id,
    method: 'delete'
  })
}

// 导出门店账目记录
export function exportAccount(query) {
  return request({
    url: '/store/account/export',
    method: 'get',
    params: query
  })
}
