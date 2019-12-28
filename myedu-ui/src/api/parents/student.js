import request from '@/utils/request'

// 查询学生数据列表
export function listStudent(query) {
  return request({
    url: '/parents/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生数据详细
export function getStudent(id) {
  return request({
    url: '/parents/student/' + id,
    method: 'get'
  })
}

// 新增学生数据
export function addStudent(data) {
  return request({
    url: '/parents/student',
    method: 'post',
    data: data
  })
}

// 修改学生数据
export function updateStudent(data) {
  return request({
    url: '/parents/student',
    method: 'put',
    data: data
  })
}

// 删除学生数据
export function delStudent(id) {
  return request({
    url: '/parents/student/' + id,
    method: 'delete'
  })
}

// 导出学生数据
export function exportStudent(query) {
  return request({
    url: '/parents/student/export',
    method: 'get',
    params: query
  })
}
