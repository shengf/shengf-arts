
# Authentication vs. Authorization
# 认证 vs. 授权


1. The process of authorization is distinct from that of authentication. Whereas authentication is the process of verifying that "you are who you say you are", authorization is the process of verifying that "you are permitted to do what you are trying to do". This does not mean authorization presupposes authentication; an anonymous agent could be authorized to a limited action set.

授权过程与认证过程不同。认证是验证“你就是你所说的人”的过程，而授权是验证“你被允许做你想做的事情”的过程。这并不意味着授权以身份验证为前提；匿名代理可以被授权给有限的操作集。

- Authentication means "Are you who you say you are?"
- Authorization means "Should you be able to do what you are trying to do?".

In short, 
- Authentication = login + password (who you are)
- Authorization = permissions (what you are allowed to do)

2. Verification vs. Permissions

3. HTTP错误码不同：
- 没有 Authentication 时的错误码: 401 Unauthorized : Similar to 403 Forbidden, but specifically for use when authentication is required and has failed or has not yet been provided. The response must include a WWW-Authenticate header field containing a challenge applicable to the requested resource. See Basic access authentication and Digest access authentication. 401 semantically means "unauthorised", the user does not have valid authentication credentials for the target resource.
Note: Some sites incorrectly issue HTTP 401 when an IP address is banned from the website (usually the website domain) and that specific address is refused permission to access a website.

- 没有 Authorization 时的错误码: 403 Forbidden : The request was valid, but the server is refusing action. The user might not have the necessary permissions for a resource, or may need an account of some sort. This code is also typically used if the request provided authentication via the WWW-Authenticate header field, but the server did not accept that authentication.

4. 参考：
- [Authentication versus Authorization
](https://stackoverflow.com/questions/6556522/authentication-versus-authorization)
- [wikipedia Authentication vs. Authorization](https://en.wikipedia.org/wiki/Authentication#Authorization)
- [初见 http 401 —— 谈谈401和403的区别](https://blog.csdn.net/stpeace/article/details/78778025)