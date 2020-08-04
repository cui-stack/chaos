let baseUrl = "/api";
switch (process.env.NODE_ENV) {
    case 'test':
        baseUrl = "http://dev.firepongo.tech/manage-api"
        break
    case 'production':
        baseUrl = "http://center.okyakid.com/manage-api"
        break
}

export default baseUrl;
