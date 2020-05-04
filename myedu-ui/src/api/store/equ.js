import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi"
// 查询门店设备列表
export function listEqu(query) {
  return request({
    url: '/store/equ/list',
    method: 'get',
    params: query
  })
}

// 查询门店设备详细
export function getEqu(id) {
  return request({
    url: '/store/equ/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增门店设备
export function addEqu(data) {
  return request({
    url: '/store/equ',
    method: 'post',
    data: data
  })
}

// 修改门店设备
export function updateEqu(data) {
  return request({
    url: '/store/equ',
    method: 'put',
    data: data
  })
}

// 删除门店设备
export function delEqu(id) {
  return request({
    url: '/store/equ/' + id,
    method: 'delete'
  })
}

// 导出门店设备
export function exportEqu(query) {
  return request({
    url: '/store/equ/export',
    method: 'get',
    params: query
  })
}
