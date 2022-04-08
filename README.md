# aws-kinesis-lambda-demo

### blueprint
![architecture](./img/architecture.png)


### Lambda
- 이벤트 중심 서버리스 컴퓨팅 서비스
- 이벤트가 트리거 할 때만 실행되고 비용 지불
- 인프라 관리없이 zip 파일 또는 컨테이너 이미지로 업로드
- 다양한 AWS 서비스들에서 Lambda 트리거 가능


### Lambda 개발 환경 구성
#### 1. AWS SAM(Serverless Application Model) CLI 설치
```
brew tap aws/tap
brew install aws-sam-cli
```


#### 2. AWS Toolkit for IntelliJ 설치
IntelliJ 마켓플레이스를 통해 AWS Toolkit for IntelliJ 플러그인을 설치합니다.


#### 3. 프로젝트 생성 

