/*
 * @Author: chenyx
 * @Date: 2023-03-17 20:05:21
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-17 20:23:06
 * @FilePath: /totd-cesium/src/views/CesiumIndex/components/InfoBox/index.ts
 */
import {render, createVNode} from 'vue'
import InfoBox from './InfoBox.vue';

interface InfoBoxOptions{
    content:string
}

export default function(options:any){
    // 渲染
    console.log('正在渲染InfoBox');
    return render(createVNode(InfoBox, options), document.body)
}