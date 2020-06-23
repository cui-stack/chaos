const Koa = require('koa');
const bodyParser = require('koa-bodyparser');
const controller = require('./controller');
const templating = require('./templating');
const rest = require('./rest');

const app = new Koa();
const isProduction = process.env.NODE_ENV === 'production';

app.use(async (ctx, next) => {
    console.log(`Process ${ctx.request.method} ${ctx.request.url}...`);
    var
        start = new Date().getTime(),
        execTime;
    await next();
    execTime = new Date().getTime() - start;
    ctx.response.set('X-Response-Time', `${execTime}ms`);
});

if (! isProduction) {
    let staticFiles = require('./static-files');
    app.use(staticFiles('/static/', __dirname + '/static'));
}

app.use(bodyParser());

app.use(templating('views', {
    noCache: !isProduction,
    watch: !isProduction
}));

app.use(rest.restify());

app.use(controller());

app.listen(3000);
console.log('app started at port 3000...');

// const model = require('./model');
// let
//     Pet = model.Pet,
//     User = model.User;
//
// (async () => {
//     var user = await User.create({
//         name: 'John',
//         gender: false,
//         email: 'john-' + Date.now() + '@garfield.pet',
//         passwd: 'hahaha'
//     });
//     console.log('created: ' + JSON.stringify(user));
//     var cat = await Pet.create({
//         ownerId: user.id,
//         name: 'Garfield',
//         gender: false,
//         birth: '2007-07-07',
//     });
//     console.log('created: ' + JSON.stringify(cat));
//     var dog = await Pet.create({
//         ownerId: user.id,
//         name: 'Odie',
//         gender: false,
//         birth: '2008-08-08',
//     });
//     console.log('created: ' + JSON.stringify(dog));
// })();
