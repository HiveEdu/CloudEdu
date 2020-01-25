import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询账户管理列表
export function listAccount(query) {
  return request({
    url: '/account/account/list',
    method: 'get',
    params: query
  })
}

// 查询账户管理详细
export function getAccount(id) {
  return request({
    url: '/account/account/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增账户管理
export function addAccount(data) {
  return request({
    url: '/account/account',
    method: 'post',
    data: data
  })
}

// 修改账户管理
export function updateAccount(data) {
  return request({
    url: '/account/account',
    method: 'put',
    data: data
  })
}

// 删除账户管理
export function delAccount(id) {
  return request({
    url: '/account/account/' + id,
    method: 'delete'
  })
}

// 导出账户管理
export function exportAccount(query) {
  return request({
    url: '/account/account/export',
    method: 'get',
    params: query
  })
}
