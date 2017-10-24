package org.springframework.security.web.support;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/10/24
 * @description
 **/
@DefaultKey("security")
@ValidScope(Scope.APPLICATION)
public class Permission {

    private final String Permission_NAMES_DELIMETER = ",";
    /**
     * 返回用户 Principal。
     * @return 用户 Principal
     */
    public Object getPrincipal(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }

    /**
     * 验证是否为未通过验证用户
     * @return 用户是否通过认证
     */
    public boolean isNotAuthenticated() {
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        return authenticated;
    }

    /**
     * 验证用户是否不具备某权限
     * @param permission 权限名称
     * @return 用户是否不具备某权限
     */
    public boolean lacksPermission(String permission) {
        return hasPermission(permission) != true;
    }

    /**
     * 验证用户是否具有以下任意一个权限
     * @param permissions 以 delimeter 为分隔符的权限列表
     * @param delimeter 权限列表分隔符
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermission(String permissions, String delimeter) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            if (delimeter == null || delimeter.length() == 0) {
                delimeter = Permission_NAMES_DELIMETER;
            }

            for (String permission : permissions.split(delimeter)) {
                if (hasPermission(permission.trim()) == true) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 验证用户是否为 游客，即未认证（包含未记住）的用户。
     * @return 用户是否为游客
     */
    public boolean isGuest() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context==null|| context.getAuthentication().getPrincipal()==null;
    }

    /**
     * 验证用户是否已认证 或 是否是已记住用户
     * @return
     */
    public boolean isUser(){
        SecurityContext context = SecurityContextHolder.getContext();

        return context!=null&&context.getAuthentication().getPrincipal()!=null;
    }

    /**
     * 验证用户是否具有某权限
     * @param permission 权限名称
     * @return 用户是否具有某权限
     */
    public boolean hasPermission(String permission){

        List<GrantedAuthority> authorities = ( List<GrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if(grantedAuthority.getAuthority().equals(permission)) {
                return true;
            }
        }
        return false;
    }











}
