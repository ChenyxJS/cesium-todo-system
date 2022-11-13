<template>
  <div class="dashboard-editor-container">


  </div>
</template>

<script>

export default {
  name: 'DashboardAdmin',
  components: {

  },
  data() {
    return {
    }
  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  width: 100%;
  height: 100%;
  position: relative;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  .chart-wrapper {
    box-sizing: border-box;
    background: #fff;
    padding: 10px 0 0 0;
  }
}
@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
.my-chart{
  width: 100%;
  flex-grow: 1;
  box-sizing: border-box;
  padding: 20px;
  background: #fff;
}
</style>
