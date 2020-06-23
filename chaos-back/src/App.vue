<template>
  <div id="app">
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"
                   v-wechat-title="$route.meta.title"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"
                 v-wechat-title="$route.meta.title"></router-view>
    <component
      v-for="(dialogInfo, index) in dialogList"
      :key="index"
      :is="ConfirmDialog"
      :dialogInfo="dialogInfo"
      :index="index"
    ></component>
  </div>
</template>

<script>
    import ConfirmDialog from "./components/ConfirmDialog/ConfirmDialog.vue";
    import dialogStore from "./components/ConfirmDialog/dialogStore";

    export default {
        name: 'App',
        computed: {
            dialogList() {
                // 绑定 dialogStore 的 dialogList 列表
                return dialogStore.state.dialogList;
            }
        },
        data() {
            return {
                // 用来绑定 component
                ConfirmDialog
            };
        },
        methods: {
            t() {
                // 传入弹窗的内容，并通过 then 和 catch 来获取用户的操作，以便继续后续的逻辑处理
                confirmDialog({text: "确认要删除吗？"}).then(res => {
                    // 用户点击确认
                    // 二次弹窗确认
                    confirmDialog({
                        text: "真的确认要删除吗？"
                    })
                        .then(res => {
                            // 用户点击确认
                        })
                        .catch(() => {
                            // 用户点击取消
                        });
                })
                    .catch(() => {
                        // 用户点击取消
                    });
            }
        }
    }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    border: 0;
  }
</style>
