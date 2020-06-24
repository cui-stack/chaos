<template>
  <div>
    <el-container>
      <el-header>配置化表单</el-header>
      <el-main>
        <el-row :gutter="50">
          <!-- 左侧为表单展示相关 -->
          <el-col :span="12">
            <!-- 上方为表单展示 -->
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>表单展示</span>
              </div>
              <!-- 传入 formComponentList 配置数据 -->
              <!-- v-model 绑定表单值 -->
              <DynamicForm
                :formComponentList="formComponentList"
                v-model="formModel"
              >
                <!-- 中间的内容会替换掉 slot -->
                <!-- 这里传入了创建和取消的按钮 -->
                <el-form-item>
                  <el-button type="primary" @click="onSubmit"
                  >立即创建</el-button
                  >
                  <el-button>取消</el-button>
                </el-form-item>
              </DynamicForm>
            </el-card>
            <!-- 下方为表单值 -->
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>表单数据</span>
              </div>
              <JSONEditor v-model="formModel"></JSONEditor>
            </el-card>
          </el-col>
          <!-- 右侧为 jsoneditor 编辑器 -->
          <el-col :span="12">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>表单配置</span>
              </div>
              <!-- v-model 绑定 formComponentList -->
              <JSONEditor
                v-model="formComponentList"
                height="600px"
              ></JSONEditor>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    // 其他内容省略
    export default {
        // 其他选项省略
        data() {
            return {
                // 表单值
                formModel: {},
                // 配置表单相关的属性和数据
                formComponentList: [
                    // 这里参考前面内容，没有做任何变更，篇幅原因省略
                ]
            };
        }
    };
</script>
