# InfluxDB 2.x (호환성 버전)에서 Basic Authentication 사용하기 + InfluxQL 사용하기

- 1.x 버전은 Baic Authentication, DB, RetentionPolicy를 사용
- 2.x 버전 부터는 Token 기반 인증,Bucket을 사용
    - 1.x버전과의 호환성을 위해서 BasicAuthenticaion을 지원한다.
    - 호환성버전 까지만


## 기존 1.x 업그레이드가 아닌, 새로운 DB 일 때

### Basic Authenticaion 사용 설정

1. influx-cli Login

```shell
$ influx config create --config-name [Influx Config 이름] \
--host-url [InfluxDB HTTP URL] \
--org [Bucket Name] \
--token [Admin 토큰] \
--active
```

2. influx v1 user Create

- 호환을 위해서 username:password 기반의 Basic Authenticaion을 제공한다.
- v1 auth라는 v1기반의 사용자를 생성해야만 가능하다. (v2부터는 token이기 때문에)
- 나중에는 결국 token기반으로 마이그레이션 해야 할 것이다.

```shell
$ influx v1 auth create \
--username [유저 명] \
--password [유저 비밀번호] \
--org [Organization 명] \
--read-bucket [읽기 권한을 줄 Bucket의 ID (이름아님 주의)] \
--write-bucket [쓰기 권한을 줄 Bucket의 ID (이름아님 주의)]
```

3. DBRP
- 매핑안하면 Insert안된다.
```shell
$ influx v1 dbrp create \
--db [DB명] \
--rp [Retention Policy] \
--bucket-id [버킷 ID]
```
