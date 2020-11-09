let baseUrl = "/api";
switch (process.env.VUE_APP_ENV) {
    case 'production':
        baseUrl = "https://center.firepongo.com/admin"
        break
}

export default baseUrl;

