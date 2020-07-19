import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询门店兴趣班列表
export function listInterclass(query) {
  return request({
    url: '/store/interclass/list',
    method: 'get',
    params: query
  })
}

// 查询门店兴趣班详细
export function getInterclass(id) {
  return request({
    url: '/store/interclass/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增门店兴趣班
export function addInterclass(data) {
  return request({
    url: '/store/interclass',
    method: 'post',
    data: data
  })
}

// 修改门店兴趣班
export function updateInterclass(data) {
  return request({
    url: '/store/interclass',
    method: 'put',
    data: data
  })
}

// 删除门店兴趣班
export function delInterclass(id) {
  return request({
    url: '/store/interclass/' + id,
    method: 'delete'
  })
}

// 导出门店兴趣班
export function exportInterclass(query) {
  return request({
    url: '/store/interclass/export',
    method: 'get',
    params: query
  })
}