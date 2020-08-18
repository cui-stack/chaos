let baseUrl = "/api";
switch (process.env.NODE_ENV) {
    case 'production':
        baseUrl = "https://firepongo.tech/chaos"
        break
}

export default baseUrl;
