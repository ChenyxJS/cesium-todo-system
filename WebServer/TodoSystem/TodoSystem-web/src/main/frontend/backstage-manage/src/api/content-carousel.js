import request from '@/utils/request'

/**
* 轮播图相关API
* */

/* 根据条件查询轮播图 */
export function getCarouselList(data) {
  return request({
    url: '/admin/carousel/selectByExample',
    method: 'post',
    data
  })
}

export function insertCarousel(data) {
  return request({
    url: '/admin/carousel/add',
    method: 'post',
    data
  })
}

export function updateCarousel(data) {
  return request({
    url: '/admin/carousel/update',
    method: 'post',
    data
  })
}

export function deleteCarousel(data) {
  return request({
    url: '/admin/carousel/delete',
    method: 'post',
    data
  })
}

