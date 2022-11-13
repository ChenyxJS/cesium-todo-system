import request from '@/utils/request'

/**
 * 学校相关API
 * */
export function getAuditList(data) {
  return request({
    url: '/admin/audit/selectByExample',
    method: 'post',
    data
  })
}


export function updateAudit(data) {
  return request({
    url: '/admin/audit/update',
    method: 'post',
    data
  })
}


