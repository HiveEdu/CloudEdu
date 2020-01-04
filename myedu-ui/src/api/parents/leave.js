import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询学生请假列表
export function listLeave(query) {
  return request({
    url: '/parents/leave/list',
    method: 'get',
    params: query
  })
}

// 查询学生请假详细
export function getLeave(id) {
  return request({
    url: '/parents/leave/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增学生请假
export function addLeave(data) {
  return request({
    url: '/parents/leave',
    method: 'post',
    data: data
  })
}

// 修改学生请假
export function updateLeave(data) {
  return request({
    url: '/parents/leave',
    method: 'put',
    data: data
  })
}

// 删除学生请假
export function delLeave(id) {
  return request({
    url: '/parents/leave/' + id,
    method: 'delete'
  })
}

// 导出学生请假
export function exportLeave(query) {
  return request({
    url: '/parents/leave/export',
    method: 'get',
    params: query
  })
}
