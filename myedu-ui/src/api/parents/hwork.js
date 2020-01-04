import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询学生作业列表
export function listHwork(query) {
  return request({
    url: '/parents/hwork/list',
    method: 'get',
    params: query
  })
}

// 查询学生作业详细
export function getHwork(id) {
  return request({
    url: '/parents/hwork/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增学生作业
export function addHwork(data) {
  return request({
    url: '/parents/hwork',
    method: 'post',
    data: data
  })
}

// 修改学生作业
export function updateHwork(data) {
  return request({
    url: '/parents/hwork',
    method: 'put',
    data: data
  })
}

// 删除学生作业
export function delHwork(id) {
  return request({
    url: '/parents/hwork/' + id,
    method: 'delete'
  })
}

// 导出学生作业
export function exportHwork(query) {
  return request({
    url: '/parents/hwork/export',
    method: 'get',
    params: query
  })
}
