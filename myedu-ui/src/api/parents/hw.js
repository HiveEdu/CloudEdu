import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询学生身高体重记录列表
export function listHw(query) {
  return request({
    url: '/parents/hw/list',
    method: 'get',
    params: query
  })
}

// 查询学生身高体重记录详细
export function getHw(id) {
  return request({
    url: '/parents/hw/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增学生身高体重记录
export function addHw(data) {
  return request({
    url: '/parents/hw',
    method: 'post',
    data: data
  })
}

// 修改学生身高体重记录
export function updateHw(data) {
  return request({
    url: '/parents/hw',
    method: 'put',
    data: data
  })
}

// 删除学生身高体重记录
export function delHw(id) {
  return request({
    url: '/parents/hw/' + id,
    method: 'delete'
  })
}

// 导出学生身高体重记录
export function exportHw(query) {
  return request({
    url: '/parents/hw/export',
    method: 'get',
    params: query
  })
}