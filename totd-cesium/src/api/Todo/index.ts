import request from '@/utils/http/request'

export function getAllTodoData(data:any){
    return request({
        url:"/web/todo/getAll",
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

export function updateTodoData(data:any){
    return request({
        url:"/web/todo/update",
        method:"POST",
        data
    })
}

export function deleteTodoData(data:any){
    return request({
        url:"/web/todo/delete",
        method:"POST",
        data
    })
}
