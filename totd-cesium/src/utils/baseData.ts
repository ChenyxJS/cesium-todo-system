
export const TODOPointTypeMap = {
    "Editor": { id: 0, color: '#E6A23C', label: '草稿箱', status: true, type: "warning" },
    "Unfinished": { id: 1, color: '#67C23A', label: '未完成', status: true, type: "success" },
    "Done": { id: 2, color: '#909399', label: '已完成', status: true, type: "info" },
    "Emergency": { id: 3, color: '#F56C6C', label: '紧急', status: true, type: "danger" }
}
export function getTODOPointType(typeNum: any) {
    const map: any = {
        0: { key:'Editor',id: 0, color: '#E6A23C', label: '草稿箱', status: true, type: "warning" },
        1: { key:'Unfinished',id: 1, color: '#67C23A', label: '未完成', status: true, type: "success" },
        2: { key:'Done',id: 2, color: '#909399', label: '已完成', status: true, type: "info" },
        3: { key:'Emergency',id: 3, color: '#F56C6C', label: '紧急', status: true, type: "danger" },
    }
    return map[typeNum]
}


