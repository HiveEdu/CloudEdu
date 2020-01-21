import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询课程列表
export function listCourse(query) {
  return request({
    url: '/store/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getCourse(id) {
  return request({
    url: '/store/course/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增课程
export function addCourse(data) {
  return request({
    url: '/store/course',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateCourse(data) {
  return request({
    url: '/store/course',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delCourse(id) {
  return request({
    url: '/store/course/' + id,
    method: 'delete'
  })
}

// 导出课程
export function exportCourse(query) {
  return request({
    url: '/store/course/export',
    method: 'get',
    params: query
  })
}
