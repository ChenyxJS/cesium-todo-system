/*
* 用于存放静态数据
* */

//学校等级
export let sexList = [
  {
    label: '男',
    value: 1
  },
  {
    label: '女',
    value: 2
  }
]

//学校等级
export let schoolLevelList = [
  {
    value: '0',
    label: '小学'
  },
  {
    value: '1',
    label: '初中'
  },
  {
    value: '2',
    label: '高中'
  },
]

//学校类型
export let schoolTypeList = [
  {
    value: '0',
    label: '公办'
  },
  {
    value: '1',
    label: '民办'
  },
]
//单位类型
export let orgTypeList = [
  {
    value: '2',
    label: '行政单位'
  },
  {
    value: '3',
    label: '平台'
  },
  {
    value: '4',
    label: '律师事务所'
  }

]

//宿舍类型
export let dormitoryTypeList = [
  {
    value: '1',
    label: '男生宿舍'
  },
  {
    value: '2',
    label: '女生宿舍'
  },
  {
    value: '3',
    label: '楼号'
  },
  {
    value: '4',
    label: '楼层'
  },
]
//用于填充题目选项
export let questionPrefixList = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]
//题目类型
export let questionTypeList = [
  {
    value: '0',
    label: '单选'
  },
  {
    value: '1',
    label: '多选'
  },
]
//教育类型
export let educationTypeList = [
  {
    value: '0',
    label: '临时'
  },
  {
    value: '1',
    label: '长期'
  },
  {
    value: '2',
    label: '学期'
  },
]
//教育范围
export let educationRangeList = [
  {
    value: '1',
    label: '学校'
  },
  {
    value: '2',
    label: '年级'
  },
  {
    value: '3',
    label: '班级'
  },
  {
    value: '4',
    label: '个人'
  },
]
//预警库类型
export let warningDatabaseTypeList = [
  {
    value: '1',
    label: '通用库',

  },
  {
    value: '2',
    label: '特色库',

  },
  {
    value: '3',
    label: '校规',

  },
  {
    value: '4',
    label: '班规',

  },
]

//预警库等级
export let warningDatabaseLevelList = [
  {
    value: '1',
    label: '一级'
  },
  {
    value: '2',
    label: '二级'
  },
  {
    value: '3',
    label: '三级'
  },
]


//周期
export let cycleTypeList = [
  {
    value: '1',
    label: '年'
  },
  {
    value: '2',
    label: '月'
  },
  {
    value: '3',
    label: '周'
  },
  {
    value: '4',
    label: '日'
  },
]

/*通知的范围*/
export let noticeRangeList = [
  {label: '学校', value: 1},
  {label: '年级', value: 2},
  {label: '班级', value: 3}
]

export let gradeNoticeRangeList = [
  {label: '年级', value: 2},
  {label: '班级', value: 3}
]

export let classNoticeRangeList = [
  {label: '班级', value: 3}
]

/*校长级别 可通知的角色*/
export let presidentSchoolRangeList = [
  {label: '年级主任', value: 9},
  {label: '班主任', value: 12},
  {label: '任课老师', value: 14},
  {label: '其他教职工', value: 17},
  {label: '心理辅导', value: 18},
  {label: '家长', value: 4}
]
/*通知年级主任及其以下的角色*/
export let gradeRangeList = [
  {label: '年级主任', value: 9},
  {label: '班主任', value: 12},
  {label: '任课老师', value: 14},
  {label: '家长', value: 4}
]

/*年级主任可通知的角色*/
export let classGradeRangeList = [
  {label: '班主任', value: 12},
  {label: '任课老师', value: 14},
  {label: '家长', value: 4}
]

/*通知班主任及其一下的角色*/
export let classRangeList = [
  {label: '班主任', value: 12},
  {label: '任课老师', value: 14},
  {label: '家长', value: 4}
]
/*班主任可通知的角色*/
export let classClassRangeList = [
  {label: '任课老师', value: 14},
  {label: '家长', value: 4}
]

//校规、班规状态
export let schoolClassRuleTypeList = [
  {
    value: '0',
    label: '未投票'
  },
  {
    value: '1',
    label: '通过'
  },
  {
    value: '2',
    label: '投票中'
  },
]
export let educationAuditStatus = [
  {
    value: 0,
    label: '待审核'
  },
  {
    value: 1,
    label: '通过'
  },
  {
    value: 2,
    label: '不通过'
  }
]
