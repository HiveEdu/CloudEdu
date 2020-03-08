import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询投诉列表
export function listComplaint(query) {
  return request({
    url: '/parents/complaint/list',
    method: 'get',
    params: query
  })
}

// 查询投诉详细
export function getComplaint(id) {
  return request({
    url: '/parents/complaint/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增投诉
export function addComplaint(data) {
  return request({
    url: '/parents/complaint',
    method: 'post',
    data: data
  })
}



// 修改投诉
export function updateComplaint(data) {
  return request({
    url: '/parents/complaint',
    method: 'put',
    data: data
  })
}

// 删除投诉
export function delComplaint(id) {
  return request({
    url: '/parents/complaint/' + id,
    method: 'delete'
  })
}

// 导出投诉
export function exportComplaint(query) {
  return request({
    url: '/parents/complaint/export',
    method: 'get',
    params: query
  })
}

//回复投诉
export function reply(data) {
  return request({
    url: '/parents/complaint/reply/',
    method: 'put',
    data: data
  })
}
