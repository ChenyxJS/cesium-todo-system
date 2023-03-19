/*
 * @Author: chenyx
 * @Date: 2023-03-17 20:05:21
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-18 21:08:41
 * @FilePath: /totd-cesium/src/views/CesiumIndex/components/InfoBox/index.ts
 */
import {render, createVNode} from 'vue'
import InfoBox from './InfoBox.vue';

export default function(options:any){
    // 渲染
    return render(createVNode(InfoBox, options), document.body)
}