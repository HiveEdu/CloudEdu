import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询门店作业管理列表
export function listStorework(query) {
  return request({
    url: '/store/storework/list',
    method: 'get',
    params: query
  })
}

// 查询门店作业管理详细
export function getStorework(id) {
  return request({
    url: '/store/storework/' +praseStrEmpty(id),
    method: 'get'
  })
}

// 新增门店作业管理
export function addStorework(data) {
  return request({
    url: '/store/storework',
    method: 'post',
    data: data
  })
}

// 修改门店作业管理
export function updateStorework(data) {
  return request({
    url: '/store/storework',
    method: 'put',
    data: data
  })
}

// 删除门店作业管理
export function delStorework(id) {
  return request({
    url: '/store/storework/' + id,
    method: 'delete'
  })
}

// 导出门店作业管理
export function exportStorework(query) {
  return request({
    url: '/store/storework/export',
    method: 'get',
    params: query
  })
}