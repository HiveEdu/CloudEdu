import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询门店列表
export function listStore(query) {
  return request({
    url: '/store/store/list',
    method: 'get',
    params: query
  })
}

// 查询门店详细
export function getStore(id) {
  return request({
    url: '/store/store/' +  praseStrEmpty(id),
    method: 'get'
  })
}

// 新增门店
export function addStore(data) {
  return request({
    url: '/store/store',
    method: 'post',
    data: data
  })
}

// 修改门店
export function updateStore(data) {
  return request({
    url: '/store/store',
    method: 'put',
    data: data
  })
}

// 删除门店
export function delStore(id) {
  return request({
    url: '/store/store/' + id,
    method: 'delete'
  })
}

// 导出门店
export function exportStore(query) {
  return request({
    url: '/store/store/export',
    method: 'get',
    params: query
  })
}
