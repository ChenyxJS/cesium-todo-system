/*
*  获取本周的第一天
* */
export function getCurrentWeekFirstDay(date) {
  if (date === null) { return '' }
  let weekFirstDay = new Date(date - (date.getDay() - 1) * 86400000)
  let firstMonth = Number(weekFirstDay.getMonth()) + 1

  if (firstMonth < 10) {
    firstMonth = '0' + firstMonth
  }
  let weekFirstDays = weekFirstDay.getDate();
  if (weekFirstDays < 10) {
    weekFirstDays = '0' + weekFirstDays;
  }
  return weekFirstDay.getFullYear() + '-' + firstMonth + '-' + weekFirstDays;
}

/*
*  获取本周的最后一天
* */
export function getCurrentWeekLastDay(date) {
  if (date === null) { return '' }
  let weekFirstDay = new Date(date - (date.getDay() - 1) * 86400000)
  let weekLastDay = new Date((weekFirstDay / 1000 + 6 * 86400) * 1000)
  let lastMonth = Number(weekLastDay.getMonth()) + 1
  if (lastMonth < 10) {
    lastMonth = '0' + lastMonth
  }
  let weekLastDays = weekLastDay.getDate();
  if (weekLastDays < 10) {
    weekLastDays = '0' + weekLastDays;
  }
  return weekFirstDay.getFullYear() + '-' + lastMonth + '-' + weekLastDays;
}

/*
*  获取当前月的第一天
* */
export function getCurrentMonthFirstDay() {
  let date = new Date();
  date.setDate(1);
  let month = parseInt(date.getMonth() + 1);
  let day = date.getDate();
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return date.getFullYear() + '-' + month + '-' + day;
}

/*
* 获取当前月的最后一天
* */
export function getCurrentMonthLastDay(date) {
  if (date === null) { return '' }
  date = new Date(date);
  let currentMonth = date.getMonth();
  let nextMonth = ++currentMonth;
  let nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
  let oneDay = 1000 * 60 * 60 * 24;
  let lastTime = new Date(nextMonthFirstDay - oneDay);
  let month = parseInt(lastTime.getMonth() + 1);
  let day = lastTime.getDate();
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return date.getFullYear() + '-' + month + '-' + day;
}
