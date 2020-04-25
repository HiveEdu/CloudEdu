import request from '@/utils/request'

// 查询门店学生管理列表
export function listStoreStudent(query) {
  return request({
    url: '/store/storeStudent/list',
    method: 'get',
    params: query
  })
}

// 查询门店学生管理详细
export function getStoreStudent(id) {
  return request({
    url: '/store/storeStudent/' + id,
    method: 'get'
  })
}
// 查询门店下的班级
export function getStoreClassByStoreId(id) {
  return request({
    url: '/store/class/getStoreClassByStoreId/' + id,
    method: 'get'
  })
}
// 新增门店学生管理
export function addStoreStudent(data) {
  return request({
    url: '/store/storeStudent',
    method: 'post',
    data: data
  })
}

// 修改门店学生管理
export function updateStoreStudent(data) {
  return request({
    url: '/store/storeStudent',
    method: 'put',
    data: data
  })
}

// 删除门店学生管理
export function delStoreStudent(id) {
  return request({
    url: '/store/storeStudent/' + id,
    method: 'delete'
  })
}

// 导出门店学生管理
export function exportStoreStudent(query) {
  return request({
    url: '/store/storeStudent/export',
    method: 'get',
    params: query
  })
}

//更改学生状态状态离校
export function changeStatusOff(id) {
  return request({
    url: '/store/storeStudent/changeStatusOff/' + id,
    method: 'get',
  })
}

//更改学生状态状态在校
export function changeStatusOn(id) {
  return request({
    url: '/store/storeStudent/changeStatusOn/' + id,
    method: 'get',
  })
}
//签到
export function sigint(id) {
  return request({
    url: '/store/storeStudent/sigint/' + id,
    method: 'get',
  })
}

//签退
export function sigout(id) {
  return request({
    url: '/store/storeStudent/sigout/' + id,
    method: 'get',
  })
}
//设置学生所属班级
export function setClassStu(data){
  return request({
    url: '/store/storeStudent/setClass',
    method: 'put',
    data: data
  })
}
