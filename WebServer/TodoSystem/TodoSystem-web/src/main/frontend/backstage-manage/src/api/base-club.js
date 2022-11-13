import request from '@/utils/request'

/**
 * 学校相关API
 * */
export function getClubList(data) {
  return request({
    url: '/admin/club/selectByExample',
    method: 'post',
    data
  })
}



export function insertClub(data) {
  return request({
    url: '/admin/club/add',
    method: 'post',
    data
  })
}

export function updateClub(data) {
  return request({
    url: '/admin/club/update',
    method: 'post',
    data
  })
}

export function deleteClub(data) {
  return request({
    url: '/admin/club/delete',
    method: 'post',
    data
  })
}

