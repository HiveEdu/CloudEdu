import request from '@/utils/request'

// 查询账户资金变动流水列表
export function listAccount_change(query) {
  return request({
    url: '/account/account_change/list',
    method: 'get',
    params: query
  })
}

// 查询账户资金变动流水详细
export function getAccount_change(id) {
  return request({
    url: '/account/account_change/' + id,
    method: 'get'
  })
}

// 新增账户资金变动流水
export function addAccount_change(data) {
  return request({
    url: '/account/account_change',
    method: 'post',
    data: data
  })
}

// 修改账户资金变动流水
export function updateAccount_change(data) {
  return request({
    url: '/account/account_change',
    method: 'put',
    data: data
  })
}

// 删除账户资金变动流水
export function delAccount_change(id) {
  return request({
    url: '/account/account_change/' + id,
    method: 'delete'
  })
}

// 导出账户资金变动流水
export function exportAccount_change(query) {
  return request({
    url: '/account/account_change/export',
    method: 'get',
    params: query
  })
}