// 基础配置
export const baseConfig = {
    // 网页标题
    title: "CesiumTotd"
}
// 打包配置
export const buildConfig = {
    buildDir:''
}
// Cesium配置
export const cesiumConfig = {
    token:'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI4NGI1Y2MzMS1jMjcyLTRmYWEtODI1Yi01NTRjMjRiMjMyYTYiLCJpZCI6MTE0NDA1LCJpYXQiOjE2NjgwNjc5OTV9.tVgQYbYuJOE9lwILQzmKn5Ko3vr3lkHlxmPwEkqLl3c'
}

const config = {
    ...baseConfig,
    ...buildConfig
}
export default config