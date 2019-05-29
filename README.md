https://stackoverflow.com/questions/56352304/how-to-implement-rest-login-by-using-spring-security

# Get token
`curl -u user:password -v http://localhost:8080/api/token`

# Get me
`curl -H "x-auth-token: $token" -v http://localhost:8080/me`