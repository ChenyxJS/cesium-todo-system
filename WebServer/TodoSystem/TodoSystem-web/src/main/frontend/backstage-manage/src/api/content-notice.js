import request from '@/utils/request'

/**
* 通知相关API
* */
export function getNoticeList(data) {
  return request({
    url: '/admin/notice/selectByExample',
    method: 'post',
    data
  })
}

export function insertNotice(data) {
  return request({
    url: '/admin/notice/add',
    method: 'post',
    data
  })
}

export function updateNotice(data) {
  return request({
    url: '/admin/notice/update',
    method: 'post',
    data
  })
}

export function deleteNotice(data) {
  return request({
    url: '/admin/notice/delete',
    method: 'post',
    data
  })
}


