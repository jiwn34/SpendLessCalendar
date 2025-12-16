📅 SpendLess Calendar

캘린더 기반 스마트 가계부 앱 (Android / Kotlin)

날짜 선택 → 지출 입력 → 자동 합산 → AI 분석까지 연결된
캘린더 중심 개인 가계부 애플리케이션


📌 프로젝트 개요

SpendLess Calendar는
사용자가 날짜를 기준으로 지출을 기록하고,
월 단위로 지출 흐름을 한눈에 파악할 수 있도록 설계된
Android 캘린더형 가계부 앱입니다.

기존 가계부의 “리스트 중심 UI”에서 벗어나
📆 캘린더 + 자동 합계 + AI 분석 구조를 핵심으로 합니다.



📌 개발 목표

- 날짜 기반 지출 관리 (직관적인 사용성)
- 입력 즉시 캘린더에 금액 합계 반영
- 지출 데이터의 수정 / 삭제 / 복구 지원
- AI를 활용한 지출 요약 및 카테고리 추천
- Room + Flow 기반의 안정적인 데이터 처리


🛠 사용 기술 스택

| 구분         | 기술                              |
| ------------ | ----------------------------------- |
| Language     | Kotlin                              |
| Architecture | MVVM                                |
| UI           | Fragment, RecyclerView, ViewBinding |
| DB           | Room (SQLite)                       |
| Async        | Coroutine, Flow                     |
| Navigation   | Jetpack Navigation                  |
| AI           | Custom AI Advisor Module            |
| Build        | Android Studio                      |



📱 주요 기능

1️⃣ 캘린더 화면
* 월 단위 캘린더 표시
* 날짜별 지출 금액 합계 자동 표시
* 이전/다음 달 이동 가능
👉 지출이 있는 날짜는 한눈에 확인 가능

2️⃣ 지출 입력 기능
* 날짜 선택 후 지출 입력
* 입력 항목:
  - 금액
  - 카테고리
  - 메모
* 저장 즉시 DB 반영

3️⃣ 일별 지출 리스트
* 날짜 클릭 시 해당 날짜의 지출 목록 표시
* 항목별:
  - 금액
  - 카테고리
  - 메모

 4️⃣ 지출 수정 / 삭제
* 기존 지출 데이터 불러오기
* 수정 후 저장 시:
  - DB 업데이트
  - 캘린더 합계 자동 반영
* 삭제 기능 지원

5️⃣ AI 지출 분석 기능
* 일별 지출 내역을 기반으로:
  - AI 지출 요약 생성
  - 지출 카테고리 추천
* 입력 화면에서 실시간 추천 가능
* 단순 기록을 넘어
**“지출 패턴 인식 + 소비 인식 개선”**을 목표로 함

6️⃣ 월별 리포트 화면 
* 선택한 월 기준:
  - 총 지출 금액 표시
  - 카테고리별 지출 합계 분석
* 데이터가 없는 경우 Empty UI 표시



🧩 앱 구조 (아키텍처)
UI (Fragment)
 └── ViewModel
      └── Repository
           └── Room DAO
                └── SQLite


* View ↔ ViewModel 완전 분리

* Flow 기반 실시간 데이터 반영

* DB 변경 시 UI 자동 갱신


📂 주요 패키지 구조
com.spendless.calendar
 ┣ ui
 ┃ ┣ calendar
 ┃ ┣ daily
 ┃ ┣ input
 ┃ ┗ report
 ┣ data
 ┃ ┣ model
 ┃ ┣ local (Room)
 ┃ ┗ repository
 ┣ ai
 ┗ MainActivity


✅ 현재 구현 완료 상태

✔ 캘린더 기반 지출 관리
✔ 날짜별 지출 합계 표시
✔ 지출 입력 / 수정 / 삭제
✔ AI 지출 요약 & 카테고리 추천
✔ 월별 리포트 분석
✔ 안정적인 DB 연동 (Room + Flow)


🚀 향후 확장 계획

* 지출 통계 차트 시각화
* 월별/주별 소비 패턴 분석
* AI 기반 소비 습관 피드백
* 백업/복원 기능
* 클라우드 연동


* 📌 한 줄 요약
SpendLess Calendar는
“날짜를 클릭하는 순간 소비가 보이는
스마트 캘린더 가계부 앱”입니다.
