import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询点名签到列表
export function listSignin(query) {
  return request({
    url: '/store/signin/list',
    method: 'get',
    params: query
  })
}

// 查询点名签到详细
export function getSignin(id) {
  return request({
    url: '/store/signin/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增点名签到
export function addSignin(data) {
  return request({
    url: '/store/signin',
    method: 'post',
    data: data
  })
}

// 修改点名签到
export function updateSignin(data) {
  return request({
    url: '/store/signin',
    method: 'put',
    data: data
  })
}

// 删除点名签到
export function delSignin(id) {
  return request({
    url: '/store/signin/' + id,
    method: 'delete'
  })
}

// 导出点名签到
export function exportSignin(query) {
  return request({
    url: '/store/signin/export',
    method: 'get',
    params: query
  })
}