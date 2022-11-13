import request from '@/utils/http/request'

export function getAllTodoData(data:any){
    return request({
        url:"/web/todo/selectByExample",
        method:"GET",
        data
    })
}

export function addTodoData(data:any){
    return request({
        url:"/web/todo/add",
        method:"POST",
        data
    })
}