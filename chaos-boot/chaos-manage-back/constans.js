let baseUrl = "/api";
switch (process.env.NODE_ENV) {
    case 'development':
        baseUrl = "https://dev.firepongo.com/chaos"
        break
    case 'test':
        baseUrl = "https://test.firepongo.tech/chaos"
        break
    case 'production':
        //baseUrl = "https://firepongo.tech/chaos"
        baseUrl = "http://center.okyakid.com/chaos"
        break
}

export default baseUrl;
