import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
// 查询家教老师表列表
export function listInfo(query) {
  return request({
    url: '/hometeacher/info/list',
    method: 'get',
    params: query
  })
}

// 查询家教老师表详细
export function getInfo(id) {
  return request({
    url: '/hometeacher/info/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增家教老师表
export function addInfo(data) {
  return request({
    url: '/hometeacher/info',
    method: 'post',
    data: data
  })
}

// 修改家教老师表
export function updateInfo(data) {
  return request({
    url: '/hometeacher/info',
    method: 'put',
    data: data
  })
}

// 删除家教老师表
export function delInfo(id) {
  return request({
    url: '/hometeacher/info/' + id,
    method: 'delete'
  })
}

// 导出家教老师表
export function exportInfo(query) {
  return request({
    url: '/hometeacher/info/export',
    method: 'get',
    params: query
  })
}