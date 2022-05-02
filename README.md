<div align="center">
  
![soft](https://capsule-render.vercel.app/api?type=soft&color=000000&text=%20빡메모&fontSize=40&animation=twinkling&fontColor=ffffff)

  <h3 align="center">비밀메모 기능이 있는 간단한 메모앱</h3>

  <p align="center">
    🎉제 1회 빡코딩콘 이벤트 출품작
    <br />
    <a href="https://spangle-wedelia-2dc.notion.site/1-90fa4be2b51a4dad8a37df72bfc466d4"><strong>제 1회 빡코딩콘 참가자 모음 »</strong></a>
    <br />
    <br />
  </p>

  <img src="https://img.shields.io/badge/Android-3DDC84?style=plastic-square&logo=Android&logoColor=white"/></a>
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=plastic-square&logo=Kotlin&logoColor=white"/></a>
  
</div>
<br />


## Table of Contents

<ol>
  <li>
    <a href="#about-the-project">About The Project</a>
    <ul>
      <li><a href="#built-with">Built With</a></li>
    </ul>
  </li>
  <li><a href="#usage">Usage</a>
    <ul>
      <li><a href="#메모-목록-화면">메모 목록 화면</a></li>
      <li><a href="#메모-편집-화면">메모 편집 화면</a></li>
      <li><a href="#메모-검색-기능">메모 검색 기능</a></li>
      <li><a href="#비밀-메모-설정-기능">비밀 메모 설정 기능</a></li>
    </ul>
  </li>
</ol>
<br />

## About The Project
개발 유버 ['개발하는 정대리'](https://www.youtube.com/c/%EA%B0%9C%EB%B0%9C%ED%95%98%EB%8A%94%EC%A0%95%EB%8C%80%EB%A6%AC) 채널에서 열린 제 1회 빡코딩콘 이벤트에 참가하여 개발하게 되었습니다

</br>

<details>
  <summary>이벤트 과제 내용</summary>
  
    🎉 제 1회 빡코딩콘 이벤트 시작합니다!!
  
    ## 과제 ## 

    주제: 메모서비스 만들기 (앱,웹 상관없음)

    👩🏼‍💻화면구성:
    - 메모 목록화면
    - 메모 상세화면
    - 메모 편집, 작성 화면 

    ⭐️기능:
    - 사용자는 작성한 메모 목록을 볼 수 있어야함 
    - 메모 목록에 노출되는 메모는 작성된 메모문장 한줄만 노출된다.
    - 메모 작성 페이지에서 메모 작성이 가능하다.
    - 메모를 작성할때 작성된 메모의 글자수가 노출된다.
    - 사용자는 메모를 검색할 수 있어야 한다.
    - 사용자는 메모를 편집할 수 있어야 한다.
    - 사용자는 메모를 삭제할 수 있어야 한다.
    - 작성된 메모는 비밀메모로 변경이 가능하다.

    🔐비밀메모: 
    - 메모목록에 메모 문장이 노출되지 않는다.
    - 메모 목록 화면에는 “비밀메모 입니다” 혹은 잠금 표시로 노출된다.
    - 메모 목록에서 상세보기클릭시 비밀메모인 경우 암호를 입력해야 메모 상세 화면으로 이동이 가능하다.
    - 일반메모는 메모 상세화면에서 비밀메모로 변환이 가능하다.
    - 일반메모에서 비밀메모로 설정시 비밀번호 입력창이 뜨고 비밀번호를 입력하면 비밀메모로 바뀐다.

    - 위 요건들 포함 추가 기능 및 화면 자유롭게 추가가능


    💬기획의도:
    빡빡이 여러분들과 저의 동기부여와 성장을 위해 그리고 최근 채용시 과제전형이 많아 진 것을 보고 이와 같이 빡코딩콘을 기획하게 되었습니다.

    저도 이벤트에 함께할 예정입니다! 

    4일동안 같이 한번 열심히 달려봅시다! 

    많은 참여 부탁드립니다! 빡코디이이이이잉!🔥👨‍💻
</details>

</br>

<p align="right">(<a href="#top">back to top</a>)</p>
<br />

### Built With

* [Android Studio](https://developer.android.com/)
* [Kotlin](https://kotlinlang.org/)
* [Room](https://developer.android.com/training/data-storage/room?)
* [ViewModel](https://developer.android.com/reference/android/arch/lifecycle/ViewModel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [DataBinding](https://developer.android.com/topic/libraries/data-binding)
* [Couroutine](https://developer.android.com/kotlin/coroutines?)

<p align="right">(<a href="#top">back to top</a>)</p>

## Usage

### 메모 목록 화면
메모 목록이 보이는 화면입니다

![목록1](https://user-images.githubusercontent.com/63830874/166262649-ee856c29-435e-42e0-ac03-b4207a95f204.gif)

### 메모 편집 화면
메모를 추가, 수정, 삭제를 할 수 있습니다

![추가](https://user-images.githubusercontent.com/63830874/166263025-8cbc0594-331d-4d03-bc7a-1a02a8b52993.gif)
![수정](https://user-images.githubusercontent.com/63830874/166263029-7047376c-30f8-4ece-931a-6d1a5e1b6941.gif)
![삭제](https://user-images.githubusercontent.com/63830874/166263031-3e35fdc8-4892-4bc8-919b-f430775c536f.gif)

### 메모 검색 기능
메모를 검색할 수 있습니다

RecyclerView Adapter에 Filterable 인터페이스를 구현하였습니다

![검색](https://user-images.githubusercontent.com/63830874/166263033-b7c4228c-acc6-4056-aae4-3fa9a520897f.gif)

### 비밀 메모 설정 기능

비밀번호를 설정하여 메모 내용을 숨길 수 있습니다

![비밀메모](https://user-images.githubusercontent.com/63830874/166263035-52294cac-3efa-4c76-aaa2-8a0af570851c.gif)

<p align="right">(<a href="#top">back to top</a>)</p>
