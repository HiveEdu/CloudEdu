import request from '@/utils/request'

// 查询会员等级列表
export function listMemberLevel(query) {
  return request({
    url: '/dataBasic/leave/list',
    method: 'get',
    params: query
  })
}

// 查询会员等级详细
export function getMemberLevel(id) {
  return request({
    url: '/dataBasic/leave/' + id,
    method: 'get'
  })
}

// 新增会员等级
export function addMemberLevel(data) {
  return request({
    url: '/dataBasic/leave',
    method: 'post',
    data: data
  })
}

// 修改会员等级
export function updateMemberLevel(data) {
  return request({
    url: '/dataBasic/leave',
    method: 'put',
    data: data
  })
}

// 删除会员等级
export function delMemberLevel(id) {
  return request({
    url: '/dataBasic/leave/' + id,
    method: 'delete'
  })
}

// 导出会员等级
export function exportMemberLevel(query) {
  return request({
    url: '/dataBasic/memberLevel/export',
    method: 'get',
    params: query
  })
}
