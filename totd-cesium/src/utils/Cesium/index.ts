import * as Cesium from "cesium";
import { cesiumConfig } from "@/config";
import { TODOPointTypeMap, getTODOPointType } from "@/utils/baseData";
import { Ref, ref } from "vue";
import { TODO } from "@/utils/Interface/TODO";
import InfoBox from "@/views/CesiumIndex/components/InfoBox/index";
import { useInfoBoxStore } from "@/store/InfoBox";
import { useEditPanelStore } from "@/store/EditPanel";

const infoBoxStore = useInfoBoxStore();
const editPanelStore = useEditPanelStore();
// Cesium的token
Cesium.Ion.defaultAccessToken = cesiumConfig.token;

class CesiumController {
  // 视图
  viewer: any;
  // 实体
  entity: any;
  // 实体列表
  entityList: any;
  // 当前操作模式
  OperationMode: any;
  // 操作模式列表
  OperationModeList: any;
  // 点类型
  pointType: any;
  // 是否可以显示实体信息窗口
  isCanShowInfoBox: boolean = true;
  // selectedEntity
  selectedEntity: any;
  // 实体数据列表
  EntityDataList: any;

  constructor() {
    this.init_data();
  }

  /**
   * @Descripttion: 初始化数据
   * @msg:
   * @return {*}
   */
  init_data() {
    this.viewer = null;
    this.entity = null;
    this.entityList = [];
    this.EntityDataList = [];
    this.OperationMode = "View"; // 默认为View浏览模式
    this.OperationModeList = ["View", "Editor"];
    this.pointType = TODOPointTypeMap;
  }
  /**
   * @Descripttion: 获取当前操作模式
   * @msg:
   * @return {*}
   */
  getMode() {
    return this.OperationMode;
  }
  getPointType() {
    return this.pointType;
  }
  getSelectedEntity() {
    return this.selectedEntity;
  }
  /**
   * @Descripttion: 初始化Cesium基础配置
   * @msg:
   * @param {string} el dom
   * @param {any} EntityDataList 实体集合数据
   * @return {*}
   */
  init(el: string, EntityDataList?: any) {
    // 初始化视图
    this.viewer = new Cesium.Viewer(el, {
      // 是否显示信息窗口
      infoBox: false,
      // 是否创建动画
      animation: false,
      // 是否显示图层选择器
      baseLayerPicker: false,
      // 是否显示全屏按钮
      fullscreenButton: false,
      // 是否显示右上角的查询按钮
      geocoder: true,
      // 是否显示HOME按钮
      homeButton: false,
      // 是否显示场景控制按钮
      sceneModePicker: false,
      // 是否显示帮助按钮
      navigationHelpButton: false,
      // 是否显示时间轴
      timeline: false,
      // 是否显示选取指示器
      selectionIndicator: true,
      // 地形
      terrainProvider: Cesium.createWorldTerrain(),
    });
    // 镜头飞入
    this.viewer.camera.flyTo({
      destination: Cesium.Cartesian3.fromDegrees(118.2837, 35.1184, 1500),
      orientation: {
        heading: Cesium.Math.toRadians(0),
        roll: 0.0,
      },
    });
    // 开启深度检测
    this.viewer.scene.globe.depthTestAgainstTerrain = true;
    // 初始化实体集合
    this.initEntityMap(EntityDataList);

    return this.viewer;
  }
  /**
   * @Descripttion: 初始化实体集合
   * @msg:
   * @return {*}
   */
  initEntityMap(dataList?: any) {
    this.EntityDataList = dataList;
    // 地图初始化后夹在实体
    if (this.EntityDataList) {
      // 先初始化实体集合
      this.entityList = [];
      this.viewer.entities.removeAll();
      let pointList = this.createEntityByDataList(this.EntityDataList);
      this.addEntity(pointList);
    }
  }
  /**
   * @Descripttion: 镜头飞到指定实体
   * @msg:
   * @param {number} lng
   * @param {number} lat
   * @param {number} alt 角度
   * @return {*}
   */
  viewerFlyToLngLat(id: string, lng: number, lat: number) {
    const entity = this.viewer.entities.getById(id);
    this.viewer._selectedEntity = entity;
    this.viewer.camera.flyTo({
      destination: Cesium.Cartesian3.fromDegrees(lng, lat, 1500),
      orientation: {
        heading: Cesium.Math.toRadians(0),
        roll: 0.0,
      },
    });
  }
  /**
   * @Descripttion: 点击事件
   * @msg:
   * @return {*}
   */
  clickEvent() {
    const handler = new Cesium.ScreenSpaceEventHandler(
      this.viewer.scene.canvas
    );
    handler.setInputAction((e: any) => {
      // 获取拾取点的信息
      var pick = this.viewer.scene.pick(e.position);
      const isEntity = pick && pick.id ? true : false;
      //屏幕坐标
      const { position } = e;
      const ray = this.viewer.camera.getPickRay(position);
      const cartesian3 = this.viewer.scene.globe.pick(ray, this.viewer.scene);
       //笛卡尔坐标转经纬度
       const radians = this.viewer.scene.globe.ellipsoid.cartesianToCartographic(cartesian3);
       const lat = Cesium.Math.toDegrees(radians.latitude); //弧度转度
       const lng = Cesium.Math.toDegrees(radians.longitude);
       const alt = radians.height;
      // 首先判断点击的是否是实体
      if (!isEntity) {
        // 点击的不是实体
        if (this.OperationMode === "View") {
          // VIEW模式下，关闭显示的弹窗
          this.closeAllWindows();
        } else if (this.OperationMode === "Editor" && !editPanelStore.visible) {
          // EDITER模式下且没有已经打开的编辑窗口时，点击地图部分，显示创建totd事件表单窗口
          this.closeInfoBox();
          // 创建编辑实体
          this.selectedEntity = this.createEntity(cartesian3, "Editor");
          // 显示编辑表单窗口
          editPanelStore.editEntity = this.selectedEntity;
          editPanelStore.positions = {lat:lat,lng:lng,alt:alt}
          editPanelStore.initTodo()
          editPanelStore.visible = true;
          // 这里需要再请求后端接口返回成功后再添加至实体集合中
          this.addEntity(this.selectedEntity);
          // 给该实体添加选中框
          this.viewer._selectedEntity = this.selectedEntity;
        }
      } else {
        // 点击的是实体则显示该实体的信息窗口 让镜头飞向实体
        // this.viewerFlyToLngLat(pick.id._id, lng, lat)
        this.entity = this.getEntityForID(pick.id._id);
        this.createEntityInfoBox(this.entity, e.position);
      }
    }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
    return handler;
  }
  /**
   * @Descripttion: 创建实体信息窗口
   * @msg:
   * @return {*}
   */
  createEntityInfoBox(entity: any, position: any) {
    // 获取该实体的信息
    let todo: TODO = {
      todoId: 0,
      todoType: 1,
      todoTitle: "todoTitle",
      todoLng: 0,
      todoLat: 0,
      todoAlt: 0,
      todoAddress: "todoAddress",
      todoDesc: "todoDesc",
      todoStartTime: new Date(),
      todoEndTime: new Date(),
    };
    this.EntityDataList.forEach((item: TODO) => {
      if (Number(entity.id.substring(5)) === item.todoId) {
        todo = item;
      }
    });
    // 创建实体信息窗口
    InfoBox({
      id: entity.id,
      todo: todo,
    });
    infoBoxStore.visible = true;

    // 显示信息窗口
    this.showEntityInfoBox(entity, position);
  }
  /**
   * @Descripttion: 显示实体信息窗口
   * @msg:
   * @return {*}
   */
  showEntityInfoBox(entity: any, position: any) {
    if (!this.isCanShowInfoBox) {
      return;
    }
    var InfoBoxDom = document.getElementById("EntityInfoBox_" + entity.id);
    // 显示该实体的信息窗口，并隐藏其他窗口
    this.closeAllWindows();
    if (InfoBoxDom) {
      // InfoBoxDom.style.display = 'block'
      infoBoxStore.visible = true;
      InfoBoxDom.style.left =
        position.x - InfoBoxDom.clientWidth / 2 + 15 + "px";
      InfoBoxDom.style.top = position.y - InfoBoxDom.clientHeight - 35 + "px";
    }
    // 转换坐标
    const earthPosition = this.viewer.camera.pickEllipsoid(
      position,
      this.viewer.scene.globe.ellipsoid
    );
    // 设置窗口的位置
    this.viewer.scene.postRender.addEventListener(() => {
      // 若窗口是显示状态，转换到屏幕坐标
      if (InfoBoxDom && infoBoxStore.visible) {
        var winpos =
          this.viewer.scene.cartesianToCanvasCoordinates(earthPosition);
        if (winpos) {
          InfoBoxDom.style.left =
            winpos.x - InfoBoxDom.clientWidth / 2 + 15 + "px";
          InfoBoxDom.style.top = winpos.y - InfoBoxDom.clientHeight - 35 + "px";
        }
      }
    });
  }
  /**
   * @Descripttion: 根据DataList初始化entity
   * @msg:
   * @return {*}
   */
  createEntityByDataList(dataList?: any) {
    let list: any = [];
    dataList.forEach((item: TODO) => {
      let pointType: any = {};
      pointType = this.pointType[getTODOPointType(item.todoType).key];
      const position = this.lngAndlatTocartesian3(
        item.todoLng,
        item.todoLat,
        item.todoAlt
      );
      list.push(
        new Cesium.Entity({
          id: "todo" + item.todoType + item.todoId,
          name: getTODOPointType(item.todoType).key,
          position: position,
          billboard: {
            image: `../../../public/${getTODOPointType(item.todoType).key}.png`,
            //和原始大小相比的缩放比例
            scale: 2,
            disableDepthTestDistance: 99000000,
            verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
            pixelOffset: new Cesium.Cartesian2(-16, 16),
            horizontalOrigin: Cesium.HorizontalOrigin.LEFT,
            // 按距离缩放
            scaleByDistance: new Cesium.NearFarScalar(1.5e2, 1, 10000, 0.0),
            width: 32,
            height: 32,
          },
        })
      );
    });
    return list;
  }
  /**
   * @Descripttion: 初始化entity
   * @msg:
   * @return {*}
   */
  createEntity(position: any, type: string) {
    let pointType: any = {};
    if (!type) {
      // 若未传点类型，则默认为未完成类型
      type = "Unfinished";
    }
    pointType = this.pointType[type];
    const Entity = new Cesium.Entity({
      id: "todo" + pointType.id + this.entityList.length,
      name: type,
      position: position,
      billboard: {
        image: `../../../public/${type}.png`,
        //model : {
        //    uri : '../../SampleData/models/CesiumGround/Cesium_Ground.gltf'
        //},
        scale: 2,
        disableDepthTestDistance: 99000000,
        verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
        pixelOffset: new Cesium.Cartesian2(-16, 16),
        // eyeOffset: new Cesium.Cartesian3(0.0, 0.0, 0.0), // default
        horizontalOrigin: Cesium.HorizontalOrigin.LEFT,
        // 按距离缩放
        scaleByDistance: new Cesium.NearFarScalar(1.5e2, 1, 10000, 0.0),
        width: 32,
        height: 32,
      },
    });
    return Entity;
  }
  /**
   * @Descripttion: 添加entity到实体集合
   * @msg:
   * @param {any} entity
   * @param {any} entityList
   * @return {*}
   */
  addEntity(temp: any) {
    if (Array.isArray(temp)) {
      // 若传入的是数组则，遍历该数组添加到实体集合中
      temp.forEach((item) => {
        // 若实体集合中不存在该实体则添加
        if (!this.viewer.entities.getById(item.id)) {
          this.entityList.push(item);
          this.viewer.entities.add(item);
        }
      });
    } else {
      if (!this.viewer.entities.getById(temp.id)) {
        this.entityList.push(temp);
        this.viewer.entities.add(temp);
      }
    }
  }
  /**
   * @Descripttion: 从entity实体集合移除实体
   * @msg:
   * @param {any} temp
   * @return {*}
   */
  removeEntity(temp: any) {
    if (Array.isArray(temp)) {
      // 若传入的是数组则，遍历该数组添加到实体集合中
      temp.forEach((item) => {
        this.viewer.entities.removeById(item.id);
      });
    } else {
      this.viewer.entities.removeById(temp.id);
    }
  }
  /**
   * @Descripttion: 根据ID获取Entity
   * @msg:
   * @param {string} id
   * @return {*}
   */
  getEntityForID(id: string) {
    const entityTemp: any = ref();
    this.entityList.forEach((item: any) => {
      if (item._id === id) {
        entityTemp.value = item;
      }
    });
    return entityTemp.value;
  }
  /**
   * @Descripttion: 关闭实体信息窗口
   * @msg:
   * @return {*}
   */
  closeInfoBox() {
    // 关闭已显示的信息窗口
    infoBoxStore.visible = false;
  }
  /**
   * @Descripttion: 关闭所有弹窗
   * @msg:
   * @return {*}
   */
  closeAllWindows() {
    // 关闭实体信息窗口
    this.closeInfoBox();
  }

  /**
   * @Descripttion: 改变当前模式
   * @msg:
   * @param {number} mode
   * @return {*}
   */
  changeOperationMode(mode: any) {
    if (typeof mode == "number") {
      this.OperationMode = this.OperationModeList[mode];
    } else if (typeof mode == "string") {
      this.OperationMode = mode;
    }
  }
  /**
   * @Descripttion: 将经纬度转换成C3坐标
   * @msg:
   * @param {number} lng
   * @param {number} lat
   * @param {number} alt
   * @return {*}
   */
  lngAndlatTocartesian3(lng: number, lat: number, alt: number) {
    var ellipsoid = this.viewer.scene.globe.ellipsoid;
    var cartographic = Cesium.Cartographic.fromDegrees(lng, lat, alt);
    var cartesian3 = ellipsoid.cartographicToCartesian(cartographic);
    return cartesian3;
  }
  /**
   * @Descripttion: 改变显示点类型
   * @msg:
   * @return {*}
   */
  changeShowPointType(type: string, status: boolean) {
    // 修改pointType
    this.pointType[type].status = status;
    if (status) {
      // 显示
      this.entityList.forEach((item: any) => {
        if (item.name === type && !this.viewer.entities.getById(item.id)) {
          // 若该点的类型是要显示的类型，则添加至显示实体集合中
          this.viewer.entities.add(item);
        }
      });
    } else {
      // 隐藏
      this.entityList.forEach((item: any) => {
        if (item.name === type) {
          // 将该类型的点从实体集合中移除
          this.viewer.entities.remove(item);
        }
      });
    }
  }
}
const CController = new CesiumController();
export default CController;
