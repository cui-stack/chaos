![](https://img.shields.io/badge/language-java-483D8B.svg)
![](https://img.shields.io/badge/language-javascript-483D8B.svg)
![](https://img.shields.io/badge/language-TypeScript-483D8B.svg)
![](https://img.shields.io/badge/license-MIT-483D8B.svg)

# chaos 快速开发架构
用于构建快速上线应用
架构组成：chaos+[order](https://gitee.com/ape-stack/order)    
chaos：提供基础组件定义  
order：提供开箱可用的架构模型
## 快速开始
### chaos-cloud
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

# 架构矩阵
|架构|技术栈|介绍|进度|
|---|---|---|---|
|chaos-cloud<br>3.0.0-SNAPSHOT| nacos<br>sentinel<br>openfeign<br>dubbo<br>redis<br>MyBatis-plus<br/>mysql<br/>hutool<br/>swagger2<br>jwt<br/>spring-security<br/>actuator |[快速开始][chaos-cloud-start-url]|✅|
|chaos-vue<br>1.0.0|vue-2.6.11<br>vue-router-3.3.4<br>element-ui-2.13.2<br>vuex-3.5.1<br>axios-0.19.2<br>less-3.12.2|[快速开始][chaos-vue-start-url]|✅|
|chaos-mh<br>1.0.0|okhttp_3.10.0<br>retrofit_2.4.0<br>rxjava_2.2.3<br>glide_4.8.0<br>material-dialogs_0.9.6.0<br>androidx<br>AAC|[快速开始][chaos-mh-start-url]|✅|
|chaos-weapp|js<br>wx-2.10.1<br>weui<br>less|[快速开始][chaos-weapp-start-url]|✅|
|chaos-taro<br>1.0.0|ts<br>react-16.10.0<br>taro-3.0.8<br>taro-ui-3.0.0-alpha.3<br>sass<br>dva-2.0.2|[快速开始][chaos-taro-start-url]|✅|
|chaos-uniapp<br>1.0.0|vue<br>vuex<br>uni-ui|[快速开始][chaos-uniapp-start-url]|✅|
|chaos-center|docker<br>compose|[快速开始][chaos-center-start-url]|✅|

[chaos-cloud-start-url]:https://gitee.com/ape-stack/chaos/blob/master/chaos-cloud/readme.md
[chaos-vue-start-url]:https://gitee.com/ape-stack/chaos/tree/master/chaos-vue/readme.md
[chaos-weapp-start-url]:https://gitee.com/ape-stack/chaos/tree/master/chaos-weapp/readme.md
[chaos-taro-start-url]:https://gitee.com/ape-stack/chaos/tree/master/chaos-taro/readme.md
[chaos-uniapp-start-url]:https://gitee.com/ape-stack/chaos/tree/master/chaos-uniapp/readme.md
[chaos-mh-start-url]:https://gitee.com/ape-stack/chaos/tree/master/chaos-mh/readme.md
[chaos-center-start-url]:https://gitee.com/ape-stack/chaos/blob/master/chaos-center/readme.md

# 联系我们
# *Poweredby* 阿暴
