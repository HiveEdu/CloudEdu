import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询分班管理列表
export function listClass(query) {
  return request({
    url: '/store/class/list',
    method: 'get',
    params: query
  })
}

// 查询分班管理详细
export function getClass(id) {
  return request({
    url: '/store/class/'+ praseStrEmpty(id),
    method: 'get'
  })
}

// 新增分班管理
export function addClass(data) {
  return request({
    url: '/store/class',
    method: 'post',
    data: data
  })
}

// 修改分班管理
export function updateClass(data) {
  return request({
    url: '/store/class',
    method: 'put',
    data: data
  })
}

// 删除分班管理
export function delClass(id) {
  return request({
    url: '/store/class/' + id,
    method: 'delete'
  })
}

// 导出分班管理
export function exportClass(query) {
  return request({
    url: '/store/class/export',
    method: 'get',
    params: query
  })
}