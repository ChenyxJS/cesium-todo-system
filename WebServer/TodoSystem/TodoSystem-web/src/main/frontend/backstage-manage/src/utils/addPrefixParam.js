/*
给请求参数添加param前缀
传入form对象
* */
export default function (obj) {
  const keys = Object.keys(obj)
  console.log(keys);
  const newObj = {}
  keys.forEach((key) => {
    newObj['param.' + key] = obj[key]
  })
  return newObj
}
