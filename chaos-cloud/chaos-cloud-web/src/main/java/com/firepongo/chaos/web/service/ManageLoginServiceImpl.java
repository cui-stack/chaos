package com.firepongo.chaos.web.service;

public abstract class ManageLoginServiceImpl extends BaseLoginServiceImpl {

//    @Autowired
//    private ManageLoginKeyService loginKeyService;
//    @Autowired
//    private JWTHelper jwtHelper;
//
//    public abstract ManageLoginUser getUserInfo(ManageLoginDto loginDto);
//
//    /**
//     * 登录处理
//     *
//     * @param loginDto
//     * @return
//     */
//    @Override
//    public ManageLoginUser doLogin(LoginDto loginDto) {
//        ManageLoginUser user = getUserInfo((ManageLoginDto) loginDto);
//        if (user == null) {
//            return null;
//        }
//        user.setIp(loginDto.getIp());
//        user.setLoginTime(new Date());
//        String token = jwtHelper.createToken(user.getMu());
//        user.setToken(token);
//        redisService.set(loginKeyService.key(user.getMu()), user, 7 * 24 * 60 * 60);
//        afterLogin(user);
//        return user;
//    }
//
//    @Override
//    public String refreshToken(String userMu) {
//        ManageLoginUser user;
//        try {
//            user = (ManageLoginUser) redisService.get(loginKeyService.key(userMu));
//        } catch (Exception e) {
//            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN, "无效token，请重新登录");
//        }
//        if (user == null) {
//            return null;
//        }
//        String newToken = jwtHelper.createToken(user.getMu());
//        user.setToken(newToken);
//        redisService.refreshToken(loginKeyService.key(userMu), user, 7 * 24 * 60 * 60);
//        return newToken;
//    }
//
//    @Override
//    public boolean doLogoutAll() {
//        Set keys = redisHelper.keys(loginKeyService.REDIS_LOGIN_USER + "*");
//        String[] arrays = (String[]) keys.stream().toArray(String[]::new);
//        redisHelper.del(arrays);
//        return true;
//    }
//
//    @Override
//    public String key(String userMu) {
//        return loginKeyService.key(userMu);
//    }
//
//    @Override
//    public String getRedisLoginUser() {
//        return loginKeyService.REDIS_LOGIN_USER;
//    }
//
//    @Override
//    public LoginUser getLoginUser(String msg) {
//        if (StringUtils.isEmpty(msg)) {
//            return null;
//        }
//        try {
//            return (LoginUser) redisService.get(key(msg));
//        } catch (Exception e) {
//            throw new AuthenticationException(ResultEnum.LOGIN_AGAIN, "无效token，请重新登录");
//        }
//    }

}
