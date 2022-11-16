import request from '@/utils/request'

/**
 * 学校相关API
 * */
export function getClubList(data) {
  return request({
    url: '/admin/todo/selectByExample',
    method: 'post',
    data
  })
}



export function insertClub(data) {
  return request({
    url: '/admin/todo/add',
    method: 'post',
    data
  })
}

export function updateClub(data) {
  return request({
    url: '/admin/todo/update',
    method: 'post',
    data
  })
}

export function deleteClub(data) {
  return request({
    url: '/admin/todo/delete',
    method: 'post',
    data
  })
}

