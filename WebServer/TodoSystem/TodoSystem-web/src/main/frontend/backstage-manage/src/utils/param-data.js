const PARAM = {
  do(obj,noParamList = []) {
    let res = {};
    for(let key in obj){
      let isParam = true;
      noParamList.forEach(n=>{
        if(n === key){
          isParam = false
        }
      });

      if(isParam){
        res[`param.${key}`] = obj[key]
      } else {
        res[key] = obj[key]
      }
    }
    return res
  }
};
export default PARAM
