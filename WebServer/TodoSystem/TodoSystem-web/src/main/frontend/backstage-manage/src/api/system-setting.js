import request from '@/utils/request'

/**
 * 根据条件获取值类型
 */
export function getLovType(data) {
  return request({
    url: '/system/lovTypeAndValue/getLovType',
    method: 'post',
    data
  })
}

/**
 * 根据条件获取值列表
 */
export function getLovValue(data) {
  return request({
    url: '/system/lovTypeAndValue/getLovValue',
    method: 'post',
    data
  })
}

/**
 * 添加值列表
 */
export function insertLovValue(data) {
  return request({
    url: '/system/lovTypeAndValue/addLookupValues',
    method: 'post',
    data
  })
}

/**
 * 修改值列表
 */
export function updateLovValue(data) {
  return request({
    url: '/system/lovTypeAndValue/updateLookupValues',
    method: 'post',
    data
  })
}

/**
 * 删除值列表
 */
export function delectLovValue(data) {
  return request({
    url: '/system/lovTypeAndValue/deleteLookupValues',
    method: 'post',
    data
  })
}

//===========下面是systemManage里面所需的所有API
export function getConfigList(data) {
  return request({
    url: '/system/systemConfig/selectByExample',
    method: 'post',
    data
  })
}
export function insertConfig(data) {
  return request({
    url: '/system/systemConfig/add',
    method: 'post',
    data
  })
}
export function updateConfig(data) {
  return request({
    url: '/system/systemConfig/update',
    method: 'post',
    data
  })
}
export function deleteConfig(data) {
  return request({
    url: '/system/systemConfig/delete',
    method: 'post',
    data
  })
}
export function getConfigItemListByCode(data) {
  return request({
    url: '/system/systemConfig/getSystemConfigValueByCodeFromDb',
    method: 'post',
    data
  })
}
export function getConfigItemListById(data) {
  return request({
    url: '/system/systemConfigValue/selectByExample',
    method: 'post',
    data
  })
}
export function insertConfigItem(data) {
  return request({
    url: '/system/systemConfigValue/add',
    method: 'post',
    data
  })
}
export function updateConfigItem(data) {
  return request({
    url: '/system/systemConfigValue/update',
    method: 'post',
    data
  })
}
export function deleteConfigItem(data) {
  return request({
    url: '/system/systemConfigValue/delete',
    method: 'post',
    data
  })
}

export function getKeyValueList(data) {
  return request({
    url: '/system/lovTypeAndValue/getLovType',
    method: 'post',
    data
  })
}
export function insertKeyValue(data) {
  return request({
    url: '/system/lovTypeAndValue/addLookupTypes',
    method: 'post',
    data
  })
}
export function updateKeyValue(data) {
  return request({
    url: '/system/lovTypeAndValue/updateLookupTypes',
    method: 'post',
    data
  })
}
export function deleteKeyValue(data) {
  return request({
    url: '/system/lovTypeAndValue/deleteLookupTypes',
    method: 'post',
    data
  })
}
export function getKeyValueItemList(data) {
  return request({
    url: '/system/lovTypeAndValue/getLovValue',
    method: 'post',
    data
  })
}
export function insertKeyValueItem(data) {
  return request({
    url: '/system/lovTypeAndValue/addLookupValues',
    method: 'post',
    data
  })
}
export function updateKeyValueItem(data) {
  return request({
    url: '/system/lovTypeAndValue/updateLookupValues',
    method: 'post',
    data
  })
}
export function deleteKeyValueItem(data) {
  return request({
    url: '/system/lovTypeAndValue/deleteLookupValues',
    method: 'post',
    data
  })
}
