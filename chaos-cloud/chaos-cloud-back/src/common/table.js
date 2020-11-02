// // 新增一个数据
// addTableItem(item = {}) {
//   // 添加到列表中，同时自增 id
//   this.tableData.push({ ...item, id: this.tableData.length + 1 });
// },
// // 删除一个数据
// deleteTableItem(id) {
//   // 查找到对应的索引，然后删除
//   const index = this.tableData.findIndex(x => x.id === id);
//   this.tableData.splice(index, 1);
// },
// // 移动一个数据
// moveTableItem(id, direction) {
//   const dataLength = this.tableData.length;
//   // 查找到对应的索引，然后取出，再插入
//   const index = this.tableData.findIndex(x => x.id === id);
//   switch (direction) {
//     // 上移
//     case "up":
//       if (index > 0) {
//         // 第一个不进行上移
//         const item = this.tableData.splice(index, 1)[0];
//         this.tableData.splice(index - 1, 0, item);
//       }
//       break;
//     // 下移
//     case "down":
//       if (index < dataLength - 1) {
//         // 最后一个不进行下移
//         const item = this.tableData.splice(index, 1)[0];
//         this.tableData.splice(index + 1, 0, item);
//       }
//       break;
//   }
// }
