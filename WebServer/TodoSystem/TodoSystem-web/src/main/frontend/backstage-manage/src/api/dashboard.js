import request from '@/utils/request'

/**
 * 首页页面相关API
 * */
export function getStatisticsCount(data) {
  return request({
    url: '/admin/home/selectStatisticsCount',
    method: 'post',
    data
  })
}
