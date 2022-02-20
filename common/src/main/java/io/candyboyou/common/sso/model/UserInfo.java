package io.candyboyou.common.sso.model;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    public final static String mock_USER_NAME ="mock";

    private String token;
    // 来源：ARK;HERMES
    private String origin;
    // 操作人账号
    private String accountNo;
    // 操作人名称
    private String name;
    // company name
    private String companyName;
    //用户id
    private String userId;
    //邮箱
    private String email;
    // Permissions
    private List<String> permissions;

    //如果是ark登录，使用sellerId
    private String sellerId;
    //如果是auqa登录，使用providerCode
    private String providerCode;

    private String others;

    private List<String> roles;

    public UserInfo() {
    }

    public UserInfo(String origin, String accountNo, String name, String userId, String email,
                    String companyName, List<String> permissions, String others) {
        this.origin = origin;
        this.accountNo = accountNo;
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.companyName = companyName;
        this.permissions = permissions;
        this.others = others;
    }

    public static UserInfo mock(String userId) {
        UserInfo mock = new UserInfo("GAIA", "mock", "mock", userId, "balaraking@balaraking.com", "mock", new ArrayList<>(0), null);
        mock.setToken("mock_token_" + userId);
        return mock;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
