import request from '@/utils/request'

// 查询课程基本数据列表
export function listCourse(query) {
  return request({
    url: '/dataBasic/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程基本数据详细
export function getCourse(id) {
  return request({
    url: '/dataBasic/course/' + id,
    method: 'get'
  })
}

// 新增课程基本数据
export function addCourse(data) {
  return request({
    url: '/dataBasic/course',
    method: 'post',
    data: data
  })
}

// 修改课程基本数据
export function updateCourse(data) {
  return request({
    url: '/dataBasic/course',
    method: 'put',
    data: data
  })
}

// 删除课程基本数据
export function delCourse(id) {
  return request({
    url: '/dataBasic/course/' + id,
    method: 'delete'
  })
}

// 导出课程基本数据
export function exportCourse(query) {
  return request({
    url: '/dataBasic/course/export',
    method: 'get',
    params: query
  })
}