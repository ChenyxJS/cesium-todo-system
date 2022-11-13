import { TODO } from '@/utils/Interface/TODO'

export const TODOPointTypeMap = {
    "Editor": { id: 0, color: '#E6A23C', label: '草稿箱', status: true },
    "Unfinished": { id: 1, color: '#67C23A', label: '未完成', status: true },
    "Done": { id: 2, color: '#909399', label: '已完成', status: true },
    "Emergency": { id: 3, color: '#F56C6C', label: '紧急', status: true }
}
export function getTODOPointType(typeNum: number) {
    const map: any = {
        0: "Editor",
        1: "Unfinished",
        2: "Done",
        3: "Emergency",
    }
    return map[typeNum]
}

function mock() {
    let list: any = []
    for (let i = 0; i <= 7; i++) {
        const todo: TODO = {
            todoId: i,
            todoType: i % 4,
            todoTitle: "todo" + i,
            todoLng: 118.2837 + i / 1000,
            todoLat: 35.1184 + i / 1000,
            todoAlt: 10,
            todoAddress: "todo测试地址" + i,
            todoDesc: "todo测试事件详情内容" + i,
            todoStartTime:new Date,
            todoEndTime:new Date,
        }
        list.push(todo)
    }
    return list
}

export const TODODataList = mock()