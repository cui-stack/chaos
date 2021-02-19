const getters = {
  user: state => state.admin.user,
  menus: state => state.admin.menus,
  lastPost: state => state.app.lastPost,
  baseUrl: state => state.app.baseUrl,
  isCollapse: state => state.app.isCollapse,
  asideWidth: state => state.app.asideWidth,
  path: state => state.app.path,
}
export default getters



