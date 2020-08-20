let baseUrl = "";
switch (process.env.VUE_APP_ENV) {
    case 'development':
        baseUrl = "https://dev.firepongo.tech/chaos"
        break
    case 'production':
        //  baseUrl = "https://firepongo.tech/chaos"
        baseUrl = "https://center.iya101.com/chaos"
        break
}

export default baseUrl;
