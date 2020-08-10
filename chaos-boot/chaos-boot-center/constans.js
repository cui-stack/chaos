let baseUrl = "/api";
switch (process.env.NODE_ENV) {
    case 'dev':
        baseUrl = "https://dev.firepongo.tech"
        break
    case 'test':
        baseUrl = "https://test.firepongo.tech"
        break
    case 'production':
        baseUrl = "https://firepongo.tech"
        break
}

export default baseUrl;
