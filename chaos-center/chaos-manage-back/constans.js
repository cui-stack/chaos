let baseUrl = "/api";
switch (process.env.VUE_APP_ENV) {
    case 'production':
        baseUrl = "https://center.okyakid.com/chaos"
        break
}

export default baseUrl;
