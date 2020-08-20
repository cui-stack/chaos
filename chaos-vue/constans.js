let baseUrl = "";
switch (process.env.VUE_APP_ENV) {
    case 'development':
        baseUrl = "https://dev.firepongo.tech"
        break
    case 'testing':
        baseUrl = "https://test.firepongo.tech"
        break
    case 'production':
        baseUrl = "https://firepongo.tech"
        break
}

export default baseUrl;
