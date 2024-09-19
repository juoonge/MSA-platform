## 물류 관리 및 배송 시스템을 위한 MSA 기반 플랫폼 개발

👩‍👩‍👧‍👦 팀 멤버
-----
- 권판준
- 박주원
- 장숭혁

  

📃 프로젝트 팀 노션
----
https://www.notion.so/teamsparta/18-i-c934008571bf416998708a795e6c9b13

프로젝트 기능 
---- 
1️⃣ MSA 아키텍처 구성

- Eureka 서버로 서비스 관리
- API Gateway를 통해 인증 및 권한 확인 로직 추가
- 모든 애플리케이션을 Docker 컨테이너로 실행 및 관리
- Gemini API 연동

2️⃣ 허브 관리

- 허브 엔티티의 CRUD + 검색 기능
- 허브 간 이동 정보 관리
- 배송 담당자 관리

3️⃣ 업체 및 상품 관리

- 업체 및 상품의 CRUD + 검색 기능
- 주문 및 배송 관리
- 주문 생성 시 배송과 배송 경로 자동 생성

4️⃣ 슬랙 메시지 관리

- 슬랙을 통한 알림 메시지 발송 및 기록 저장
- AI 연동 기능
- 스케줄러를 통해 배송 담당자에게 날씨 및 배송 정보 알림
- Gemini API를 활용한 메시지 생성 및 슬랙 전송

5️⃣ 사용자 관리

- 회원가입, 로그인, JWT 기반 인증 및 권한 관리
- 사용자 정보의 논리적 삭제 처리




적용 기술 
---- 
- Eureka
- API Gateway
- Docker
- Gemini API
- 스케줄러
- Zipkin
- Prometheus/Grafana
- AOP
- JWT

개발 환경
----
- Intellij IDEA
- JDK 17
- Spring Boot 3.3.4
- gradle
- Docker, Docker Compose
- Spring Cloud (Eureka, Feign, Gateway 등)

트러블 슈팅
-----
<img width="1183" alt="스크린샷 2024-09-20 오전 12 34 14" src="https://github.com/user-attachments/assets/4aa3f679-b552-4501-8fa5-9bfe9b717c16">

<img width="1136" alt="스크린샷 2024-09-20 오전 12 34 23" src="https://github.com/user-attachments/assets/9fa49067-ee1f-4a14-9518-55a86296c02a">


프로젝트 인프라
----
![제목 없는 다이어그램 drawio (1)](https://github.com/user-attachments/assets/69f6d963-27c9-4e35-8544-7d1ff9008577)

프로젝트 구조
----
![image (4)](https://github.com/user-attachments/assets/5953c6cf-e51f-445b-b03f-a3e2522b5947)



