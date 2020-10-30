import {
  create
} from "dva-core";

import createLoading from "dva-loading";

let app: any;
let store: any;
let dispatch: any;
let registered: any;

function createApp(opt) {
  opt.onAction = []
  if (opt.enableLog) {
    //opt.onAction.push(createLogger())
  }

  app = create(opt)
  app.use(createLoading())
  if (!registered) {
    opt.models.forEach(model => app.model(model));
  }

  registered = true;
  app.start()
  store = app._store;
  app.getStore = () => store;
  app.use({
    onError(err: any) {
      console.log(err);
    }
  })

  dispatch = store.dispatch;
  app.dispatch = dispatch;
  return app;

}

export default {

  createApp,
  getDispatch() {
    return app.dispatch
  }

};