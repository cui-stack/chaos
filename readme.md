# chaos 快速开发架构
架构组成：chaos+[order](https://gitee.com/ape-stack/order)    
chaos：提供基础组件定义  
order：提供开箱可用的架构模型
## 快速开始
### chaos-chloud
```
cd /chaos-cloud
mvn install
mvn deploy
```
#### 特性列表  
1. 开启async,duboo,nacos,redis,scheduling  
2. MU,DTO,DATA定义  
3. 全局异常机制  
4. 全局拦截机制  
5. 多入口登录（微信小程序登录，后台登录)  
6. 通用分页模型  
7. 通用结果模型  
8. 通用转换  DATA<->MuModel 

### chaos chaos-vue
```
cd /chaos-vue/chaos-vue
npm run lib
npm publish
cd /chaos-vue/chaos-data
npm publish
```
#### 特性列表  
1. 通用组件Head,CenterHead,Input,Paging,SideMenu  
2. 通用函数fetch,store,Data  
3. 通用对象PageData,RuleData  
