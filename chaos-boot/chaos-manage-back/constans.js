let baseUrl = "/api";
switch (process.env.NODE_ENV) {
    case 'development':
        baseUrl = "https://dev.firepongo.com/manage-api"
        break
    case 'test':
        baseUrl = "https://test.firepongo.tech/manage-api"
        break
    case 'production':
        baseUrl = "https://firepongo.tech/manage-api"
        break
}

export default baseUrl;
