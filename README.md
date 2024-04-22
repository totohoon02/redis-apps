# redis-apps

redis-applications with SprignBoot

### Install && Run Server(MAC)

- brew install redis
- brew services start redis
- brew services stop redis
- brew services restart redis

### Spring Settings

```
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```

### OTP

- 일회용 비밀번호(이메일 등 인증에 사용)
- 인증 요청을 하면 redis에 인증정보(email), 인증코드(랜덤 생성) 저장
- 인증 확인을 할 때 다시 가져와서 비교
- 일정시간(3m) 이후 자동으로 만료되게 하고 인증이 성공한 후에도 삭제 처리
- [ ] 현재 코드를 그냥 보이게 해놨는데 나중에 메일서비스 만들면 수정
