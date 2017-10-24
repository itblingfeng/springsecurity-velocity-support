# springsecurity-velocity-support

在 velocity 模板文件中，实现 spring security权限验证 

相关方法：

      一. $security.getPrincipal()
	  detail：返回用户 Principal
	  param：无
	  return：Object
  
       2. $security.isNotAuthenticated()
	  detail：验证是否为未通过验证用户
	  param：无
	  return：Boolean

       3. $security.lacksPermission(String permission)
	  detail：验证用户是否不具备某权限
	  param：permission 权限名称
	  return：Boolean  
   
       4. $security.hasAnyPermission(String permissions, String delimeter)
 	 detail：验证用户是否具有以下任意一个权限
 	 param： permissions 以 delimeter 为分隔符的权限列表
         delimeter 权限列表分隔符
	 return：Boolean  

       5. $security.isGuest()
 	 detail：验证用户是否为 游客，即未认证（包含未记住）的用户。
	 param： 无
	 return：Boolean  

       6. $security.isUser()
	 detail：验证用户是否已认证 或 是否是已记住用户
	 param：无
	 return：Boolean  
   
      7.$security.hasPermission(String permission)
         detail：验证用户是否具有某权限
	 param： permission 权限名称
	 return：Boolean

=======
